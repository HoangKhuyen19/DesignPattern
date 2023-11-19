package Presentation.Controller;

import Domain.hoaDonService;
import Domain.Model.hoaDon;

public abstract class Command {
    protected hoaDonService hoaDonServiceRemote;
    protected hoaDon hoaDon;
    public Command(hoaDonService hoaDonServiceRemote,hoaDon hoaDon){
        this.hoaDonServiceRemote = hoaDonServiceRemote;
        this.hoaDon = hoaDon;
    }
    public abstract void execute();
}
