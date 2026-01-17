package me.tutien.cultivation.thienkiep;

import me.tutien.cultivation.TuTienCultivation;
import me.tutien.cultivation.data.PlayerCultivationData;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ThienKiep {

    public static void start(Player p, PlayerCultivationData data) {

        p.sendTitle("§4⚡ THIÊN KIẾP ⚡", "§7Thiên đạo khảo nghiệm", 10, 60, 10);
        p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 2f, 1f);

        new BukkitRunnable() {
            int wave = 1;

            @Override
            public void run() {
                if (!p.isOnline() || p.isDead()) {
                    p.sendMessage("§c❌ Đột phá thất bại!");
                    cancel();
                    return;
                }

                if (wave > 3) {
                    data.dotPha();
                    p.sendMessage("§a✔ Đột phá thành công!");
                    cancel();
                    return;
                }

                p.sendMessage("§eThiên kiếp · Wave " + wave);
                ThienKiepBoss.spawn(p, data.getStage(), wave);
                wave++;
            }
        }.runTaskTimer(
                TuTienCultivation.getPlugin(TuTienCultivation.class),
                20, 120
        );
    }
}
