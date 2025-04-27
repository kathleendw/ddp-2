package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Dosen;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;
import assignments.assignment4.frontend.staf.ui.DetailAnggotaPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahDosenPanel extends SistakaPanel {
    public TambahDosenPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        JLabel titleLabel = new JLabel("Tambah Dosen");
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        // membuat komponen Label, TextField, dan Button
        JLabel lblNama = new JLabel("Nama");
        lblNama.setFont(SistakaPanel.fontGeneral);
        lblNama.setForeground(Color.black);
        JTextField tfNama = new JTextField(16);
        tfNama.setFont(SistakaPanel.fontGeneral);
        tfNama.setForeground(Color.black);
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
        panel.add(btnTambah);
        panel.add(btnKembali);

        layout.add(panel);
        this.add(layout);

        btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfNama.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Tidak dapat menambahkan dosen silahkan periksa kembali input anda!", 
                        "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    Dosen dosen = SistakaNG.addDosen(tfNama.getText());

                    DetailAnggotaPanel.add(dosen.getId());
                    int result = JOptionPane.showOptionDialog(null,  "Berhasil menambahkan dosen dengan id " + dosen.getId(),
                        "Success!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,null,null);
            
                    if(result == JOptionPane.OK_OPTION){
                        tfNama.setText("");
                        main.setPanel("tambahDosen");
                    }
                }
            }
        });

        btnKembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tfNama.setText("");
                main.setPanel("staf");
            }
        });
    }

    @Override
    public void refresh() {

    }

}
