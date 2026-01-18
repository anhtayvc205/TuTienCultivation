package me.tutien.cultivation;

import me.tutien.cultivation.command.*;
import me.tutien.cultivation.listener.CombatListener;
import me.tutien.cultivation.listener.CultivationListener;
import me.tutien.cultivation.storage.CultivationStorage;
import me.tutien.cultivation.task.CultivationTask;
import org.bukkit.plugin.java.JavaPlugin;

public final class TuTienCultivation extends JavaPlugin {

    private static TuTienCultivation instance;
    private static CultivationStorage storage;

    @Override
    public void onEnable() {
        instance = this;
        storage = new CultivationStorage();

        // ===== COMMAND =====
        getCommand("tutien").setExecutor(new TutienCommand());
        getCommand("tuluyen").setExecutor(new TuluyenCommand());
        getCommand("dotpha").setExecutor(new DotPhaCommand());

        // ===== LISTENER =====
        getServer().getPluginManager().registerEvents(new CombatListener(), this);
        getServer().getPluginManager().registerEvents(new CultivationListener(), this);

        // ===== TASK =====
        new CultivationTask().runTaskTimer(this, 20, 20);

        getLogger().info("§aTuTienCultivation ENABLED (Paper 1.21.8)");
    }

    @Override
    public void onDisable() {
        getLogger().info("§cTuTienCultivation DISABLED");
    }

    // ===== STATIC ACCESS =====
    public static TuTienCultivation getInstance() {
        return instance;
    }

    public static CultivationStorage storage() {
        return storage;
    }
}
