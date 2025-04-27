package assignments.assignment4.frontend.staf.ui;

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
public class PeringkatPanel extends SistakaPanel {
    static JLabel peringkat;
    public PeringkatPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        JLabel titleLabel = new JLabel("Peringkat");
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);
        peringkat = new JLabel();
        peringkat.setFont(SistakaPanel.fontGeneral);
        peringkat.setForeground(Color.black);
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
        panel.add(peringkat,c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(btnKembali,c);

        this.add(panel);

        btnKembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("staf");
            }
        });

    }
    @Override
    public void refresh() {

    }
    public static void add(String string){
        peringkat.setText(string);
    }

}

