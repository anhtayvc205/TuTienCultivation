package me.tutien.cultivation;

public enum RealmStage {
    LUYEN_KHI(100),
    TRUC_CO(200),
    KET_DAN(300);

    public final int maxKhi;

    RealmStage(int maxKhi) {
        this.maxKhi = maxKhi;
    }

    public RealmStage next() {
        RealmStage[] arr = values();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == this) return arr[i + 1];
        }
        return null;
    }
}
