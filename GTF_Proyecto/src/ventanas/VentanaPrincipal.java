package ventanas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame {
    private Image backgroundImage;
    private JPanel contentPane1;
    private JPanel contentPane2;
    private JButton btnJugar;
    private JButton btnCerrarSesion;
    private JButton btnBiblioteca;
    private JButton btnHistorial;
    
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

        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(backgroundPanel);

       
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.weightx = 1.0;
        gbc2.weighty = 1.0; 
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.insets = new Insets(20, 100, 20, 100);
        backgroundPanel.add(contentPane1, gbc2);

        // Codigo para el juego en si
//        for (int i = 1; i <= 3; i++) {
//            JLabel label = new JLabel("Pais" + i); // Cambia el texto según sea necesario
//            GridBagConstraints gbcLabel = new GridBagConstraints();
//            gbcLabel.gridx = 0; // Columna 0
//            gbcLabel.gridy = i - 1; // Fila según el número de etiqueta
//            gbcLabel.insets = new Insets(5, 5, 5, 5); // Espacio alrededor de cada JLabel
//            gbcLabel.anchor = GridBagConstraints.CENTER;
//            contentPane1.add(label, gbcLabel);
//        }

        btnJugar = new JButton("Jugar");
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnBiblioteca = new JButton("Biblioteca");
        btnHistorial = new JButton("Historial");

       
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.fill = GridBagConstraints.HORIZONTAL; 
        gbcButton.weightx = 1.0; 
        gbcButton.insets = new Insets(10, 5, 10, 5); 

       
        gbcButton.gridx = 0; 
        gbcButton.gridy = 0;  
        contentPane1.add(btnJugar, gbcButton);

       
        gbcButton.gridx = 1; 
        contentPane1.add(btnCerrarSesion, gbcButton);
        
        gbcButton.gridx = 0; 
        gbcButton.gridy = 1;
        contentPane1.add(btnBiblioteca, gbcButton);
        
        gbcButton.gridx = 1; 
        contentPane1.add(btnHistorial, gbcButton);
        
        
        
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

    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
    }
}
