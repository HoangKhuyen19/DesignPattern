package Presentation.Controller;

import Domain.hoaDonService;
import Domain.Model.hoaDon;

public class deleteCommand extends Command{
    private int id;
    public deleteCommand(hoaDonService hoaDonServiceRemote,hoaDon hoaDon,int id) {
        super(hoaDonServiceRemote, hoaDon);
        this.id = id;
    }

    @Override
    public void execute() {
        hoaDonServiceRemote.deleteHoaDon(id);
    }
}