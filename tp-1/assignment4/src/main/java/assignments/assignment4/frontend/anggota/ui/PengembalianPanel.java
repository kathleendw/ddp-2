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
import java.util.List;

// TODO: Implementasikan hal-hal yang diperlukan
public class PengembalianPanel extends SistakaPanel {
    static JComboBox cbBuku;
    public PengembalianPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        JLabel titleLabel = new JLabel("Pengembalian Buku");
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        // membuat komponen Label, TextField, Dropdown Menu, dan Button
        JLabel lblBuku = new JLabel("Buku");
        lblBuku.setFont(SistakaPanel.fontGeneral);
        lblBuku.setForeground(Color.black);
        cbBuku = new JComboBox();
        cbBuku.setFont(SistakaPanel.fontGeneral);
        cbBuku.setForeground(Color.black);
        JLabel lblTanggalPengembalian = new JLabel("Tanggal Pengembalian (DD/MM/YYYY)");
        lblTanggalPengembalian.setFont(SistakaPanel.fontGeneral);
        lblTanggalPengembalian.setForeground(Color.black);
        JTextField tfTanggalPengembalian = new JTextField(16);
        tfTanggalPengembalian.setFont(SistakaPanel.fontGeneral);
        tfTanggalPengembalian.setForeground(Color.black);
        JLabel lblKembalikan = new JLabel();
        lblKembalikan.setFont(SistakaPanel.fontGeneral);
        lblKembalikan.setForeground(Color.black);
        JButton btnKembalikan = new JButton("Kembalikan");
        btnKembalikan.setFont(SistakaPanel.fontButton);
        btnKembalikan.setForeground(Color.blue);
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
        panel.add(lblTanggalPengembalian);
        panel.add(tfTanggalPengembalian);
        panel.add(lblKembalikan);
        panel.add(btnKembalikan);
        panel.add(btnKembali);

        layout.add(panel);
        this.add(layout);

        btnKembalikan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cbBuku.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Silahkan memilih buku!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    String[] judulPenulis = cbBuku.getSelectedItem().toString().split(" oleh ");
                    Buku buku = SistakaNG.findBuku(judulPenulis[0], judulPenulis[1]);
                    lblKembalikan.setText(SistakaNG.kembalikanBuku(buku, tfTanggalPengembalian.getText()));
                    JOptionPane.showOptionDialog(null,  lblKembalikan, "Info", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,null,null);
                    main.setPanel("pengembalian");
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
