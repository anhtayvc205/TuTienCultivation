package me.tutien.cultivation.command;

import me.tutien.cultivation.TuTienCultivation;
import me.tutien.cultivation.data.PlayerCultivationData;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class DotPhaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) return true;

        PlayerCultivationData data = TuTienCultivation.storage().get(p.getUniqueId());

        if (!data.canDotPha()) {
            p.sendMessage("§cChưa đủ linh khí để đột phá!");
            return true;
        }

        data.dotPha();
        p.sendMessage("§aĐột phá thành công! Cảnh giới: §e" + data.getRealm().getName());
        return true;
    }
}
