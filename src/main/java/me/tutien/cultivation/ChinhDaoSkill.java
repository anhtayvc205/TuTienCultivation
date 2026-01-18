package me.tutien.cultivation;

import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.potion.*;

public class ChinhDaoSkill implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player p)) return;

        PlayerCultivationData data = TuTienCultivation.storage().get(p.getUniqueId());
        if (data.getStage().dao != DaoType.CHINH_DAO) return;

        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 0));
    }
}
