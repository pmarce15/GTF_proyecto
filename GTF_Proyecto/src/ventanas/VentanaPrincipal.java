package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import clases.Pais;
import dao.daoUsuario;
import modelo.Usuarios;

public class VentanaPrincipal extends JFrame {
    private Image backgroundImage;
    private JPanel contentPane1;
    private JPanel contentPane2;
    private JPanel contentPane3;
    private JPanel contentPane4;
    private JButton btnJugar;
    private JButton btnBiblioteca;
    private JButton btnAjustes;
    private JButton btnHistorial;
    private JLabel lblDificultad;
    private JLabel lblModoJuego;
    private JLabel lblTitulo;
    private JButton btnRetoDiario;
    private JButton btnEstadisticasRetoDiario;
    daoUsuario dao = new daoUsuario();
    private Usuarios user;
    
    public VentanaPrincipal(Usuarios user) {
    	this.user = user;
    	String dificultad = user.getDificultad();
        String modoJuego = user.getModoJuego();
        System.out.println("Modo de Juego: " + modoJuego);
        System.out.println("Dificultad: " + dificultad);
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setIconImage(Toolkit.getDefaultToolkit().getImage(principal.main.class.getResource("/imagenes/logoGTF.jpg")));
        setTitle("Página principal GTF");
        BackgroundPanel backgroundPanel = new BackgroundPanel("/imagenes/FondoGeneral1.png");
        backgroundPanel.setLayout(new GridBagLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane1 = new JPanel();
        contentPane1.setBackground(new Color(0, 255, 255, 150));
        contentPane1.setLayout(new GridBagLayout());
        
        contentPane2 = new JPanel();
        contentPane2.setBackground(new Color(0, 255, 255, 150));
        contentPane2.setLayout(new GridBagLayout());
        
        contentPane3 = new JPanel(); 
        contentPane3.setLayout(new GridBagLayout());
        contentPane3.setBackground(Color.RED);
        contentPane3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        
        contentPane4 = new JPanel();
        contentPane4.setBackground(new Color(0, 255, 255, 150));
        contentPane4.setLayout(new GridLayout(1,2));
        contentPane4.setOpaque(false);    
        
        
        btnRetoDiario = new JButton();
        btnEstadisticasRetoDiario = new JButton();
        btnRetoDiario.setContentAreaFilled(false); 
        btnEstadisticasRetoDiario.setContentAreaFilled(false);
       
        
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/imagenes/RetoDiario.png"));
        Image img1 = icon1.getImage();
        Image scaledImg1 = img1.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        btnRetoDiario.setIcon(new ImageIcon(scaledImg1));
       
        
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/imagenes/EstadisticasRetoDiario.png"));
        Image img2 = icon2.getImage();
        Image scaledImg2 = img2.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        btnEstadisticasRetoDiario.setIcon(new ImageIcon(scaledImg2));
        
        contentPane4.add(btnRetoDiario);
        contentPane4.add(btnEstadisticasRetoDiario);
        
        
        setContentPane(backgroundPanel);

       
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.weightx = 1.0;
        gbc2.weighty = 1.0; 
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.insets = new Insets(155, 100, 20, 100);
        backgroundPanel.add(contentPane1, gbc2);
        
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.fill = GridBagConstraints.BOTH;
        gbc3.weightx = 1.0;
        gbc3.weighty = 1.0; 
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        gbc3.insets = new Insets(70, 100, 115, 100);
        backgroundPanel.add(contentPane2, gbc3);
        
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.BOTH;
        gbc1.weightx = 1.0;
        gbc1.weighty = 1.0; 
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(20, 100, 200, 100);
        backgroundPanel.add(contentPane3, gbc1);
        
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.fill = GridBagConstraints.BOTH;
        gbc4.weightx = 1.0;
        gbc4.weighty = 1.0;
        gbc4.gridx = 0;
        gbc4.gridy = 0;
        gbc4.insets = new Insets(10, 350, 225, 10);
        backgroundPanel.add(contentPane4, gbc4);
        
        
       

     

        btnJugar = new JButton("Jugar");
        btnBiblioteca = new JButton("Biblioteca");
        btnAjustes = new JButton("Ajustes"); 
        btnHistorial = new JButton("Historial");
        lblDificultad = new JLabel("Dificultad");
        lblModoJuego = new JLabel("Modo de Juego");
        lblTitulo = new JLabel("Elige tu modo de Juego");
        lblDificultad.setHorizontalAlignment(SwingConstants.CENTER);
        lblModoJuego.setHorizontalAlignment(SwingConstants.CENTER);
        lblDificultad.setVerticalAlignment(SwingConstants.CENTER);
        lblModoJuego.setVerticalAlignment(SwingConstants.CENTER);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setVerticalAlignment(SwingConstants.CENTER);
        
        
        Font font = null;
            
            try {
				font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/pixelFont/ka1.ttf"));
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            font = font.deriveFont(Font.BOLD, 10); 
            lblTitulo.setFont(font);
            lblDificultad.setFont(font);
            lblModoJuego.setFont(font);
            
          
            lblTitulo.setForeground(Color.WHITE);  
            lblTitulo.setOpaque(false);            
            lblTitulo.setBackground(new Color(0, 0, 0, 0)); 
            
            lblTitulo.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
      
      
        
        
        String[] opciones = {"Facil", "Medio","Dificil"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        
        String[] opciones2 = {"Normal", "Por partes"};
        JComboBox<String> comboBox2 = new JComboBox<>(opciones2);

        comboBox.setPreferredSize(new Dimension(50, 25)); 
        comboBox2.setPreferredSize(new Dimension(50, 25));
        
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.fill = GridBagConstraints.BOTH; 
        gbcLabel.weightx = 0.5; 
        gbcLabel.insets = new Insets(10, 5, 10, 5); 
       
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.fill = GridBagConstraints.HORIZONTAL; 
        gbcButton.weightx = 1.0; 
        gbcButton.insets = new Insets(10, 5, 10, 5); 
        
        GridBagConstraints gbcComboBox = new GridBagConstraints();
        gbcComboBox.fill = GridBagConstraints.HORIZONTAL; 
        gbcComboBox.weightx = 0.5; 
        gbcComboBox.insets = new Insets(10, 5, 10, 5); 
        
        
        gbcLabel.gridx = 0; 
	  	gbcLabel.gridy = 0;  
	  	contentPane3.add(lblTitulo, gbcLabel);
	  	
        gbcComboBox.gridx = 0; 
	  	gbcComboBox.gridy = 0;  
	  	contentPane2.add(lblDificultad, gbcComboBox);
     
        gbcComboBox.gridx = 0; 
	  	gbcComboBox.gridy = 0;  
	  	contentPane2.add(lblDificultad, gbcComboBox);
	         
	    gbcComboBox.gridx = 1;  
	    contentPane2.add(lblModoJuego, gbcComboBox);
	        
	    gbcComboBox.gridx = 0; 
	    gbcComboBox.gridy = 1;  
	    contentPane2.add(comboBox, gbcComboBox);
	         
	    gbcComboBox.gridx = 1;  
	    contentPane2.add(comboBox2, gbcComboBox);
       
        gbcButton.gridx = 0; 
        gbcButton.gridy = 0;  
        contentPane1.add(btnJugar, gbcButton);

       
        gbcButton.gridx = 1; 
        contentPane1.add(btnBiblioteca, gbcButton);
        
        gbcButton.gridx = 0; 
        gbcButton.gridy = 1;
        contentPane1.add(btnAjustes, gbcButton);
        
        gbcButton.gridx = 1; 
        contentPane1.add(btnHistorial, gbcButton);
        
        btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String dificultad = (String) comboBox.getSelectedItem();
            	String modo = (String) comboBox2.getSelectedItem();
            	VentanaJuego ventanaJuego = new VentanaJuego(dificultad,modo,user);
            	ventanaJuego.setVisible(true);
            	dispose();
            	
            }
        });
        
        btnBiblioteca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaBiblioteca ventanaBiblioteca = new VentanaBiblioteca(user);
				ventanaBiblioteca.setVisible(true);
				dispose();
			}
		});
        
        btnHistorial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaHistorial ventanaHistorial = new VentanaHistorial(user, user.getModoJuego(), user.getDificultad(), user.getPuntuacion(),false);
				ventanaHistorial.setVisible(true);
				dispose();				
			}
		});
        
        btnAjustes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VentanaAjustes ventanaAjustes = new VentanaAjustes(user);
				ventanaAjustes.setVisible(true);
				dispose();
			}
		});
       
        btnRetoDiario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String archivoUltimaFecha = "reto_diario.txt";
                
                
                if (!puedeJugar(archivoUltimaFecha)) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Ya has jugado el Reto Diario. Intentalo de nuevo después de 24 horas.",
                        "Reto Diario",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    return;
                }

                
                guardarFechaActual(archivoUltimaFecha);

                
                JDialog dialog = new JDialog(); 
                dialog.setTitle("Reto Diario");
                dialog.setModal(true); 
                dialog.setSize(215, 300);
                dialog.setLayout(new BorderLayout());
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                JPanel panel = new JPanel(new GridLayout(4, 1));
                JPanel panel2 = new JPanel(new GridLayout(1, 3));
                JLabel lblBandera = new JLabel();
                JLabel lblVida1 = new JLabel();
                JLabel lblVida2 = new JLabel();
                JLabel lblVida3 = new JLabel();
                JButton btnIntento = new JButton("Probar");
                JTextField textField = new JTextField();

                
                ImageIcon iconVida = new ImageIcon(getClass().getResource("/imagenes/corazonVida.png"));
                Image imgVida = iconVida.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                lblVida1.setIcon(new ImageIcon(imgVida));
                lblVida2.setIcon(new ImageIcon(imgVida));
                lblVida3.setIcon(new ImageIcon(imgVida));

                Map<String, String> datos = null;
                try {
                    datos = Pais.cargarPaises("/facil.csv");
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return;
                }

                List<String> nombresPaises = new ArrayList<>(datos.keySet());
                Collections.shuffle(nombresPaises);

                String paisCorrecto = nombresPaises.get(0); 
                String rutaImagen = datos.get(paisCorrecto);

                if (rutaImagen != null) {
                    ImageIcon icon = new ImageIcon(getClass().getResource("/" + rutaImagen));
                    Image img = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                    lblBandera.setIcon(new ImageIcon(img));
                }

                panel2.add(lblVida1);
                panel2.add(lblVida2);
                panel2.add(lblVida3);

                panel.add(lblBandera);
                panel.add(textField);
                panel.add(panel2);
                panel.add(btnIntento);

                dialog.add(panel, BorderLayout.CENTER);

                btnIntento.addActionListener(new ActionListener() {
                    private int contadorVidas = 3;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String paisIntento = textField.getText().trim().toUpperCase();
                        String paisCorrectoNormalizado = paisCorrecto.toUpperCase();

                        if (paisCorrectoNormalizado.equals(paisIntento)) {
                            JOptionPane.showMessageDialog(
                                dialog,
                                "¡Correcto! El país es: " + paisCorrecto,
                                "Éxito",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                            dialog.dispose(); 
                            int nuevoAciertos = user.getAciertos()+1;
                            user.setAciertos(nuevoAciertos);
                            dao.actualizarAciertosYFallos(user);
                            dialog.dispose(); 
//                            VentanaHistorial ventanaHistorial = new VentanaHistorial(user, modoJuego, dificultad, nuevoAciertos);
//                            ventanaHistorial.setVisible(true);
                        } else {
                            contadorVidas--;
                            if (contadorVidas == 2) {
                                lblVida1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/corazonVidaPerdida.png"))
                                        .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
                            } else if (contadorVidas == 1) {
                                lblVida2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/corazonVidaPerdida.png"))
                                        .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
                            } else if (contadorVidas == 0) {
                                lblVida3.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/corazonVidaPerdida.png"))
                                        .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
                                JOptionPane.showMessageDialog(
                                    dialog,
                                    "Has perdido. El país correcto era: " + paisCorrecto,
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                                );
                                dialog.dispose(); 
                                int nuevoFallos = user.getFallos()+1;
                                user.setFallos(nuevoFallos);
                                dao.actualizarAciertosYFallos(user);
                                dialog.dispose();
                                
//                                VentanaHistorial ventanaHistorial = new VentanaHistorial(user, modo, dificultad, 0); // 0 en caso de fallos
//                                ventanaHistorial.setVisible(true);
                            }
                        }
                    }
                });

                dialog.setLocationRelativeTo(null); 
                dialog.setVisible(true); 
            }

            private boolean puedeJugar(String archivo) {
                try {
                    if (!Files.exists(Paths.get(archivo))) {
                        return true; 
                    }

                    List<String> lineas = Files.readAllLines(Paths.get(archivo));
                    for (String linea : lineas) {
                        String[] partes = linea.split(";");
                        if (partes.length == 2) {
                            String nombreArchivo = partes[0].trim();
                            String fechaArchivo = partes[1].trim();

                            if (nombreArchivo.equals(user.getUsuario())) {
                                LocalDateTime ultimaFecha = LocalDateTime.parse(fechaArchivo, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                                return ChronoUnit.HOURS.between(ultimaFecha, LocalDateTime.now()) >= 24;
                            }
                        }
                    }
                    return true; 
                } catch (IOException | DateTimeParseException ex) {
                    ex.printStackTrace();
                    return true; 
                }
            }

            private void guardarFechaActual(String archivo) {
                try {
                    List<String> lineas = Files.exists(Paths.get(archivo))
                        ? Files.readAllLines(Paths.get(archivo))
                        : new ArrayList<>();

                    boolean encontrado = false;
                    for (int i = 0; i < lineas.size(); i++) {
                        String[] partes = lineas.get(i).split(";");
                        if (partes.length == 2 && partes[0].trim().equals(user.getUsuario())) {
                            
                            lineas.set(i, user.getUsuario() + ";" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        
                        lineas.add(user.getUsuario() + ";" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    }

                    
                    Files.write(Paths.get(archivo), lineas);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
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
