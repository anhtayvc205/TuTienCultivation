package me.tutien.cultivation;

import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;

public class CultivationListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        PlayerCultivationData data = TuTienCultivation.storage().get(p.getUniqueId());

        if (!data.isCultivating()) return;

        if (e.getFrom().distance(e.getTo()) > 0.01) {
            data.setCultivating(false);
            p.sendMessage("§cTu luyện bị gián đoạn vì di chuyển!");
        }
    }
}
