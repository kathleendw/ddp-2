package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PeminjamanPanel extends SistakaPanel {
    static JComboBox cbBuku;
    public PeminjamanPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        JLabel titleLabel = new JLabel("Pinjam Buku");
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        // membuat komponen Label, TextField, Dropdown Menu, dan Button
        JLabel lblBuku = new JLabel("Buku");
        lblBuku.setFont(SistakaPanel.fontGeneral);
        lblBuku.setForeground(Color.black);
        cbBuku = new JComboBox();
        cbBuku.setFont(SistakaPanel.fontGeneral);
        cbBuku.setForeground(Color.black);
        JLabel lblTanggalPeminjaman = new JLabel("Tanggal Peminjaman (DD/MM/YYYY)");
        lblTanggalPeminjaman.setFont(SistakaPanel.fontGeneral);
        lblTanggalPeminjaman.setForeground(Color.black);
        JTextField tfTanggalPeminjaman = new JTextField(16);
        tfTanggalPeminjaman.setFont(SistakaPanel.fontGeneral);
        tfTanggalPeminjaman.setForeground(Color.black);
        JLabel lblPinjam = new JLabel();
        lblPinjam.setFont(SistakaPanel.fontGeneral);
        lblPinjam.setForeground(Color.black);
        JButton btnPinjam = new JButton("Pinjam");
        btnPinjam.setFont(SistakaPanel.fontButton);
        btnPinjam.setForeground(Color.blue);
        JButton btnKembali = new JButton("Kembali");
        btnKembali.setFont(SistakaPanel.fontButton);
        btnKembali.setForeground(Color.blue);

        // km
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(40, 40, 40, 40));
        JPanel panel = new JPanel(new GridLayout(15, 1, 10, 10));
        panel.add(titleLabel);
        panel.add(lblBuku);
        panel.add(cbBuku);
        panel.add(lblTanggalPeminjaman);
        panel.add(tfTanggalPeminjaman);
        panel.add(lblPinjam);
        panel.add(btnPinjam);
        panel.add(btnKembali);

        layout.add(panel);
        this.add(layout);

        btnPinjam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cbBuku.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Silahkan memilih buku!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    String[] judulPenulis = cbBuku.getSelectedItem().toString().split(" oleh ");
                    Buku buku = SistakaNG.findBuku(judulPenulis[0], judulPenulis[1]);
                    lblPinjam.setText(SistakaNG.pinjamBuku(buku, tfTanggalPeminjaman.getText()));
                    JOptionPane.showOptionDialog(null,  lblPinjam, "Info", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,null,null);
                    main.setPanel("peminjaman");
                }
            }
        });

        btnKembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("anggota");
            }
        });
    }

    @Override
    public void refresh() {

    }
    public static void add(String item){
        cbBuku.addItem(item);
    }

}

