package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import dao.daoUsuario;
import modelo.Usuarios;

public class VentanaLogin extends JFrame {
	
	private JPanel contentPane;
	daoUsuario dao= new daoUsuario();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,450,300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new GridBagLayout());
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 

       
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(new JLabel("Usuario:"), gbc);
        
        gbc.gridx = 1;
        JTextField usuarioField = new JTextField(15);
        contentPane.add(usuarioField, gbc);

       
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        contentPane.add(passwordField, gbc);

       
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Iniciar Sesión");
        contentPane.add(loginButton, gbc);
        
//        gbc.gridx = 5;
//        gbc.gridy = 5;
        setIconImage(Toolkit.getDefaultToolkit().getImage(principal.main.class.getResource("/imagenes/logoGTF.jpg")));
        setTitle("Inicio Sesión GTF");
//        JLabel labelLogo = new JLabel(iconLogo);
//        contentPane.add(labelLogo, gbc);
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	Usuarios user = new Usuarios();
        		user.setUsuario(usuarioField.getText());
        		user.setContrasenya(passwordField.getText());
            	
            	if (dao.comprobarUsuario(user)) {
                    VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                    ventanaPrincipal.setVisible(true);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "EL USUARIO NO EXISTE", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });


        
        gbc.gridy = 3;
        JButton registerButton = new JButton("Registrarse");
        contentPane.add(registerButton, gbc);
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                VentanaRegistro ventanaRegistro = new VentanaRegistro();
                ventanaRegistro.setVisible(true);
                
               
                dispose();
            }
        });

        
        setContentPane(contentPane);
        
        
        
      
    }
		
		
	} 

