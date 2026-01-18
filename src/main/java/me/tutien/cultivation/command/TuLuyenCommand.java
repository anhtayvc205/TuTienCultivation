package me.tutien.cultivation.command;

import me.tutien.cultivation.data.PlayerCultivationData;
import org.bukkit.entity.Player;
import me.tutien.cultivation.*;
import me.tutien.cultivation.realm.RealmStage;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TuLuyenCommand implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if (!(s instanceof Player p)) return true;

        var d = TuTienCultivation.storage().get(p.getUniqueId());
        if (d.getStage() == null) {
            p.sendMessage("§cChưa chọn đạo!");
            return true;
        }

        d.setCultivating(!d.isCultivating());
        p.sendMessage(d.isCultivating() ? "§aBắt đầu tu luyện" : "§cDừng tu luyện");

        if (d.isCultivating()) {
            new BukkitRunnable() {
                public void run() {
                    if (!d.isCultivating()) { cancel(); return; }

                    // 🔥 LINH KHÍ TĂNG THEO CẢNH GIỚI
                    long gain = Math.max(1, d.getStage().ordinal() + 1);
                    d.addKhi(gain);

                    p.sendActionBar("§bLinh khí: " + d.getLinhKhi()
                            + "§7 / " + d.getStage().getNeedLinhKhi());
                }
            }.runTaskTimer(TuTienCultivation.getPlugin(TuTienCultivation.class), 20, 20);
        }
        return true;
    }
}
