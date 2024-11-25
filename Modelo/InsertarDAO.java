package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


import javax.swing.JOptionPane;

public class InsertarDAO {
    public static boolean InsertarUsuario(String Nombre1, String Nombre2, String Apellido1, String Apellido2, String Usuario, String Contraseña) {
        String sql = "{CALL InsertarUsuario(?, ?, ?, ?, ?, ?)}";

        try (Connection conn = Conexion.conectar()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        
            CallableStatement stmt = conn.prepareCall(sql); 

            stmt.setString(1, Nombre1);
            stmt.setString(2, Nombre2);
            stmt.setString(3, Apellido1);
            stmt.setString(4, Apellido2);
            stmt.setString(5, Usuario);
            stmt.setString(6, Contraseña);

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
            JOptionPane.showMessageDialog(null, "A ocurrido un error!"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
