package me.tutien.cultivation;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class ThienKiepBoss {

    private static final Random r = new Random();

    /* ======================== WAVE QUÁI ======================== */
    public static void spawnMobWave(Player p, int wave) {
        World w = p.getWorld();
        Location base = p.getLocation();

        for (int i = 0; i < wave * 4; i++) {
            Zombie z = w.spawn(base.clone().add(rand(), 0, rand()), Zombie.class);
            z.setCustomName("§7Thiên Kiếp Dư Linh");
            z.setCustomNameVisible(true);
            z.setTarget(p);
        }
    }

    /* ======================== BOSS ======================== */
    public static void spawnBoss(Player p, PlayerCultivationData data) {
        World w = p.getWorld();
        Location loc = p.getLocation().clone().add(0, 0, 3);

        WitherSkeleton boss = w.spawn(loc, WitherSkeleton.class);
        boss.setCustomName("§4§lThiên Kiếp Chi Chủ");
        boss.setCustomNameVisible(true);
        boss.setTarget(p);

        int lvl = data.getStage().ordinal() + 1;
        double maxHp = 300 + lvl * 120;

        boss.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHp);
        boss.setHealth(maxHp);
        boss.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(8 + lvl * 2);
        boss.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.35);

        p.sendMessage("§8[§cThiên Đạo§8] §4Thiên kiếp cuối cùng… bắt đầu!");

        new BukkitRunnable() {
            int tick = 0;
            boolean phase2 = false;

            @Override
            public void run() {
                if (!p.isOnline() || boss.isDead()) {
                    cancel();
                    if (boss.isDead()) finish(p, data);
                    return;
                }

                tick++;

                /* ================= PHASE 2 ================= */
                if (!phase2 && boss.getHealth() < maxHp * 0.5) {
                    phase2 = true;
                    p.sendMessage("§8[§cThiên Đạo§8] §cThiên uy tăng mạnh!");
                    boss.getWorld().playSound(boss.getLocation(),
                            Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 0.5f);
                }

                /* ================= SKILL 1: SÉT ================= */
                if (tick % 3 == 0) {
                    Location l = p.getLocation();
                    l.getWorld().strikeLightningEffect(l);
                    p.damage(3 + lvl, boss);
                }

                /* ================= SKILL 2: TRIỆU HỒI ================= */
                if (tick % 6 == 0) {
                    spawnMobWave(p, 2);
                }

                /* ================= SKILL 3: NỔ LINH KHÍ ================= */
                if (tick % 9 == 0) {
                    boss.getWorld().createExplosion(p.getLocation(), 2.5F, false, false);
                }

                /* ================= SKILL 4: PHÁP CẦU (phase 2) ================= */
                if (phase2 && tick % 4 == 0) {
                    Fireball fb = boss.launchProjectile(Fireball.class);
                    fb.setYield(1.5f);
                }

                /* ================= SKILL 5: HÚT LINH KHÍ (phase 2) ================= */
                if (phase2 && tick % 7 == 0) {
                    long drain = Math.min(1000, data.getLinhKhi());
                    data.setLinhKhi(data.getLinhKhi() - drain);
                    p.sendMessage("§cLinh khí bị thiên đạo hút mất " + drain + "!");
                }

                /* ================= SKILL 6: ĐẨY LÙI ================= */
                if (tick % 10 == 0) {
                    Vector v = p.getLocation().toVector()
                            .subtract(boss.getLocation().toVector())
                            .normalize().multiply(1.5).setY(0.5);
                    p.setVelocity(v);
                }
            }
        }.runTaskTimer(TuTienCultivation.get(), 40, 40);
    }

    /* ======================== KẾT THÚC ======================== */
    private static void finish(Player p, PlayerCultivationData data) {
        RealmStage next = data.getStage().next();

        if (next != null) {
            data.setStage(next);
            data.setLinhKhi(0);;

            p.sendMessage("§8[§cThiên Đạo§8] §7Ngươi… đã vượt qua.");
            p.sendMessage("§6✦ Đột phá thành công → §e" + next.display);

            p.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, p.getLocation(), 150);
            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
        } else {
            p.sendMessage("§8[§cThiên Đạo§8] §dNgươi đã đứng ngoài thiên đạo…");
        }
    }

    private static double rand() {
        return r.nextDouble() * 6 - 3;
    }
}
