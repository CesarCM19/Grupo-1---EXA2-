//Clase realizada por César Campos - C31592
package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

    public class FrmInsertar extends JFrame {
        public FrmInsertar(){
    JPanel Cuadro = new JPanel();
    Cuadro.setBackground(Color.WHITE);
    ImageIcon Fondo = new ImageIcon("Imagenes\\tecnologia.jpg"); 
    JLabel Imagen = new JLabel(Fondo);
    JFrame Ventana = new JFrame("Ingresar Usuario");
    Ventana.setSize(800,1000);
    Ventana.setLocationRelativeTo(null);
    Ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    //*Textos y campos */
    JLabel msjInsertar = new JLabel("Insertar un usuario");
    msjInsertar.setFont(new Font("Arial", Font.BOLD, 30));
    JLabel Nombre1 = new JLabel("Primer Nombre: ");
    JTextField N1txt= new JTextField("");
    JLabel Nombre2 = new JLabel("Segundo Nombre: ");
    JTextField N2txt= new JTextField();
    JLabel Ape1 = new JLabel("Primer Apellido: ");
    JTextField Ape1txt= new JTextField();
    JLabel Ape2 = new JLabel("Segundo Apellido: ");
    JTextField Ape2txt = new JTextField();
    JLabel Usuario = new JLabel("Nombre de usuario: ");
    JTextField Usuariotxt = new JTextField();
    JLabel Contraseña = new JLabel("Contraseña: ");
    JTextField Contratxt = new JTextField();
    JButton btnInsertar = new JButton("Insertar Usuario");
    JButton btnAtras = new JButton("Atrás");

    //*Ubicaciones */
    msjInsertar.setBounds(150,5,300,50);
    Nombre1.setBounds(200,55,100,50);
        N1txt.setBounds(80,105,300,50);
    Nombre2.setBounds(200,155,190,50);
        N2txt.setBounds(80,205,300,50);
    Ape1.setBounds(200,255,100,50);
        Ape1txt.setBounds(80,305,300,50);
    Ape2.setBounds(200,355,360,50);
        Ape2txt.setBounds(80,405,300,50);
    Usuario.setBounds(200,455,200,50);
        Usuariotxt.setBounds(80,505,300,50);
    Contraseña.setBounds(80,555,200,50);
        Contratxt.setBounds(80,605,50,50);
    btnInsertar.setBounds(300,670,200,30);
    btnAtras.setBounds(80,670,200,30);

    //*Agregación Elementos gráficos */
    Ventana.add(msjInsertar);
    Ventana.add(Nombre1);
    Ventana.add(Nombre2);
    Ventana.add(Ape1);
    Ventana.add(Ape1txt);
    Ventana.add(Ape2txt);
    Ventana.add(Ape2);
    Ventana.add(N1txt);
    Ventana.add(N2txt);
    Ventana.add(Usuario);
    Ventana.add(Usuariotxt);
    Ventana.add(Contraseña);
    Ventana.add(Contratxt);
    Ventana.add(btnInsertar);
    Ventana.add(btnAtras);
    Ventana.add(Imagen);
    Ventana.setVisible(true);

    //*Acciones de los botones */
    btnInsertar.addActionListener(new ActionListener (){
        public void actionPerformed ( ActionEvent e){
            new Modelo.Conexion();

        }
    });
    }//Fin del constructor

    public static void main(String[] args) {
        new FrmInsertar();
    }
}//Fin de la clase