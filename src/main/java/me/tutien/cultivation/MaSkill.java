package me.tutien.cultivation;

import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.Player;

public class MaSkill implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player p)) return;

        PlayerCultivationData data = TuTienCultivation.storage().get(p.getUniqueId());
        if (data.getStage().dao != DaoType.MA_DAO) return;

        double heal = e.getDamage() * 0.3;
        p.setHealth(Math.min(p.getMaxHealth(), p.getHealth() + heal));
    }
}
