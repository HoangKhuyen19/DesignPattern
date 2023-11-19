package pesistence;

import java.util.List;

import Domain.Model.hoaDon;

public interface HoaDonDAO {
    void addHoaDon(hoaDon hoaDonGio);
    void updateHoaDon(hoaDon hoaDonGio);
    void deleteHoaDon(int hoaDonID);
    hoaDon findHoaDon(int hoaDonID);
    List<hoaDon> loadHoaDon();
}
