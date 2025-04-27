package assignments.assignment4.frontend;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Pengguna;
import assignments.assignment4.frontend.staf.ui.StafHomePanel;
import assignments.assignment4.frontend.anggota.ui.AnggotaHomePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import static assignments.assignment4.backend.SistakaNG.getDaftarAnggota;
import static assignments.assignment4.backend.SistakaNG.getDaftarStaf;

// TODO: Implementasikan hal-hal yang diperlukan
public class LoginPanel extends SistakaPanel {
    boolean isLoginSuccess;
    public LoginPanel(HomeGUI main) {
        super(main);

        JLabel lblID = new JLabel("Masukkan ID Anda untuk login ke sistem");
        lblID.setFont(SistakaPanel.fontGeneral);
        lblID.setForeground(Color.black);
        JTextField tfID = new JTextField(16);
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(SistakaPanel.fontButton);
        btnLogin.setForeground(Color.blue);

        // km
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(40, 40, 40, 40));
        JPanel panel = new JPanel(new GridLayout(15, 1, 10, 10));
        panel.add(lblID);
        panel.add(tfID);
        panel.add(btnLogin);

        layout.add(panel);
        this.add(layout);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (tfID.getText().equals("")) {
                    isLoginSuccess = false;
                    JOptionPane.showMessageDialog(null, "Harap masukkan ID anda pada kotak di atas!",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    for (int i = 0; i < SistakaNG.getDaftarStaf().size(); i++) {
                        if (SistakaNG.getDaftarStaf().get(i).getId().equals(tfID.getText())) { // jika id yang dimasukkan benar
                            
                            main.setUser(SistakaNG.getDaftarStaf().get(i));
                            StafHomePanel.add("Selamat datang kembali " + SistakaNG.getDaftarStaf().get(i).getNama());
                            main.setPanel("staf");
                            isLoginSuccess = true;
                            break;
                        }
                    }
                    // jika yang login adalah anggota
                    for (int j = 0; j < SistakaNG.getDaftarAnggota().size(); j++) {
                        if (SistakaNG.getDaftarAnggota().get(j) != null) {
                            if (SistakaNG.getDaftarAnggota().get(j).getId().equals(tfID.getText())) { // jika id yang dimasukkan benar
                                main.setUser(SistakaNG.getDaftarAnggota().get(j));
                                AnggotaHomePanel.add("Selamat datang kembali " + SistakaNG.getDaftarAnggota().get(j).getNama());
                                main.setPanel("anggota");
                                isLoginSuccess = true;
                                break;
                            }
                        }
                    }
                    if (!isLoginSuccess) {
                        // jika id yang dimasukkan salah
                        JOptionPane.showMessageDialog(null, "Pengguna dengan ID " + tfID.getText() + " tidak ditemukan!",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });


    }

    @Override
    public void refresh() {

    }

}
