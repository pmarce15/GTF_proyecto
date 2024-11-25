package ventanas;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaBiblioteca extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnNivel1;
	private JButton btnNivel2;
	private JButton btnNivel3;
	private JButton btnAtras;
	
	public VentanaBiblioteca() {
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setBounds(100, 100, 450, 300);
	     setIconImage(Toolkit.getDefaultToolkit().getImage(principal.main.class.getResource("/imagenes/logoGTF.jpg")));
	     setTitle("Página principal GTF");
	     BackgroundPanel backgroundPanel = new BackgroundPanel("/imagenes/FondoGeneral1.png");
	     backgroundPanel.setLayout(new GridBagLayout());
	     setContentPane(backgroundPanel);
	     
	     //backgroundPanel.add(btnAtras, BorderLayout.NORTH);
	     
	     
	     
	     
//	     btnAtras.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
//				ventanaPrincipal.setVisible(true);
//				dispose();
//			}
//		});
	        
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

}
