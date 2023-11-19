package Presentation.Controller;

import Domain.hoaDonService;
import Domain.Model.hoaDon;

public class updateCommand extends Command{

    public updateCommand(hoaDonService hoaDonServiceRemote,hoaDon hoaDon) {
        super(hoaDonServiceRemote, hoaDon);
    }

    @Override
    public void execute() {
        hoaDonServiceRemote.updateHoaDon(hoaDon);
    }
    
}
