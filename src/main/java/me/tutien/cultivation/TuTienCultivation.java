package me.tutien.cultivation;

import me.tutien.cultivation.command.*;
import me.tutien.cultivation.storage.CultivationStorage;
import org.bukkit.plugin.java.JavaPlugin;

public class TuTienCultivation extends JavaPlugin {

    private static CultivationStorage storage;

    @Override
    public void onEnable() {
        storage = new CultivationStorage();

        getCommand("dotpha").setExecutor(new DotPhaCommand());
        getCommand("tuluyen").setExecutor(new TuLuyenCommand());
    }

    public static CultivationStorage storage() {
        return storage;
    }
}
