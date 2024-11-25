package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaJuego extends JFrame {
	private BackgroundPanel backgroundPanel;
	private JPanel contentPane1;
	private JPanel contentPane2;
	private JButton botonAtras;
	private JLabel lblBanderas;
	
	public VentanaJuego(String dificultad, String modo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setIconImage(Toolkit.getDefaultToolkit().getImage(principal.main.class.getResource("/imagenes/logoGTF.jpg")));
        setTitle("Página Historial GTF");
        BackgroundPanel backgroundPanel = new BackgroundPanel("/imagenes/FondoGeneral1.png");
        backgroundPanel.setLayout(new GridBagLayout());
        
        contentPane1 = new JPanel();
        contentPane1.setBackground(new Color(0, 255, 255, 150));
        contentPane1.setLayout(new GridBagLayout());
        
        contentPane2 = new JPanel();
        contentPane2.setBackground(new Color(0, 255, 255, 150));
        contentPane2.setLayout(new BorderLayout());
        
        
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(backgroundPanel);

       
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.BOTH;
        gbc1.weightx = 1.0;
        gbc1.weighty = 1.0; 
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(120, 100, 10, 100);
        backgroundPanel.add(contentPane1, gbc1);
        
//        GridBagConstraints gbc2 = new GridBagConstraints();
//        gbc2.fill = GridBagConstraints.BOTH;
//        gbc2.weightx = 1.0;
//        gbc2.weighty = 1.0; 
//        gbc2.gridx = 0;
//        gbc2.gridy = 0;
//        gbc2.insets = new Insets(120, 100, 10, 100);
//        backgroundPanel.add(contentPane2, gbc2);
//        
        
      for (int i = 1; i <= 3; i++) {
          JButton boton = new JButton("Pais" + i); // Cambia el texto según sea necesario
          GridBagConstraints gbcBoton = new GridBagConstraints();
          gbcBoton.gridx = 0; // Columna 0
          gbcBoton.gridy = i - 1; // Fila según el número de etiqueta
          gbcBoton.insets = new Insets(5, 5, 5, 5); // Espacio alrededor de cada JLabel
          gbcBoton.anchor = GridBagConstraints.CENTER;
          contentPane1.add(boton, gbcBoton);
      }
//        
//      gbc2.gridx = 0; 
//	  gbc2.gridy = 0;  
//	  contentPane2.add(lblBanderas, gbc2);
//        
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

