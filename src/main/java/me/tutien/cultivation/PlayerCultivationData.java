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

    /* ================= BASIC ================= */

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
        this.linhKhi = Math.max(0, linhKhi);
    }

    /* ================= LINH KHÍ ================= */

    // alias để KHÔNG lỗi build
    public void addKhi(long amount) {
        addLinhKhi(amount);
    }

    public void addLinhKhi(long amount) {
        this.linhKhi += amount;
    }

    public void resetKhi() {
        resetLinhKhi();
    }

    public void resetLinhKhi() {
        this.linhKhi = 0;
    }

    public long getNeedLinhKhi() {
        return stage.maxLinhKhi;
    }

    /* ================= TU LUYỆN ================= */

    public boolean isCultivating() {
        return cultivating;
    }

    public void setCultivating(boolean cultivating) {
        this.cultivating = cultivating;
    }

    /* ================= BONUS (CHO COMBAT) ================= */

    // CombatListener đang dùng
    public double getBonusDamage() {
        return stage.ordinal() * 0.8;
    }

    public double getBonusHealth() {
        return stage.ordinal() * 2.5;
    }

    /* ================= DEBUG ================= */

    @Override
    public String toString() {
        return "PlayerCultivationData{" +
                "uuid=" + uuid +
                ", dao=" + dao +
                ", stage=" + stage +
                ", linhKhi=" + linhKhi +
                ", cultivating=" + cultivating +
                '}';
    }
}
