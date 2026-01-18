package me.tutien.cultivation;

public enum RealmStage {

    // ================= CHÍNH ĐẠO =================
    PHAM_NHAN(DaoType.CHINH_DAO, "Phàm Nhân", 100),
    LUYEN_KHI(DaoType.CHINH_DAO, "Luyện Khí", 1_000),
    TRUC_CO(DaoType.CHINH_DAO, "Trúc Cơ", 10_000),
    KET_DAN(DaoType.CHINH_DAO, "Kết Đan", 100_000),
    NGUYEN_ANH(DaoType.CHINH_DAO, "Nguyên Anh", 1_000_000),
    HOA_THAN(DaoType.CHINH_DAO, "Hóa Thần", 10_000_000),
    LUYEN_HU(DaoType.CHINH_DAO, "Luyện Hư", 100_000_000),
    HOP_THE(DaoType.CHINH_DAO, "Hợp Thể", 1_000_000_000L),
    DAI_THUA(DaoType.CHINH_DAO, "Đại Thừa", 10_000_000_000L),

    // ================= KIẾM TU =================
    KIEM_SI(DaoType.KIEM_DAO, "Kiếm Sĩ", 1_000),
    KIEM_KHI(DaoType.KIEM_DAO, "Kiếm Khí", 10_000),
    KIEM_Y(DaoType.KIEM_DAO, "Kiếm Ý", 100_000),
    KIEM_TAM(DaoType.KIEM_DAO, "Kiếm Tâm", 1_000_000),
    KIEM_HON(DaoType.KIEM_DAO, "Kiếm Hồn", 10_000_000),
    KIEM_VUC(DaoType.KIEM_DAO, "Kiếm Vực", 100_000_000),
    KIEM_THAN(DaoType.KIEM_DAO, "Kiếm Thần", 1_000_000_000L),

    // ================= MA TU =================
    MA_NHAN(DaoType.MA_DAO, "Ma Nhân", 1_000),
    MA_KHI(DaoType.MA_DAO, "Ma Khí", 10_000),
    MA_DAN(DaoType.MA_DAO, "Ma Đan", 100_000),
    MA_ANH(DaoType.MA_DAO, "Ma Anh", 1_000_000),
    MA_TON(DaoType.MA_DAO, "Ma Tôn", 10_000_000),
    MA_VUC(DaoType.MA_DAO, "Ma Vực", 100_000_000),
    MA_DE(DaoType.MA_DAO, "Ma Đế", 1_000_000_000L);

    // ===================================================

    public final DaoType dao;
    public final String display;
    public final long maxLinhKhi;

    RealmStage(DaoType dao, String display, long maxLinhKhi) {
        this.dao = dao;
        this.display = display;
        this.maxLinhKhi = maxLinhKhi;
    }

    /** lấy cảnh giới kế tiếp CÙNG đạo */
    public RealmStage next() {
        RealmStage[] arr = values();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == this && arr[i + 1].dao == this.dao) {
                return arr[i + 1];
            }
        }
        return null;
    }

    /** cảnh giới đầu tiên của mỗi đạo */
    public static RealmStage first(DaoType dao) {
        for (RealmStage r : values())
            if (r.dao == dao)
                return r;
        return PHAM_NHAN;
    }
}
