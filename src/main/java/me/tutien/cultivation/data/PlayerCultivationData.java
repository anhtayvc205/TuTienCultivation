package me.tutien.cultivation.data;

import me.tutien.cultivation.stage.RealmStage;

public class PlayerCultivationData {

    private RealmStage stage = RealmStage.LUYEN_KHI;
    private double linhKhi = 0;

    public RealmStage getStage() { return stage; }

    public boolean canDotPha() {
        return linhKhi >= stage.getNeed();
    }

    public void nextStage() {
        stage = stage.next();
        linhKhi = 0;
    }

    public void addLinhKhi(double v) {
        linhKhi += v;
    }
}
