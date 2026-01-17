package me.tutien.cultivation;

import me.tutien.cultivation.command.*;
import me.tutien.cultivation.listener.CultivationListener;
import me.tutien.cultivation.storage.CultivationStorage;
import org.bukkit.plugin.java.JavaPlugin;

public class TuTienCultivation extends JavaPlugin {

    private static CultivationStorage storage;

    @Override
    public void onEnable() {
        storage = new CultivationStorage();

        getCommand("tutien").setExecutor(new TutienCommand());
        getCommand("tuluyen").setExecutor(new TuluyenCommand());
        getCommand("dotpha").setExecutor(new DotPhaCommand());

        getServer().getPluginManager().registerEvents(
                new CultivationListener(), this
        );

        getLogger().info("TuTienCultivation PHẦN 1 loaded");
    }

    public static CultivationStorage storage() {
        return storage;
    }
}
