package me.tutien.cultivation.thienkiep;

import me.tutien.cultivation.TuTienCultivation;
import me.tutien.cultivation.data.PlayerCultivationData;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

public class ThienKiepBoss {

    public static void spawnMobWave(Player p, int wave) {
        World w = p.getWorld();
        Location base = p.getLocation();

        for (int i = 0; i < wave * 3; i++) {
            Location l = base.clone().add(
                    Math.random() * 4 - 2,
                    0,
                    Math.random() * 4 - 2
            );

            Zombie z = w.spawn(l, Zombie.class);
            z.setCustomName("§cThiên Kiếp Dư Linh");
            z.setCustomNameVisible(true);
        }
    }

    public static void spawnBoss(Player p, PlayerCultivationData data) {
        World w = p.getWorld();
        Location loc = p.getLocation().clone().add(0, 0, 3);

        WitherSkeleton boss = w.spawn(loc, WitherSkeleton.class);
        boss.setCustomName("§4§lThiên Kiếp Chi Chủ");
        boss.setCustomNameVisible(true);

        int stage = data.getStage().ordinal() + 1;

        double hp = 100 + stage * 80;
        double dmg = 8 + stage * 3;

        AttributeInstance maxHp = boss.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (maxHp != null) maxHp.setBaseValue(hp);

        boss.setHealth(hp);

        AttributeInstance atk = boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        if (atk != null) atk.setBaseValue(dmg);

        // ⚡ boss skill
        new BukkitRunnable() {
            @Override
            public void run() {
                if (boss.isDead() || !p.isOnline()) {
                    cancel();
                    return;
                }

                Location strike = p.getLocation();
                strike.getWorld().strikeLightningEffect(strike);
                p.damage(3 + stage, boss);
            }
        }.runTaskTimer(
                TuTienCultivation.getInstance(),
                40, 60
        );
    }
}
