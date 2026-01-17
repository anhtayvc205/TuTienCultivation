package me.tutien.cultivation.thienkiep;

import me.tutien.cultivation.TuTienCultivation;
import me.tutien.cultivation.data.PlayerCultivationData;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ThienKiep {

    public static void start(Player p, PlayerCultivationData data) {
        World w = p.getWorld();
        Location loc = p.getLocation();

        p.sendMessage("§c⚡ Thiên Kiếp giáng xuống! ⚡");
        w.strikeLightningEffect(loc);

        new BukkitRunnable() {
            int wave = 0;

            @Override
            public void run() {
                wave++;

                if (wave <= 3) {
                    p.sendMessage("§6Thiên Kiếp đợt " + wave + " bắt đầu!");
                    ThienKiepBoss.spawnMobWave(p, wave);
                }

                if (wave == 4) {
                    p.sendMessage("§4⚠ Boss Thiên Kiếp xuất hiện!");
                    ThienKiepBoss.spawnBoss(p, data);
                    cancel();
                }
            }
        }.runTaskTimer(TuTienCultivation.getPlugin(TuTienCultivation.class), 0, 100);
    }
}
