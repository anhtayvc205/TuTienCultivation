package me.tutien.cultivation.storage;

import me.tutien.cultivation.data.PlayerCultivationData;
import java.util.*;

public class CultivationStorage {

    private final Map<UUID, PlayerCultivationData> map = new HashMap<>();

    public PlayerCultivationData get(UUID uuid) {
        return map.computeIfAbsent(uuid, k -> new PlayerCultivationData());
    }
}
