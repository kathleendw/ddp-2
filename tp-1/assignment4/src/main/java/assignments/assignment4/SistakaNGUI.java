package assignments.assignment4;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.*;
import assignments.assignment4.frontend.HomeGUI;
import java.awt.event.*;
import javax.swing.*;

public class SistakaNGUI {
    public static void main(String[] args) {
        // Membuat Frame
        JFrame frame = new JFrame();
        HomeGUI home = new HomeGUI(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SistakaNG");
        SistakaNG.registerStaf();

        // TODO: Tambahkan hal-hal lain yang diperlukan
        new HomeGUI(frame);
        frame.setVisible(true);
    }

}

