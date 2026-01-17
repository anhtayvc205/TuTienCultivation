package me.tutien.cultivation.thienkiep;

import me.tutien.cultivation.realm.RealmStage;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ThienKiepSkill {

    private static final Random r = new Random();

    public static void bind(LivingEntity boss, RealmStage stage) {

        new BukkitRunnable() {

            int tick = 0;

            @Override
            public void run() {
                if (boss.isDead() || !boss.isValid()) {
                    cancel();
                    return;
                }

                tick++;
                World w = boss.getWorld();
                Location l = boss.getLocation();

                if (tick % 4 == 0) {
                    // ⚡ Lôi phạt
                    w.strikeLightningEffect(l);
                    for (Entity e : w.getNearbyEntities(l, 4, 4, 4))
                        if (e instanceof Player p)
                            p.damage(4 + stage.ordinal());
                }

                if (tick % 6 == 0) {
                    // 💥 Nổ linh khí
                    w.createExplosion(l, 0F, false, false);
                }

                if (tick % 10 == 0) {
                    // 🩸 Hút sinh mệnh
                    double heal = 10 + stage.ordinal() * 5;
                    boss.setHealth(Math.min(
                            boss.getHealth() + heal,
                            boss.getAttribute(Attribute.MAX_HEALTH).getBaseValue()
                    ));
                }
            }
        }.runTaskTimer(
                Bukkit.getPluginManager().getPlugin("TuTienCultivation"),
                40, 40
        );
    }
}
