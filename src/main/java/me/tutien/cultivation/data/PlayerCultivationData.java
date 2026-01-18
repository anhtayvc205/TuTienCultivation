package me.tutien.cultivation.data;

import me.tutien.cultivation.realm.RealmStage;

public class PlayerCultivationData {

    private RealmStage realm = RealmStage.LUYEN_KHI;
    private long linhKhi = 0;
    private boolean cultivating = false;

    /* ================== REALM ================== */
    public RealmStage getStage() { // alias cũ
        return realm;
    }

    public RealmStage getRealm() {
        return realm;
    }

    public void setRealm(RealmStage r) {
        this.realm = r;
    }

    /* ================== LINH KHÍ ================== */
    public long getLinhKhi() {
        return linhKhi;
    }

    public void addKhi(long amount) { // alias cũ
        linhKhi += amount;
    }

    public void addLinhKhi(long amount) {
        linhKhi += amount;
    }

    public boolean canDotPha() {
        return linhKhi >= realm.getNeedLinhKhi();
    }

    public void dotPha() {
        if (!canDotPha()) return;
        linhKhi = 0;
        realm = realm.next();
    }

    /* ================== TU LUYỆN ================== */
    public boolean isCultivating() {
        return cultivating;
    }

    public void setCultivating(boolean b) {
        cultivating = b;
    }

    /* ================== DAO (GIỮ ĐỂ KHÔNG LỖI) ================== */
    public Object getDao() {
        return null;
    }

    public void chooseDao(Object dao) {
        // placeholder để không lỗi
    }
}
