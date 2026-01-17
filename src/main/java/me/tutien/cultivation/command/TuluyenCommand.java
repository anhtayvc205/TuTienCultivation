package me.tutien.cultivation.command;

import me.tutien.cultivation.TuTienCultivation;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TuluyenCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if (!(s instanceof Player p)) return true;

        var d = TuTienCultivation.storage().get(p.getUniqueId());
        d.setCultivating(!d.isCultivating());

        p.sendMessage(d.isCultivating()
                ? "§aBắt đầu tu luyện..."
                : "§cDừng tu luyện");
        return true;
    }
}
