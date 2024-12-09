package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.daoUsuario;
import domain.Pais;
import domain.Usuarios;

public class VentanaJuegoEscribir extends JFrame {
    private BackgroundPanel backgroundPanel;
    private JPanel contentPane1;
    private JLabel lblBanderas;
    private JLabel lblPuntuacion;
    private JTextField textFieldRespuesta;
    private int puntuacion = 0;
    private String paisCorrecto;
    private Set<String> paisesMostrados = new HashSet<>();
    private daoUsuario dao = new daoUsuario();
    private String modoJuego;
    private String dificultad;

    public VentanaJuegoEscribir(String dificultad, String modo, Usuarios user) {
    	
    	this.modoJuego = modo;
    	this.dificultad = dificultad;
    	
    	user.setDificultad(dificultad);
    	user.setModoJuego(modo);
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setIconImage(Toolkit.getDefaultToolkit().getImage(principal.main.class.getResource("/imagenes/logoGTF.jpg")));
        setTitle("Adivina el País");
        setResizable(false);
        setLocationRelativeTo(null);

        backgroundPanel = new BackgroundPanel("/imagenes/FondoGeneral1.png");
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        contentPane1 = new JPanel();
        contentPane1.setOpaque(false);
        contentPane1.setLayout(new BorderLayout());

        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/pixelFont/Ka1.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        font = font.deriveFont(Font.BOLD, 20);

        lblPuntuacion = new JLabel("Puntos: 0");
        lblPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
        lblPuntuacion.setForeground(Color.BLACK);
        lblPuntuacion.setFont(font);

        lblBanderas = new JLabel();
        lblBanderas.setHorizontalAlignment(SwingConstants.CENTER);
        lblBanderas.setVerticalAlignment(SwingConstants.CENTER);

        textFieldRespuesta = new JTextField();
        textFieldRespuesta.setFont(font);
        textFieldRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldRespuesta.setPreferredSize(new Dimension(200, 40));

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(e -> verificarRespuesta(dificultad, user));
        btnConfirmar.setFont(font);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(lblPuntuacion, gbc);

        gbc.gridy = 1;
        gbc.weighty = 1.0;
        backgroundPanel.add(lblBanderas, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0;
        backgroundPanel.add(textFieldRespuesta, gbc);

        gbc.gridy = 3;
        backgroundPanel.add(btnConfirmar, gbc);

        configurarJuego(dificultad);
    }

    private void verificarRespuesta(String dificultad, Usuarios user) {
        String respuesta = normalizarTexto(textFieldRespuesta.getText());
        String paisCorrectoNormalizado = normalizarTexto(paisCorrecto);

        if (respuesta.equals(paisCorrectoNormalizado)) {
            puntuacion += calcularPuntos(dificultad);
            lblPuntuacion.setText("Puntos: " + puntuacion);
            configurarJuego(dificultad);
            textFieldRespuesta.setText("");
        } else {
            finalizarJuego(user);
        }
    }

    private int calcularPuntos(String dificultad) {
        switch (dificultad) {
            case "Facil":
                return 10;
            case "Medio":
                return 25;
            case "Dificil":
                return 50;
            default:
                return 0;
        }
    }

    private void configurarJuego(String dificultad) {
        try {
            Map<String, String> datos = Pais.cargarPaises("/" + dificultad.toLowerCase() + ".csv");

            List<String> nombresPaises = new ArrayList<>(datos.keySet());
            nombresPaises.removeAll(paisesMostrados);

            if (nombresPaises.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "¡No quedan más países por mostrar!",
                    "Fin del Juego",
                    JOptionPane.INFORMATION_MESSAGE
                );
                finalizarJuego(null);
                return;
            }

            Collections.shuffle(nombresPaises);
            paisCorrecto = nombresPaises.get(0);
            paisesMostrados.add(paisCorrecto);

            String rutaImagen = datos.get(paisCorrecto);

            if (rutaImagen != null) {
                lblBanderas.setIcon(null);
                lblBanderas.repaint();

                ImageIcon icon = new ImageIcon(getClass().getResource("/" + rutaImagen));
                Image img = icon.getImage();
                lblBanderas.setIcon(new ImageIcon(img));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void finalizarJuego(Usuarios user) {
        if (user != null) {
            int nuevaPuntuacion = puntuacion;
            user.setPuntuacion(nuevaPuntuacion);
            dao.actualizarPuntuacion(user);
            dao.agregarPartidaAlHistorial(user, nuevaPuntuacion, dificultad, modoJuego);
        }

        JOptionPane.showMessageDialog(
            this,
            "Juego terminado. Puntuación final: " + puntuacion,
            "Fin del Juego",
            JOptionPane.INFORMATION_MESSAGE
        );

        int opcion = JOptionPane.showOptionDialog(
            null,
            "¿A dónde quieres ir?",
            "Opciones",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new Object[] { "Ir a Ventana Principal", "Ir a Historial" },
            "Ir a Ventana Principal"
        );

        if (opcion == JOptionPane.YES_OPTION) {
        	VentanaHistorial vhFake = new VentanaHistorial(user, user.getModoJuego(), user.getDificultad(), puntuacion, true);
            vhFake.dispose();
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(user);
            ventanaPrincipal.setVisible(true);
        } else if (opcion == JOptionPane.NO_OPTION) {
            VentanaHistorial ventanaHistorial = new VentanaHistorial(
                user,
                user.getDificultad(),
                user.getModoJuego(),
                puntuacion,
                true
            );
            ventanaHistorial.setVisible(true);
        }
        dispose();
    }


    private String normalizarTexto(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toLowerCase();
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

