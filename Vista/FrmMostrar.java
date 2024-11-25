package Vista;

import Modelo.MostrarDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
        ImageIcon fondo = new ImageIcon("Imagenes\\tecnologia.jpg");
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
    
        // Botón para regresar
   JButton botonRegresar = crearBoton("Regresar al Menú", 330, 340, "Imagenes\\hogar.png", " Al presionar regresará al menú principal");
   cuadro.add(botonRegresar);

    
        // Agregar componentes al JFrame
        add(cuadro);
        add(imagenFondo);
        setVisible(true);

   
   
   // Llenar la tabla con datos
   cargarUsuarios();

   // Acción del botón regresar
   botonRegresar.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
             setVisible(false);
           dispose();
           new Menu().setVisible(true); // 
           
       }
   });
}

// Método para cargar datos en la tabla
private void cargarUsuarios() {
    MostrarDAO MostrarDAO = new MostrarDAO();
   List<Object[]> usuarios = MostrarDAO.obtenerUsuarios();

   modeloTabla.setRowCount(0); // Limpiar datos previos
   for (Object[] usuario : usuarios) {
       modeloTabla.addRow(usuario);
   }
}



    
    /* Metodo para crear boton con icono, 
    ademas de funciones como la de tooltip para
    mostrar mensaje cuando  el cursor se pone encima
    */
    public static JButton crearBoton(String texto, int x, int y, String rutaImagen, String tooltip) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBounds(x, y, 250, 50);
        boton.setBackground(new Color(105, 149, 203));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(186, 140, 99), 1, true));
        boton.setContentAreaFilled(true);
        boton.setOpaque(true);

        try {
            ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imagenEscalada));
        } catch (Exception e) {
            System.out.println("Error al cargar el icono: " + e.getMessage());
        }

        boton.setHorizontalTextPosition(SwingConstants.RIGHT);
        boton.setIconTextGap(10);
        boton.setToolTipText(tooltip);

        return boton;
    }
   
    //comentario
}
