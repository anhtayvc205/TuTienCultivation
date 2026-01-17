package me.tutien.cultivation.data;

import me.tutien.cultivation.dao.DaoType;
import me.tutien.cultivation.realm.RealmStage;

public class PlayerCultivationData {

    private DaoType dao = DaoType.NONE;
    private RealmStage stage;
    private long linhKhi;
    private boolean cultivating;

    public DaoType getDao() { return dao; }
    public RealmStage getStage() { return stage; }
    public long getLinhKhi() { return linhKhi; }

    public void chooseDao(DaoType d) {
        dao = d;
        linhKhi = 0;
        for (RealmStage r : RealmStage.values())
            if (r.dao == d) { stage = r; break; }
    }

    public void addKhi(long amount) {
        linhKhi = Math.min(stage.maxLinhKhi, linhKhi + amount);
    }

    public boolean canDotPha() {
        return linhKhi >= stage.maxLinhKhi;
    }

    public void dotPha() {
        RealmStage n = stage.next();
        if (n != null) {
            stage = n;
            linhKhi = 0;
        }
    }

    public boolean isCultivating() { return cultivating; }
    public void setCultivating(boolean b) { cultivating = b; }
}
