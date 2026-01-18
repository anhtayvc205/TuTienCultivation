package me.tutien.cultivation;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class SwordSkill implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        if (!(e.getPlayer() instanceof Player p)) return;
        if (!p.getInventory().getItemInMainHand().getType().toString().contains("SWORD")) return;

        PlayerCultivationData data = TuTienCultivation.storage().get(p.getUniqueId());
        if (data.getStage().dao != DaoType.KIEM_DAO) return;

        p.sendMessage("§b⚔ Vạn Kiếm Quy Tông!");

        for (int i = 0; i < 10; i++) {
            Arrow a = p.launchProjectile(Arrow.class);
            a.setVelocity(p.getLocation().getDirection().add(
                    new Vector(Math.random()-0.5, Math.random()*0.2, Math.random()-0.5)
            ).multiply(2));
            a.setDamage(4 + data.getStage().ordinal());
        }
    }
}
