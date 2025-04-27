package assignments.assignment4.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class WelcomePanel extends SistakaPanel {

    public WelcomePanel(HomeGUI homeGUI) {
        super(homeGUI);
        // TODO: Implementasikan hal-hal yang diperlukan
        JLabel titleLabel = new JLabel("Welcome to SistakaNG");
        titleLabel.setFont(SistakaPanel.fontTitle);
        titleLabel.setForeground(Color.black);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(SistakaPanel.fontButton);
        btnLogin.setForeground(Color.blue);
        JButton btnExit = new JButton("Exit");
        btnExit.setFont(SistakaPanel.fontButton);
        btnExit.setForeground(Color.blue);

        // km
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(40, 40, 40, 40));
        JPanel panel = new JPanel(new GridLayout(15, 1, 10, 10));
        panel.add(titleLabel);
        panel.add(btnLogin);
        panel.add(btnExit);

        layout.add(panel);
        this.add(layout);
        
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setPanel("login");
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.exit();
            }
        });
    }

    @Override
    public void refresh() {

    }

}
