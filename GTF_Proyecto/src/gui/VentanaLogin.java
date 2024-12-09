package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import db.daoUsuario;
import domain.Usuarios;

public class VentanaLogin extends JFrame {
	
    private JPanel contentPane;
    daoUsuario dao = new daoUsuario();
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaLogin frame = new VentanaLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/MapaBanderas.jpg"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setLayout(new GridBagLayout()); 
        setContentPane(contentPane);

        
        JPanel contentPane2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/Fondo1.jpg"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane2.setLayout(new GridLayout(6, 1)); 
       
        //IAG ChatGPT
        //FILE: conversacionesGPT-opendeusto.txt
		//SIN CAMBIOS

        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.NONE; 
        contentPane.add(contentPane2, gbc);

        setResizable(false);
        setLocationRelativeTo(null);

       
        JLabel usuarioLabel = new JLabel("Usuario:");
        estiloLabel(usuarioLabel, new Color(255, 255, 255), Color.BLACK);
        contentPane2.add(usuarioLabel);

        JTextField usuarioField = new JTextField(15);
        contentPane2.add(usuarioField);

        JLabel contraseñaLabel = new JLabel("Contraseña:");
        estiloLabel(contraseñaLabel, new Color(255, 255, 255), Color.BLACK);
        contentPane2.add(contraseñaLabel);

        JPasswordField passwordField = new JPasswordField(15);
        contentPane2.add(passwordField);

        JButton loginButton = new JButton("Iniciar Sesión");
        estiloBoton(loginButton, new Color(200, 50, 50), Color.WHITE);
        contentPane2.add(loginButton);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/logoGTF.jpg")));
        setTitle("Inicio Sesión GTF");

        loginButton.addActionListener(e -> {
            Usuarios user = new Usuarios();
            user.setUsuario(usuarioField.getText());
            user.setContrasenya(new String(passwordField.getPassword()));

            if (dao.comprobarUsuario(user)) {
                user.setPuntuacion(dao.obtenerPuntuacion(usuarioField.getText()));
                user.setAciertos(dao.obtenerAciertos(usuarioField.getText()));
                user.setFallos(dao.obtenerFallos(usuarioField.getText()));
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(user);
                ventanaPrincipal.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "EL USUARIO NO EXISTE", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton registerButton = new JButton("Registrarse");
        estiloBoton(registerButton, new Color(200, 50, 50), Color.WHITE);
        contentPane2.add(registerButton);

        registerButton.addActionListener(e -> {
            VentanaRegistro ventanaRegistro = new VentanaRegistro();
            ventanaRegistro.setVisible(true);
            dispose();
        });
    }

   
    private void estiloBoton(JButton boton, Color fondo, Color texto) {
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }
    
    private void estiloLabel(JLabel label, Color colorTexto, Color colorBorde) {
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(colorTexto);
        label.setOpaque(true);
        label.setBackground(new Color(0, 0, 0, 100)); 
        label.setBorder(BorderFactory.createLineBorder(colorBorde, 1)); 
        label.setHorizontalAlignment(SwingConstants.CENTER); 
        
    }
    
    
    
}
