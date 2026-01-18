package me.tutien.cultivation;

import java.util.UUID;

public class PlayerCultivationData {

    private UUID uuid;

    private DaoType dao = DaoType.NONE;
    private RealmStage stage = RealmStage.PHAM_NHAN;

    private long linhKhi = 0;
    private boolean cultivating = false;

    public PlayerCultivationData(UUID uuid) {
        this.uuid = uuid;
    }

    /* =====================
       DAO – CẢNH GIỚI
       ===================== */
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

    /* =====================
       LINH KHÍ
       ===================== */
    public long getLinhKhi() {
        return linhKhi;
    }

    public void addLinhKhi(long amount) {
        this.linhKhi += amount;
    }

    // 👉 HÀM BỊ THIẾU – FIX LỖI BUILD
    public void setLinhKhi(long value) {
        this.linhKhi = value;
    }

    public long getNeedLinhKhi() {
        return stage.maxLinhKhi;
    }

    public boolean canDotPha() {
        return linhKhi >= stage.maxLinhKhi && stage.next() != null;
    }

    /* =====================
       TU LUYỆN
       ===================== */
    public boolean isCultivating() {
        return cultivating;
    }

    public void setCultivating(boolean cultivating) {
        this.cultivating = cultivating;
    }

    /* =====================
       ĐỘT PHÁ
       ===================== */
    public void dotPhaSuccess() {
        RealmStage next = stage.next();
        if (next != null) {
            stage = next;
            linhKhi = 0;
        }
    }

    /* =====================
       BONUS SỨC MẠNH
       ===================== */
    public double getBonusDamage() {
        return stage.ordinal() * 1.5;
    }

    public double getBonusHealth() {
        return stage.ordinal() * 2.0;
    }
}
