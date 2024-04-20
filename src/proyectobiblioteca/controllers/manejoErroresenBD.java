/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectobiblioteca.controllers;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import proyectobiblioteca.config.BasedeDatos;
/**
 *
 * @author juang
 */
public class manejoErroresenBD {
    
        public static void registrarError(String error, String modulo) {
        try {
            BasedeDatos.conectar();
            String consulta = "INSERT INTO error_log (error, modulo) VALUES (?, ?)";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            statement.setString(1, error);
            statement.setString(2, modulo);

            statement.executeUpdate();
            // BasedeDatos.desconectar();
// Crea un nuevo JDialog personalizado

// Muestra un mensaje en el cuadro de diálogo
            JOptionPane.showMessageDialog(null, "Se ha registrado el error: " + error);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }
    }
    
}
