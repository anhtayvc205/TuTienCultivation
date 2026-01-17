package me.tutien.cultivation;

public class PlayerCultivationData {

    private RealmStage stage = RealmStage.LUYEN_KHI;
    private int linhKhi = 0;
    private boolean cultivating = false;

    public boolean isCultivating() { return cultivating; }
    public void setCultivating(boolean b) { cultivating = b; }

    public RealmStage getStage() { return stage; }
    public int getLinhKhi() { return linhKhi; }

    public void addKhi() {
        linhKhi = Math.min(stage.maxKhi, linhKhi + 1);
    }

    public boolean canDotPha() {
        return linhKhi >= stage.maxKhi;
    }

    public void dotPha() {
        RealmStage next = stage.next();
        if (next != null) {
            stage = next;
            linhKhi = 0;
        }
    }

    /* ====== STAT ====== */
    public double getBonusHealth() {
        return stage.ordinal() * 4.0;
    }

    public double getBonusDamage() {
        return stage.ordinal() * 1.0;
    }
}
