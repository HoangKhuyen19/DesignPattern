package Presentation.Controller;

import Domain.hoaDonService;
import Domain.Model.hoaDon;

public class tbThanhTienCommand extends Command{
    private int month,year;
    public tbThanhTienCommand(hoaDonService hoaDonServiceRemote, hoaDon hoaDon,int month,int year) {
        super(hoaDonServiceRemote, hoaDon);
        this.month = month;
        this.year = year;
    }

    @Override
    public void execute() {
        hoaDonServiceRemote.trungBinhThanhTien(month,year);
    }
    
}
