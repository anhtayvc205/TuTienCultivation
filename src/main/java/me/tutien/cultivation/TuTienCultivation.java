package me.tutien.cultivation;

import me.tutien.cultivation.command.*;
import me.tutien.cultivation.listener.CombatListener;
import me.tutien.cultivation.listener.CultivationListener;
import me.tutien.cultivation.storage.CultivationStorage;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class TuTienCultivation extends JavaPlugin {

    private static TuTienCultivation instance;
    private static CultivationStorage storage;

    @Override
    public void onEnable() {
        instance = this;
        storage = new CultivationStorage();

        registerCommands();
        registerListeners();

        getLogger().info("TuTienCultivation ENABLED (Paper 1.21.8)");
    }

    @Override
    public void onDisable() {
        getLogger().info("TuTienCultivation DISABLED");
    }

    // ================= COMMAND =================
    private void registerCommands() {
        safeCommand("tutien", new TutienCommand());
        safeCommand("tuluyen", new TuluyenCommand());
        safeCommand("dotpha", new DotPhaCommand());
    }

    private void safeCommand(String name, Object executor) {
        PluginCommand cmd = getCommand(name);
        if (cmd == null) {
            getLogger().severe("❌ Command /" + name + " chưa khai trong plugin.yml");
            return;
        }
        cmd.setExecutor((org.bukkit.command.CommandExecutor) executor);
    }

    // ================= LISTENER =================
    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new CombatListener(), this);
        getServer().getPluginManager().registerEvents(new CultivationListener(), this);
    }

    // ================= STATIC ACCESS =================
    public static TuTienCultivation getInstance() {
        return instance;
    }

    public static CultivationStorage storage() {
        return storage;
    }
}
