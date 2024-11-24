package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmMostrar extends JFrame {
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;

    public FrmMostrar(){
        setTitle("Ver Usuarios");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1190, 720);
        setLocationRelativeTo(null);
        setResizable(false);

        //panel principal con diseño transparante
        JPanel cuadro = new JPanel();
        cuadro.setBackground(new Color(247, 255, 255, 180)); 
        cuadro.setLayout(null);
        cuadro.setBounds(150, 110, 900, 400);

        // Fondo de la ventana
        ImageIcon fondo = new ImageIcon("PF_CesarCM_BryanCE\\src\\Images\\tecnologia.jpg");
        JLabel imagenFondo = new JLabel(fondo);
        imagenFondo.setSize(fondo.getIconWidth(), fondo.getIconHeight());

          // Título centrado
        JLabel etiquetaTitulo = new JLabel("Usuarios Actuales");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        etiquetaTitulo.setBounds(355, 20, 250, 40);
        etiquetaTitulo.setForeground(new Color(38, 104, 184));
        cuadro.add(etiquetaTitulo);

        // Configuración de la tabla
        String[] columnas = {"ID", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Login", "Clave"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaUsuarios = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setBounds(50, 80, 800, 250);
        cuadro.add(scrollPane);
    }
    
    
    
    
}
