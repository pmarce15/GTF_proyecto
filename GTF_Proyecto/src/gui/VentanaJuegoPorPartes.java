package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
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
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.daoUsuario;
import domain.Pais;
import domain.Usuarios;

public class VentanaJuegoPorPartes extends JFrame {
    private JLabel lblBanderas;
    private JLabel lblPuntuacion;
    private JTextField textFieldRespuesta;
    private JButton btnPista;
    private int puntuacion = 0;
    private String paisCorrecto;
    private Set<String> paisesMostrados = new HashSet<>();
    private daoUsuario dao = new daoUsuario();
    private JLabel[] cubiertas = new JLabel[3];
    private int pistasUsadas = 0;
    private String modoJuego;
    private String dificultad;

    public VentanaJuegoPorPartes(String dificultad,String modo, Usuarios user) {
    	
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

        BackgroundPanel backgroundPanel = new BackgroundPanel("/imagenes/FondoGeneral1.png");
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        Font font = cargarFuente("/pixelFont/Ka1.ttf");

        lblPuntuacion = new JLabel("Puntos: 0");
        lblPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
        lblPuntuacion.setOpaque(false);
        lblPuntuacion.setFont(font.deriveFont(16f));
        lblPuntuacion.setForeground(Color.BLACK);
        backgroundPanel.add(lblPuntuacion, BorderLayout.NORTH);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(300, 150));
        backgroundPanel.add(layeredPane, BorderLayout.CENTER);

        lblBanderas = new JLabel();
        lblBanderas.setBounds(75, 0, 300, 150);
        layeredPane.add(lblBanderas, JLayeredPane.DEFAULT_LAYER);

        for (int i = 0; i < cubiertas.length; i++) {
            cubiertas[i] = new JLabel();
            cubiertas[i].setOpaque(true);
            cubiertas[i].setBackground(Color.GRAY); 
            cubiertas[i].setBounds(i * 100, 0, 100, 150); 
            layeredPane.add(cubiertas[i], JLayeredPane.PALETTE_LAYER);
        }

        JPanel panelInferior = new JPanel();
        panelInferior.setOpaque(false);
        panelInferior.setLayout(new BorderLayout());
        backgroundPanel.add(panelInferior, BorderLayout.SOUTH);

        JPanel textPanel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.CENTER));
        textPanel.setOpaque(false); 
        textFieldRespuesta = new JTextField(15);
        textFieldRespuesta.setFont(font.deriveFont(16f));
        textFieldRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(textFieldRespuesta);
        panelInferior.add(textPanel, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setOpaque(false);
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(font.deriveFont(16f));
        btnConfirmar.addActionListener(e -> verificarRespuesta(dificultad, user));
        panelBotones.add(btnConfirmar);

        btnPista = new JButton("Pista");
        btnPista.setFont(font.deriveFont(16f));
        btnPista.addActionListener(e -> darPista());
        panelBotones.add(btnPista);

        panelInferior.add(panelBotones, BorderLayout.SOUTH);

        configurarJuego(dificultad);
    }

    private void verificarRespuesta(String dificultad, Usuarios user) {
        String respuesta = normalizarTexto(textFieldRespuesta.getText());
        String paisCorrectoNormalizado = normalizarTexto(paisCorrecto);

        if (respuesta.equals(paisCorrectoNormalizado)) {
            int puntosGanados = Math.max(calcularPuntos(dificultad) - pistasUsadas * 5, 1);
            puntuacion += puntosGanados;
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
                ImageIcon icon = new ImageIcon(getClass().getResource("/" + rutaImagen));
                Image img = icon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
                lblBanderas.setIcon(new ImageIcon(img));

                for (JLabel cubierta : cubiertas) {
                    cubierta.setVisible(true);
                }
                pistasUsadas = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void darPista() {
        if (pistasUsadas < cubiertas.length) {
            cubiertas[pistasUsadas].setVisible(false);
            pistasUsadas++;
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

    private Font cargarFuente(String path) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(path));
            return font.deriveFont(Font.BOLD, 20);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("Arial", Font.BOLD, 20);
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

