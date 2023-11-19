package Domain;

import java.util.List;

import Domain.Model.hoaDon;

public interface hoaDonService {
    void addHoaDon(hoaDon hoaDon);
    void updateHoaDon(hoaDon hoaDon);
    void deleteHoaDon(int hoaDonID);
    hoaDon findHoaDon(int hoaDonID);
    List<hoaDon> loadHoaDon();
    void thongKeSoLuongHD();
    double trungBinhThanhTien(int month,int year);
}   
