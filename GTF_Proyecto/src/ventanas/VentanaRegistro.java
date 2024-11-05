package ventanas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;
import dao.daoUsuario;
import modelo.Usuarios;


public class VentanaRegistro extends JFrame {
	
	private JPanel contentPane;
	daoUsuario dao= new daoUsuario();
	Conexion cx= new Conexion();
	
	public VentanaRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,450,300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new GridBagLayout());
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio alrededor de los componentes

        // Etiqueta y campo de texto para Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(new JLabel("Usuario:"), gbc);
        
        gbc.gridx = 1;
        JTextField usuarioField = new JTextField(15);
        contentPane.add(usuarioField, gbc);

        // Etiqueta y campo de texto para Contraseña
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        contentPane.add(passwordField, gbc);


        // Botón de Registro
        gbc.gridy = 3;
        JButton registerButton = new JButton("Registrarse");
        contentPane.add(registerButton, gbc);
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		Usuarios user = new Usuarios();
            		user.setUsuario(usuarioField.getText());
            		user.setContrasenya(passwordField.getText());
            		if(dao.insertarUsuario(user)) {
            			JOptionPane.showMessageDialog(null, "SE AGREGO CORRECTAMENTE");
            			 VentanaLogin ventanaLogin = new VentanaLogin();
                         ventanaLogin.setVisible(true);
                         dispose();
            		}else {
            			JOptionPane.showMessageDialog(null, "ERROR");
            		}
            		
            	}catch (Exception e2) {
            		JOptionPane.showMessageDialog(null, "ERROR");
            	}
             
            }
        });

        // Establecer el contentPane como el panel principal
        setContentPane(contentPane);
    }
		
}
