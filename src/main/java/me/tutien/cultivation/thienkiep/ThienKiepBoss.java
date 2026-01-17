package me.tutien.cultivation.thienkiep;

import me.tutien.cultivation.realm.RealmStage;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;

public class ThienKiepBoss {

    public static void spawn(Player p, RealmStage stage, int wave) {

        Location l = p.getLocation().add(0, 1, 0);
        World w = p.getWorld();

        Zombie boss = (Zombie) w.spawnEntity(l, EntityType.ZOMBIE);

        boss.setCustomName("§cThiên Kiếp · " + stage.display);
        boss.setCustomNameVisible(true);

        double hp = 40 + (stage.ordinal() * 30L);
        double dmg = 6 + wave * 2;

        boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
        boss.setHealth(hp);
        boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(dmg);

        boss.setTarget(p);
        boss.setAdult();

        ThienKiepSkill.bind(boss, wave);
    }
}
