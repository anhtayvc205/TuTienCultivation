package me.tutien.cultivation.realm;

import me.tutien.cultivation.dao.DaoType;

public enum RealmStage {

    PHAM_NHAN(DaoType.CHINH_DAO, "Phàm Nhân", 100),
    LUYEN_KHI(DaoType.CHINH_DAO, "Luyện Khí", 1_000),
    TRUC_CO(DaoType.CHINH_DAO, "Trúc Cơ", 10_000),
    KET_DAN(DaoType.CHINH_DAO, "Kết Đan", 100_000),
    NGUYEN_ANH(DaoType.CHINH_DAO, "Nguyên Anh", 1_000_000),
    HOA_THAN(DaoType.CHINH_DAO, "Hóa Thần", 10_000_000),
    LUYEN_HU(DaoType.CHINH_DAO, "Luyện Hư", 100_000_000),
    HOP_THE(DaoType.CHINH_DAO, "Hợp Thể", 1_000_000_000L),
    DAI_THUA(DaoType.CHINH_DAO, "Đại Thừa", 10_000_000_000L),

    KIEM_TU(DaoType.KIEM_DAO, "Kiếm Tu", 1_000),
    KIEM_Y(DaoType.KIEM_DAO, "Kiếm Ý", 10_000),
    KIEM_TAM(DaoType.KIEM_DAO, "Kiếm Tâm", 100_000),
    KIEM_HON(DaoType.KIEM_DAO, "Kiếm Hồn", 1_000_000),
    KIEM_VUC(DaoType.KIEM_DAO, "Kiếm Vực", 10_000_000),
    KIEM_THAN(DaoType.KIEM_DAO, "Kiếm Thần", 100_000_000),

    MA_TU(DaoType.MA_DAO, "Ma Tu", 1_000),
    MA_KHI(DaoType.MA_DAO, "Ma Khí", 10_000),
    MA_DAN(DaoType.MA_DAO, "Ma Đan", 100_000),
    MA_ANH(DaoType.MA_DAO, "Ma Anh", 1_000_000),
    MA_VUC(DaoType.MA_DAO, "Ma Vực", 10_000_000),
    MA_THAN(DaoType.MA_DAO, "Ma Thần", 100_000_000);

    public final DaoType dao;
    public final String display;
    public final long maxLinhKhi;

    RealmStage(DaoType dao, String display, long max) {
        this.dao = dao;
        this.display = display;
        this.maxLinhKhi = max;
    }

    public RealmStage next() {
        RealmStage[] arr = values();
        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i] == this && arr[i + 1].dao == dao)
                return arr[i + 1];
        return null;
    }
}
