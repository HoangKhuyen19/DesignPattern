package Main;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Presentation.GUI.giaoDien;

public class TestDrive {
    public static void main(String[] args) {
        try 
        {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
            {
                if ("Nimbus".equals(info.getName())) 
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        giaoDien giaoDienRemote = new giaoDien();
        giaoDienRemote.setVisible(true);
    }
}
