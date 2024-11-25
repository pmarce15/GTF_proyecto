package ventanas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class VentanaHistorial extends JFrame {

	private BackgroundPanel backgroundPanel;
	private JPanel contentPane1;
	private JButton botonAtras;
	
	public VentanaHistorial() {
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

       
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.BOTH;
        gbc1.weightx = 1.0;
        gbc1.weighty = 1.0; 
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(50, 50, 50, 50);
        backgroundPanel.add(contentPane1, gbc1);
        
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.fill = GridBagConstraints.NONE; 
        gbcButton.insets = new Insets(0, 0, 10, 5); 
        
        botonAtras = new JButton("Atras");  //EDITAR PARA PONER FLECHA HACIA ATRAS
        
        gbcButton.gridx = 0; 
        gbcButton.gridy = 0;
        gbcButton.anchor = GridBagConstraints.NORTHWEST;
        backgroundPanel.add(botonAtras, gbcButton);
        
        
        botonAtras.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaPrincipal vp = new VentanaPrincipal();
				vp.setVisible(true);
				dispose();
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
