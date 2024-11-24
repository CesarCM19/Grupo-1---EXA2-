package Vista;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrmMostrar {
    private Jtable tablaUsuarios;
    Private DefaultTableModel modelotabla;

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

    }
    
}
