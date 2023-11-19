package pesistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import Domain.Model.hoaDon;
import Domain.Model.hoaDonGio;
import Domain.Model.hoaDonNgay;

public class hoaDonDAOImpl implements HoaDonDAO{
    private Connection connection;
    public hoaDonDAOImpl(){
        String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=quanLyHoaDon";
        String username = "sa";
        String password = "1932003";
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addHoaDon(hoaDon hoaDon) {
        String sql = "INSERT INTO sqlHoaDon(ngayHoaDon,tenKhachHang,maPhong,donGia,soGioThue,soNgayThue,thanhTien) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            Date sqlDate = new Date(hoaDon.getNgayHoaDon().getTime());
            statement.setDate(1, sqlDate);
            statement.setString(2, hoaDon.getTenKhachHang());
            statement.setInt(3, hoaDon.getMaPhong());
            statement.setDouble(4, hoaDon.getDonGia());
            if(hoaDon instanceof hoaDonGio){
                int soGioThue = ((hoaDonGio)hoaDon).getSoGioThue();
                statement.setInt(5,soGioThue);
                statement.setNull(6, Types.INTEGER);
            }
            else if(hoaDon instanceof hoaDonNgay){
                int soNgayThue = ((hoaDonNgay)hoaDon).getSoNgayThue();
                statement.setNull(5, Types.INTEGER);
                statement.setInt(6,soNgayThue);
            }
            statement.setDouble(7, hoaDon.getThanhTien());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateHoaDon(hoaDon hoaDon) {
        String sql = "UPDATE sqlHoaDon set ngayHoaDon=?, tenKhachHang = ?,maPhong = ?,donGia =?,soGioThue = ?,soNgayThue =?,thanhTien =? WHERE maHoaDon=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            Date sqlDate = new Date(hoaDon.getNgayHoaDon().getTime());
            statement.setDate(1, sqlDate);
            statement.setString(2, hoaDon.getTenKhachHang());
            statement.setInt(3, hoaDon.getMaPhong());
            statement.setDouble(4, hoaDon.getDonGia());
            if(hoaDon instanceof hoaDonGio){
                int soGioThue = ((hoaDonGio)hoaDon).getSoGioThue();
                statement.setInt(5,soGioThue);
                statement.setNull(6, Types.INTEGER);
            }
            else if(hoaDon instanceof hoaDonNgay){
                int soNgayThue = ((hoaDonNgay)hoaDon).getSoNgayThue();
                statement.setNull(5, Types.INTEGER);
                statement.setInt(6,soNgayThue);
            }
            statement.setDouble(7,hoaDon.getThanhTien());
            statement.setInt(8, hoaDon.getMaHoaDon());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteHoaDon(int hoaDonID) {
        String sql = "DELETE FROM sqlHoaDon WHERE maHoaDon = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,hoaDonID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public hoaDon findHoaDon(int hoaDonID) {
        String sql = "SELECT * FROM  sqlHoaDon WHERE maHoaDon = ?"; 
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hoaDonID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int maHoaDon = resultSet.getInt("maHoaDon");
                Date ngayHoaDon = resultSet.getDate("ngayHoaDon");
                String tenKhachHang = resultSet.getString("tenKhachHang");
                int maPhong = resultSet.getInt("maPhong");
                double donGia = resultSet.getDouble("donGia");
                int soGioThue = resultSet.getInt("soGioThue");
                int soNgayThue = resultSet.getInt("soNgayThue");
                double thanhTien = resultSet.getDouble("thanhTien");
                if(soGioThue == 0){
                    return new hoaDonNgay(maHoaDon, ngayHoaDon, tenKhachHang, maPhong, donGia, soNgayThue,thanhTien);
                }else{
                    return new hoaDonGio(maHoaDon, ngayHoaDon,tenKhachHang, maPhong,donGia, soGioThue,thanhTien);
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<hoaDon> loadHoaDon() {
        List<hoaDon> listHoaDons = new ArrayList<>();
        String sql = "SELECT * FROM sqlHoaDon";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int maHoaDon = resultSet.getInt("maHoaDon");
                Date ngayHoaDon = resultSet.getDate("ngayHoaDon");
                String tenKhachHang = resultSet.getString("tenKhachHang");
                int maPhong = resultSet.getInt("maPhong");
                double donGia = resultSet.getDouble("donGia");
                int soGioThue = resultSet.getInt("soGioThue");
                int soNgayThue = resultSet.getInt("soNgayThue");
                double thanhTien = resultSet.getDouble("thanhTien");
                if(soGioThue == 0){
                    listHoaDons.add(new hoaDonNgay(maHoaDon, ngayHoaDon,tenKhachHang, maPhong,donGia, soNgayThue,thanhTien));
                }
                else{
                    listHoaDons.add(new hoaDonGio(maHoaDon, ngayHoaDon,tenKhachHang, maPhong,donGia, soGioThue,thanhTien));
                }
                
            }
        } catch (Exception e) {
        }
        return listHoaDons;
    }
}
