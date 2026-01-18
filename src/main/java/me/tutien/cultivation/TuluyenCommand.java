package me.tutien.cultivation;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TuluyenCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if (!(s instanceof Player p)) return false;

        PlayerCultivationData data = TuTienCultivation.storage().get(p.getUniqueId());

        if (data.isCultivating()) {
            p.sendMessage("§cBạn đang tu luyện rồi!");
            return true;
        }

        data.setCultivating(true);
        p.sendMessage("§aBắt đầu tu luyện... Đứng yên để hấp thụ linh khí.");

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!data.isCultivating() || !p.isOnline()) {
                    cancel();
                    return;
                }

                data.addKhi(
                    TuTienCultivation.get().getConfig().getLong("linh-khi-moi-giay")
                );

                p.sendActionBar(
                    "§bLinh khí: " + data.getLinhKhi() + " / " + data.getStage().getNeedLinhKhi()
                );
            }
        }.runTaskTimer(TuTienCultivation.get(), 20, 20);

        return true;
    }
}
