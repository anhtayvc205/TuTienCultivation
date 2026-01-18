package me.tutien.cultivation;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class DotPhaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if (!(s instanceof Player p)) return false;

        PlayerCultivationData data = TuTienCultivation.storage().get(p.getUniqueId());

        if (!data.canDotPha()) {
            p.sendMessage("§cChưa đủ linh khí để đột phá!");
            return true;
        }

        data.setCultivating(false);
        p.sendMessage("§6⚡ Bạn bắt đầu đột phá! Thiên kiếp sắp giáng xuống!");

        ThienKiep.start(p, data); // message 3 sẽ có file này
        return true;
    }
}
