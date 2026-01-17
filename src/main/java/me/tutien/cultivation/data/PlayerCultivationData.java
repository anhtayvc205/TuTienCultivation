package me.tutien.cultivation.data;

import me.tutien.cultivation.dao.DaoType;
import me.tutien.cultivation.realm.RealmStage;

public class PlayerCultivationData {

    private DaoType dao = DaoType.NONE;
    private RealmStage stage;
    private int linhKhi;
    private boolean cultivating;

    public DaoType getDao() { return dao; }
    public RealmStage getStage() { return stage; }

    public void chooseDao(DaoType d) {
        dao = d;
        for (RealmStage r : RealmStage.values()) {
            if (r.dao == d) {
                stage = r;
                break;
            }
        }
    }

    public boolean isCultivating() { return cultivating; }
    public void setCultivating(boolean b) { cultivating = b; }

    public void addKhi() {
        linhKhi = Math.min(stage.maxLinhKhi, linhKhi + 1);
    }

    public boolean canDotPha() {
        return linhKhi >= stage.maxLinhKhi;
    }

    public void dotPha() {
        RealmStage next = stage.next();
        if (next != null) {
            stage = next;
            linhKhi = 0;
        }
    }
}
