package me.tutien.cultivation.command;

import me.tutien.cultivation.*;
import me.tutien.cultivation.thienkiep.ThienKiep;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class DotPhaCommand implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if (!(s instanceof Player p)) return true;

        var d = TuTienCultivation.storage().get(p.getUniqueId());
        if (!d.canDotPha()) {
            p.sendMessage("§cChưa đủ linh khí!");
            return true;
        }

        ThienKiep.start(p, d);
        return true;
    }
}
