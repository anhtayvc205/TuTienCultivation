package me.tutien.cultivation;

public class PlayerCultivationData {

    private DaoType dao = DaoType.CHINH_DAO;
    private RealmStage stage = RealmStage.PHAM_NHAN;
    private long linhKhi = 0;
    private boolean cultivating = false;

    public DaoType getDao() {
        return dao;
    }

    public void setDao(DaoType dao) {
        this.dao = dao;
    }

    public RealmStage getStage() {
        return stage;
    }

    public void setStage(RealmStage stage) {
        this.stage = stage;
    }

    public long getLinhKhi() {
        return linhKhi;
    }

    public void addKhi(long amount) {
        linhKhi += amount;
    }

    public void resetKhi() {
        linhKhi = 0;
    }

    public boolean canDotPha() {
        return linhKhi >= stage.getNeedLinhKhi();
    }

    public boolean isCultivating() {
        return cultivating;
    }

    public void setCultivating(boolean cultivating) {
        this.cultivating = cultivating;
    }
}
