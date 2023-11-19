package Domain.Model;

import java.util.Date;

public class hoaDonGio extends hoaDon{
    //FIELD
    private int soGioThue;
    //CONSTRUCTOR
    public hoaDonGio(){}
    public hoaDonGio(int maHoaDon, Date ngayHoaDon,String tenKhachHang, int maPhong, double donGia,int soGioThue,double thanhTien) {
        super(maHoaDon, ngayHoaDon,tenKhachHang, maPhong, donGia,thanhTien);
        this.soGioThue = soGioThue;
        this.thanhTien = thanhTien();
    }
    //METHODS
    @Override
    public double thanhTien() {
        double thanhTien;
        if(soGioThue<=24){
            thanhTien = soGioThue * donGia;
        }
        else if(soGioThue>24 && soGioThue <=30){
            thanhTien = 24*donGia;
        }
        else{
            return thanhTien = 0;
        }
        return thanhTien;
    }
    public int getSoGioThue() {
        return soGioThue;
    }
    public void setSoGioThue(int soGioThue) {
        this.soGioThue = soGioThue;
    }
}
