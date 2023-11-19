package Presentation.Controller;

import java.util.ArrayList;
import java.util.List;

import Domain.hoaDonService;
import Domain.Model.hoaDon;

public class loadHDCommand extends Command{
    private List<hoaDon> listHoaDons = new ArrayList<>();
    public loadHDCommand(hoaDonService hoaDonServiceRemote,hoaDon hoaDon) {
        super(hoaDonServiceRemote, hoaDon);
    }

    @Override
    public void execute() {
        listHoaDons.addAll(hoaDonServiceRemote.loadHoaDon());
    }
}
