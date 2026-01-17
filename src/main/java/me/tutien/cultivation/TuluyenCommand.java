package me.tutien.cultivation;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TuluyenCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if (!(s instanceof Player p)) return true;

        PlayerCultivationData data =
                TuTienCultivation.storage().get(p.getUniqueId());

        data.setCultivating(!data.isCultivating());
        p.sendMessage(data.isCultivating()
                ? "§aBắt đầu tu luyện"
                : "§cDừng tu luyện");
        return true;
    }
}
