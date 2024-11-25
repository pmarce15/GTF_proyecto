package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame {
    private Image backgroundImage;
    private JPanel contentPane1;
    private JPanel contentPane2;
    private JPanel contentPane3;
    private JButton btnJugar;
    private JButton btnCerrarSesion;
    private JButton btnAjustes;
    private JButton btnHistorial;
    private JLabel lblDificultad;
    private JLabel lblModoJuego;
    private JLabel lblTitulo;
    
    public VentanaPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setIconImage(Toolkit.getDefaultToolkit().getImage(principal.main.class.getResource("/imagenes/logoGTF.jpg")));
        setTitle("Página principal GTF");
        BackgroundPanel backgroundPanel = new BackgroundPanel("/imagenes/FondoGeneral1.png");
        backgroundPanel.setLayout(new GridBagLayout());

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
        
        
        
        setResizable(false);
        setLocationRelativeTo(null);
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

     

        btnJugar = new JButton("Jugar");
        btnCerrarSesion = new JButton("Biblioteca");
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
            // Cargar la fuente desde el directorio de recursos
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
            
            // Efectos visuales
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
        contentPane1.add(btnCerrarSesion, gbcButton);
        
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
            	VentanaJuego ventanaJuego = new VentanaJuego(dificultad,modo);
            	ventanaJuego.setVisible(true);
            	dispose();
            	
            }
        });
        

        
        btnHistorial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaHistorial ventanaHistorial = new VentanaHistorial();
				ventanaHistorial.setVisible(true);
				dispose();				
			}
		});
        
        btnAjustes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaBiblioteca ventanaBiblioteca = new VentanaBiblioteca();
				ventanaBiblioteca.setVisible(true);
				dispose();
			}
		});
       
        
        
    }

    private class BackgroundPanel extends JPanel {
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

    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
    }
}
