package me.tutien.cultivation;

import me.tutien.cultivation.data.PlayerCultivationData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CultivationTask extends BukkitRunnable {

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerCultivationData data =
                    TuTienCultivation.storage().get(p.getUniqueId());

            if (data.isCultivating()) {
                data.addKhi();
                p.sendActionBar(
                        "§e" + data.getStage().name() +
                        " §7(" + data.getLinhKhi() + "/" + data.getStage().maxKhi + ")"
                );
            }
        }
    }
}
