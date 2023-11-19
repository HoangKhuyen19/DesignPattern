package Domain;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Observer.Publisher;
import Domain.Model.hoaDon;
import Domain.Model.hoaDonGio;
import pesistence.HoaDonDAO;
import pesistence.hoaDonDAOImpl;


public class hoaDonServiceImpl extends Publisher implements hoaDonService {
    private HoaDonDAO hoaDonDAO;
    private List<hoaDon> hoaDons = new ArrayList<>();
    private int countGio = 0,countNgay = 0;
    private double trungBinhThanhTien;
    public hoaDonServiceImpl(){
        hoaDonDAO = new hoaDonDAOImpl();
    }
    @Override
    public void addHoaDon(hoaDon hoaDon) {
        hoaDonDAO.addHoaDon(hoaDon);
    }

    @Override
    public void updateHoaDon(hoaDon hoaDon) {
        hoaDonDAO.updateHoaDon(hoaDon);
    }

    @Override
    public void deleteHoaDon(int hoaDonID) {
        hoaDonDAO.deleteHoaDon(hoaDonID);
    }

    @Override
    public hoaDon findHoaDon(int hoaDonID) {
        hoaDons = new ArrayList<>();
        hoaDon hoaDon = hoaDonDAO.findHoaDon(hoaDonID);
        try {
            hoaDons.add(hoaDon);
            notifySubscribers();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy hoá đơn có ID: "+hoaDonID);
            loadHoaDon();
        }
        return hoaDon ;
    }
    @Override
    public List<hoaDon> loadHoaDon() {
        hoaDons = hoaDonDAO.loadHoaDon();
        notifySubscribers();
        return hoaDons;
    }
    public void thongKeSoLuongHD(){
        for (hoaDon hoaDon : hoaDons) {
            if(hoaDon instanceof hoaDonGio){
                countGio++;
            }
            else{
                countNgay++;
            } 
        }   
        notifySubscribers();
        countGio = 0;
        countNgay = 0;
    }
    public double trungBinhThanhTien(int month,int year){
        int soHoaDon = 0;
        int tongThanhTien = 0;
        List<hoaDon> hoaDonsTam = new ArrayList<>();
        for (hoaDon hoaDon : hoaDons) {
            if(hoaDon.getNgayHoaDon().getMonth() + 1 == month && hoaDon.getNgayHoaDon().getYear()+1900 == year){
                hoaDonsTam.add(hoaDon);
                tongThanhTien += hoaDon.thanhTien();
                soHoaDon++;
            }
        }
        if(soHoaDon > 0){
            trungBinhThanhTien = tongThanhTien/soHoaDon;
        }
        else{
            trungBinhThanhTien = 0;
        }
        hoaDons = hoaDonsTam;
        notifySubscribers();
        return trungBinhThanhTien;
    }
    public List<hoaDon> getHoaDons() {
        return hoaDons;
    }
    public int getCountGio() {
        return countGio;
    }
    public int getCountNgay() {
        return countNgay;
    }
    public double getTrungBinhThanhTien() {
        return trungBinhThanhTien;
    }
}
