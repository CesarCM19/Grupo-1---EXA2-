package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class InsertarDAO {
    public static boolean InsertarUsuario(String Cédula ,String Nombre1, String Nombre2, String Apellido1, String Apellido2, String Usuario, String Contraseña) {
        String sql = "{CALL InsertarUser(?, ?, ?, ?, ?, ?, ?, ?)}";
        // Obtener la fecha actual del sistema
        LocalDate fechaActual = LocalDate.now();
        // Formatear la fecha 
        String fechaFormateada = fechaActual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        try (Connection conn = Conexion.conectar();
            CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, Cédula);
            stmt.setString(2, Nombre1);
            stmt.setString(3, Nombre2);
            stmt.setString(4, Apellido1);
            stmt.setString(5, Apellido2);
            stmt.setString(6, Usuario);
            stmt.setString(7, Contraseña);
            stmt.setString(8, fechaFormateada); 

            int comprobante = stmt.executeUpdate();

            if (comprobante > 0) {
                JOptionPane.showMessageDialog(null, "Usuario Ingresado Exitosamente !", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo insertar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException e) {
            // Manejar errores de SQL y mostrar un mensaje de error
            JOptionPane.showMessageDialog(null, "A ocurrido un error!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
