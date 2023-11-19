package Presentation.Controller;

import Domain.hoaDonService;
import Domain.Model.hoaDon;

public class findCommand extends Command{
    private int id;
    public findCommand(hoaDonService hoaDonServiceRemote,hoaDon hoaDon,int id) {
        super(hoaDonServiceRemote, hoaDon);
        this.id = id;
    }
    @Override
    public void execute() {
        hoaDonServiceRemote.findHoaDon(id);
    }
}
