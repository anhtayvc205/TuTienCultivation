package me.tutien.cultivation.thienkiep;

import me.tutien.cultivation.TuTienCultivation;
import me.tutien.cultivation.data.PlayerCultivationData;
import me.tutien.cultivation.realm.RealmStage;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ThienKiepBoss {

    private static final Random random = new Random();

    // ================== START THIEN KIEP ==================
    public static void start(Player p, PlayerCultivationData data) {
        p.sendMessage("§c⚡ Thiên kiếp bắt đầu giáng xuống! ⚡");

        new BukkitRunnable() {
            int wave = 1;

            @Override
            public void run() {
                if (!p.isOnline()) {
                    cancel();
                    return;
                }

                if (wave <= 3) {
                    spawnMobWave(p, wave);
                    p.sendMessage("§e☁ Thiên kiếp đợt " + wave + "!");
                    wave++;
                } else {
                    spawnBoss(p, data);
                    cancel();
                }
            }
        }.runTaskTimer(TuTienCultivation.getInstance(), 20, 100);
    }

    // ================== MOB WAVE ==================
    private static void spawnMobWave(Player p, int wave) {
        World w = p.getWorld();
        Location base = p.getLocation();

        for (int i = 0; i < wave * 4; i++) {
            Zombie z = w.spawn(base.clone().add(rand(), 0, rand()), Zombie.class);
            z.setCustomName("§7Thiên Kiếp Dư Linh");
            z.setCustomNameVisible(true);
            z.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
            z.setTarget(p);
        }
    }

    // ================== BOSS ==================
    private static void spawnBoss(Player p, PlayerCultivationData data) {
        World w = p.getWorld();
        Location loc = p.getLocation().add(0, 0, 3);

        WitherSkeleton boss = w.spawn(loc, WitherSkeleton.class);
        boss.setCustomName("§4§lThiên Kiếp Chi Chủ");
        boss.setCustomNameVisible(true);
        boss.setTarget(p);

        RealmStage stage = data.getStage();
        int level = stage.ordinal() + 1;

        double maxHp = 200 + level * 80;
        double dmg = 6 + level * 2;

        boss.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHp);
        boss.setHealth(maxHp);
        boss.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(dmg);
        boss.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.35);

        p.sendMessage("§4☠ Boss thiên kiếp xuất hiện!");

        new BukkitRunnable() {
            int tick = 0;

            @Override
            public void run() {
                if (!p.isOnline() || boss.isDead()) {
                    cancel();
                    if (boss.isDead()) {
                        onBossDefeated(p, data);
                    }
                    return;
                }

                tick++;

                // ⚡ skill sét
                if (tick % 3 == 0) {
                    boss.getWorld().strikeLightningEffect(p.getLocation());
                    p.damage(2 + level, boss);
                }

                // ☄ skill gọi quái
                if (tick % 6 == 0) {
                    spawnMobWave(p, 2);
                }

                // 💥 skill nổ
                if (tick % 10 == 0) {
                    boss.getWorld().createExplosion(p.getLocation(), 2F, false, false);
                }

            }
        }.runTaskTimer(TuTienCultivation.getInstance(), 40, 60);
    }

    // ================== KHI GIẾT BOSS ==================
    private static void onBossDefeated(Player p, PlayerCultivationData data) {
        p.sendMessage("§a✔ Bạn đã vượt qua thiên kiếp!");

        RealmStage next = data.getStage().next();
        if (next != null) {
            data.setStage(next);
            data.setLinhKhi(0);
            p.sendMessage("§6✦ Đột phá thành công → " + next.display);
            p.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, p.getLocation(), 100);
            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
        } else {
            p.sendMessage("§d✦ Bạn đã đạt cảnh giới tối cao!");
        }
    }

    private static double rand() {
        return random.nextDouble() * 6 - 3;
    }
}
