package me.tutien.cultivation.thienkiep;

import me.tutien.cultivation.realm.RealmStage;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;

public class ThienKiepBoss {

    public static void spawnWave(Player p, RealmStage stage, int wave) {
        World w = p.getWorld();
        Location l = p.getLocation();

        int amount = 2 + wave * 2;
        for (int i = 0; i < amount; i++) {
            Zombie z = (Zombie) w.spawnEntity(
                    l.clone().add(Math.random()*6-3, 0, Math.random()*6-3),
                    EntityType.ZOMBIE
            );

            double hp = 20 + stage.ordinal() * 10 + wave * 5;

            z.setCustomName("§cThiên Kiếp Linh Hồn");
            z.setCustomNameVisible(true);
            z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(hp);
            z.setHealth(hp);
            z.setTarget(p);
        }
    }

    public static void spawnBoss(Player p, RealmStage stage) {

        World w = p.getWorld();
        Location l = p.getLocation();

        WitherSkeleton boss = (WitherSkeleton) w.spawnEntity(l, EntityType.WITHER_SKELETON);

        double hp = 300 + stage.ordinal() * 120;
        double dmg = 10 + stage.ordinal() * 4;

        boss.setCustomName("§4§lThiên Đạo · " + stage.display);
        boss.setCustomNameVisible(true);

        boss.getAttribute(Attribute.MAX_HEALTH).setBaseValue(hp);
        boss.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(dmg);
        boss.setHealth(hp);

        boss.setTarget(p);

        ThienKiepSkill.bind(boss, stage);
    }
}
