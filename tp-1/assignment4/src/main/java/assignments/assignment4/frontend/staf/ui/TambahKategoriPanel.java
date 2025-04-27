package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Kategori;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;
import assignments.assignment4.frontend.staf.ui.TambahBukuPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahKategoriPanel extends SistakaPanel {
    public TambahKategoriPanel(HomeGUI main) {
        super(main);
        // membuat dan mengatur label untuk judul
        JLabel titleLabel = new JLabel("Tambah Kategori");
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        // membuat komponen Label, TextField, dan Button
        JLabel lblNama = new JLabel("Nama");
        lblNama.setFont(SistakaPanel.fontGeneral);
        lblNama.setForeground(Color.black);
        JTextField tfNama = new JTextField(16);
        tfNama.setFont(SistakaPanel.fontGeneral);
        tfNama.setForeground(Color.black);
        JLabel lblPoin = new JLabel("Poin");
        lblPoin.setFont(SistakaPanel.fontGeneral);
        lblPoin.setForeground(Color.black);
        JTextField tfPoin = new JTextField(16);
        tfPoin.setFont(SistakaPanel.fontGeneral);
        tfPoin.setForeground(Color.black);
        JButton btnTambah = new JButton("Tambah");
        btnTambah.setFont(SistakaPanel.fontButton);
        btnTambah.setForeground(Color.blue);
        JButton btnKembali = new JButton("Kembali");
        btnKembali.setFont(SistakaPanel.fontButton);
        btnKembali.setForeground(Color.blue);

        // km
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(40, 40, 40, 40));
        JPanel panel = new JPanel(new GridLayout(15, 1, 10, 10));
        panel.add(titleLabel);
        panel.add(lblNama);
        panel.add(tfNama);
        panel.add(lblPoin);
        panel.add(tfPoin);
        panel.add(btnTambah);
        panel.add(btnKembali);

        layout.add(panel);
        this.add(layout);

        btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfNama.getText().equals("") || tfPoin.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Tidak dapat menambahkan kategori silahkan periksa kembali input anda!", 
                        "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if (!SistakaPanel.isNumeric(tfPoin.getText())) {
                    JOptionPane.showMessageDialog(null, "Poin harus berupa angka!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if (SistakaNG.findKategori(tfNama.getText()) != null) {
                    Kategori kategori = SistakaNG.findKategori(tfNama.getText());
                    JOptionPane.showMessageDialog(null, "Kategori " + kategori.getNama() + " sudah pernah ditambahkan!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                } 
                else {
                    Kategori kategori = SistakaNG.addKategori(tfNama.getText(), Integer.parseInt(tfPoin.getText()));
                    TambahBukuPanel.add(kategori.getNama());
                    int result = JOptionPane.showOptionDialog(null,  "Kategori " + kategori.getNama() + " dengan poin " + kategori.getPoin() + 
                        " berhasil ditambahkan!", "Success!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,null,null);
            
                    if(result == JOptionPane.OK_OPTION){
                        tfNama.setText("");
                        tfPoin.setText("");
                        main.setPanel("tambahKategori");
                    }
                }
            }
        });

        btnKembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tfNama.setText("");
                tfPoin.setText("");
                main.setPanel("staf");
            }
        });
    }

    @Override
    public void refresh() {

    }

}

