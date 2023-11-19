package Presentation.Controller;

import Domain.hoaDonService;

public class soLuongCommand extends Command{

    public soLuongCommand(hoaDonService hoaDonServiceRemote, Domain.Model.hoaDon hoaDon) {
        super(hoaDonServiceRemote, hoaDon);
    }

    @Override
    public void execute() {
        hoaDonServiceRemote.thongKeSoLuongHD();
    }
}
