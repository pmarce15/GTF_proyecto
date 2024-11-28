package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Pais;
import dao.daoUsuario;
import modelo.Usuarios;
import ventanas.VentanaHistorial.BackgroundPanel;

public class VentanaAjustes extends JFrame {
	
	private BackgroundPanel backgroundPanel;
	private JPanel contentPane1;
	private JButton botonAtras;
	private JButton btnCerrarSesion;
	private JButton btnCambiarUsuario;
	private JButton btnCambiarContra;
	private daoUsuario usuarioDao;
	String usuarioAutenticado;

	
	public VentanaAjustes() {   ///(String usuarioAutenticado)
		//this.usuarioAutenticado = usuarioAutenticado;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setIconImage(Toolkit.getDefaultToolkit().getImage(principal.main.class.getResource("/imagenes/logoGTF.jpg")));
        setTitle("Página Historial GTF");
        
        BackgroundPanel backgroundPanel = new BackgroundPanel("/imagenes/FondoGeneral1.png");
        backgroundPanel.setLayout(new GridBagLayout());
        
        contentPane1 = new JPanel();
        contentPane1.setBackground(new Color(0, 255, 255, 150));
        contentPane1.setLayout(new GridBagLayout());
        
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(backgroundPanel);

        usuarioDao = new daoUsuario();
       
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.weightx = 1.0;
        gbc1.weighty = 1.0; 
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(50, 50, 50, 50);
        backgroundPanel.add(contentPane1, gbc1);        
        
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.fill = GridBagConstraints.NONE; 
        gbcButton.insets = new Insets(0, 0, 10, 5); 
        
        GridBagConstraints gbcButton2 = new GridBagConstraints();
        gbcButton2.fill = GridBagConstraints.NONE; 
        gbcButton2.insets = new Insets(10, 5, 10, 5); 

        botonAtras = new JButton("Atras");  //EDITAR PARA PONER FLECHA HACIA ATRAS
        gbcButton.gridx = 0; 
        gbcButton.gridy = 0;
        gbcButton.anchor = GridBagConstraints.NORTHWEST;
        backgroundPanel.add(botonAtras, gbcButton);
        
        btnCerrarSesion = new JButton("Cerrar Sesion");
        gbcButton2.gridx = 0; 
        gbcButton2.gridy = 0; 
        contentPane1.add(btnCerrarSesion,gbcButton2);
        
        btnCambiarUsuario = new JButton("Editar nombre de usuario");
        gbcButton2.gridx = 0; 
        gbcButton2.gridy = 1; 
        contentPane1.add(btnCambiarUsuario,gbcButton2);
        
        btnCambiarContra = new JButton("Editar contraseña");
        gbcButton2.gridx = 0; 
        gbcButton2.gridy = 2;
        contentPane1.add(btnCambiarContra,gbcButton2);

        
        botonAtras.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaPrincipal vp = new VentanaPrincipal();
				vp.setVisible(true);
				dispose();
			}
		});
        
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	VentanaLogin ventanaLogin = new VentanaLogin();
                ventanaLogin.setVisible(true);
                dispose(); 
            }
        });
        
        btnCambiarUsuario.addActionListener(new ActionListener() {   ////////////////////////////COMENTADO EN CLASE (HUGO) 
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nuevoNombre = JOptionPane.showInputDialog(VentanaAjustes.this, "Introduce nuevo nombre de usuario.");
				
				if(nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
					 //////!!!
					boolean exito = usuarioDao.actualizarUsuario(usuarioAutenticado, nuevoNombre);
					
					if(exito) {
						JOptionPane.showMessageDialog(VentanaAjustes.this, "Nombre de usuario actualizado correctamente");
						usuarioAutenticado = nuevoNombre;//*****
					}else {
						JOptionPane.showMessageDialog(VentanaAjustes.this, "Error al actualizar el nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(VentanaAjustes.this, "El nombre de usuario no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
        
        btnCambiarContra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contrasenaActual = JOptionPane.showInputDialog(VentanaAjustes.this, "Introduce tu contraseña actual.");

                if (contrasenaActual != null && !contrasenaActual.trim().isEmpty()) {
                    Usuarios usuario = new Usuarios();
                    usuario.setUsuario(usuarioAutenticado);
                    usuario.setContrasenya(contrasenaActual);

                    boolean esCorrecta = usuarioDao.comprobarUsuario(usuario);

                    if (esCorrecta) {
                        String nuevaContrasena = JOptionPane.showInputDialog(VentanaAjustes.this, "Introduce la nueva contraseña.");

                        if (nuevaContrasena != null && !nuevaContrasena.trim().isEmpty()) {
                            boolean exito = usuarioDao.actualizarContrasena(usuarioAutenticado, nuevaContrasena);

                            if (exito) {
                                JOptionPane.showMessageDialog(VentanaAjustes.this, "Contraseña actualizada correctamente");
                            } else {
                                JOptionPane.showMessageDialog(VentanaAjustes.this, "Error al actualizar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(VentanaAjustes.this, "La nueva contraseña no puede estar vacía.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(VentanaAjustes.this, "La contraseña actual es incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(VentanaAjustes.this, "La contraseña actual no puede estar vacía.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


	}	
	
	public void setUsuarioAutenticado(String usuario) {
	    this.usuarioAutenticado = usuario;
	}
	
	public class BackgroundPanel extends JPanel {
		private Image backgroundImage;
		public BackgroundPanel(String imagePath) {
	            backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
	        }

	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	        }
	}

}
