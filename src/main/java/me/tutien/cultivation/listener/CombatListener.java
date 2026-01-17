package me.tutien.cultivation;

import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.Player;

public class CombatListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player p)) return;

        PlayerCultivationData data =
                TuTienCultivation.storage().get(p.getUniqueId());

        e.setDamage(e.getDamage() + data.getBonusDamage());
    }
}
