package me.tutien.cultivation;

import org.bukkit.plugin.java.JavaPlugin;

public class TuTienCultivation extends JavaPlugin {

    private static CultivationStorage storage;

    @Override
    public void onEnable() {
        storage = new CultivationStorage();

        getCommand("tuluyen").setExecutor(new TuluyenCommand());
        getCommand("dotpha").setExecutor(new DotPhaCommand());

        getServer().getPluginManager().registerEvents(new CombatListener(), this);

        new CultivationTask().runTaskTimer(this, 20, 20);

        getLogger().info("TuTienCultivation ENABLED (Part 1)");
    }

    public static CultivationStorage storage() {
        return storage;
    }
}
