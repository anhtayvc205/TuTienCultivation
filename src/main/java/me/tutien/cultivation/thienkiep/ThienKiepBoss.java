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

    private static final Random r = new Random();

    // ================= START =================
    public static void start(Player p, PlayerCultivationData data) {
        p.sendMessage("§c⚡ Thiên kiếp giáng xuống!");

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
                    p.sendMessage("§eThiên kiếp đợt " + wave);
                    wave++;
                } else {
                    spawnBoss(p, data);
                    cancel();
                }
            }
        }.runTaskTimer(TuTienCultivation.getInstance(), 20, 100);
    }

    // ================= WAVE =================
    public static void spawnMobWave(Player p, int wave) {
        World w = p.getWorld();
        Location base = p.getLocation();

        for (int i = 0; i < wave * 4; i++) {
            Zombie z = w.spawn(base.clone().add(rand(), 0, rand()), Zombie.class);
            z.setCustomName("§7Thiên Kiếp Dư Linh");
            z.setTarget(p);
        }
    }

    // ================= BOSS =================
    public static void spawnBoss(Player p, PlayerCultivationData data) {
        World w = p.getWorld();
        Location loc = p.getLocation().add(0, 0, 3);

        WitherSkeleton boss = w.spawn(loc, WitherSkeleton.class);
        boss.setCustomName("§4§lThiên Kiếp Chi Chủ");
        boss.setCustomNameVisible(true);
        boss.setTarget(p);

        RealmStage stage = data.getStage();
        int lvl = stage.ordinal() + 1;

        double hp = 200 + lvl * 80;
        double dmg = 6 + lvl * 2;

        boss.getAttribute(Attribute.MAX_HEALTH).setBaseValue(hp);
        boss.setHealth(hp);
        boss.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(dmg);

        new BukkitRunnable() {
            int t = 0;

            @Override
            public void run() {
                if (!p.isOnline() || boss.isDead()) {
                    cancel();
                    if (boss.isDead()) finish(p, data);
                    return;
                }

                t++;

                if (t % 3 == 0) {
                    boss.getWorld().strikeLightningEffect(p.getLocation());
                    p.damage(2 + lvl, boss);
                }

                if (t % 6 == 0) {
                    spawnMobWave(p, 2);
                }

                if (t % 10 == 0) {
                    boss.getWorld().createExplosion(p.getLocation(), 2F, false, false);
                }
            }
        }.runTaskTimer(TuTienCultivation.getInstance(), 40, 60);
    }

    // ================= END =================
    private static void finish(Player p, PlayerCultivationData data) {
        RealmStage next = data.getStage().next();
        if (next != null) {
            data.setStage(next);
            data.setLinhKhi(0);
            p.sendMessage("§aĐột phá thành công → §e" + next.display);
            p.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, p.getLocation(), 80);
        }
    }

    private static double rand() {
        return r.nextDouble() * 6 - 3;
    }
}
