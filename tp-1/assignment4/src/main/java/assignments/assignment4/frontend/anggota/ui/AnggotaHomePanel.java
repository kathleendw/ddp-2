package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Implementasikan hal-hal yang diperlukan
public class AnggotaHomePanel extends SistakaPanel {
    static JLabel titleLabel;
    public AnggotaHomePanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        titleLabel = new JLabel();
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        // membuat komponen Button
        JButton btnPeminjaman = new JButton("Peminjaman");
        btnPeminjaman.setFont(SistakaPanel.fontButton);
        btnPeminjaman.setForeground(Color.blue);
        JButton btnPengembalian = new JButton("Pengembalian");
        btnPengembalian.setFont(SistakaPanel.fontButton);
        btnPengembalian.setForeground(Color.blue);
        JButton btnPembayaranDenda = new JButton("Pembayaran Denda");
        btnPembayaranDenda.setFont(SistakaPanel.fontButton);
        btnPembayaranDenda.setForeground(Color.blue);
        JButton btnDetailUser = new JButton("Detail Anggota");
        btnDetailUser.setFont(SistakaPanel.fontButton);
        btnDetailUser.setForeground(Color.blue);
        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(SistakaPanel.fontButton);
        btnLogout.setForeground(Color.blue);

        // km
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(40, 40, 40, 40));
        JPanel panel = new JPanel(new GridLayout(15, 1, 10, 10));
        panel.add(titleLabel);
        panel.add(btnPeminjaman);
        panel.add(btnPengembalian);
        panel.add(btnPembayaranDenda);
        panel.add(btnDetailUser);
        panel.add(btnLogout);

        layout.add(panel);
        this.add(layout);

        btnPeminjaman.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("peminjaman");
            }
        });

        btnPengembalian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("pengembalian");
            }
        });

        btnPembayaranDenda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("pembayaran");
            }
        });

        btnDetailUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("detailUser");
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("login");
            }
        });
    }
    @Override
    public void refresh() {

    }
    public static void add(String string){
        titleLabel.setText(string);
    }


}
