package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import clases.Pais;
import modelo.Usuarios;

public class VentanaBiblioteca extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton btnAtras;
    private JButton btnFacil;
    private JButton btnMedio;
    private JButton btnDificil;
    private JTable tablaPaises;
    private DefaultTableModel modeloTabla;

    public VentanaBiblioteca(Usuarios user) {
        // Configuración básica de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/logoGTF.jpg")));
        setTitle("Página principal GTF");
        setResizable(false);
        setLocationRelativeTo(null);

        // Configurar el fondo
        BackgroundPanel backgroundPanel = new BackgroundPanel("/imagenes/FondoGeneral1.png");
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // Crear barra de navegación superior
        JPanel panelNavegacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNavegacion.setOpaque(false);

        btnAtras = new JButton("Atrás");
        btnFacil = new JButton("Fácil");
        btnMedio = new JButton("Medio");
        btnDificil = new JButton("Difícil");

        panelNavegacion.add(btnAtras);
        panelNavegacion.add(btnFacil);
        panelNavegacion.add(btnMedio);
        panelNavegacion.add(btnDificil);

        backgroundPanel.add(panelNavegacion, BorderLayout.NORTH);

        // Crear tabla para mostrar los países
        modeloTabla = new DefaultTableModel(new String[]{"Nombre del País", "Imagen"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return (columnIndex == 1) ? ImageIcon.class : String.class;
            }
        };

        tablaPaises = new JTable(modeloTabla);
        tablaPaises.setRowHeight(80);

        // Personalizar el renderizado de las celdas
        DefaultTableCellRenderer textoCentrado = new DefaultTableCellRenderer();
        textoCentrado.setHorizontalAlignment(SwingConstants.CENTER);
        textoCentrado.setFont(new Font("Serif", Font.BOLD, 16));
        tablaPaises.getColumnModel().getColumn(0).setCellRenderer(textoCentrado);

        JScrollPane scrollPane = new JScrollPane(tablaPaises);
        scrollPane.getViewport().setBackground(new Color(240, 240, 240)); // Fondo para la tabla
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        // Agregar funcionalidad a los botones
        btnFacil.addActionListener(e -> cargarPaisesEnTabla("/facil.csv"));
        btnMedio.addActionListener(e -> cargarPaisesEnTabla("/medio.csv"));
        btnDificil.addActionListener(e -> cargarPaisesEnTabla("/dificil.csv"));

        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal vp = new VentanaPrincipal(user);
                vp.setVisible(true);
                dispose();
            }
        });
    }

    private void cargarPaisesEnTabla(String path) {
        try {
            Map<String, String> mapaPaises = Pais.cargarPaises(path);
            insertarEnTabla(mapaPaises);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertarEnTabla(Map<String, String> mapaPaises) {
        modeloTabla.setRowCount(0); // Limpiar la tabla

        for (Map.Entry<String, String> entry : mapaPaises.entrySet()) {
            String nombrePais = entry.getKey();
            String rutaImagen = entry.getValue();
            ImageIcon imagen = new ImageIcon(getClass().getResource("/" + rutaImagen));

            // Escalar la imagen para que sea rectangular como una bandera
            Image imagenEscalada = imagen.getImage().getScaledInstance(120, 80, Image.SCALE_SMOOTH);
            modeloTabla.addRow(new Object[]{nombrePais, new ImageIcon(imagenEscalada)});
        }
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
