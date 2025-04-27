package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.*;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Implementasikan hal-hal yang diperlukan
public class DetailAnggotaPanel extends SistakaPanel {
    static JComboBox cbID ;
    public DetailAnggotaPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        JLabel titleLabel = new JLabel("Lihat Detail Anggota");
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        JLabel lblID = new JLabel("Pilih ID Anggota");
        lblID.setFont(SistakaPanel.fontGeneral);
        lblID.setForeground(Color.black);
        cbID = new JComboBox();
        cbID.setFont(SistakaPanel.fontGeneral);
        cbID.setForeground(Color.black);
        JLabel lblDetailAnggota = new JLabel();
        lblDetailAnggota.setFont(SistakaPanel.fontGeneral);
        lblDetailAnggota.setForeground(Color.black);
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
        panel.add(lblID,c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(cbID,c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(lblDetailAnggota,c);
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
                if (cbID.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Silahkan memilih ID Anggota!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    lblDetailAnggota.setText(SistakaNG.findAnggota(((String)cbID.getSelectedItem())).detail());
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

    }public static void add(String item){
        cbID.addItem(item);
    }


}
