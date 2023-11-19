package Domain.Model;

import java.util.Date;

public class hoaDonNgay extends hoaDon{
    //Field
    private int soNgayThue;
    //Constructor
    public hoaDonNgay(int maHoaDon, Date ngayHoaDon, String tenKhachHang,int maPhong, double donGia,int soNgayThue,double thanhTien) {
        super(maHoaDon, ngayHoaDon,tenKhachHang,maPhong, donGia,thanhTien);
        this.soNgayThue = soNgayThue;
        this.thanhTien = thanhTien();
    }
    //Methods
    @Override
    public double thanhTien() {
        double thanhTien;
        if(soNgayThue>0 && soNgayThue <=7){
            thanhTien = soNgayThue * donGia;
        }
        else{
            thanhTien = 7 * donGia + (soNgayThue-7)*donGia*0.8 ;
        }
        return thanhTien;
    }
    public int getSoNgayThue() {
        return soNgayThue;
    }
    public void setSoNgayThue(int soNgayThue) {
        this.soNgayThue = soNgayThue;
    }
    
}
