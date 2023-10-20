
package proyectobiblioteca.controllers;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import proyectobiblioteca.config.BasedeDatos;
import proyectobiblioteca.models.AutorDTO;
import proyectobiblioteca.models.LibrosDTO;


public class AutorDAO {
    public DefaultTableModel ObtenerTablaAutores() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Autor");
        modelo.addColumn("Nombre");
        try {
            BasedeDatos.conectar();
            ResultSet resultado = BasedeDatos.ejecutarSQL("SELECT * FROM autor");
            if (resultado != null) {
                while (resultado.next()) {
                    int id_autor = resultado.getInt("id_autor");
                    String nombre = resultado.getString("nombre");
                    
                    AutorDTO autorDTO=new AutorDTO();
                    autorDTO.setId_autor(id_autor);
                    
                    // Agregamos los datos al modelo
                    Object[] fila = {id_autor, nombre};
                    modelo.addRow(fila);
                }
            } else {
                System.out.println("No se pudo ejecutar la consulta SQL.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }

        return modelo;
    }

    
}
