//Clase realizada por César Campos - C31592
package Vista;

import javax.swing.*;

import Modelo.InsertarDAO;

import java.awt.*;
import java.awt.event.*;

    public class FrmInsertar extends JFrame {
        public FrmInsertar(){
    JPanel Cuadro = new JPanel();
    Cuadro.setBackground(new Color(247, 255, 255, 180));
    ImageIcon Fondo = new ImageIcon("Imagenes\\tecnologia.jpg"); 
    JLabel Imagen = new JLabel(Fondo);
    JFrame Ventana = new JFrame("Ingresar Usuario");
    Ventana.setSize(800,900);
    Ventana.setLocationRelativeTo(null);
    Ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    //*Textos y campos */
    JLabel msjInsertar = new JLabel("Insertar un usuario");
    msjInsertar.setFont(new Font("Arial", Font.BOLD, 30));
    JLabel Nombre1 = new JLabel("Primer Nombre: ");
    JTextField N1txt= new JTextField();
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
    JButton btnInsertar = FrmMostrar.crearBoton("Crear Usuario",400,750,"Imagenes\\ingresarUsu.png","Crear Usuario");
    JButton btnAtras = FrmMostrar.crearBoton("Atrás",180,750,"Imagenes\\Salir.png","Regresar al menú");

    //*Ubicaciones */
    msjInsertar.setBounds(270,55,300,50);
    Cuadro.setBounds(100,30,600,800);
    Nombre1.setBounds(350,105,100,50);
        N1txt.setBounds(250,155,300,50);
    Nombre2.setBounds(350,205,190,50);
        N2txt.setBounds(250,255,300,50);
    Ape1.setBounds(350,305,100,50);
        Ape1txt.setBounds(250,355,300,50);
    Ape2.setBounds(350,405,200,50);
        Ape2txt.setBounds(250,455,300,50);
    Usuario.setBounds(350,505,200,50);
        Usuariotxt.setBounds(250,555,300,50);
    Contraseña.setBounds(350,605,200,50);
        Contratxt.setBounds(250,655,300,50);
    btnInsertar.setSize(200,30);
    btnAtras.setSize(200,30);

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
    Ventana.add(Cuadro);
    Ventana.add(Imagen);
    Ventana.setVisible(true);

    //*Acciones de los botones */
    btnInsertar.addActionListener(new ActionListener (){
        public void actionPerformed ( ActionEvent e){
            String PNombre = N1txt.getText();
            String SNombre = N2txt.getText();
            String PApellido = Ape1txt.getText();
            String SApellido = Ape2txt.getText();
            String User = Usuariotxt.getText();
            String Pass = Contratxt.getText();
            new Modelo.Conexion();
            InsertarDAO.InsertarUsuario(PNombre, SNombre, PApellido,SApellido,User, Pass);
        }
    });
    btnAtras.addActionListener(new ActionListener (){
        @Override
        public void actionPerformed (ActionEvent e){
            Ventana.dispose();
            new Menu().setVisible(true);
        }
    });
    }//Fin del constructor
    public static void main(String[] args) {
        new FrmInsertar();
    }
}//Fin de la clase