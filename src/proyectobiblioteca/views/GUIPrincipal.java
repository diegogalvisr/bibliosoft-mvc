package proyectobiblioteca.views;

import java.awt.*;
import java.awt.event.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicToggleButtonUI;
import proyectobiblioteca.controllers.*;
import proyectobiblioteca.models.LibrosDTO;
import proyectobiblioteca.models.UsuariosDTO;
import proyectobiblioteca.models.adminsDTO;

public class GUIPrincipal extends JFrame {

    public static boolean logeo = false;
    private final JPanel panelPrin = new JPanel();
    private final JPanel panelIzquierda = new JPanel();
    private JLabel jLabelTop;
    private JPanel panelArriba;
    private JPanel panelAbajo;
    private final Color verde = Color.decode("#36b756");
    private final Color verdeClaro = Color.decode("#ffffff");
    private JButton botonHome, botonAdmins, botonUsers, botonLibros;
    private final Container container = getContentPane();
    adminsDAO ADMINSdao = new adminsDAO();
    UsuariosDAO usuariosDAO = new UsuariosDAO();
    LibrosDAO librosDAO = new LibrosDAO();

    public GUIPrincipal() {
        if (!logeo) {
            JOptionPane.showMessageDialog(rootPane, "Debes logearte para ingresar a este menú");
            GUILogin GL = new GUILogin();
            GL.setVisible(true);
        } else {
            pintarPanelIzquierdo();
            initComponents();

            // Obtener el tamaño de la pantalla
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            // Establecer el tamaño del JFrame según el tamaño de la pantalla
            setSize(screenSize.width, screenSize.height);

            setTitle("Sistema de Biblioteca - Colegio Francisco Jose de Caldas");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(true);
        }
    }

    private void initComponents() {
        panelArriba = new JPanel();
        panelAbajo = new JPanel();
        jLabelTop = new JLabel();
        jLabelTop.setFont(new Font("Agency FB", Font.BOLD, 30));
        // jLabelTop.setPreferredSize(new Dimension(200, 100)); // establece el tamaño preferido a 200x50 píxeles
        panelArriba.add(jLabelTop);

        panelIzquierda.setBackground(verde);
        panelPrin.setBackground(verdeClaro);
        panelPrin.setPreferredSize(new Dimension(610, 800));
        panelIzquierda.setPreferredSize(new Dimension(300, 800));

        panelArriba.setBackground(verde);
        panelAbajo.setBackground(verdeClaro);
        container.setLayout(new BorderLayout());
        panelPrin.setLayout(new BorderLayout());
        panelPrin.add(panelArriba, BorderLayout.NORTH);
        panelArriba.setPreferredSize(new Dimension(610, 150));
        //  panelArriba.add(jLabelTop);
        panelPrin.add(panelAbajo, BorderLayout.SOUTH);
        panelAbajo.setPreferredSize(new Dimension(610, 600));

        container.add(panelIzquierda, BorderLayout.WEST);
        container.add(panelPrin, BorderLayout.CENTER);

        accionHome();
        pintarMenuHome();
        pintarMenuAdmins();
        pintarMenuUsers();
        pintarMenuLibros();

    }

    private void accionHome() {
        panelAbajo.removeAll();
        jLabelTop.setText("dashboard/home");
        panelAbajo.setLayout(new FlowLayout());

        JToggleButton botonAdmin = new JToggleButton("Administradores");
        botonAdmin.setUI(new BootstrapSwitchUI()); // Aplica la clase UI personalizada
        botonAdmin.setPreferredSize(new Dimension(200, 150));

        // Establece el icono en el botón
        // botonAdmin.setIcon(new ImageIcon(getClass().getResource("/resources/iconAdmin.png")));
        botonAdmin.setBackground(verde);
        botonAdmin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonAdmin.setBackground(Color.YELLOW);  // Cambia el color de fondo al pasar el mouse
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonAdmin.setBackground(verde);  // Restaura el color de fondo original
            }
        });

        botonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar el clic en el botón y realizar la acción deseada
                if (botonAdmin.isSelected()) {
                    // El botón está seleccionado (clicado)
                    accionAdmins();
                } else {
                    // El botón no está seleccionado
                    System.out.println("Botón no clicado");
                }
            }
        });

        JToggleButton botonUser = new JToggleButton("Usuarios");
        botonUser.setUI(new BootstrapSwitchUI()); // Aplica la clase UI personalizada
        botonUser.setPreferredSize(new Dimension(200, 150));

        // Establece el icono en el botón
        botonUser.setIcon(new ImageIcon(getClass().getResource("/resources/iconAdmin.png")));
        botonUser.setBackground(verde);
        botonUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonUser.setBackground(Color.YELLOW);  // Cambia el color de fondo al pasar el mouse
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonUser.setBackground(verde);  // Restaura el color de fondo original
            }
        });

        botonUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar el clic en el botón y realizar la acción deseada
                if (botonUser.isSelected()) {
                    // El botón está seleccionado (clicado)
                    accionUsers();
                } else {
                    // El botón no está seleccionado
                    System.out.println("Botón no clicado");
                }
            }
        });

        panelAbajo.setLayout(new FlowLayout());
        panelAbajo.add(botonAdmin);
        panelAbajo.add(botonUser);

        panelAbajo.repaint();
        panelAbajo.revalidate();
    }

    private static class BootstrapSwitchUI extends BasicToggleButtonUI {

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            // Personaliza la apariencia y el comportamiento del botón aquí
        }
    }

    private void accionAdmins() {
        panelAbajo.removeAll();
        jLabelTop.setText("dashboard/administradores");

        // Crear la tabla y configurar sus propiedades
        JTable table = new JTable();
        table.setRowHeight(30);
        table.setModel(ADMINSdao.tablaAdmins());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(610, 400));
        table.setOpaque(false);
        table.setBackground(verdeClaro);
        table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(), 30));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(verde);
        table.getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        table.setFont(new Font("Arial", Font.PLAIN, 10));
        table.setGridColor(Color.LIGHT_GRAY);
        table.setSelectionBackground(Color.decode("#f3eb55"));
        table.setSelectionForeground(Color.BLACK);

// Crear los botones
        JButton nuevoButton = new JButton("Nuevo");
        nuevoButton.setBackground(verde);
        nuevoButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JButton editarButton = new JButton("Editar");
        editarButton.setBackground(verde);
        editarButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBackground(verde);
        eliminarButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

// Crear el campo de búsqueda
        JTextField buscarField = new JTextField(20);
        buscarField.setMaximumSize(buscarField.getPreferredSize());
        buscarField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

// Crear un JPanel para los botones y el campo de búsqueda
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.X_AXIS));
        botonesPanel.setOpaque(false);
        botonesPanel.setBackground(new Color(0, 0, 0, 0));
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botonesPanel.add(Box.createHorizontalGlue());
        botonesPanel.add(buscarField);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Agregar un espacio entre los botones
        botonesPanel.add(nuevoButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        botonesPanel.add(editarButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        botonesPanel.add(eliminarButton);

        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada < 0) {
                    JOptionPane.showMessageDialog(null, "Es necesario que seleccione un usuario de la tabla para eliminar");
                    return;
                }

                String usuario = (String) table.getValueAt(filaSeleccionada, 0); // Obtiene el nombre de usuario de la fila seleccionada
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar al usuario: " + usuario + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    ADMINSdao.eliminarAdmin(usuario); // Realiza la eliminación en la base de datos
                    table.setModel(ADMINSdao.tablaAdmins()); // Actualiza la tabla con los nuevos datos
                }
            }
        });

        nuevoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionbtnNuevo();

            }
        });
        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada < 0) {
                    JOptionPane.showMessageDialog(null, "Es necesario que seleccione en la tabla un usuario a modificar");
                } else {
                    ArrayList<Object> datosFila = new ArrayList<>();

                    for (int i = 0; i < table.getColumnCount(); i++) {
                        datosFila.add(table.getValueAt(filaSeleccionada, i));
                    }

                    accionbtnEditar(datosFila);
                }
            }
        });

        // Agregar el JPanel y el JScrollPane al panelAbajo
        panelAbajo.setLayout(new BoxLayout(panelAbajo, BoxLayout.Y_AXIS));
        panelAbajo.add(botonesPanel);
        panelAbajo.add(Box.createRigidArea(new Dimension(0, 10))); // Agregar un espacio entre los botones y la tabla
        panelAbajo.add(scrollPane);

        panelAbajo.repaint();
        panelAbajo.revalidate();

        // Agregar un DocumentListener al campo de búsqueda para detectar cambios en el texto
        buscarField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }

            // Método de búsqueda
            public void buscar() {
                String texto = buscarField.getText();
                table.setModel(ADMINSdao.busqueda(texto));
            }
        });
    }

    private void accionUsers() {
        panelAbajo.removeAll();
        jLabelTop.setText("dashboard/usuarios");

        // Crear la tabla y configurar sus propiedades
        JTable table = new JTable();
        table.setRowHeight(30);
        table.setModel(usuariosDAO.tablaUsers());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(610, 400));
        table.setOpaque(false);
        table.setBackground(verdeClaro);
        table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(), 30));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(verde);
        table.getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        table.setFont(new Font("Arial", Font.PLAIN, 10));
        table.setGridColor(Color.LIGHT_GRAY);
        table.setSelectionBackground(Color.decode("#f3eb55"));
        table.setSelectionForeground(Color.BLACK);

// Crear los botones
        JButton nuevoButton = new JButton("Nuevo");
        nuevoButton.setBackground(verde);
        nuevoButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JButton editarButton = new JButton("Editar");
        editarButton.setBackground(verde);
        editarButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBackground(verde);
        eliminarButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

// Crear el campo de búsqueda
        JTextField buscarField = new JTextField(20);
        buscarField.setMaximumSize(buscarField.getPreferredSize());
        buscarField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

// Crear un JPanel para los botones y el campo de búsqueda
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.X_AXIS));
        botonesPanel.setOpaque(false);
        botonesPanel.setBackground(new Color(0, 0, 0, 0));
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botonesPanel.add(Box.createHorizontalGlue());
        botonesPanel.add(buscarField);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Agregar un espacio entre los botones
        botonesPanel.add(nuevoButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        botonesPanel.add(editarButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        botonesPanel.add(eliminarButton);

        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada < 0) {
                    JOptionPane.showMessageDialog(null, "Es necesario que seleccione un usuario de la tabla para eliminar ");
                    return;
                }
                int idAdmin = (int) table.getValueAt(filaSeleccionada, 0);
                UsuariosDAO.eliminarUser(idAdmin);
                System.out.println(idAdmin);
                table.setModel(usuariosDAO.tablaUsers());
            }
        });

        nuevoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionbtnNuevoUser();

            }
        });
        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada < 0) {
                    JOptionPane.showMessageDialog(null, "Es necesario que seleccione en la tabla un usuario a modificar");
                } else {
                    ArrayList<Object> datosFila = new ArrayList<>();

                    for (int i = 0; i < table.getColumnCount(); i++) {
                        datosFila.add(table.getValueAt(filaSeleccionada, i));
                    }

                    accionbtnEditarUser(datosFila);
                }
            }
        });

        // Agregar el JPanel y el JScrollPane al panelAbajo
        panelAbajo.setLayout(new BoxLayout(panelAbajo, BoxLayout.Y_AXIS));
        panelAbajo.add(botonesPanel);
        panelAbajo.add(Box.createRigidArea(new Dimension(0, 10))); // Agregar un espacio entre los botones y la tabla
        panelAbajo.add(scrollPane);

        panelAbajo.repaint();
        panelAbajo.revalidate();

        // Agregar un DocumentListener al campo de búsqueda para detectar cambios en el texto
        buscarField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }

            // Método de búsqueda
            public void buscar() {
                String texto = buscarField.getText();
                table.setModel(usuariosDAO.busqueda(texto));
            }
        });
    }

    private void accionLibros() {
        panelAbajo.removeAll();
        jLabelTop.setText("dashboard/libros");

        // Crear la tabla y configurar sus propiedades
        JTable table = new JTable();
        table.setRowHeight(30);
        table.setModel(librosDAO.obtenerTablaLibros());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(610, 400));
        table.setOpaque(false);
        table.setBackground(verdeClaro);
        table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(), 30));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(verde);
        table.getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        table.setFont(new Font("Arial", Font.PLAIN, 10));
        table.setGridColor(Color.LIGHT_GRAY);
        table.setSelectionBackground(Color.decode("#f3eb55"));
        table.setSelectionForeground(Color.BLACK);

// Crear los botones
        JButton nuevoAutor = new JButton("Nuevo Autor");
        nuevoAutor.setBackground(verde);
        nuevoAutor.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JButton nuevoEditorial = new JButton("Nuevo Editorial");
        nuevoEditorial.setBackground(verde);
        nuevoEditorial.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JButton nuevoButton = new JButton("Nuevo");
        nuevoButton.setBackground(verde);
        nuevoButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JButton editarButton = new JButton("Editar");
        editarButton.setBackground(verde);
        editarButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBackground(verde);
        eliminarButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

// Crear el campo de búsqueda
        JTextField buscarField = new JTextField(20);
        buscarField.setMaximumSize(buscarField.getPreferredSize());
        buscarField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

// Crear un JPanel para los botones y el campo de búsqueda
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.X_AXIS));
        botonesPanel.setOpaque(false);
        botonesPanel.setBackground(new Color(0, 0, 0, 0));
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botonesPanel.add(Box.createHorizontalGlue());
        botonesPanel.add(buscarField);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Agregar un espacio entre los botones
        botonesPanel.add(nuevoButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        botonesPanel.add(editarButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        botonesPanel.add(eliminarButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        botonesPanel.add(nuevoAutor);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        botonesPanel.add(nuevoEditorial);

        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada < 0) {
                    JOptionPane.showMessageDialog(null, "Es necesario que seleccione un usuario de la tabla para eliminar ");
                    return;
                }
                int idLibro = (int) table.getValueAt(filaSeleccionada, 0);
                librosDAO.eliminarLibro(idLibro);
                table.setModel(librosDAO.obtenerTablaLibros());
            }
        });

        nuevoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionbtnNuevoLibro();

            }
        });
        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada < 0) {
                    JOptionPane.showMessageDialog(null, "Es necesario que seleccione en la tabla un usuario a modificar");
                } else {
                    ArrayList<Object> datosFila = new ArrayList<>();

                    for (int i = 0; i < table.getColumnCount(); i++) {
                        datosFila.add(table.getValueAt(filaSeleccionada, i));
                    }

                    accionbtnEditarLibro(datosFila);
                }
            }
        });

        // Agregar el JPanel y el JScrollPane al panelAbajo
        panelAbajo.setLayout(new BoxLayout(panelAbajo, BoxLayout.Y_AXIS));
        panelAbajo.add(botonesPanel);
        panelAbajo.add(Box.createRigidArea(new Dimension(0, 10))); // Agregar un espacio entre los botones y la tabla
        panelAbajo.add(scrollPane);

        panelAbajo.repaint();
        panelAbajo.revalidate();

        // Agregar un DocumentListener al campo de búsqueda para detectar cambios en el texto
        buscarField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }

            // Método de búsqueda
            public void buscar() {
                String texto = buscarField.getText();
                table.setModel(librosDAO.busqueda(texto));
            }
        });
    }

    private void accionbtnNuevo() {
        panelAbajo.removeAll();
        jLabelTop.setText("dashboard/administradores/nuevo");

        // Agrega las referencias a los archivos CSS y JavaScript de Bootstrap Switch
        JLabel bootstrapSwitchCss = new JLabel();
        bootstrapSwitchCss.setText("<html><head><link rel='stylesheet' href='lib/bootstrap-switch.min.css'></head><body></body></html>");
        JLabel bootstrapSwitchJs = new JLabel();
        bootstrapSwitchJs.setText("<html><head><script src='lib/bootstrap-switch.min.js'></script></head><body></body></html>");
        // Crea los componentes del formulario utilizando las clases de estilo de Bootstrap

        JLabel lblClave = new JLabel("Clave:");
        lblClave.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblClave.setForeground(new Color(52, 58, 64));

        JPasswordField txtClave = new JPasswordField();
        txtClave.setPreferredSize(new Dimension(250, 30));
        txtClave.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblNombre.setForeground(new Color(52, 58, 64));

        JTextField txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(250, 30));
        txtNombre.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblApellido.setForeground(new Color(52, 58, 64));

        JTextField txtApellido = new JTextField();
        txtApellido.setPreferredSize(new Dimension(250, 30));
        txtApellido.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblUsuario.setForeground(new Color(52, 58, 64));

        JTextField txtUsuario = new JTextField();
        txtUsuario.setEditable(false);
        txtUsuario.setPreferredSize(new Dimension(250, 30));
        txtUsuario.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblDireccion = new JLabel("Direccion:");
        lblDireccion.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblDireccion.setForeground(new Color(52, 58, 64));

        JTextField txtDireccion = new JTextField();
        txtDireccion.setPreferredSize(new Dimension(250, 30));
        txtDireccion.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblCargo.setForeground(new Color(52, 58, 64));

        JComboBox JBCargo = new JComboBox();
        JBCargo.addItem("Seleccion");
        JBCargo.addItem("Administrador");
        JBCargo.addItem("Auxiliar");
        JBCargo.setPreferredSize(new Dimension(250, 30));
        JBCargo.setBackground(Color.white);
        JBCargo.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        // Agrega los componentes al panel utilizando un diseño de cuadrícula
        panelAbajo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelAbajo.add(lblUsuario, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtUsuario, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelAbajo.add(lblClave, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtClave, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelAbajo.add(lblNombre, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtNombre, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelAbajo.add(lblApellido, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtApellido, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelAbajo.add(lblDireccion, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtDireccion, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelAbajo.add(lblCargo, gbc);
        gbc.gridx = 1;
        panelAbajo.add(JBCargo, gbc);

        JButton enviar = new JButton("Enviar");
        enviar.setPreferredSize(new Dimension(250, 30));
        enviar.setBackground(verde);
        enviar.setForeground(Color.WHITE);
        enviar.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 1;
        gbc.gridy = 6;
        panelAbajo.add(enviar, gbc);
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setPreferredSize(new Dimension(250, 30));
        btnRegresar.setBackground(verde);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 1;
        gbc.gridy = 7;
        panelAbajo.add(btnRegresar, gbc);
        JButton btnGenerarUser = new JButton("Generar Usuario");
        btnGenerarUser.setPreferredSize(new Dimension(250, 30));
        btnGenerarUser.setBackground(verde);
        btnGenerarUser.setForeground(Color.WHITE);
        btnGenerarUser.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 1;
        gbc.gridy = 8;
        panelAbajo.add(btnGenerarUser, gbc);

        // Agrega los archivos CSS y JavaScript de Bootstrap Switch al panel
        panelAbajo.add(bootstrapSwitchCss);
        panelAbajo.add(bootstrapSwitchJs);

        enviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtUsuario.getText().isEmpty() || txtClave.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son requeridos");
                } else {

                    adminsDTO nuevoAdmin = new adminsDTO();
                    nuevoAdmin.setUsuario(txtUsuario.getText());
                    nuevoAdmin.setClave(txtClave.getText());
                    nuevoAdmin.setNombres(txtNombre.getText());
                    nuevoAdmin.setApellidos(txtApellido.getText());
                    nuevoAdmin.setDireccion(txtDireccion.getText());
                    nuevoAdmin.setCargo(1);
                    adminsDAO.insertarAdmin(nuevoAdmin);

                    txtUsuario.setText("");
                    txtClave.setText("");
                    txtNombre.setText("");
                    txtApellido.setText("");
                    txtDireccion.setText("");

                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionAdmins();
            }
        });

        btnGenerarUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtUsuario.setText(generateUsername(txtNombre.getText(), txtApellido.getText()));

            }
        });

        // Actualiza la interfaz gráfica
        panelAbajo.revalidate();
        panelAbajo.repaint();
    }

    private void accionbtnNuevoUser() {
        panelAbajo.removeAll();
        jLabelTop.setText("dashboard/usuarios/nuevo");

        // Agrega las referencias a los archivos CSS y JavaScript de Bootstrap Switch
        JLabel bootstrapSwitchCss = new JLabel();
        bootstrapSwitchCss.setText("<html><head><link rel='stylesheet' href='lib/bootstrap-switch.min.css'></head><body></body></html>");
        JLabel bootstrapSwitchJs = new JLabel();
        bootstrapSwitchJs.setText("<html><head><script src='lib/bootstrap-switch.min.js'></script></head><body></body></html>");
        // Crea los componentes del formulario utilizando las clases de estilo de Bootstrap

        JLabel lblTelefono1 = new JLabel("Telefono 1:");
        lblTelefono1.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblTelefono1.setForeground(new Color(52, 58, 64));

        JPasswordField txtTelefono1 = new JPasswordField();
        txtTelefono1.setPreferredSize(new Dimension(250, 30));
        txtTelefono1.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblNombre.setForeground(new Color(52, 58, 64));

        JTextField txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(250, 30));
        txtNombre.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblApellido.setForeground(new Color(52, 58, 64));

        JTextField txtApellido = new JTextField();
        txtApellido.setPreferredSize(new Dimension(250, 30));
        txtApellido.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblUsuario.setForeground(new Color(52, 58, 64));

        JTextField txtUsuario = new JTextField();
        txtUsuario.setEditable(false);
        txtUsuario.setPreferredSize(new Dimension(250, 30));
        txtUsuario.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblDireccion = new JLabel("Direccion:");
        lblDireccion.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblDireccion.setForeground(new Color(52, 58, 64));

        JTextField txtDireccion = new JTextField();
        txtDireccion.setPreferredSize(new Dimension(250, 30));
        txtDireccion.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblTelefono2 = new JLabel("Telefono 2:");
        lblTelefono2.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblTelefono2.setForeground(new Color(52, 58, 64));

        JTextField txtTelefono2 = new JTextField();
        txtTelefono2.setPreferredSize(new Dimension(250, 30));
        txtTelefono2.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblGrado = new JLabel("Grado:");
        lblGrado.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblGrado.setForeground(new Color(52, 58, 64));

        JComboBox JCGrado = new JComboBox();
        JCGrado.addItem("601");
        JCGrado.addItem("602");
        JCGrado.addItem("603");
        JCGrado.addItem("604");

        // Agrega los componentes al panel utilizando un diseño de cuadrícula
        panelAbajo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelAbajo.add(lblUsuario, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtUsuario, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelAbajo.add(lblNombre, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtNombre, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelAbajo.add(lblApellido, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtApellido, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelAbajo.add(lblDireccion, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtDireccion, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelAbajo.add(lblTelefono1, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtTelefono1, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelAbajo.add(lblTelefono2, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtTelefono2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelAbajo.add(lblGrado, gbc);
        gbc.gridx = 1;
        panelAbajo.add(JCGrado, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;

        JButton enviar = new JButton("Enviar");
        enviar.setPreferredSize(new Dimension(250, 30));
        enviar.setBackground(verde);
        enviar.setForeground(Color.WHITE);
        enviar.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 1;
        gbc.gridy = 8;
        panelAbajo.add(enviar, gbc);
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setPreferredSize(new Dimension(250, 30));
        btnRegresar.setBackground(verde);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 1;
        gbc.gridy = 9;
        panelAbajo.add(btnRegresar, gbc);
        JButton btnGenerarUser = new JButton("Generar Usuario");
        btnGenerarUser.setPreferredSize(new Dimension(250, 30));
        btnGenerarUser.setBackground(verde);
        btnGenerarUser.setForeground(Color.WHITE);
        btnGenerarUser.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 1;
        gbc.gridy = 10;
        panelAbajo.add(btnGenerarUser, gbc);

        // Agrega los archivos CSS y JavaScript de Bootstrap Switch al panel
        panelAbajo.add(bootstrapSwitchCss);
        panelAbajo.add(bootstrapSwitchJs);

        enviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtUsuario.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son requeridos");
                } else {
                    UsuariosDTO usersDto = new UsuariosDTO();
                    usersDto.setUser_generico(Integer.parseInt(txtUsuario.getText()));
                    usersDto.setNombre(txtNombre.getText());
                    usersDto.setApellido(txtApellido.getText());
                    usersDto.setDireccion(txtDireccion.getText());
                    usersDto.setTelefono(txtTelefono1.getText());
                    usersDto.setTelefonoF(txtTelefono2.getText());
                    usersDto.setGrado(Integer.parseInt((String) JCGrado.getSelectedItem()));
                    usuariosDAO.insertarUser(usersDto);

                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionUsers();
            }
        });

        btnGenerarUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtUsuario.setText(Integer.toString(generarUsuario()));

            }
        });

        // Actualiza la interfaz gráfica
        panelAbajo.revalidate();
        panelAbajo.repaint();
    }

    private void accionbtnNuevoLibro() {
        panelAbajo.removeAll();
        jLabelTop.setText("dashboard/libros/nuevo");

        // Agrega las referencias a los archivos CSS y JavaScript de Bootstrap Switch
        JLabel bootstrapSwitchCss = new JLabel();
        bootstrapSwitchCss.setText("<html><head><link rel='stylesheet' href='lib/bootstrap-switch.min.css'></head><body></body></html>");
        JLabel bootstrapSwitchJs = new JLabel();
        bootstrapSwitchJs.setText("<html><head><script src='lib/bootstrap-switch.min.js'></script></head><body></body></html>");
        // Crea los componentes del formulario utilizando las clases de estilo de Bootstrap

        JLabel lblContenido = new JLabel("Contenido:");
        lblContenido.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblContenido.setForeground(new Color(52, 58, 64));

        JPasswordField txtContenido = new JPasswordField();
        txtContenido.setPreferredSize(new Dimension(250, 30));
        txtContenido.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblCategoria.setForeground(new Color(52, 58, 64));

        JPasswordField txtCategoria = new JPasswordField();
        txtCategoria.setPreferredSize(new Dimension(250, 30));
        txtCategoria.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblCantidad.setForeground(new Color(52, 58, 64));

        JPasswordField txtCantidad = new JPasswordField();
        txtCantidad.setPreferredSize(new Dimension(250, 30));
        txtCantidad.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblISBN = new JLabel("ISBN:");
        lblISBN.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblISBN.setForeground(new Color(52, 58, 64));

        JTextField txtISBN = new JTextField();
        txtISBN.setPreferredSize(new Dimension(250, 30));
        txtISBN.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblTitulo = new JLabel("Titulo:");
        lblTitulo.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblTitulo.setForeground(new Color(52, 58, 64));

        JTextField txtTitulo = new JTextField();
        txtTitulo.setPreferredSize(new Dimension(250, 30));
        txtTitulo.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblTPLibro = new JLabel("Tipo de libro:");
        lblTPLibro.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblTPLibro.setForeground(new Color(52, 58, 64));

        JTextField txtTPLibro = new JTextField();
        txtTPLibro.setPreferredSize(new Dimension(250, 30));
        txtTPLibro.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblPrecio.setForeground(new Color(52, 58, 64));

        JTextField txtPrecio = new JTextField();
        txtPrecio.setPreferredSize(new Dimension(250, 30));
        txtPrecio.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblEditorial = new JLabel("Editorial:");
        lblEditorial.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblEditorial.setForeground(new Color(52, 58, 64));

        JComboBox<String> JCEeditorial = new JComboBox<>();
        librosDAO.cargarEditoriales(JCEeditorial);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblAutor.setForeground(new Color(52, 58, 64));

        JComboBox<String> JCAutor = new JComboBox<>();

        // Llamar al método para cargar los autores en el JComboBox
        librosDAO.cargarAutores(JCAutor);

        // Agrega los componentes al panel utilizando un diseño de cuadrícula
        panelAbajo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelAbajo.add(lblISBN, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtISBN, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelAbajo.add(lblTitulo, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtTitulo, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelAbajo.add(lblAutor, gbc);
        gbc.gridx = 1;
        panelAbajo.add(JCAutor, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelAbajo.add(lblEditorial, gbc);
        gbc.gridx = 1;
        panelAbajo.add(JCEeditorial, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelAbajo.add(lblTPLibro, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtTPLibro, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelAbajo.add(lblPrecio, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtPrecio, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelAbajo.add(lblContenido, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtContenido, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        panelAbajo.add(lblCategoria, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtCategoria, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        panelAbajo.add(lblCantidad, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtCantidad, gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;

        JButton enviar = new JButton("Enviar");
        enviar.setPreferredSize(new Dimension(250, 30));
        enviar.setBackground(verde);
        enviar.setForeground(Color.WHITE);
        enviar.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 1;
        gbc.gridy = 10;
        panelAbajo.add(enviar, gbc);
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setPreferredSize(new Dimension(250, 30));
        btnRegresar.setBackground(verde);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 1;
        gbc.gridy = 11;
        panelAbajo.add(btnRegresar, gbc);

        // Agrega los archivos CSS y JavaScript de Bootstrap Switch al panel
        panelAbajo.add(bootstrapSwitchCss);
        panelAbajo.add(bootstrapSwitchJs);

        enviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtISBN.getText().isEmpty() || txtTitulo.getText().isEmpty() || txtCategoria.getText().isEmpty() || txtContenido.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son requeridos");
                } else {
                    LibrosDTO libros = new LibrosDTO();
                    libros.setIsbn(Integer.parseInt(txtISBN.getText()));
                    libros.setTitulo(txtTitulo.getText());
                    libros.setIdEditorial(JCEeditorial.getSelectedIndex());
                    libros.setIdAutor(JCAutor.getSelectedIndex());
                    libros.setTipoLibro(txtTPLibro.getText());
                    libros.setPrecio(Integer.parseInt(txtPrecio.getText()));
                    libros.setContMaterial(txtContenido.getText());
                    libros.setCategoria(txtCategoria.getText());
                    libros.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    librosDAO.insertarLibro(libros);

                    txtISBN.setText("");
                    txtTitulo.setText("");
                    txtTPLibro.setText("");
                    txtPrecio.setText("");
                    txtContenido.setText("");
                    txtCategoria.setText("");
                    txtCantidad.setText("");

                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionLibros();
            }
        });

        /* btnGenerarUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtUsuario.setText(Integer.toString(generarUsuario()));

            }
        });*/
        // Actualiza la interfaz gráfica
        panelAbajo.revalidate();
        panelAbajo.repaint();
    }

    public static int generarUsuario() {
        int añoActual = Year.now().getValue();
        Random random = new Random();
        int[] numerosAleatorios = new int[4];

        for (int i = 0; i < 4; i++) {
            numerosAleatorios[i] = random.nextInt(10);
        }

        String usuario = String.valueOf(añoActual) + String.format("%04d", new Random().nextInt(10000));
        int usuarioInt = Integer.parseInt(usuario);

        return usuarioInt;
    }

    public static String generateUsername(String usuario, String apellido) {
        Random random = new Random();
        int number = random.nextInt(100);
        String username = (usuario.substring(0, 1) + apellido + number).toLowerCase();
        return username;
    }

    private void accionbtnEditar(ArrayList lista) {
        ArrayList<Object> datosFila = lista;
        panelAbajo.removeAll();
        jLabelTop.setText("dashboard/administradores/editar");

        // Agrega las referencias a los archivos CSS y JavaScript de Bootstrap Switch
        JLabel bootstrapSwitchCss = new JLabel();
        bootstrapSwitchCss.setText("<html><head><link rel='stylesheet' href='lib/bootstrap-switch.min.css'></head><body></body></html>");
        JLabel bootstrapSwitchJs = new JLabel();
        bootstrapSwitchJs.setText("<html><head><script src='lib/bootstrap-switch.min.js'></script></head><body></body></html>");

        // Crea los componentes del formulario utilizando las clases de estilo de Bootstrap
        JLabel lblID = new JLabel("Usuario:");
        lblID.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblID.setForeground(new Color(52, 58, 64));

        JTextField txtID = new JTextField();
        txtID.setText((String) datosFila.get(1));
        txtID.setPreferredSize(new Dimension(250, 30));
        txtID.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblUsuario.setForeground(new Color(52, 58, 64));

        JTextField txtUsuario = new JTextField();
        txtUsuario.setText((String) datosFila.get(1));
        txtUsuario.setPreferredSize(new Dimension(250, 30));
        txtUsuario.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblClave = new JLabel("Clave:");
        lblClave.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblClave.setForeground(new Color(52, 58, 64));

        JPasswordField txtClave = new JPasswordField();
        txtClave.setText((String) datosFila.get(2));
        txtClave.setPreferredSize(new Dimension(250, 30));
        txtClave.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblNombre.setForeground(new Color(52, 58, 64));

        JTextField txtNombre = new JTextField();
        txtNombre.setText((String) datosFila.get(3));
        txtNombre.setPreferredSize(new Dimension(250, 30));
        txtNombre.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblApellido.setForeground(new Color(52, 58, 64));

        JTextField txtApellido = new JTextField();
        txtApellido.setText((String) datosFila.get(4));
        txtApellido.setPreferredSize(new Dimension(250, 30));
        txtApellido.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblDireccion = new JLabel("Direccion:");
        lblDireccion.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblDireccion.setForeground(new Color(52, 58, 64));

        JTextField txtDireccion = new JTextField();
        txtDireccion.setText((String) datosFila.get(5));
        txtDireccion.setPreferredSize(new Dimension(250, 30));
        txtDireccion.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblCargo.setForeground(new Color(52, 58, 64));

        JComboBox JBCargo = new JComboBox();
        JBCargo.addItem("Seleccione");
        JBCargo.addItem("Administrador");
        JBCargo.addItem("Auxiliar");

        JBCargo.setSelectedIndex((int) datosFila.get(6));
        JBCargo.setPreferredSize(new Dimension(250, 30));
        JBCargo.setBackground(Color.white);
        JBCargo.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        // Agrega los componentes al panel utilizando un diseño de cuadrícula
        panelAbajo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelAbajo.add(lblUsuario, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtUsuario, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelAbajo.add(lblClave, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtClave, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelAbajo.add(lblNombre, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtNombre, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelAbajo.add(lblApellido, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtApellido, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelAbajo.add(lblDireccion, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtDireccion, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelAbajo.add(lblCargo, gbc);
        gbc.gridx = 1;
        panelAbajo.add(JBCargo, gbc);

        JButton enviar = new JButton("Enviar");
        enviar.setPreferredSize(new Dimension(250, 30));
        enviar.setBackground(verde);
        enviar.setForeground(Color.WHITE);
        enviar.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 1;
        gbc.gridy = 6;
        panelAbajo.add(enviar, gbc);
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setPreferredSize(new Dimension(250, 30));
        btnRegresar.setBackground(verde);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 1;
        gbc.gridy = 7;
        panelAbajo.add(btnRegresar, gbc);

        // Agrega los archivos CSS y JavaScript de Bootstrap Switch al panel
        panelAbajo.add(bootstrapSwitchCss);
        panelAbajo.add(bootstrapSwitchJs);

        enviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtUsuario.getText().isEmpty() || txtClave.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son requeridos");
                } else {
                    adminsDTO nuevoAdmin = new adminsDTO();
                    nuevoAdmin.setUsuario(txtUsuario.getText());
                    nuevoAdmin.setClave(txtClave.getText());
                    nuevoAdmin.setNombres(txtNombre.getText());
                    nuevoAdmin.setApellidos(txtApellido.getText());
                    nuevoAdmin.setDireccion(txtDireccion.getText());
                    System.out.println("Dirección a actualizar: " + txtDireccion.getText());
                    nuevoAdmin.setCargo(JBCargo.getSelectedIndex());
                    nuevoAdmin.setId_admin((int) datosFila.get(0));
                    adminsDAO.actualizarAdmin(nuevoAdmin);

                    txtUsuario.setText("");
                    txtClave.setText("");
                    txtNombre.setText("");
                    txtApellido.setText("");
                    txtDireccion.setText("");
                    JBCargo.setSelectedIndex(0);

                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionAdmins();
            }
        });

        // Actualiza la interfaz gráfica
        panelAbajo.revalidate();
        panelAbajo.repaint();
    }

    private void accionbtnEditarLibro(ArrayList lista) {
        ArrayList<Object> datosFila = lista;
        panelAbajo.removeAll();
        jLabelTop.setText("dashboard/libros/editar");

        // Agrega las referencias a los archivos CSS y JavaScript de Bootstrap Switch
        JLabel bootstrapSwitchCss = new JLabel();
        bootstrapSwitchCss.setText("<html><head><link rel='stylesheet' href='lib/bootstrap-switch.min.css'></head><body></body></html>");
        JLabel bootstrapSwitchJs = new JLabel();
        bootstrapSwitchJs.setText("<html><head><script src='lib/bootstrap-switch.min.js'></script></head><body></body></html>");
        // Crea los componentes del formulario utilizando las clases de estilo de Bootstrap

        JLabel lblContenido = new JLabel("Contenido:");
        lblContenido.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblContenido.setForeground(new Color(52, 58, 64));

        JTextField txtContenido = new JTextField();

        txtContenido.setPreferredSize(new Dimension(250, 30));
        txtContenido.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblCategoria.setForeground(new Color(52, 58, 64));

        JTextField txtCategoria = new JTextField();
        txtCategoria.setPreferredSize(new Dimension(250, 30));
        txtCategoria.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblCantidad.setForeground(new Color(52, 58, 64));

        JTextField txtCantidad = new JTextField();
        txtCantidad.setPreferredSize(new Dimension(250, 30));
        txtCantidad.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblISBN = new JLabel("ISBN:");
        lblISBN.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblISBN.setForeground(new Color(52, 58, 64));

        JTextField txtISBN = new JTextField();
        txtISBN.setPreferredSize(new Dimension(250, 30));
        txtISBN.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblTitulo = new JLabel("Titulo:");
        lblTitulo.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblTitulo.setForeground(new Color(52, 58, 64));

        JTextField txtTitulo = new JTextField();
        txtTitulo.setPreferredSize(new Dimension(250, 30));
        txtTitulo.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblTPLibro = new JLabel("Tipo de libro:");
        lblTPLibro.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblTPLibro.setForeground(new Color(52, 58, 64));

        JTextField txtTPLibro = new JTextField();
        txtTPLibro.setPreferredSize(new Dimension(250, 30));
        txtTPLibro.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblPrecio.setForeground(new Color(52, 58, 64));

        JTextField txtPrecio = new JTextField();
        txtPrecio.setPreferredSize(new Dimension(250, 30));
        txtPrecio.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblEditorial = new JLabel("Editorial:");
        lblEditorial.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblEditorial.setForeground(new Color(52, 58, 64));

        JComboBox<String> JCEeditorial = new JComboBox<>();
        librosDAO.cargarEditoriales(JCEeditorial);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblAutor.setForeground(new Color(52, 58, 64));

        JComboBox<String> JCAutor = new JComboBox<>();

        // Llamar al método para cargar los autores en el JComboBox
        librosDAO.cargarAutores(JCAutor);

        // Agrega los componentes al panel utilizando un diseño de cuadrícula
        panelAbajo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelAbajo.add(lblISBN, gbc);

        gbc.gridx = 1;
        txtISBN.setText((String) datosFila.get(1));
        panelAbajo.add(txtISBN, gbc);

        gbc.gridx = 2; // Cambia el valor de gridx para el siguiente componente en la misma fila
        panelAbajo.add(lblTitulo, gbc);

        gbc.gridx = 3; // Cambia el valor de gridx para el campo de texto del título
        txtTitulo.setText((String) datosFila.get(2));
        panelAbajo.add(txtTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelAbajo.add(lblAutor, gbc);

        gbc.gridx = 1;
        panelAbajo.add(JCAutor, gbc);

        gbc.gridx = 2; // Cambia el valor de gridx para el siguiente componente en la misma fila
        panelAbajo.add(lblEditorial, gbc);

        gbc.gridx = 3; // Cambia el valor de gridx para el campo de selección de la editorial
        panelAbajo.add(JCEeditorial, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelAbajo.add(lblTPLibro, gbc);

        gbc.gridx = 1;
        txtTPLibro.setText((String) datosFila.get(5));
        panelAbajo.add(txtTPLibro, gbc);

        gbc.gridx = 2; // Cambia el valor de gridx para el siguiente componente en la misma fila
        panelAbajo.add(lblPrecio, gbc);

        gbc.gridx = 3; // Cambia el valor de gridx para el campo de texto de Precio
        panelAbajo.add(txtPrecio, gbc);

        txtPrecio.setText(datosFila.get(6).toString());

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelAbajo.add(lblContenido, gbc);

        gbc.gridx = 1;
        panelAbajo.add(txtContenido, gbc);
        txtContenido.setText((String) datosFila.get(7));

        gbc.gridx = 2; // Cambia el valor de gridx para el siguiente componente en la misma fila
        panelAbajo.add(lblCategoria, gbc);

        gbc.gridx = 3; // Cambia el valor de gridx para el campo de texto de Categoría
        panelAbajo.add(txtCategoria, gbc);

        txtCategoria.setText((String) datosFila.get(8));

        gbc.gridx = 0;
        gbc.gridy = 8;
        panelAbajo.add(lblCantidad, gbc);

        gbc.gridx = 1;
        panelAbajo.add(txtCantidad, gbc);

        txtCantidad.setText(datosFila.get(9).toString());

        JButton enviar = new JButton("Enviar");
        enviar.setPreferredSize(new Dimension(250, 30));
        enviar.setBackground(verde);
        enviar.setForeground(Color.WHITE);
        enviar.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 0; // Cambia el valor de gridx
        gbc.gridy = 10;
        panelAbajo.add(enviar, gbc);
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setPreferredSize(new Dimension(250, 30));
        btnRegresar.setBackground(verde);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 1; // Cambia el valor de gridx
        gbc.gridy = 10; // Mantén el mismo valor de gridy para que estén en la misma fila
        panelAbajo.add(btnRegresar, gbc);

        // Agrega los archivos CSS y JavaScript de Bootstrap Switch al panel
        panelAbajo.add(bootstrapSwitchCss);
        panelAbajo.add(bootstrapSwitchJs);

        enviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtISBN.getText().isEmpty() || txtTitulo.getText().isEmpty() || txtCategoria.getText().isEmpty() || txtContenido.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son requeridos");
                } else {
                    LibrosDTO libros = new LibrosDTO();
                    libros.setIdLibro((int) datosFila.get(0));
                    libros.setIsbn(Integer.parseInt(txtISBN.getText()));
                    libros.setTitulo(txtTitulo.getText());
                    libros.setIdEditorial(JCEeditorial.getSelectedIndex());
                    libros.setIdAutor(JCAutor.getSelectedIndex());
                    libros.setTipoLibro(txtTPLibro.getText());
                    libros.setPrecio(Integer.parseInt(txtPrecio.getText()));
                    libros.setContMaterial(txtContenido.getText());
                    libros.setCategoria(txtCategoria.getText());
                    libros.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    librosDAO.actualizarLibro(libros);

                    txtISBN.setText("");
                    txtTitulo.setText("");
                    txtTPLibro.setText("");
                    txtPrecio.setText("");
                    txtContenido.setText("");
                    txtCategoria.setText("");
                    txtCantidad.setText("");

                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionLibros();
            }
        });

        // Actualiza la interfaz gráfica
        panelAbajo.revalidate();
        panelAbajo.repaint();
    }

    private void accionbtnEditarUser(ArrayList lista) {
        ArrayList<Object> datosFila = lista;
        panelAbajo.removeAll();
        jLabelTop.setText("dashboard/usuarios/editar");

        // Agrega las referencias a los archivos CSS y JavaScript de Bootstrap Switch
        JLabel bootstrapSwitchCss = new JLabel();
        bootstrapSwitchCss.setText("<html><head><link rel='stylesheet' href='lib/bootstrap-switch.min.css'></head><body></body></html>");
        JLabel bootstrapSwitchJs = new JLabel();
        bootstrapSwitchJs.setText("<html><head><script src='lib/bootstrap-switch.min.js'></script></head><body></body></html>");
        // Crea los componentes del formulario utilizando las clases de estilo de Bootstrap

        JLabel lblTelefono1 = new JLabel("Telefono 1:");
        lblTelefono1.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblTelefono1.setForeground(new Color(52, 58, 64));

        JTextField txtTelefono1 = new JTextField();

        txtTelefono1.setText((String) datosFila.get(5));

        txtTelefono1.setPreferredSize(new Dimension(250, 30));
        txtTelefono1.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblNombre.setForeground(new Color(52, 58, 64));

        JTextField txtNombre = new JTextField();
        txtNombre.setText((String) datosFila.get(2));
        txtNombre.setPreferredSize(new Dimension(250, 30));
        txtNombre.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblApellido.setForeground(new Color(52, 58, 64));

        JTextField txtApellido = new JTextField();
        txtApellido.setText((String) datosFila.get(3));
        txtApellido.setPreferredSize(new Dimension(250, 30));
        txtApellido.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblUsuario.setForeground(new Color(52, 58, 64));

        JTextField txtUsuario = new JTextField();
        txtUsuario.setText(Integer.toString((int) datosFila.get(1)));
        txtUsuario.setEditable(false);
        txtUsuario.setPreferredSize(new Dimension(250, 30));
        txtUsuario.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblDireccion = new JLabel("Direccion:");
        lblDireccion.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblDireccion.setForeground(new Color(52, 58, 64));

        JTextField txtDireccion = new JTextField();
        txtDireccion.setText((String) datosFila.get(4));
        txtDireccion.setPreferredSize(new Dimension(250, 30));
        txtDireccion.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblTelefono2 = new JLabel("Telefono 2:");
        lblTelefono2.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblTelefono2.setForeground(new Color(52, 58, 64));

        JTextField txtTelefono2 = new JTextField();
        txtTelefono2.setText((String) datosFila.get(6));

        txtTelefono2.setPreferredSize(new Dimension(250, 30));
        txtTelefono2.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));

        JLabel lblGrado = new JLabel("Grado:");
        lblGrado.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblGrado.setForeground(new Color(52, 58, 64));

        JComboBox JCGrado = new JComboBox();
        JCGrado.addItem("601");
        JCGrado.addItem("602");
        JCGrado.addItem("603");
        JCGrado.addItem("604");
        JCGrado.setSelectedItem(datosFila.get(7));

        // Agrega los componentes al panel utilizando un diseño de cuadrícula
        panelAbajo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelAbajo.add(lblUsuario, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtUsuario, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelAbajo.add(lblNombre, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtNombre, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelAbajo.add(lblApellido, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtApellido, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelAbajo.add(lblDireccion, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtDireccion, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelAbajo.add(lblTelefono1, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtTelefono1, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelAbajo.add(lblTelefono2, gbc);
        gbc.gridx = 1;
        panelAbajo.add(txtTelefono2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelAbajo.add(lblGrado, gbc);
        gbc.gridx = 1;
        panelAbajo.add(JCGrado, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;

        JButton enviar = new JButton("Enviar");
        enviar.setPreferredSize(new Dimension(250, 30));
        enviar.setBackground(verde);
        enviar.setForeground(Color.WHITE);
        enviar.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 1;
        gbc.gridy = 8;
        panelAbajo.add(enviar, gbc);
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setPreferredSize(new Dimension(250, 30));
        btnRegresar.setBackground(verde);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125)));
        gbc.gridx = 1;
        gbc.gridy = 9;
        panelAbajo.add(btnRegresar, gbc);

        // Agrega los archivos CSS y JavaScript de Bootstrap Switch al panel
        panelAbajo.add(bootstrapSwitchCss);
        panelAbajo.add(bootstrapSwitchJs);

        enviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtUsuario.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son requeridos");
                } else {
                    UsuariosDTO usersDto = new UsuariosDTO();
                    usersDto.setId_usuario((int) datosFila.get(0));
                    usersDto.setUser_generico(Integer.parseInt(txtUsuario.getText()));
                    usersDto.setNombre(txtNombre.getText());
                    usersDto.setApellido(txtApellido.getText());
                    usersDto.setDireccion(txtDireccion.getText());
                    usersDto.setTelefono(txtTelefono1.getText());
                    usersDto.setTelefonoF(txtTelefono2.getText());
                    usersDto.setGrado(Integer.parseInt((String) JCGrado.getSelectedItem()));
                    UsuariosDAO.actualizarUser(usersDto);

                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionUsers();
            }
        });

        // Actualiza la interfaz gráfica
        panelAbajo.revalidate();
        panelAbajo.repaint();

    }

    private void pintarMenuHome() {
        botonHome = new JButton();
        botonHome.setPreferredSize(new Dimension(300, 50));
        botonHome.setOpaque(true);
        botonHome.setBackground(verdeClaro);
        botonHome.setText("Home");
        panelIzquierda.add(botonHome);
        botonHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Se dio click en  boton Home");
                accionHome();
            }
        });
    }

    private void pintarMenuAdmins() {
        botonAdmins = new JButton();
        botonAdmins.setPreferredSize(new Dimension(300, 50));
        botonAdmins.setContentAreaFilled(false); // Quitar el relleno del botón
        botonAdmins.setOpaque(true);
        botonAdmins.setBackground(verdeClaro);
        botonAdmins.setText("Administradores");
        panelIzquierda.add(botonAdmins);
        botonAdmins.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Se dio click en  boton Admins");
                accionAdmins();
            }
        });
    }

    private void pintarMenuUsers() {
        botonUsers = new JButton();
        botonUsers.setPreferredSize(new Dimension(300, 50));
        botonUsers.setContentAreaFilled(false); // Quitar el relleno del botón
        botonUsers.setOpaque(true);
        botonUsers.setBackground(verdeClaro);
        botonUsers.setText("Usuarios");
        panelIzquierda.add(botonUsers);
        botonUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Se dio click en  boton Users");
                accionUsers();
            }
        });
    }

    private void pintarMenuLibros() {
        botonLibros = new JButton();
        botonLibros.setPreferredSize(new Dimension(300, 50));
        botonLibros.setContentAreaFilled(false); // Quitar el relleno del botón
        botonLibros.setOpaque(true);
        botonLibros.setBackground(verdeClaro);
        botonLibros.setText("Libros");
        panelIzquierda.add(botonLibros);
        botonLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Se dio click en  boton Libros");
                accionLibros();
            }
        });
    }

    public void pintarPanelIzquierdo() {
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon(getClass().getResource("/resources/logoHome.png")));
        logo.setPreferredSize(new Dimension(300, 200));
        panelIzquierda.add(logo);
    }

    public static void main(String[] args) {
        new GUIPrincipal();
    }
}
