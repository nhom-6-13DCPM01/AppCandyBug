package com.example.appcandybug.Other;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.util.Date;

public class NgayGiao {
    private Date ngayDat;
    private int ngayDuKien;
    private Date khungGioHoatDong;
    private Date gioCuaTongSoLuongDonTon;
    private long soLuongNhanVien;

    public NgayGiao() {
    }

    /**
     * Các bạn có dùng hàm tạo này để ghi số liệu
     * Lưu ý: Vì đây các param là kiểu Date nên các bạn phải tạo Date ... = new Date(); cho từng cái
     * @param ngayDat Ngày khách hàng đặt (Kiểu Date)
     * @param ngayDuKien Ngày dự kiến có thể giao (Kiểu int)
     * @param khungGioHoatDong Khung giờ hoạt động giao hàng. VD: 8 giờ - 22 giừ (Kiểu Date)
     *                         Lưu ý: chỉ nhập từ 0 - 23 không thể nhập 10PM
     * @param gioCuaTongSoLuongDonTon Tổng giờ để giao tất cả đơn hàng trong ngày đó đã được duyệt
     * @param soLuongNhanVien Số lượng nhân viên giao hàng
     *
     * @see #NgayGiao(int, int, int, int, int, int, long)
     */
    public NgayGiao(Date ngayDat, int ngayDuKien, Date khungGioHoatDong, Date gioCuaTongSoLuongDonTon, long soLuongNhanVien) {
        this.ngayDat = ngayDat;
        this.ngayDuKien = ngayDuKien;
        this.khungGioHoatDong = khungGioHoatDong;
        this.gioCuaTongSoLuongDonTon = gioCuaTongSoLuongDonTon;
        this.soLuongNhanVien = soLuongNhanVien;
    }


    /**
     * Các bạn có dùng hàm tạo này để ghi số liệu
     * Lưu ý: Vì đây các param là kiểu Date nên các bạn phải tạo Date ... = new Date(); cho từng cái
     * @param ngayDat Ngày khách hàng đặt (Kiểu Date)
     * @param ngayDuKien Ngày dự kiến có thể giao (Kiểu int)
     * @param gioBatDauGiao Giờ bắt đầu giao hàng. VD: Thời gian giao hàng từ 8 giờ (Kiểu Date)
     * @param gioKetThucGiao Giờ kết thúc giao hàng. VD: Thời gian giao hàng đến 22 giờ (Kiểu Date)
     * @param gioCuaTongSoLuongDonTon Tổng giờ để giao tất cả đơn hàng trong ngày đó đã được duyệt
     * @param soLuongNhanVien Số lượng nhân viên giao hàng
     * @see #NgayGiao(int, int, int, int, int, int, long)
     */
    public NgayGiao(Date ngayDat, int ngayDuKien, Date gioBatDauGiao, Date gioKetThucGiao, Date gioCuaTongSoLuongDonTon, long soLuongNhanVien) {
        this.ngayDat = ngayDat;
        this.ngayDuKien = ngayDuKien;
        this.khungGioHoatDong = tinhKhungGioHoatDong(gioBatDauGiao, gioKetThucGiao);
        this.gioCuaTongSoLuongDonTon = gioCuaTongSoLuongDonTon;
        this.soLuongNhanVien = soLuongNhanVien;
    }

    /**
     * Các bạn có dùng hàm tạo này để ghi số liệu
     * Lưu ý: Vì đây là nhập riêng lẻ ngày tháng năm nên các bạn hãy nhập cho đúng số liệu
     * @param ngayDat Ngày trong khoảng 1 - 31. Ngày khách hàng đặt
     * @param thangDat Tháng trong khoảng 0 - 11. Tháng khách hàng đặt
     * @param namDat Năm nhỏ nhất 1900. Năm khách hàng đặt
     * @param ngayDuKien Ngày trong khoảng 1 - 31. Ngày dự kiến có thể giao hàng đến cho khách
     * @param khungGioHoatDong - Khung giờ hoạt động giao hàng. VD: 8 giờ - 22 giừ (Kiểu int)
     *                           Lưu ý: chỉ nhập từ 0 - 23 không thể nhập 10PM
     * @param gioCuaTongSoLuongDonTon - Tổng giờ để giao tất cả đơn hàng trong ngày đó đã được duyệt
     * @param soLuongNhanVien - Số lượng nhân viên giao hàng
     * @see #NgayGiao(Date, int, Date, Date, long)
     */
    public NgayGiao(int ngayDat, int thangDat, int namDat, int ngayDuKien, int khungGioHoatDong, int gioCuaTongSoLuongDonTon, long soLuongNhanVien) {
        this.ngayDat = new Date(namDat, thangDat, ngayDat);
        this.ngayDuKien = ngayDuKien;
        this.khungGioHoatDong = new Date(0, 0, 0, khungGioHoatDong, 0);
        this.gioCuaTongSoLuongDonTon = new Date(0, 0, 0, gioCuaTongSoLuongDonTon, 0);
        this.soLuongNhanVien = soLuongNhanVien;
    }

    /**
     * Các bạn có dùng hàm tạo này để ghi số liệu
     * Lưu ý: Vì đây là nhập riêng lẻ ngày tháng năm nên các bạn hãy nhập cho đúng số liệu
     * @param ngayDat Ngày trong khoảng 1 - 31. Ngày khách hàng đặt
     * @param thangDat Tháng trong khoảng 0 - 11. Tháng khách hàng đặt
     * @param namDat Năm nhỏ nhất 1900. Năm khách hàng đặt
     * @param ngayDuKien Ngày trong khoảng 1 - 31. Ngày dự kiến có thể giao hàng đến cho khách
     * @param gioBatDauGiao Giờ bắt đầu giao hàng. VD: Thời gian giao hàng từ 8 giờ (Kiểu int)
     * @param gioKetThucGiao Giờ kết thúc giao hàng. VD: Thời gian giao hàng đến 22 giờ (Kiểu int)
     * @param gioCuaTongSoLuongDonTon Tổng giờ để giao tất cả đơn hàng trong ngày đó đã được duyệt
     * @param soLuongNhanVien Số lượng nhân viên giao hàng
     * @see #NgayGiao(Date, int, Date, Date, Date, long)
     */
    public NgayGiao(int ngayDat, int thangDat, int namDat, int ngayDuKien, int gioBatDauGiao, int gioKetThucGiao, int gioCuaTongSoLuongDonTon, long soLuongNhanVien) {
        this.ngayDat = new Date(namDat, thangDat, ngayDat);
        this.ngayDuKien = ngayDuKien;
        this.khungGioHoatDong = tinhKhungGioHoatDong(gioBatDauGiao, gioKetThucGiao);
        this.gioCuaTongSoLuongDonTon = new Date(0, 0, 0, gioCuaTongSoLuongDonTon, 0);
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

    public Date getKhungGioHoatDong() {
        return khungGioHoatDong;
    }

    public void setKhungGioHoatDong(Date khungGioHoatDong) {
        this.khungGioHoatDong = khungGioHoatDong;
    }

    public Date getGioCuaTongSoLuongDonTon() {
        return gioCuaTongSoLuongDonTon;
    }

    public void setGioCuaTongSoLuongDonTon(Date gioCuaTongSoLuongDonTon) {
        this.gioCuaTongSoLuongDonTon = gioCuaTongSoLuongDonTon;
    }

    public long getSoLuongNhanVien() {
        return soLuongNhanVien;
    }

    public void setSoLuongNhanVien(long soLuongNhanVien) {
        this.soLuongNhanVien = soLuongNhanVien;
    }

    /**
     * Hàm này dùng để tính ra khung giờ hoạt động nếu người dùng không biết
     * @param gioBatDauGiao Giờ bắt đầu giao hàng. VD: Thời gian giao hàng từ 8 giờ (Kiểu int)
     * @param gioKetThucGiao Giờ kết thúc giao hàng. VD: Thời gian giao hàng đến 22 giờ (Kiểu int)
     * @return Khung giờ hoạt động giao hàng (Kiểu Date)
     */
    protected Date tinhKhungGioHoatDong(int gioBatDauGiao, int gioKetThucGiao){
        Date batDau = new Date(0, 0, 0, gioBatDauGiao, 0);
        Date ketThuc = new Date(0, 0, 0, gioKetThucGiao, 0);

        int ketQua = (int) (ketThuc.getHours() - batDau.getHours());
        Date khungGioGiao = new Date(0, 0, 0, ketQua , 0);

        return khungGioGiao;
    }

    /**
     * Hàm này dùng để tính ra khung giờ hoạt động nếu người dùng không biết
     * @param gioBatDauGiao Giờ bắt đầu giao hàng. VD: Thời gian giao hàng từ 8 giờ (Kiểu Date)
     * @param gioKetThucGiao Giờ kết thúc giao hàng. VD: Thời gian giao hàng đến 22 giờ (Kiểu Date)
     * @return Khung giờ hoạt động giao hàng (Kiểu Date)
     */
    protected Date tinhKhungGioHoatDong(@NonNull Date gioBatDauGiao, @NonNull Date gioKetThucGiao){
        int ketQua = (int) (gioKetThucGiao.getHours() - gioBatDauGiao.getHours());
        Date khungGioGiao = new Date(0, 0, 0, ketQua , 0);

        return khungGioGiao;
    }

    protected Date tinhTongGioGiaoDonDangDuaTrenSoLuongNhanVien(int tongSoLuongDonHang){
        Date gioGiaoMotDonHang = new Date(0, 0, 0, 1, 0);

        long tongGioGiaoHetDonHang = (long)(((tongSoLuongDonHang * gioGiaoMotDonHang.getHours()) / (this.getSoLuongNhanVien())));


        Date ketQua = new Date(tongGioGiaoHetDonHang);

        return ketQua;
    }

    protected Date tinhNgayGiaoHang(int tongSoLuongDonHang, int maDonHang){
        int gioCongThem = ((maDonHang + 1) % (tongSoLuongDonHang + maDonHang)) * 60;
        Date ngayBatDauGiao = new Date(this.getNgayDat().getYear(), this.getNgayDat().getMonth(),this.getNgayDat().getDate() + this.getNgayDuKien(), this.getKhungGioHoatDong().getHours() + this.getGioCuaTongSoLuongDonTon().getHours() + this.tinhTongGioGiaoDonDangDuaTrenSoLuongNhanVien(tongSoLuongDonHang).getHours(), gioCongThem);

        return ngayBatDauGiao;
    }}
