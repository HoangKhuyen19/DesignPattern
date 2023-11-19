package Presentation.Controller;

import Domain.hoaDonService;
import Domain.Model.hoaDon;

public class addCommand extends Command{

    public addCommand(hoaDonService hoaDonServiceRemote, hoaDon hoaDon) {
        super(hoaDonServiceRemote, hoaDon);
    }

    @Override
    public void execute() {
       hoaDonServiceRemote.addHoaDon(hoaDon);
    }
    
}
