package me.tutien.cultivation.thienkiep;

import me.tutien.cultivation.data.PlayerCultivationData;
import me.tutien.cultivation.realm.RealmStage;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.*;

public class ThienKiep {

    public static void start(Player p, PlayerCultivationData data) {

        RealmStage stage = data.getStage();
        World w = p.getWorld();
        Location center = p.getLocation();
        List<LivingEntity> alive = new ArrayList<>();

        p.sendTitle("§4⚡ Thiên Kiếp", stage.displayName, 10, 60, 10);

        new BukkitRunnable() {
            int wave = 1;
            int tick = 0;

            @Override
            public void run() {
                if (!p.isOnline() || p.isDead()) {
                    alive.forEach(Entity::remove);
                    cancel();
                    return;
                }

                if (alive.isEmpty()) {
                    if (wave > 3) {
                        data.dotPha();
                        p.sendMessage("§aĐột phá thành công!");
                        cancel();
                        return;
                    }

                    spawnWave(w, p, stage, wave, alive, center);
                    wave++;
                }

                if (tick++ % 60 == 0) {
                    for (LivingEntity e : alive) {
                        w.strikeLightningEffect(e.getLocation());
                        p.damage(2);
                    }
                }
            }
        }.runTaskTimer(
                Bukkit.getPluginManager().getPlugin("TuTienCultivation"),
                20, 20
        );
    }

    private static void spawnWave(World w, Player p, RealmStage stage,
                                  int wave, List<LivingEntity> list, Location c) {

        EntityType type = wave == 3 ? EntityType.IRON_GOLEM : EntityType.ZOMBIE;
        int count = wave == 1 ? 3 : wave == 2 ? 2 : 1;

        for (int i = 0; i < count; i++) {
            LivingEntity e = (LivingEntity) w.spawnEntity(
                    c.clone().add(Math.random() * 4 - 2, 0, Math.random() * 4 - 2),
                    type
            );
            if (e instanceof Mob m) m.setTarget(p);
            e.setCustomName("§cThiên Kiếp · " + stage.displayName);
            list.add(e);
        }
    }
}
