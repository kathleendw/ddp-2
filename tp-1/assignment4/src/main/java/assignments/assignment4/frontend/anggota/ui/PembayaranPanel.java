package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Implementasikan hal-hal yang diperlukan
public class PembayaranPanel extends SistakaPanel {
    public PembayaranPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        JLabel titleLabel = new JLabel("Bayar Denda");
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        // membuat komponen Label, TextField, Dropdown Menu, dan Button
        JLabel lblJumlah = new JLabel("Jumlah Denda");
        lblJumlah.setFont(SistakaPanel.fontGeneral);
        lblJumlah.setForeground(Color.black);
        JTextField tfJumlah = new JTextField(16);
        tfJumlah.setFont(SistakaPanel.fontGeneral);
        tfJumlah.setForeground(Color.black);
        JLabel lblBayar = new JLabel();
        lblBayar.setFont(SistakaPanel.fontGeneral);
        lblBayar.setForeground(Color.black);
        JButton btnBayar = new JButton("Bayar");
        btnBayar.setFont(SistakaPanel.fontButton);
        btnBayar.setForeground(Color.blue);
        JButton btnKembali = new JButton("Kembali");
        btnKembali.setFont(SistakaPanel.fontButton);
        btnKembali.setForeground(Color.blue);

        // km
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(40, 40, 40, 40));
        JPanel panel = new JPanel(new GridLayout(15, 1, 10, 10));
        panel.add(titleLabel);
        panel.add(lblJumlah);
        panel.add(tfJumlah);
        panel.add(lblBayar);
        panel.add(btnBayar);
        panel.add(btnKembali);

        layout.add(panel);
        this.add(layout);

        btnBayar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfJumlah.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Tidak dapat membayar denda silahkan periksa kembali input anda!", 
                        "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    lblBayar.setText(SistakaNG.bayarDenda(Long.parseLong(tfJumlah.getText())));
                    JOptionPane.showOptionDialog(null,  lblBayar, "Info", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,null,null);
                    main.setPanel("pembayaran");
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

}

