package me.tutien.cultivation;

import me.tutien.cultivation.command.*;
import me.tutien.cultivation.core.CultivationTask;
import me.tutien.cultivation.storage.CultivationStorage;
import org.bukkit.plugin.java.JavaPlugin;

public class TuTienCultivation extends JavaPlugin {

    private static CultivationStorage storage;

    @Override
    public void onEnable() {
        storage = new CultivationStorage();

        getCommand("tuluyen").setExecutor(new TuluyenCommand());
        getCommand("dotpha").setExecutor(new DotPhaCommand());

        new CultivationTask().runTaskTimer(this, 20, 20);
        getLogger().info("TuTienCultivation ENABLED");
    }

    public static CultivationStorage storage() {
        return storage;
    }
}
