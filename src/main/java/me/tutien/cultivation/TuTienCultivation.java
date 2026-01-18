package me.tutien.cultivation;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class TuTienCultivation extends JavaPlugin {

    private static TuTienCultivation instance;
    private static CultivationStorage storage;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        storage = new CultivationStorage();

        // ===== COMMAND =====
        safeCommand("tutien", new TutienCommand());
        safeCommand("tuluyen", new TuluyenCommand());
        safeCommand("dotpha", new DotPhaCommand());

        // ===== LISTENER =====
        getServer().getPluginManager().registerEvents(new CultivationListener(), this);
        getServer().getPluginManager().registerEvents(new CombatListener(), this);

        // ===== SKILL LISTENER =====
        getServer().getPluginManager().registerEvents(new SwordSkill(), this);
        getServer().getPluginManager().registerEvents(new MaSkill(), this);
        getServer().getPluginManager().registerEvents(new ChinhDaoSkill(), this);

        getLogger().info("§aTuTienCultivation ENABLED (VIP PRO)");
    }

    @Override
    public void onDisable() {
        getLogger().info("§cTuTienCultivation DISABLED");
    }

    // ================= COMMAND SAFE REGISTER =================
    private void safeCommand(String name, Object executor) {
        PluginCommand cmd = getCommand(name);
        if (cmd == null) {
            getLogger().severe("❌ Command /" + name + " chưa khai trong plugin.yml");
            return;
        }
        cmd.setExecutor((org.bukkit.command.CommandExecutor) executor);
    }

    // ================= STATIC ACCESS =================
    public static TuTienCultivation get() {
        return instance;
    }

    public static CultivationStorage storage() {
        return storage;
    }
}
