package me.tutien.cultivation.thienkiep;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ThienKiepSkill {

    private static final Random r = new Random();

    public static void bind(LivingEntity boss, int wave) {

        new BukkitRunnable() {

            @Override
            public void run() {
                if (boss.isDead() || !boss.isValid()) {
                    cancel();
                    return;
                }

                int skill = r.nextInt(3);
                Location l = boss.getLocation();
                World w = l.getWorld();

                switch (skill) {
                    case 0 -> { // sét
                        w.strikeLightningEffect(l);
                        for (Entity e : w.getNearbyEntities(l, 4, 4, 4)) {
                            if (e instanceof Player p) p.damage(3 + wave);
                        }
                    }
                    case 1 -> { // nổ linh khí
                        w.createExplosion(l, 0F, false, false);
                        for (Entity e : w.getNearbyEntities(l, 3, 3, 3)) {
                            if (e instanceof Player p) p.damage(2 + wave);
                        }
                    }
                    case 2 -> { // hút máu
                        double heal = 4 + wave * 2;
                        boss.setHealth(Math.min(
                                boss.getHealth() + heal,
                                boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()
                        ));
                    }
                }
            }
        }.runTaskTimer(
                Bukkit.getPluginManager().getPlugin("TuTienCultivation"),
                60, 60
        );
    }
}
