package me.tutien.cultivation.thienkiep;

import me.tutien.cultivation.data.PlayerCultivationData;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

public class ThienKiepBoss {

    public static void spawnMobWave(Player p, int wave) {
        World w = p.getWorld();
        for (int i = 0; i < wave * 3; i++) {
            Zombie z = w.spawn(p.getLocation().add(Math.random()*3,0,Math.random()*3), Zombie.class);
            z.setCustomName("§cThiên Kiếp Dư Linh");
        }
    }

    public static void spawnBoss(Player p, PlayerCultivationData data) {
        World w = p.getWorld();
        Location loc = p.getLocation().add(0,0,3);

        WitherSkeleton boss = w.spawn(loc, WitherSkeleton.class);
        boss.setCustomName("§4§lThiên Kiếp Chi Chủ");
        boss.setCustomNameVisible(true);

        double hp = 100 + data.getStage().ordinal() * 50;

        boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
        boss.setHealth(hp);
        boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(8 + data.getStage().ordinal() * 2);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (boss.isDead() || !p.isOnline()) {
                    cancel();
                    return;
                }

                // skill sét
                boss.getWorld().strikeLightning(p.getLocation());

                p.damage(3, boss);
            }
        }.runTaskTimer(
                Bukkit.getPluginManager().getPlugin("TuTienCultivation"),
                40, 60
        );
    }
}
