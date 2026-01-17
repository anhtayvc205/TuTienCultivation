package me.tutien.cultivation;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class DotPhaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if (!(s instanceof Player p)) return true;

        PlayerCultivationData data =
                TuTienCultivation.storage().get(p.getUniqueId());

        if (!data.canDotPha()) {
            p.sendMessage("§cChưa đủ linh khí!");
            return true;
        }

        ThienKiep.start(p, data);
        return true;
    }
}
