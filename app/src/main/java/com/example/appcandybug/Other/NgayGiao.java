package com.example.appcandybug.Other;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class NgayGiao {
    private Date ngayDat;
    private int ngayDuKien;
    private int gioBatDauGiao;
    private int gioKetThucGiao;
    private int tongSoLuongDonTon;
    private int soLuongNhanVien;

    public NgayGiao() {
    }

    /**
     * Các bạn có dùng hàm tạo này để ghi số liệu
     * Lưu ý: Vì đây là nhập riêng lẻ ngày tháng năm nên các bạn hãy nhập cho đúng số liệu
     * @param ngayDat Ngày trong khoảng 1 - 31. Ngày khách tạo order
     * @param ngayDuKien Ngày trong khoảng 1 - 31. Ngày dự kiến có thể giao hàng đến cho khách
     * @param gioBatDauGiao Giờ bắt đầu giao hàng. VD: Thời gian giao hàng từ 8 giờ (Kiểu int)
     * @param gioKetThucGiao Giờ kết thúc giao hàng. VD: Thời gian giao hàng đến 22 giờ (Kiểu int)
     * @param tongSoLuongDonTon Tổng giờ để giao tất cả đơn hàng trong ngày đó đã được duyệt
     * @param soLuongNhanVien Số lượng nhân viên giao hàng
     */
    public NgayGiao(Date ngayDat, int ngayDuKien, int gioBatDauGiao, int gioKetThucGiao, int tongSoLuongDonTon, int soLuongNhanVien) {
        this.ngayDat = ngayDat;
        this.ngayDuKien = ngayDuKien + 2;
        this.gioBatDauGiao = gioBatDauGiao;
        this.gioKetThucGiao = gioKetThucGiao;
        this.tongSoLuongDonTon = tongSoLuongDonTon;
        this.soLuongNhanVien = soLuongNhanVien;
    }

    /**
     * Các bạn có dùng hàm tạo này để ghi số liệu
     * Lưu ý: Vì đây là nhập riêng lẻ ngày tháng năm nên các bạn hãy nhập cho đúng số liệu
     * @param ngayDat Ngày trong khoảng 1 - 31. Ngày khách hàng tạo order
     * @param thangDat Tháng trong khoảng 0 - 11. Tháng khách hàng tạo order
     * @param namDat Năm nhỏ nhất 1900. Năm khách hàng tạo order
     * @param ngayDuKien Ngày trong khoảng 1 - 31. Ngày dự kiến có thể giao hàng đến cho khách
     * @param gioBatDauGiao Giờ bắt đầu giao hàng. VD: Thời gian giao hàng từ 8 giờ (Kiểu int)
     * @param gioKetThucGiao Giờ kết thúc giao hàng. VD: Thời gian giao hàng đến 22 giờ (Kiểu int)
     * @param tongSoLuongDonTon Tổng giờ để giao tất cả đơn hàng trong ngày đó đã được duyệt
     * @param soLuongNhanVien Số lượng nhân viên giao hàng
     */
    public NgayGiao(int ngayDat, int thangDat, int namDat, int ngayDuKien, int gioBatDauGiao, int gioKetThucGiao, int tongSoLuongDonTon, int soLuongNhanVien) {
        this.ngayDat = new Date(namDat, thangDat, ngayDat);
        this.ngayDuKien = ngayDuKien + 2;
        this.gioBatDauGiao = gioBatDauGiao;
        this.gioKetThucGiao = gioKetThucGiao;
        this.tongSoLuongDonTon = tongSoLuongDonTon;
        this.soLuongNhanVien = soLuongNhanVien;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public int getNgayDuKien() {
        return ngayDuKien;
    }

    public void setNgayDuKien(int ngayDuKien) {
        this.ngayDuKien = ngayDuKien;
    }

    public int getGioBatDauGiao() {
        return gioBatDauGiao;
    }

    public void setGioBatDauGiao(int gioBatDauGiao) {
        this.gioBatDauGiao = gioBatDauGiao;
    }

    public int getGioKetThucGiao() {
        return gioKetThucGiao;
    }

    public void setGioKetThucGiao(int gioKetThucGiao) {
        this.gioKetThucGiao = gioKetThucGiao;
    }

    public int getTongSoLuongDonTon() {
        return tongSoLuongDonTon;
    }

    public void setTongSoLuongDonTon(int tongSoLuongDonTon) {
        this.tongSoLuongDonTon = tongSoLuongDonTon;
    }

    public int getSoLuongNhanVien() {
        return soLuongNhanVien;
    }

    public void setSoLuongNhanVien(int soLuongNhanVien) {
        this.soLuongNhanVien = soLuongNhanVien;
    }

    /**
     * Hàm này dùng để tính ra khung giờ hoạt động nếu người dùng không biết
     * @param gioBatDauGiao Giờ bắt đầu giao hàng. VD: Thời gian giao hàng từ 8 giờ (Kiểu int)
     * @param gioKetThucGiao Giờ kết thúc giao hàng. VD: Thời gian giao hàng đến 22 giờ (Kiểu int)
     * @return Khung giờ hoạt động giao hàng (Kiểu int)
     */
    protected int tinhKhungGioHoatDong(int gioBatDauGiao, int gioKetThucGiao){
        Date batDau = new Date(0, 0, 0, gioBatDauGiao, 0);
        Date ketThuc = new Date(0, 0, 0, gioKetThucGiao, 0);

        int ketQua = (int) (ketThuc.getHours() - batDau.getHours());Date khungGioGiao = new Date(0, 0, 0, ketQua , 0);

        return ketQua;
    }

    /**
     * Hàm này dùng để tính ra khung giờ hoạt động nếu người dùng không biết
     * @param gioBatDauGiao Giờ bắt đầu giao hàng. VD: Thời gian giao hàng từ 8 giờ (Kiểu Date)
     * @param gioKetThucGiao Giờ kết thúc giao hàng. VD: Thời gian giao hàng đến 22 giờ (Kiểu Date)
     * @return Khung giờ hoạt động giao hàng (Kiểu int)
     */
    protected int tinhKhungGioHoatDong(@NonNull Date gioBatDauGiao, @NonNull Date gioKetThucGiao){
        int ketQua = (int) (gioKetThucGiao.getHours() - gioBatDauGiao.getHours());

        return ketQua;
    }

    public Date tinhNgayGiaoHang(int maDonHang){
        int gioCuaTongSoLuongDonTon = this.getTongSoLuongDonTon() / this.getSoLuongNhanVien();
        int gioCongThem =  gioCuaTongSoLuongDonTon + (maDonHang - (maDonHang % this.getSoLuongNhanVien()));
        Date ngayBatDauGiao = new Date(this.getNgayDat().getYear(), this.getNgayDat().getMonth(), this.getNgayDuKien() + this.getNgayDat().getDate(), 0, gioCongThem);

        return ngayBatDauGiao;
    }
}
