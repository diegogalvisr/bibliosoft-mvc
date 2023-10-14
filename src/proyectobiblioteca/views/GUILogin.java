package proyectobiblioteca.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import proyectobiblioteca.controllers.adminsDAO;

public class GUILogin extends JFrame {

    private final JPanel panelPrin=new JPanel();
    private final JPanel panelIzquierda=new JPanel();
     private JPanel panelLogin=new JPanel();
    private final Color verde = Color.decode("#36b756");
    private final Color verdeClaro = Color.decode("#ffffff");
    private JButton botonHome, botonAdmins;
    private JLabel usuarioLabel, passwordLabel;
    private JTextField usuarioField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private final adminsDAO adminsdao = new adminsDAO();
    private final Container container= getContentPane();
    

    public GUILogin() {
        initComponents();
        setSize(600, 400);
        setTitle("Sistema de Biblioteca - Colegio Francisco Jose de Caldas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        panelIzquierda.setBackground(verde);
        panelPrin.setBackground(verdeClaro);
        panelPrin.setPreferredSize(new Dimension(610, 800));
        panelIzquierda.setPreferredSize(new Dimension(200, 800));

        // Configuramos el contenedor principal con un BorderLayout
        container.setLayout(new BorderLayout());

        // Agregamos los paneles al contenedor principal
        container.add(panelIzquierda, BorderLayout.WEST);
        container.add(panelPrin, BorderLayout.CENTER);
        
        /////FORMULARIO LOGIN
            JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon(getClass().getResource("/resources/logoHome.png")));
        panelIzquierda.setLayout(new BorderLayout());
        panelIzquierda.add(logo, BorderLayout.CENTER);
        panelLogin = new JPanel(new GridBagLayout());
        usuarioLabel = new JLabel("Usuario:");
        passwordLabel = new JLabel("Contraseña:");
        usuarioField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Iniciar sesión");

        usuarioLabel.setPreferredSize(new Dimension(100, 30));
        passwordLabel.setPreferredSize(new Dimension(100, 30));
        usuarioField.setPreferredSize(new Dimension(200, 30));
        passwordField.setPreferredSize(new Dimension(200, 30));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(10, 10, 0, 10);
        panelLogin.add(usuarioLabel, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 0, 0, 10);
        panelLogin.add(usuarioField, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(10, 10, 0, 10);
        panelLogin.add(passwordLabel, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 0, 0, 10);
        panelLogin.add(passwordField, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 10, 10, 10);
        panelLogin.add(loginButton, c);

        panelLogin.setPreferredSize(new Dimension(400, 300));
        panelLogin.setOpaque(false);

        panelPrin.setLayout(new BorderLayout());
        panelPrin.add(panelLogin, BorderLayout.CENTER);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                System.out.println(usuario);
                System.out.println(passwordField.getText());

                boolean esValido = adminsdao.validarUsuario(usuarioField.getText(), passwordField.getText());
                if (esValido) {
                    if (adminsdao.cargo==1) {
                           JOptionPane.showMessageDialog(null, "Bienvenido al sistema ingresas como Administrador");
                            GUIPrincipal formPrin=new GUIPrincipal();
                   formPrin.setVisible(true);
                   setVisible(false);
                    }else{
                    JOptionPane.showMessageDialog(null, "Bienvenido al sistema ingresas como Auxiliar");
                    /*        GUIPrincipalAuxiliares formPrin=new GUIPrincipalAuxiliares();
                   formPrin.setVisible(true);
                   setVisible(false);*/
                    }
                  
                } else {
                    JOptionPane.showMessageDialog(null, "INCORRECTO");
                }

            }
        });
    }

  
    public void pintarPanelIzquierdo() {
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon(getClass().getResource("/resources/logoHome.png")));

        botonHome = new JButton();
        botonHome.setPreferredSize(new Dimension(300, 50));
        botonHome.setContentAreaFilled(false); // Quitar el relleno del botón
        botonHome.setOpaque(true);
        botonHome.setBackground(verdeClaro);

        botonAdmins = new JButton();
        botonAdmins.setPreferredSize(new Dimension(300, 50));
        botonAdmins.setContentAreaFilled(false); // Quitar el relleno del botón
        botonAdmins.setOpaque(true);
        botonAdmins.setBackground(verdeClaro);

        panelIzquierda.add(logo);
        panelIzquierda.add(botonHome);
        panelIzquierda.add(botonAdmins);
        container.add(panelIzquierda, BorderLayout.WEST);
        container.add(panelPrin, BorderLayout.CENTER);
    }

  

    public static void main(String[] args) {
        new GUILogin();
    }
}
