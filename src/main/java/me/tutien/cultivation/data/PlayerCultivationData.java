package me.tutien.cultivation.data;

import me.tutien.cultivation.realm.RealmStage;

public class PlayerCultivationData {

    private RealmStage realm = RealmStage.LUYEN_KHI;
    private double linhKhi = 0;

    public RealmStage getRealm() {
        return realm;
    }

    public void setRealm(RealmStage realm) {
        this.realm = realm;
    }

    public double getLinhKhi() {
        return linhKhi;
    }

    public void addLinhKhi(double amount) {
        this.linhKhi += amount;
    }

    public boolean canDotPha() {
        return linhKhi >= realm.getNeedLinhKhi();
    }

    public void dotPha() {
        if (canDotPha()) {
            realm = realm.next();
            linhKhi = 0;
        }
    }
}
