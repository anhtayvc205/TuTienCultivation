package me.tutien.cultivation.thienkiep;

import me.tutien.cultivation.data.PlayerCultivationData;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

public class ThienKiep {

    public static void start(Player p, PlayerCultivationData data) {
        World w = p.getWorld();
        Location loc = p.getLocation();

        p.sendMessage("§c§l⚡ Thiên Kiếp giáng xuống! ⚡");
        p.getWorld().strikeLightningEffect(loc);

        new BukkitRunnable() {
            int wave = 0;

            @Override
            public void run() {
                wave++;

                if (wave <= 3) {
                    spawnMobWave(p, loc, wave);
                    p.sendMessage("§eThiên kiếp đợt " + wave + " ập tới!");
                    return;
                }

                spawnBoss(p, loc);
                cancel();
            }
        }.runTaskTimer(
                Bukkit.getPluginManager().getPlugin("TuTienCultivation"),
                40, 100
        );
    }

    private static void spawnMobWave(Player p, Location loc, int wave) {
        World w = loc.getWorld();
        int amount = 2 + wave * 2;

        for (int i = 0; i < amount; i++) {
            Zombie z = (Zombie) w.spawnEntity(
                    loc.clone().add(Math.random()*4-2, 0, Math.random()*4-2),
                    EntityType.ZOMBIE
            );
            z.setCustomName("§cThiên Kiếp Linh Hồn");
            z.setCustomNameVisible(true);
            z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(20 + wave * 10);
            z.setHealth(z.getAttribute(Attribute.MAX_HEALTH).getBaseValue());
        }
    }

    private static void spawnBoss(Player p, Location loc) {
        World w = loc.getWorld();

        WitherSkeleton boss = (WitherSkeleton) w.spawnEntity(loc, EntityType.WITHER_SKELETON);
        boss.setCustomName("§4§lThiên Kiếp Chân Linh");
        boss.setCustomNameVisible(true);

        boss.getAttribute(Attribute.MAX_HEALTH).setBaseValue(200);
        boss.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(12);
        boss.setHealth(200);

        p.sendMessage("§4§l⚠ Boss Thiên Kiếp xuất hiện! ⚠");

        // Skill đơn giản
        new BukkitRunnable() {
            int tick = 0;
            @Override
            public void run() {
                if (boss.isDead()) {
                    p.sendMessage("§a✔ Bạn đã vượt qua Thiên Kiếp!");
                    cancel();
                    return;
                }
                tick++;

                if (tick % 4 == 0) {
                    boss.getWorld().strikeLightning(boss.getLocation());
                }
            }
        }.runTaskTimer(
                Bukkit.getPluginManager().getPlugin("TuTienCultivation"),
                40, 40
        );
    }
}
