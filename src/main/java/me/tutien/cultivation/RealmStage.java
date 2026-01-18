package me.tutien.cultivation;

public enum RealmStage {

    /* ============ PHÀM ============ */
    PHAM_NHAN(DaoType.CHINH_DAO, "Phàm Nhân", 100),

    /* ============ CHÍNH ĐẠO ============ */
    LUYEN_KHI(DaoType.CHINH_DAO, "Luyện Khí", 1_000),
    TRUC_CO(DaoType.CHINH_DAO, "Trúc Cơ", 10_000),
    KET_DAN(DaoType.CHINH_DAO, "Kết Đan", 100_000),
    NGUYEN_ANH(DaoType.CHINH_DAO, "Nguyên Anh", 1_000_000),
    HOA_THAN(DaoType.CHINH_DAO, "Hóa Thần", 10_000_000),
    LUYEN_HU(DaoType.CHINH_DAO, "Luyện Hư", 100_000_000),
    HOP_THE(DaoType.CHINH_DAO, "Hợp Thể", 1_000_000_000L),
    DAI_THUA(DaoType.CHINH_DAO, "Đại Thừa", 10_000_000_000L),
    CHAN_TIEN(DaoType.CHINH_DAO, "Chân Tiên", 100_000_000_000L),

    /* ============ KIẾM ĐẠO ============ */
    KIEM_TU(DaoType.KIEM_DAO, "Kiếm Tu", 2_000),
    KIEM_Y(DaoType.KIEM_DAO, "Kiếm Ý", 20_000),
    KIEM_TAM(DaoType.KIEM_DAO, "Kiếm Tâm", 200_000),
    KIEM_HON(DaoType.KIEM_DAO, "Kiếm Hồn", 2_000_000),
    KIEM_VUC(DaoType.KIEM_DAO, "Kiếm Vực", 20_000_000),
    KIEM_THAN(DaoType.KIEM_DAO, "Kiếm Thần", 200_000_000),
    KIEM_TIEN(DaoType.KIEM_DAO, "Kiếm Tiên", 2_000_000_000L),

    /* ============ MA ĐẠO ============ */
    MA_TU(DaoType.MA_DAO, "Ma Tu", 2_000),
    MA_KHI(DaoType.MA_DAO, "Ma Khí", 20_000),
    MA_DAN(DaoType.MA_DAO, "Ma Đan", 200_000),
    MA_ANH(DaoType.MA_DAO, "Ma Anh", 2_000_000),
    MA_VUC(DaoType.MA_DAO, "Ma Vực", 20_000_000),
    MA_THAN(DaoType.MA_DAO, "Ma Thần", 200_000_000),
    MA_TIEN(DaoType.MA_DAO, "Ma Tiên", 2_000_000_000L);

    /* ============ FIELD ============ */
    public final DaoType dao;
    public final String display;
    public final long need;

    RealmStage(DaoType dao, String display, long need) {
        this.dao = dao;
        this.display = display;
        this.need = need;
    }

    public long getNeedLinhKhi() {
        return need;
    }

    public RealmStage next() {
        RealmStage[] arr = values();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == this && arr[i + 1].dao == dao) {
                return arr[i + 1];
            }
        }
        return this;
    }

    public boolean isMax() {
        return next() == this;
    }
}
