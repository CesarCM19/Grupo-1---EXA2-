//Clase realizada por César Campos - C31592
package Vista;

import javax.swing.*;

    public class FrmIngresar extends JFrame {
        public FrmIngresar(){
    ImageIcon Fondo = new ImageIcon("Imagenes\\Fondo.png"); 
    JFrame Ventana = new JFrame();
    Ventana.setSize(500,400);
    Ventana.setLocationRelativeTo(null);
    Ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    //*Textos y campos */
    JLabel msjInsertar = new JLabel("Insertar un usuario");
    JLabel Nombre1 = new JLabel("Primer Nombre: ");
    JTextField N1txt= new JTextField();
    JLabel Nombre2 = new JLabel("Segundo Nombre: ");
    JTextField N2txt= new JTextField();
    JLabel Ape1 = new JLabel("Primer Apellido: ");
    JTextField APe1txt= new JTextField();
    JLabel Ape2 = new JLabel("Segundo Apellido: ");
    JTextField Ape2txt = new JTextField();
    JLabel Usuario = new JLabel("Nombre de usuario: ");
    JTextField Usuariotxt = new JTextField();
    JLabel Contraseña = new JLabel("Contraseña: ");
    JTextField Contratxt = new JTextField();
    

    }
}