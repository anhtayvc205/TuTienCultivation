package me.tutien.cultivation.listener;

import me.tutien.cultivation.TuTienCultivation;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;

public class CultivationListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        var d = TuTienCultivation.storage().get(e.getPlayer().getUniqueId());
        if (!d.isCultivating()) return;

        if (e.getFrom().distance(e.getTo()) > 0.01) {
            d.setCultivating(false);
            e.getPlayer().sendMessage("§cDi chuyển → tu luyện bị gián đoạn!");
        }
    }
}
