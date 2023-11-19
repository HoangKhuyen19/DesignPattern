package Domain.Model;

import java.util.Date;

public abstract class hoaDon{
    //FIELD
    protected int maHoaDon;
    protected Date ngayHoaDon;
    private String tenKhachHang;
    protected int maPhong;
    protected double donGia;
    protected double thanhTien;
    //CONSTRUCTOR
    public hoaDon(){}
    public hoaDon(int maHoaDon, Date ngayHoaDon,String tenKhachHang, int maPhong, double donGia,double thanhTien) {
        this.maHoaDon = maHoaDon;
        this.ngayHoaDon = ngayHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.maPhong = maPhong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }
    //METHODS
    public abstract double thanhTien();
    public int getMaHoaDon() {
        return maHoaDon;
    }
    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    public Date getNgayHoaDon() {
        return ngayHoaDon;
    }
    public void setNgayHoaDon(Date ngayHoaDon) {
        this.ngayHoaDon = ngayHoaDon;
    }
    public int getMaPhong() {
        return maPhong;
    }
    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }
    public double getDonGia() {
        return donGia;
    }
    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    public String getTenKhachHang() {
        return tenKhachHang;
    }
    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }
    public double getThanhTien() {
        return thanhTien;
    }
    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
}
