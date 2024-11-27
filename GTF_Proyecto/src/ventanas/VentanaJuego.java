package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import clases.Pais;

public class VentanaJuego extends JFrame {
    private BackgroundPanel backgroundPanel;
    private JPanel contentPane1;
    private JPanel contentPane2;
    private JLabel lblBanderas;
    private JButton[] botones = new JButton[3];
    private String paisCorrecto; 

    public VentanaJuego(String dificultad, String modo) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setIconImage(Toolkit.getDefaultToolkit().getImage(principal.main.class.getResource("/imagenes/logoGTF.jpg")));
        setTitle("Página Historial GTF");
        BackgroundPanel backgroundPanel = new BackgroundPanel("/imagenes/FondoGeneral1.png");
        backgroundPanel.setLayout(new GridBagLayout());

        contentPane1 = new JPanel();
        contentPane1.setOpaque(false);
        contentPane1.setLayout(new GridBagLayout());

        contentPane2 = new JPanel();
        contentPane2.setOpaque(false);
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

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.weightx = 1.0;
        gbc2.weighty = 1.0;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.insets = new Insets(10, 100, 150, 100);
        backgroundPanel.add(contentPane2, gbc2);

        lblBanderas = new JLabel();
        contentPane2.add(lblBanderas, BorderLayout.CENTER);
        lblBanderas.setHorizontalAlignment(SwingConstants.CENTER);
        lblBanderas.setVerticalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 3; i++) {
            botones[i] = new JButton();
            botones[i].addActionListener(e -> {
                JButton botonPresionado = (JButton) e.getSource();
                if (botonPresionado.getText().equals(paisCorrecto)) {

                    if(dificultad.equals("Facil")) {
                 	   configurarJuego("/facil.csv");
                    }else if(dificultad.equals("Medio")){
                 	   configurarJuego("/medio.csv");
                    }else {
                 	   configurarJuego("/dificil.csv");
                    }
                } else {
                    botonPresionado.setBackground(Color.RED);
                }
            });
            GridBagConstraints gbcBoton = new GridBagConstraints();
            gbcBoton.gridx = 0;
            gbcBoton.gridy = i;
            gbcBoton.insets = new Insets(5, 5, 5, 5);
            gbcBoton.anchor = GridBagConstraints.CENTER;
            contentPane1.add(botones[i], gbcBoton);
        }

       if(dificultad.equals("Facil")) {
    	   configurarJuego("/facil.csv");
       }else if(dificultad.equals("Medio")){
    	   configurarJuego("/medio.csv");
       }else {
    	   configurarJuego("/dificil.csv");
       }
        
    }

    private void configurarJuego(String path) {
        try {
            // Obtener datos del archivo usando la clase Pais
            String[] datos = Pais.obtenerDatos(path);

            String rutaImagen = datos[0];
            this.paisCorrecto = datos[1];
            ArrayList<String> nombres = new ArrayList<>();
            nombres.add(paisCorrecto);
            nombres.add(datos[2]);
            nombres.add(datos[3]);

            // Barajar las opciones
            Collections.shuffle(nombres);

            Dimension dimension = new Dimension(250, 40);

            // Configurar botones con las opciones
            for (int i = 0; i < botones.length; i++) {
                botones[i].setText(nombres.get(i));
                botones[i].setBackground(null);
                botones[i].setPreferredSize(dimension);
                botones[i].setMinimumSize(dimension);
                botones[i].setMaximumSize(dimension);
            }

            // Configurar la imagen en el label
            if (rutaImagen != null) {
                lblBanderas.setIcon(null);
                lblBanderas.repaint();

                ImageIcon icon = new ImageIcon(getClass().getResource("/" + rutaImagen));
                Image img = icon.getImage();
                lblBanderas.setIcon(new ImageIcon(img));
                setTitle("Selecciona el país correspondiente");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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