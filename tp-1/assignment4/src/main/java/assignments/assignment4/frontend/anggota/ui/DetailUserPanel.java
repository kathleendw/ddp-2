package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Anggota;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Implementasikan hal-hal yang diperlukan
public class DetailUserPanel extends SistakaPanel {
    public DetailUserPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        JLabel titleLabel = new JLabel("Lihat Detail Anggota");
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        JLabel lblDetailUser = new JLabel();
        lblDetailUser.setFont(SistakaPanel.fontGeneral);
        lblDetailUser.setForeground(Color.black);
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
        panel.add(lblDetailUser,c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(btnLihat,c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(btnKembali,c);

        this.add(panel);

        btnLihat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblDetailUser.setText(((Anggota) main.getUser()).detail());
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

}
