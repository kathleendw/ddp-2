package assignments.assignment4.frontend.staf.ui;

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
public class HapusBukuPanel extends SistakaPanel {
    static JComboBox cbBuku;
    public HapusBukuPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        JLabel titleLabel = new JLabel("Hapus Buku");
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        // membuat komponen Label, TextField, Dropdown Menu, dan Button
        JLabel lblBuku = new JLabel("Buku");
        lblBuku.setFont(SistakaPanel.fontGeneral);
        lblBuku.setForeground(Color.black);
        cbBuku = new JComboBox();
        cbBuku.setFont(SistakaPanel.fontGeneral);
        cbBuku.setForeground(Color.black);
        JLabel lblHapus = new JLabel();
        lblHapus.setFont(SistakaPanel.fontGeneral);
        lblHapus.setForeground(Color.black);
        JButton btnHapus = new JButton("Hapus");
        btnHapus.setFont(SistakaPanel.fontButton);
        btnHapus.setForeground(Color.blue);
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
        panel.add(lblHapus);
        panel.add(btnHapus);
        panel.add(btnKembali);

        layout.add(panel);
        this.add(layout);

        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cbBuku.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan memilih buku!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    String[] judulPenulis = cbBuku.getSelectedItem().toString().split(" oleh ");
                    if (SistakaNG.findBuku(judulPenulis[0], judulPenulis[1]) == null) {
                        JOptionPane.showMessageDialog(null, "Buku " + judulPenulis[0] + " oleh " + judulPenulis[1] + " tidak ditemukan!",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    else if ((SistakaNG.findBuku(judulPenulis[0], judulPenulis[1]) != null) && (SistakaNG.findBuku(judulPenulis[0], judulPenulis[1]).getStok() != SistakaNG.findBuku(judulPenulis[0], judulPenulis[1]).getStokAwal())) {
                        JOptionPane.showMessageDialog(null, "Buku " + judulPenulis[0] + " oleh " + judulPenulis[1] +
                                " tidak dapat dihapus karena sedang dipinjam!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        Buku buku = SistakaNG.findBuku(judulPenulis[0], judulPenulis[1]);
                        lblHapus.setText(SistakaNG.deleteBuku(buku));
                        JOptionPane.showOptionDialog(null,  lblHapus, "Info", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,null,null);

                        main.setPanel("hapusBuku");
                        cbBuku.removeItem(buku.getJudul() + " oleh " + buku.getPenulis());
                    }
                }
            }
        });

        btnKembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("staf");
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

