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
public class DaftarPeminjamPanel extends SistakaPanel {
    static JComboBox cbBuku;
    public DaftarPeminjamPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        JLabel titleLabel = new JLabel("Lihat Daftar Peminjam");
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        JLabel lblBuku = new JLabel("Pilih buku");
        lblBuku.setFont(SistakaPanel.fontGeneral);
        lblBuku.setForeground(Color.black);
        cbBuku = new JComboBox();
        cbBuku.setFont(SistakaPanel.fontGeneral);
        cbBuku.setForeground(Color.black);
        JLabel lblPeminjam = new JLabel();
        lblPeminjam.setFont(SistakaPanel.fontGeneral);
        lblPeminjam.setForeground(Color.black);
        JButton btnLihat = new JButton("Lihat");
        btnLihat.setFont(SistakaPanel.fontButton);
        btnLihat.setForeground(Color.blue);
        JButton btnKembali = new JButton("Kembali");
        btnKembali.setFont(SistakaPanel.fontButton);
        btnKembali.setForeground(Color.blue);

        // km
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(titleLabel,c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(lblBuku,c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(cbBuku,c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(lblPeminjam,c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 5;
        panel.add(btnLihat,c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 6;
        panel.add(btnKembali,c);

        this.add(panel);

        btnLihat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cbBuku.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Silahkan memilih buku!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    String[] judulPenulis = cbBuku.getSelectedItem().toString().split(" oleh ");
                    Buku buku = SistakaNG.findBuku(judulPenulis[0], judulPenulis[1]);
                    lblPeminjam.setText(SistakaNG.daftarPeminjam(buku));
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

