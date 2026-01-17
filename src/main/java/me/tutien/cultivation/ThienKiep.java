package me.tutien.cultivation;

import org.bukkit.entity.Player;
import org.bukkit.*;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;

public class ThienKiep {

    public static void start(Player p, PlayerCultivationData data) {

        World w = p.getWorld();
        Location l = p.getLocation();

        p.sendTitle("§cThiên Kiếp", "§7Thiên đạo khảo nghiệm", 10, 60, 10);
        w.strikeLightningEffect(l);

        Zombie z = w.spawn(l.add(2, 0, 2), Zombie.class);
        z.setCustomName("§cThiên Kiếp Hộ Pháp");
        z.setTarget(p);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (z.isDead()) {
                    data.dotPha();

                    double max = 20.0 + data.getBonusHealth();
                    p.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH)
                            .setBaseValue(max);
                    p.setHealth(max);

                    p.sendMessage("§aĐột phá thành công! Máu & sức mạnh tăng!");
                    cancel();
                }
            }
        }.runTaskTimer(
                TuTienCultivation.getPlugin(TuTienCultivation.class),
                20, 20
        );
    }
}
