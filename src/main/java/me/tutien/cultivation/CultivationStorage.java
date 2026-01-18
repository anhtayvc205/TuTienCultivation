package me.tutien.cultivation;

import java.util.*;

public class CultivationStorage {

    private final Map<UUID, PlayerCultivationData> data = new HashMap<>();

    public PlayerCultivationData get(UUID uuid) {
        return data.computeIfAbsent(uuid, PlayerCultivationData::new);
    }
}
