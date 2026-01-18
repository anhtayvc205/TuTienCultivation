package me.tutien.cultivation;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ThienKiep {

    public static void start(Player p, PlayerCultivationData data) {

        p.sendTitle("§4⚡ THIÊN KIẾP ⚡", "§7Thiên đạo giáng lâm", 10, 60, 20);

        p.sendMessage("§8[§cThiên Đạo§8] §7Kẻ nghịch thiên… ngươi dám bước vào "
                + data.getStage().display + " ?");

        new BukkitRunnable() {
            int wave = 1;

            @Override
            public void run() {
                if (!p.isOnline()) {
                    cancel();
                    return;
                }

                if (wave <= 3) {
                    loreWave(p, wave);
                    ThienKiepBoss.spawnMobWave(p, wave);
                    wave++;
                } else {
                    loreBoss(p, data);
                    ThienKiepBoss.spawnBoss(p, data);
                    cancel();
                }
            }
        }.runTaskTimer(TuTienCultivation.get(), 40, 120);
    }

    private static void loreWave(Player p, int wave) {
        switch (wave) {
            case 1 -> p.sendMessage("§8[§cThiên Đạo§8] §7Thiên uy sơ hiện… chịu nổi không?");
            case 2 -> p.sendMessage("§8[§cThiên Đạo§8] §7Linh hồn ngươi đang run rẩy…");
            case 3 -> p.sendMessage("§8[§cThiên Đạo§8] §7Nếu không chết… hãy chứng minh!");
        }
    }

    private static void loreBoss(Player p, PlayerCultivationData data) {
        p.sendMessage("§8[§cThiên Đạo§8] §4Đủ rồi!");
        p.sendMessage("§8[§cThiên Đạo§8] §7Thiên kiếp chi chủ, hãy phán xét kẻ này!");
        p.sendMessage("§7Cảnh giới cần vượt: §e" + data.getStage().display);
    }
}
