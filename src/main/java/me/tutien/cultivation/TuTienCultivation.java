package me.tutien.cultivation;

import org.bukkit.plugin.java.JavaPlugin;

public final class TuTienCultivation extends JavaPlugin {

    private static TuTienCultivation instance;
    private static CultivationStorage storage;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        storage = new CultivationStorage();

        getCommand("tutien").setExecutor(new TutienCommand());
        getCommand("tuluyen").setExecutor(new TuluyenCommand());
        getCommand("dotpha").setExecutor(new DotPhaCommand());

        getServer().getPluginManager().registerEvents(new CultivationListener(), this);
        getServer().getPluginManager().registerEvents(new CombatListener(), this);

        getLogger().info("TuTienCultivation ENABLED (VIP CORE)");
    }

    public static TuTienCultivation get() {
        return instance;
    }

    public static CultivationStorage storage() {
        return storage;
    }
}
