package me.tutien.cultivation.thienkiep;

import me.tutien.cultivation.TuTienCultivation;
import me.tutien.cultivation.data.PlayerCultivationData;
import me.tutien.cultivation.realm.RealmStage;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ThienKiep {

    public static void start(Player p, PlayerCultivationData data) {

        RealmStage stage = data.getStage();
        Location loc = p.getLocation();

        p.sendTitle("§4⚡ THIÊN KIẾP ⚡",
                "§7Thiên đạo khảo nghiệm · " + stage.display,
                10, 60, 10);

        p.playSound(loc, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 2f, 1f);

        new BukkitRunnable() {
            int wave = 0;

            @Override
            public void run() {
                wave++;

                if (!p.isOnline() || p.isDead()) {
                    p.sendMessage("§c❌ Đột phá thất bại – Thiên đạo tru diệt!");
                    cancel();
                    return;
                }

                if (wave <= 5) {
                    p.sendMessage("§e⚡ Thiên kiếp đợt " + wave + " giáng xuống!");
                    ThienKiepBoss.spawnWave(p, stage, wave);
                    return;
                }

                p.sendMessage("§4§l⚠ Thiên Đạo giáng lâm!");
                ThienKiepBoss.spawnBoss(p, stage);
                cancel();
            }

        }.runTaskTimer(
                TuTienCultivation.getPlugin(TuTienCultivation.class),
                40, 120
        );
    }
}
