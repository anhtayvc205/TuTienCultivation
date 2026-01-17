package me.tutien.cultivation.realm;

import me.tutien.cultivation.dao.DaoType;

public enum RealmStage {

    /* ================= CHÍNH ĐẠO ================= */
    PHÀM_NHÂN(DaoType.CHINH_DAO, "Phàm Nhân", 80),
    LUYỆN_KHÍ(DaoType.CHINH_DAO, "Luyện Khí", 160),
    TRÚC_CƠ(DaoType.CHINH_DAO, "Trúc Cơ", 300),
    KẾT_ĐAN(DaoType.CHINH_DAO, "Kết Đan", 550),
    NGUYÊN_ANH(DaoType.CHINH_DAO, "Nguyên Anh", 900),
    HÓA_THẦN(DaoType.CHINH_DAO, "Hóa Thần", 1400),
    LUYỆN_HƯ(DaoType.CHINH_DAO, "Luyện Hư", 2000),
    HỢP_THỂ(DaoType.CHINH_DAO, "Hợp Thể", 2800),
    ĐẠI_THỪA(DaoType.CHINH_DAO, "Đại Thừa", 3800),

    /* ================= KIẾM TU ================= */
    KIẾM_TU_PHÀM(DaoType.KIEM_DAO, "Kiếm Tu Phàm", 120),
    KIẾM_Ý_SƠ_KHAI(DaoType.KIEM_DAO, "Kiếm Ý Sơ Khai", 260),
    KIẾM_TÂM(DaoType.KIEM_DAO, "Kiếm Tâm", 480),
    KIẾM_HỒN(DaoType.KIEM_DAO, "Kiếm Hồn", 800),
    KIẾM_VỰC(DaoType.KIEM_DAO, "Kiếm Vực", 1300),
    KIẾM_ĐẠO(DaoType.KIEM_DAO, "Kiếm Đạo", 2000),
    KIẾM_THẦN(DaoType.KIEM_DAO, "Kiếm Thần", 3200),

    /* ================= MA TU ================= */
    MA_TU_PHÀM(DaoType.MA_DAO, "Ma Tu Phàm", 120),
    MA_KHÍ(DaoType.MA_DAO, "Ma Khí", 280),
    HUYẾT_ẢNH(DaoType.MA_DAO, "Huyết Ảnh", 520),
    MA_ĐAN(DaoType.MA_DAO, "Ma Đan", 900),
    MA_ANH(DaoType.MA_DAO, "Ma Anh", 1500),
    MA_VỰC(DaoType.MA_DAO, "Ma Vực", 2300),
    MA_THẦN(DaoType.MA_DAO, "Ma Thần", 3600);

    public final DaoType dao;
    public final String displayName;
    public final int maxLinhKhi;

    RealmStage(DaoType dao, String name, int max) {
        this.dao = dao;
        this.displayName = name;
        this.maxLinhKhi = max;
    }

    /* ================= NEXT STAGE ================= */
    public RealmStage next() {
        RealmStage[] arr = values();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == this && arr[i + 1].dao == dao) {
                return arr[i + 1];
            }
        }
        return null;
    }
    }
