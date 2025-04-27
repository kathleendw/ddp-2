package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Mahasiswa;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;
import assignments.assignment4.frontend.staf.ui.DetailAnggotaPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahMahasiswaPanel extends SistakaPanel {
    public TambahMahasiswaPanel(HomeGUI main) {
        super(main);

        // membuat dan mengatur label untuk judul
        JLabel titleLabel = new JLabel("Tambah Mahasiswa");
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        // membuat komponen Label, TextField, Dropdown Menu, dan Button
        JLabel lblNama = new JLabel("Nama");
        lblNama.setFont(SistakaPanel.fontGeneral);
        lblNama.setForeground(Color.black);
        JTextField tfNama = new JTextField(16);
        tfNama.setFont(SistakaPanel.fontGeneral);
        tfNama.setForeground(Color.black);
        JLabel lblTanggalLahir = new JLabel("Tanggal Lahir (DD/MM/YYYY)");
        lblTanggalLahir.setFont(SistakaPanel.fontGeneral);
        lblTanggalLahir.setForeground(Color.black);
        JTextField tfTanggalLahir = new JTextField(16);
        tfTanggalLahir.setFont(SistakaPanel.fontGeneral);
        tfTanggalLahir.setForeground(Color.black);
        JLabel lblProgramStudi = new JLabel("Program Studi");
        lblProgramStudi.setFont(SistakaPanel.fontGeneral);
        lblProgramStudi.setForeground(Color.black);
        String[] programStudi = {"SIK", "SSI", "MIK", "MTI", "DIK"};
        JComboBox cbProgramStudi = new JComboBox(programStudi);
        cbProgramStudi.setFont(SistakaPanel.fontGeneral);
        cbProgramStudi.setForeground(Color.black);
        JLabel lblAngkatan = new JLabel("Angkatan");
        lblAngkatan.setFont(SistakaPanel.fontGeneral);
        lblAngkatan.setForeground(Color.black);
        JTextField tfAngkatan = new JTextField(16);
        tfAngkatan.setFont(SistakaPanel.fontGeneral);
        tfAngkatan.setForeground(Color.black);
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
        panel.add(lblTanggalLahir);
        panel.add(tfTanggalLahir);
        panel.add(lblProgramStudi);
        panel.add(cbProgramStudi);
        panel.add(lblAngkatan);
        panel.add(tfAngkatan);
        panel.add(btnTambah);
        panel.add(btnKembali);

        layout.add(panel);
        this.add(layout);

        btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mahasiswa mahasiswa = SistakaNG.addMahasiswa(tfNama.getText(), tfTanggalLahir.getText(),
                        cbProgramStudi.getSelectedItem().toString(), tfAngkatan.getText());

                if (mahasiswa == null) {
                    JOptionPane.showMessageDialog(null, "Tidak dapat menambahkan mahasiswa silahkan periksa kembali input anda!", 
                        "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    DetailAnggotaPanel.add(mahasiswa.getId());
                    int result = JOptionPane.showOptionDialog(null,  "Berhasil menambahkan mahasiswa dengan id " + mahasiswa.getId(),
                        "Success!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,null,null);
            
                    if(result == JOptionPane.OK_OPTION){
                        tfNama.setText("");
                        tfTanggalLahir.setText("");
                        tfAngkatan.setText("");
                        main.setPanel("tambahMhs");
                    }
                }
            }
        });

        btnKembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tfNama.setText("");
                tfTanggalLahir.setText("");
                tfAngkatan.setText("");
                main.setPanel("staf");
            }
        });
    }
    @Override
    public void refresh() {

    }

}
