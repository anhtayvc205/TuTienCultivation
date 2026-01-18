package me.tutien.cultivation;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TutienCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if (!(s instanceof Player p)) return false;

        PlayerCultivationData data = TuTienCultivation.storage().get(p.getUniqueId());

        if (data.getStage() != RealmStage.PHAM_NHAN) {
            p.sendMessage("§cBạn đã bắt đầu tu tiên rồi!");
            return true;
        }

        p.sendMessage("§aBạn bước lên con đường tu tiên!");
        p.sendMessage("§eDùng /tuluyen để bắt đầu tu luyện.");
        return true;
    }
}
