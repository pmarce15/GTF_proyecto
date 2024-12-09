package gui;

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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import domain.Usuarios;

public class VentanaHistorial extends JFrame {

    private BackgroundPanel backgroundPanel;
    private JPanel contentPane1;
    private JButton botonAtras;
    private JButton botonOrdenar;

    private JTable tablaPartidas;
    private DefaultTableModel modeloPartidas;
    
    private Usuarios user;
    private String dificultad;
    private String modoJuego;
    private int puntuacion;
    
   
    public VentanaHistorial(Usuarios user, String modoJuego, String dificultad, int puntuacion, boolean registrarPartida) {
    	this.user = user;
    	this.dificultad = dificultad;
        this.modoJuego = modoJuego;
        this.puntuacion = puntuacion;

        
        System.out.println("Usuario: " + user.getUsuario());
        System.out.println("Dificultad: " + dificultad);
        System.out.println("Modo de Juego: " + modoJuego);
        System.out.println("Puntuación: " + puntuacion);
    	
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
        
        
        GridBagConstraints gbcOrdenar = new GridBagConstraints();
        gbcOrdenar.fill = GridBagConstraints.NONE;
        gbcOrdenar.insets = new Insets(10, 100, 10, 10); 
        gbcOrdenar.gridx = 0; 
        gbcOrdenar.gridy = 0; 
        gbcOrdenar.anchor = GridBagConstraints.NORTHWEST; 
        botonOrdenar = new JButton("Ordenar por Puntuación");
        botonOrdenar.setPreferredSize(botonAtras.getPreferredSize()); // Igual tamaño al botón "Atrás"
        backgroundPanel.add(botonOrdenar, gbcOrdenar);

        botonOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarTabla(3, false); 
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
        cargarHistorial();
        if (registrarPartida) {
            agregarPartida(modoJuego, dificultad, puntuacion);
        }        
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

        modeloPartidas.addColumn("Fecha");
        modeloPartidas.addColumn("Dificultad");
        modeloPartidas.addColumn("Modo de Juego");
        modeloPartidas.addColumn("Puntuación");

        tablaPartidas = new JTable(modeloPartidas);
        tablaPartidas.setRowHeight(30);
        tablaPartidas.getTableHeader().setReorderingAllowed(false);
        
    }
    
    //IAG ChatGPT
  	//FILE: conversacionesGPT-opendeusto.txt

    
    private void cargarHistorial() {
        modeloPartidas.setRowCount(0); 
        try {
            List<String> lineas = Files.readAllLines(Paths.get("historial_partidas_" + user.getUsuario() + ".txt"));
            for (String linea : lineas) {
                String[] datos = linea.split(";");
                if (datos.length == 4) {
                    modeloPartidas.addRow(datos); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //IAG ChatGPT
  	//FILE: conversacionesGPT-opendeusto.txt

    
    private void agregarPartida(String modoJuego, String dificultad, int puntuacion) {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String[] nuevaPartida = {fecha, modoJuego, dificultad, String.valueOf(puntuacion)};
        modeloPartidas.addRow(nuevaPartida);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("historial_partidas_" + user.getUsuario() + ".txt", true))) {
            writer.write(String.join(";", nuevaPartida));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //IAG ChatGPT
    //File conversacionesGPT-opendeusto.txt
    //SIN CAMBIOS 
    
    private void ordenarTabla(int columna, boolean ascendente) {
        int filas = modeloPartidas.getRowCount();
        String[][] datos = new String[filas][modeloPartidas.getColumnCount()];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < modeloPartidas.getColumnCount(); j++) {
                datos[i][j] = modeloPartidas.getValueAt(i, j).toString();
            }
        }
        datos = mergeSort(datos, columna, ascendente);

        modeloPartidas.setRowCount(0);
        for (String[] fila : datos) {
            modeloPartidas.addRow(fila);
        }
    }
    
    //IAG ChatGPT
    //File conversacionesGPT-opendeusto.txt
    //SIN CAMBIOS 

    private String[][] mergeSort(String[][] datos, int columna, boolean ascendente) {
        if (datos.length <= 1) {
            return datos; 
        }

        int mid = datos.length / 2;

        String[][] izquierda = new String[mid][datos[0].length];
        String[][] derecha = new String[datos.length - mid][datos[0].length];

        System.arraycopy(datos, 0, izquierda, 0, mid);
        System.arraycopy(datos, mid, derecha, 0, datos.length - mid);

        izquierda = mergeSort(izquierda, columna, ascendente);
        derecha = mergeSort(derecha, columna, ascendente);

        return merge(izquierda, derecha, columna, ascendente);
    }
    
    //IAG ChatGPT
    //File conversacionesGPT-opendeusto.txt
    //SIN CAMBIOS 

    private String[][] merge(String[][] izquierda, String[][] derecha, int columna, boolean ascendente) {
        String[][] resultado = new String[izquierda.length + derecha.length][izquierda[0].length];
        int i = 0, j = 0, k = 0;

        while (i < izquierda.length && j < derecha.length) {
            int comparacion;
            try {
                double numIzq = Double.parseDouble(izquierda[i][columna]);
                double numDer = Double.parseDouble(derecha[j][columna]);
                comparacion = Double.compare(numIzq, numDer);
            } catch (NumberFormatException e) {
                comparacion = izquierda[i][columna].compareTo(derecha[j][columna]);
            }

            if ((ascendente && comparacion <= 0) || (!ascendente && comparacion > 0)) {
                resultado[k++] = izquierda[i++];
            } else {
                resultado[k++] = derecha[j++];
            }
        }

        while (i < izquierda.length) {
            resultado[k++] = izquierda[i++];
        }
        while (j < derecha.length) {
            resultado[k++] = derecha[j++];
        }

        return resultado;
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
