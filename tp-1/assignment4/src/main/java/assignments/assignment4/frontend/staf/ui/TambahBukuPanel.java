package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.backend.buku.Kategori;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;
import assignments.assignment4.frontend.staf.ui.HapusBukuPanel;
import assignments.assignment4.frontend.staf.ui.DaftarPeminjamPanel;
import assignments.assignment4.frontend.anggota.ui.PeminjamanPanel;
import assignments.assignment4.frontend.anggota.ui.PengembalianPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahBukuPanel extends SistakaPanel {
    static JComboBox cbKategori;
    public TambahBukuPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        JLabel titleLabel = new JLabel("Tambah Buku");
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        // membuat komponen Label, TextField, Dropdown Menu, dan Button
        JLabel lblJudul = new JLabel("Judul");
        lblJudul.setFont(SistakaPanel.fontGeneral);
        lblJudul.setForeground(Color.black);
        JTextField tfJudul = new JTextField(16);
        tfJudul.setFont(SistakaPanel.fontGeneral);
        tfJudul.setForeground(Color.black);
        JLabel lblPenulis = new JLabel("Penulis");
        lblPenulis.setFont(SistakaPanel.fontGeneral);
        lblPenulis.setForeground(Color.black);
        JTextField tfPenulis = new JTextField(16);
        tfPenulis.setFont(SistakaPanel.fontGeneral);
        tfPenulis.setForeground(Color.black);
        JLabel lblPenerbit = new JLabel("Penerbit");
        lblPenerbit.setFont(SistakaPanel.fontGeneral);
        lblPenerbit.setForeground(Color.black);
        JTextField tfPenerbit = new JTextField(16);
        tfPenerbit.setFont(SistakaPanel.fontGeneral);
        tfPenerbit.setForeground(Color.black);
        JLabel lblKategori = new JLabel("Kategori");
        lblKategori.setFont(SistakaPanel.fontGeneral);
        lblKategori.setForeground(Color.black);
        cbKategori = new JComboBox();
        cbKategori.setFont(SistakaPanel.fontGeneral);
        cbKategori.setForeground(Color.black);
        JLabel lblStok = new JLabel("Stok");
        lblStok.setFont(SistakaPanel.fontGeneral);
        lblStok.setForeground(Color.black);
        JTextField tfStok = new JTextField(16);
        tfStok.setFont(SistakaPanel.fontGeneral);
        tfStok.setForeground(Color.black);
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
        panel.add(lblJudul);
        panel.add(tfJudul);
        panel.add(lblPenulis);
        panel.add(tfPenulis);
        panel.add(lblPenerbit);
        panel.add(tfPenerbit);
        panel.add(lblKategori);
        panel.add(cbKategori);
        panel.add(lblStok);
        panel.add(tfStok);
        panel.add(btnTambah);
        panel.add(btnKembali);

        layout.add(panel);
        this.add(layout);

        btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfJudul.getText().equals("") || tfPenulis.getText().equals("") || tfPenerbit.getText().equals("") || tfStok.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Tidak dapat menambahkan buku silahkan periksa kembali input anda!", 
                        "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if (cbKategori.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan memilih kategori!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if (!SistakaPanel.isNumeric(tfStok.getText())) {
                    JOptionPane.showMessageDialog(null, "Stok harus berupa angka!", "Warning", JOptionPane.WARNING_MESSAGE);
                } 
                else if (Integer.parseInt(tfStok.getText()) <= 0) {
                    JOptionPane.showMessageDialog(null, "Stok harus lebih dari 0!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if (SistakaNG.findBuku(tfJudul.getText(), tfPenulis.getText()) != null) {
                    Buku buku = SistakaNG.findBuku(tfJudul.getText(), tfPenulis.getText());
                    JOptionPane.showMessageDialog(null, "Buku " + buku.getJudul() + " oleh " + buku.getPenulis() + " sudah pernah ditambahkan!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                } 
                else {
                    Buku buku = SistakaNG.addBuku(tfJudul.getText(), tfPenulis.getText(), tfPenerbit.getText(), cbKategori.getSelectedItem().toString(), 
                                Integer.parseInt(tfStok.getText()));

                    HapusBukuPanel.add(buku.getJudul() + " oleh " + buku.getPenulis());
                    DaftarPeminjamPanel.add(buku.getJudul() + " oleh " + buku.getPenulis());
                    PeminjamanPanel.add(buku.getJudul() + " oleh " + buku.getPenulis());
                    PengembalianPanel.add(buku.getJudul() + " oleh " + buku.getPenulis());
                    int result = JOptionPane.showOptionDialog(null,  "Buku " + buku.getJudul() + " oleh " + buku.getPenulis() + " berhasil ditambahkan!", 
                        "Success!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,null,null);
            
                    if(result == JOptionPane.OK_OPTION){
                        tfJudul.setText("");
                        tfPenulis.setText("");
                        tfPenerbit.setText("");
                        tfStok.setText("");
                        main.setPanel("tambahBuku");
        
                    }
                }
            }
        });

        btnKembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tfJudul.setText("");
                tfPenulis.setText("");
                tfPenerbit.setText("");
                tfStok.setText("");
                main.setPanel("staf");
            }
        });
    }

    @Override
    public void refresh() {

    }

    public static void add(String item){
        cbKategori.addItem(item);
    }

}

