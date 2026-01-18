package me.tutien.cultivation;

import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;

public class CombatListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player p)) return;

        PlayerCultivationData data = TuTienCultivation.storage().get(p.getUniqueId());

        if (data.isCultivating()) {
            data.setCultivating(false);
            p.sendMessage("§cTu luyện bị gián đoạn vì bị tấn công!");
        }
    }
}
