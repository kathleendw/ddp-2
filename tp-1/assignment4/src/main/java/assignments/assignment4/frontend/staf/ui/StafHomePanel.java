package assignments.assignment4.frontend.staf.ui;


import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;
import assignments.assignment4.frontend.staf.ui.PeringkatPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: Implementasikan hal-hal yang diperlukan
public class StafHomePanel extends SistakaPanel {
    static JLabel titleLabel;
    public StafHomePanel(HomeGUI main) {
        super(main);
        // membuat dan mengatur label untuk judul
        titleLabel = new JLabel();
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        // membuat komponen Button
        JButton btnMahasiswa = new JButton("Tambah Mahasiswa");
        btnMahasiswa.setFont(SistakaPanel.fontButton);
        btnMahasiswa.setForeground(Color.blue);
        JButton btnDosen = new JButton("Tambah Dosen");
        btnDosen.setFont(SistakaPanel.fontButton);
        btnDosen.setForeground(Color.blue);
        JButton btnKategori = new JButton("Tambah Kategori");
        btnKategori.setFont(SistakaPanel.fontButton);
        btnKategori.setForeground(Color.blue);
        JButton btnBuku = new JButton("Tambah Buku");
        btnBuku.setFont(SistakaPanel.fontButton);
        btnBuku.setForeground(Color.blue);
        JButton btnHapusBuku = new JButton("Hapus Buku");
        btnHapusBuku.setFont(SistakaPanel.fontButton);
        btnHapusBuku.setForeground(Color.blue);
        JButton btnPeringkat = new JButton("3 Peringkat Pertama");
        btnPeringkat.setFont(SistakaPanel.fontButton);
        btnPeringkat.setForeground(Color.blue);
        JButton btnDetailAnggota = new JButton("Detail Anggota");
        btnDetailAnggota.setFont(SistakaPanel.fontButton);
        btnDetailAnggota.setForeground(Color.blue);
        JButton btnDaftarPeminjam = new JButton("Daftar Peminjam Buku");
        btnDaftarPeminjam.setFont(SistakaPanel.fontButton);
        btnDaftarPeminjam.setForeground(Color.blue);
        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(SistakaPanel.fontButton);
        btnLogout.setForeground(Color.blue);

        // km
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(40, 40, 40, 40));
        JPanel panel = new JPanel(new GridLayout(15, 1, 10, 10));
        panel.add(titleLabel);
        panel.add(btnMahasiswa);
        panel.add(btnDosen);
        panel.add(btnKategori);
        panel.add(btnBuku);
        panel.add(btnHapusBuku);
        panel.add(btnPeringkat);
        panel.add(btnDetailAnggota);
        panel.add(btnDaftarPeminjam);
        panel.add(btnLogout);

        layout.add(panel);
        this.add(layout);

        btnMahasiswa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("tambahMhs");
            }
        });

        btnDosen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("tambahDosen");
            }
        });

        btnKategori.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("tambahKategori");
            }
        });

        btnBuku.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("tambahBuku");
            }
        });
        
        btnHapusBuku.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("hapusBuku");
            }
        });

        btnPeringkat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PeringkatPanel.add(SistakaNG.handleRankingAnggota());
                main.setPanel("peringkat");
            }
        });

        btnDetailAnggota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("detailAnggota");
            }
        });

        btnDaftarPeminjam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("daftarPeminjam");
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

