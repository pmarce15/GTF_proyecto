package ventanas;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


	public class VentanaPrincipal extends JFrame {
	    private JPanel contentPane;
		public VentanaPrincipal() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100,100,450,300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5,5,5,5));
			contentPane.setLayout(new GridBagLayout());
			setResizable(false);
			setLocationRelativeTo(null);
			setContentPane(contentPane);
}
	}
