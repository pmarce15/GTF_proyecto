package ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import modelo.Usuarios;

public class VentanaHistorial extends JFrame {

    private BackgroundPanel backgroundPanel;
    private JPanel contentPane1;
    private JButton botonAtras;

    private JTable tablaPartidas;
    private DefaultTableModel modeloPartidas;

    public VentanaHistorial(Usuarios user) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setIconImage(Toolkit.getDefaultToolkit().getImage(principal.main.class.getResource("/imagenes/logoGTF.jpg")));
        setTitle("Página Historial GTF");
        setResizable(false);
        setLocationRelativeTo(null);

        backgroundPanel = new BackgroundPanel("/imagenes/FondoGeneral1.png");
        backgroundPanel.setLayout(new GridBagLayout());

        contentPane1 = new JPanel();
        contentPane1.setBackground(new Color(0, 255, 255, 150));
        contentPane1.setLayout(new GridBagLayout());

        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(backgroundPanel);

        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.fill = GridBagConstraints.NONE;
        gbcButton.insets = new Insets(10, 10, 10, 10);
        gbcButton.gridx = 0;
        gbcButton.gridy = 0;
        gbcButton.anchor = GridBagConstraints.NORTHWEST;

        botonAtras = new JButton("Atrás"); 
        backgroundPanel.add(botonAtras, gbcButton);

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal vp = new VentanaPrincipal(user);
                vp.setVisible(true);
                dispose();
            }
        });

        JLabel lblBienvenida = new JLabel("Bienvenido, " + user.getUsuario().toString());
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 18));
        lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenida.setForeground(Color.BLACK);
        lblBienvenida.setOpaque(true);
        lblBienvenida.setBackground(Color.WHITE);

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 1; 
        gbcLabel.weightx = 1.0;
        gbcLabel.insets = new Insets(10, 0, 10, 0); 
        gbcLabel.fill = GridBagConstraints.HORIZONTAL;
        backgroundPanel.add(lblBienvenida, gbcLabel);

        GridBagConstraints gbcContentPane = new GridBagConstraints();
        gbcContentPane.gridx = 0;
        gbcContentPane.gridy = 2; 
        gbcContentPane.weightx = 1.0;
        gbcContentPane.weighty = 1.0;
        gbcContentPane.fill = GridBagConstraints.BOTH;
        gbcContentPane.insets = new Insets(20, 20, 20, 20); 
        backgroundPanel.add(contentPane1, gbcContentPane);

        crearTablaPartidas();
        JScrollPane scrollPane = new JScrollPane(tablaPartidas);
        GridBagConstraints gbcTable = new GridBagConstraints();
        gbcTable.gridx = 0;
        gbcTable.gridy = 0;
        gbcTable.weightx = 1.0;
        gbcTable.weighty = 1.0;
        gbcTable.fill = GridBagConstraints.BOTH;
        gbcTable.insets = new Insets(10, 10, 10, 10);
        contentPane1.add(scrollPane, gbcTable);
    }

    private void crearTablaPartidas() {
        modeloPartidas = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modeloPartidas.addColumn("Modo de Juego");
        modeloPartidas.addColumn("Dificultad");
        modeloPartidas.addColumn("Puntuación");

        // Datos ejemplo
//        modeloPartidas.addRow(new Object[]{"Clásico", "Fácil", 150});
//        modeloPartidas.addRow(new Object[]{"Supervivencia", "Difícil", 300});

        tablaPartidas = new JTable(modeloPartidas);
        tablaPartidas.setRowHeight(30);
        tablaPartidas.getTableHeader().setReorderingAllowed(false);
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
