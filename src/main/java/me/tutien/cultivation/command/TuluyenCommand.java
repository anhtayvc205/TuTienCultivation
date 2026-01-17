package me.tutien.cultivation.command;

import me.tutien.cultivation.TuTienCultivation;
import me.tutien.cultivation.dao.DaoType;
import me.tutien.cultivation.data.PlayerCultivationData;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TutienCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if (!(s instanceof Player p)) return true;

        PlayerCultivationData d = TuTienCultivation.storage().get(p.getUniqueId());
        if (d.getDao() != DaoType.NONE) {
            p.sendMessage("§cBạn đã tu luyện rồi!");
            return true;
        }

        d.chooseDao(DaoType.CHINH_DAO);
        p.sendMessage("§aBạn đã bước vào con đường tu tiên!");
        return true;
    }
}
