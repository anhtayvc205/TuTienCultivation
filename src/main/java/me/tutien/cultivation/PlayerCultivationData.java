package me.tutien.cultivation;

import java.util.UUID;

public class PlayerCultivationData {

    private final UUID uuid;
    private DaoType dao;
    private RealmStage stage;
    private long linhKhi;
    private boolean cultivating;

    public PlayerCultivationData(UUID uuid) {
        this.uuid = uuid;
        this.dao = DaoType.CHINH_DAO;
        this.stage = RealmStage.PHAM_NHAN;
        this.linhKhi = 0;
        this.cultivating = false;
    }

    public UUID getUuid() {
        return uuid;
    }

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

    public void setLinhKhi(long linhKhi) {
        this.linhKhi = linhKhi;
    }

    public void addLinhKhi(long amount) {
        this.linhKhi += amount;
    }

    public void resetLinhKhi() {
        this.linhKhi = 0;
    }

    public long getNeedLinhKhi() {
        return stage.maxLinhKhi;
    }

    public boolean isCultivating() {
        return cultivating;
    }

    public void setCultivating(boolean cultivating) {
        this.cultivating = cultivating;
    }
}
