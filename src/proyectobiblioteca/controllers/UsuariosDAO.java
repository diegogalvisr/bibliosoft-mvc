package proyectobiblioteca.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectobiblioteca.config.BasedeDatos;
import proyectobiblioteca.models.UsuariosDTO;
import proyectobiblioteca.models.adminsDTO;


public class UsuariosDAO {
    public ArrayList<UsuariosDTO> obtenerUsers() {
    ArrayList<UsuariosDTO> users = new ArrayList<>();
    try {
        BasedeDatos.conectar();
        ResultSet resultado = BasedeDatos.ejecutarSQL("SELECT * FROM usuarios");

        if (resultado != null) { // Verificar que el resultado no sea null
            while (resultado.next()) {
                int id_usuario = resultado.getInt("id_usuario");
                int user_generico = resultado.getInt("user_generico");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String direccion = resultado.getString("direccion");
                String telefono = resultado.getString("telefono");
                String telefonoF = resultado.getString("telefonoF");
                int grado = resultado.getInt("grado");
                int id_multa = resultado.getInt("id_multa");
                users.add(new UsuariosDTO(id_usuario, user_generico, nombre, apellido, direccion, telefono, telefonoF, grado, id_multa));
            }
        } else {
            System.out.println("No se pudo ejecutar la consulta SQL.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "He encontrado un error al momento de realizacion la gestion, porfavor contacte con soporte CODIGO: 000US", "ERROR - Bibliosoft", JOptionPane.WARNING_MESSAGE);
        manejoErroresenBD.registrarError(e.getMessage(), "USUARIOS - GET");
    } finally {
        BasedeDatos.desconectar();
    }
    return users;
}

    
     public static void cargarGrados(JComboBox<String> comboBox) {
        try {
            BasedeDatos.conectar();
            String consulta = "SELECT numero FROM grados";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            ResultSet resultado = statement.executeQuery();

            // Limpiar el JComboBox antes de agregar los nombres de autores
            comboBox.removeAllItems();
            comboBox.addItem("Seleccione");
            while (resultado.next()) {
                String nombreAutor = resultado.getString("numero");
                comboBox.addItem(nombreAutor);
            }

            // Cerrar los recursos
            resultado.close();
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "He encontrado un error al momento de realizacion la gestion, porfavor contacte con soporte CODIGO: 000US", "ERROR - Bibliosoft", JOptionPane.WARNING_MESSAGE);
         manejoErroresenBD.registrarError(e.getMessage(), "USUARIOS - GRADOS");
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }
    }

 public DefaultTableModel tablaUsers() {
    DefaultTableModel modelo = new DefaultTableModel();

    // Definimos las columnas del modelo
    modelo.addColumn("ID");
    modelo.addColumn("Usuario Genérico");
    modelo.addColumn("Nombre");
    modelo.addColumn("Apellido");
    modelo.addColumn("Direccion");
    modelo.addColumn("Telefono 1");
    modelo.addColumn("Telefono 2");
    modelo.addColumn("Grado");

    try {
        BasedeDatos.conectar();
        ResultSet resultado = BasedeDatos.ejecutarSQL("SELECT us.id_usuario, us.user_generico, us.nombre, us.apellido, us.direccion, us.telefono, us.telefonoF, gr.numero FROM usuarios us JOIN grados gr ON us.grado = gr.id WHERE us.estado ='VIG'");

        if (resultado != null) { // Verificar que el resultado no sea null
            while (resultado.next()) {
                int id_usuario = resultado.getInt("id_usuario");
                int user_generico = resultado.getInt("user_generico");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String direccion = resultado.getString("direccion");
                String telefono = resultado.getString("telefono");
                String telefonoF = resultado.getString("telefonoF");
                int grado = resultado.getInt("numero");

                // Agregamos los datos al modelo
                Object[] fila = {id_usuario, user_generico, nombre, apellido, direccion, telefono, telefonoF, grado};
                modelo.addRow(fila);
            }
        } else {
            System.out.println("No se pudo ejecutar la consulta SQL.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "He encontrado un error al momento de realizacion la gestion, porfavor contacte con soporte CODIGO: 000US", "ERROR - Bibliosoft", JOptionPane.WARNING_MESSAGE);
         manejoErroresenBD.registrarError(e.getMessage(), "USUARIOS - LISTADO");
    } finally {
        BasedeDatos.desconectar();
    }

    return modelo;
}


    public DefaultTableModel busqueda(String nombreSeleccion) {
         BasedeDatos.conectar();
        DefaultTableModel modelo = new DefaultTableModel();
        // Definimos las columnas del modelo
        modelo.addColumn("ID");
        modelo.addColumn("Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono 1");
        modelo.addColumn("Telefono 2");
        modelo.addColumn("Grado");
        modelo.addColumn("Multa");

        String sql = "SELECT id_usuario, user_generico, nombre, apellido, direccion, telefono, telefonoF, grado, id_multa FROM usuarios WHERE user_generico LIKE ? OR nombre LIKE ? OR apellido LIKE ? OR direccion LIKE ? OR telefono LIKE ? OR telefono LIKE ? OR telefonoF LIKE ? OR grado LIKE ? OR id_multa LIKE ?";

        try {
            if (BasedeDatos.conexion == null) {
                // Mostrar un mensaje de error o lanzar una excepción
                System.out.println("No hay conexión a la base de datos");
                return modelo;
            }
            // Preparar la consulta SQL y establecer el parámetro
            PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
            String parametro = "%" + nombreSeleccion + "%";
            stmt.setString(1, parametro);
            stmt.setString(2, parametro);
            stmt.setString(3, parametro);
            stmt.setString(4, parametro);
            stmt.setString(5, parametro);
            stmt.setString(6, parametro);
            stmt.setString(7, parametro);
            stmt.setString(8, parametro);
            stmt.setString(9, parametro);

            // Ejecutar la consulta y obtener el resultado
            ResultSet result = stmt.executeQuery();

            if (result != null) {
                while (result.next()) {
                    int id_usuario = result.getInt("id_usuario");
                    int user_generico = result.getInt("user_generico");
                    String nombre = result.getString("nombre");
                    String apellido = result.getString("apellido");
                    String direccion = result.getString("direccion");
                    String telefono = result.getString("telefono");
                    String telefonoF = result.getString("telefonoF");
                    int grado = result.getInt("grado");
                    int id_multa = result.getInt("id_multa");

                    // Agregamos los datos al modelo
                    Object[] fila = {id_usuario, user_generico, nombre, apellido, direccion, telefono, telefonoF, grado, id_multa};
                    modelo.addRow(fila);
                }
            }
        } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "He encontrado un error al momento de realizacion la gestion, porfavor contacte con soporte CODIGO: 000US", "ERROR - Bibliosoft", JOptionPane.WARNING_MESSAGE);
         manejoErroresenBD.registrarError(e.getMessage(), "USUARIOS - BUSQUEDA");
        } finally {
        BasedeDatos.desconectar();
    }

        return modelo;
    }

    public static void insertarUser(UsuariosDTO admin) {
        try {
            BasedeDatos.conectar();
            String consulta = "INSERT INTO usuarios (user_generico, nombre, apellido, direccion, telefono, telefonoF,grado,estado) VALUES (?, ?, ?, ?, ?, ?,?,?)";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            statement.setInt(1, admin.getUser_generico());
            statement.setString(2, admin.getNombre());
            statement.setString(3, admin.getApellido());
            statement.setString(4, admin.getDireccion());
            statement.setString(5, admin.getTelefono());
            statement.setString(6, admin.getTelefonoF());
            statement.setInt(7, admin.getGrado());
            statement.setString(8, "VIG");

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha agregado exitosamente el usuario " + admin.getUser_generico());

       } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "He encontrado un error al momento de realizacion la gestion, porfavor contacte con soporte CODIGO: 000US", "ERROR - Bibliosoft", JOptionPane.WARNING_MESSAGE);
         manejoErroresenBD.registrarError(e.getMessage(), "USUARIOS - INSERTAR");
    } finally {
        BasedeDatos.desconectar();
    }
    }
    
    public static void actualizarUser(UsuariosDTO admin) {
        try {
            BasedeDatos.conectar();
            String consulta = "UPDATE usuarios SET user_generico = ?, nombre = ?, apellido = ?, direccion = ?, telefono = ?, telefonoF = ?,grado=? WHERE id_usuario = ?";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            statement.setInt(1, admin.getUser_generico());
            statement.setString(2, admin.getNombre());
            statement.setString(3, admin.getApellido());
            statement.setString(4, admin.getDireccion());
            statement.setString(5, admin.getTelefono());
            statement.setString(6, admin.getTelefonoF());
            statement.setInt(7, admin.getGrado());
            statement.setInt(8, admin.getId_usuario());
            statement.executeUpdate();
         ///   BasedeDatos.desconectar();
            JOptionPane.showMessageDialog(null, "Se ha actualizado exitosamente el usuario " + admin.getUser_generico());
         } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "He encontrado un error al momento de realizacion la gestion, porfavor contacte con soporte CODIGO: 000US", "ERROR - Bibliosoft", JOptionPane.WARNING_MESSAGE);
         manejoErroresenBD.registrarError(e.getMessage(), "USUARIOS - ACTUALIZACION");
    } finally {
        BasedeDatos.desconectar();
    }
    }
public static boolean validarPrestamosActivos(int id_user) throws SQLException {
    int totalPresActivos = 0;
    try {
        BasedeDatos.conectar();

        // Consulta para contar los préstamos activos del usuario
        String consultaPres = "SELECT COUNT(*) AS totalPresActivos " +
                              "FROM usuarios u " +
                              "INNER JOIN prestamos p ON p.id_usuario = u.id_usuario " +
                              "WHERE u.id_usuario = ? AND p.estado = 1";
        PreparedStatement statementPres = BasedeDatos.conexion.prepareStatement(consultaPres);
        statementPres.setInt(1, id_user);
        ResultSet rs = statementPres.executeQuery();
        if (rs.next()) {
            totalPresActivos = rs.getInt("totalPresActivos");
        }
        return totalPresActivos == 0;
    } finally {
        BasedeDatos.desconectar();
    }
}

    
    public static void eliminarUser(int id_user) {
        try {
            if (validarPrestamosActivos(id_user)) {
            BasedeDatos.conectar();
            String consulta = "UPDATE usuarios SET estado ='CAN' WHERE id_usuario = ?";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            statement.setInt(1, id_user);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha desativado correctamente el usuario.");
            }else{
            JOptionPane.showMessageDialog(null, "Este usuario cuenta con prestamos activos, verifica porfavor o de lo contrario contacte con soporte.");
            }
         } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "He encontrado un error al momento de realizacion la gestion, porfavor contacte con soporte CODIGO: 000US", "ERROR - Bibliosoft", JOptionPane.WARNING_MESSAGE);
         manejoErroresenBD.registrarError(e.getMessage(), "USUARIOS - DESACTIVACION");
    } finally {
        BasedeDatos.desconectar();
    }
    }
}
