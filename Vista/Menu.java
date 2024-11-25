package Vista;

import java.awt.*;
import javax.swing.*;

public class Menu extends JFrame {
    private JPanel cuadro;
    private JButton btnIngresar, btnActualizar, btnEliminar, btnMostrar, btnSalir;

    public Menu() {
        // Configuración de la ventana principal
        setTitle("Menú Principal");
        setSize(800, 550);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Agregar la imagen de fondo
        ImageIcon fondo = new ImageIcon("Imagenes\\tecnologia.jpg");
        JLabel imagenFondo = new JLabel(fondo);
        imagenFondo.setSize(fondo.getIconWidth(), fondo.getIconHeight()); // Ajustar el tamaño al tamaño de la imagen
        add(imagenFondo);

        // Creacion del panel donde van los botones
        cuadro = new JPanel();
        cuadro.setBackground(new Color(247, 255, 255, 180));
        cuadro.setLayout(null);
        cuadro.setBounds(100, 60, 600, 400);
        imagenFondo.add(cuadro);

        // Título del menú y configuracionn de este
        JLabel etiquetaTitulo = new JLabel("Bienvenido al Menú Principal");
        etiquetaTitulo.setFont(new Font("Times New Roman", Font.BOLD, 20));
        etiquetaTitulo.setBounds(170, 10, 300, 30);
        etiquetaTitulo.setForeground(new Color(38, 104, 184));
        cuadro.add(etiquetaTitulo);

        // Configuración de los botones, tamaño, posición y espacio
        int botonAncho = 200;
        int botonAlto = 50;
        int espacio = 20;
        int panelCentroX = cuadro.getWidth() / 2 - botonAncho / 2; // Centrar los botones en el panel

        // Crear y agregar los botones
        btnIngresar = crearBoton("Insertar Usuario", panelCentroX, 60, "Insertar un nuevo usuario",
                "Imagenes\\Registrar.png");
        cuadro.add(btnIngresar);

        btnActualizar = crearBoton("Actualizar Usuario", panelCentroX, 60 + (botonAlto + espacio),
                "Actualizar información de un usuario", "Imagenes\\editar.png");
        cuadro.add(btnActualizar);

        btnMostrar = crearBoton("Mostrar Usuarios", panelCentroX, 60 + 2 * (botonAlto + espacio),
                "Mostrar lista de usuarios", "Imagenes\\buscar.png");
        cuadro.add(btnMostrar);

        btnEliminar = crearBoton("Eliminar Usuario", panelCentroX, 60 + 3 * (botonAlto + espacio),
                "Eliminar un usuario existente", "Imagenes\\eliminar.png");
        cuadro.add(btnEliminar);

        btnSalir = crearBoton("Salir", panelCentroX, 60 + 4 * (botonAlto + espacio), "Cerrar la aplicación",
                "Imagenes\\Salir.png");
        cuadro.add(btnSalir);

        // Funcionalidades de los botones

        // Botón Insertar
        btnIngresar.addActionListener(e -> {
            new FrmInsertar();
            dispose();
        });

        // Botón Actualizar
        btnActualizar.addActionListener(e -> {
            new FrmActualizar();
            dispose();
        });

        // Botón Mostrar
        btnMostrar.addActionListener(e -> {
            new FrmMostrar();
            dispose();
        });

        // Botón Eliminar
        btnEliminar.addActionListener(e -> {
            new FrmEliminar();
            dispose();
        });

        // Botón Salir
        btnSalir.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?", "Confirmación",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Gracias por preferirnos", "Cerrando sistema",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0); // Cerrar la aplicación
            }
        });

        setVisible(true); 
    }

    // Método para crear botones con íconos y texto
    private JButton crearBoton(String texto, int x, int y, String tooltip, String rutaIcono) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Times New Roman", Font.BOLD, 16)); 
        boton.setBounds(x, y, 200, 50); // tamaño 
        boton.setBackground(new Color(105, 149, 203)); // Color 
        boton.setForeground(Color.WHITE); 
        boton.setFocusPainted(false); 
        boton.setBorder(BorderFactory.createLineBorder(new Color(186, 140, 99), 1, true)); 
        boton.setContentAreaFilled(true);
        boton.setOpaque(true); 
        boton.setToolTipText(tooltip);

        // Agregar ícono al botón
        ImageIcon icono = new ImageIcon(rutaIcono); // Cargar el ícono de la ruta proporcionada
        Image imagen = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Ajustar el tamaño del ícono
        boton.setIcon(new ImageIcon(imagen));

        // Alineación del texto y el ícono
        boton.setHorizontalTextPosition(SwingConstants.RIGHT); // El texto se muestra a la derecha del ícono
        boton.setIconTextGap(10); // Espacio entre el ícono y el texto

        return boton; 
    }

}
