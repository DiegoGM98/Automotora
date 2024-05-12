package Automotora;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AutomotoraGUI extends JFrame {
    private DefaultTableModel modeloTabla;
    private String[] marcas = {"Volkswagen", "Toyota Motors", "Mercedes-Benz Group", "Ford Motor", "General Motors", "Grupo BMW", "Honda Motor", "Hyundai"};
    private Vehiculo[] vehiculos;

    public AutomotoraGUI() {
        setTitle("Automotora");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton btnAgregarCliente = new JButton("Agregar Cliente");
        btnAgregarCliente.addActionListener(e -> agregarCliente());
        panelBotones.add(btnAgregarCliente);

        JButton btnAgregarVehiculo = new JButton("Agregar Vehículo");
        btnAgregarVehiculo.addActionListener(e -> agregarVehiculo());
        panelBotones.add(btnAgregarVehiculo);

        JButton btnBuscarVehiculo = new JButton("Buscar Vehículo");
        btnBuscarVehiculo.addActionListener(e -> seleccionarMarca());
        panelBotones.add(btnBuscarVehiculo);

        panelPrincipal.add(panelBotones, BorderLayout.NORTH);

        setContentPane(panelPrincipal);

        // Inicializar los vehículos
        vehiculos = new Vehiculo[]{
                new Vehiculo("Golf", 2012, 10000, 70000, "Negro", "Volkswagen"),
                new Vehiculo("Jetta", 2014, 15000, 65000, "Gris", "Volkswagen"),
                new Vehiculo("Tiguan", 2018, 22000, 45000, "Plata", "Volkswagen"),
                new Vehiculo("Corolla", 2005, 12000, 80000, "Azul", "Toyota Motors"),
                new Vehiculo("Yaris", 2010, 10000, 60000, "Rojo", "Toyota Motors"),
                new Vehiculo("RAV4", 2015, 18000, 50000, "Blanco", "Toyota Motors"),
                new Vehiculo("Clase A", 2017, 25000, 60000, "Negro", "Mercedes-Benz Group"),
                new Vehiculo("Clase C", 2015, 30000, 70000, "Blanco", "Mercedes-Benz Group"),
                new Vehiculo("Clase E", 2019, 35000, 50000, "Gris", "Mercedes-Benz Group")
                // Agregar más vehículos según sea necesario
        };
    }

    private void agregarCliente() {
        JTextField txtNombre = new JTextField(20);
        JTextField txtDireccion = new JTextField(20);
        JTextField txtTelefono = new JTextField(20);
        JTextField txtCorreo = new JTextField(20);
        JTextField txtRut = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Dirección:"));
        panel.add(txtDireccion);
        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);
        panel.add(new JLabel("Correo electrónico:"));
        panel.add(txtCorreo);
        panel.add(new JLabel("RUT:"));
        panel.add(txtRut);

        int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Cliente",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String nombre = txtNombre.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String correo = txtCorreo.getText().trim();
            String rut = txtRut.getText().trim();

            if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || correo.isEmpty() || rut.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!correo.contains("@")) {
                JOptionPane.showMessageDialog(this, "Correo electrónico inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validarRut(rut)) {
                JOptionPane.showMessageDialog(this, "RUT inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Aquí puedes guardar los datos del cliente o hacer lo que necesites
            JOptionPane.showMessageDialog(this, "Cliente agregado correctamente:\n" +
                    "Nombre: " + nombre + "\n" +
                    "Dirección: " + direccion + "\n" +
                    "Teléfono: " + telefono + "\n" +
                    "Correo electrónico: " + correo + "\n" +
                    "RUT: " + rut);
        }
    }

    private boolean validarRut(String rut) {
        if (!rut.matches("\\d{7,8}-[0-9Kk]")) {
            return false;
        }
        String[] rutParts = rut.split("-");
        int rutNumber = Integer.parseInt(rutParts[0]);
        char dv = rutParts[1].charAt(0);
        int m = 0, s = 1;
        for (; rutNumber != 0; rutNumber /= 10) {
            s = (s + rutNumber % 10 * (9 - m++ % 6)) % 11;
        }
        char calculatedDV = (char) (s != 0 ? s + 47 : 75);
        return dv == calculatedDV;
    }

    private void agregarVehiculo() {
        JTextField txtModelo = new JTextField(20);
        JTextField txtAño = new JTextField(20);
        JTextField txtPrecio = new JTextField(20);
        JTextField txtKilometros = new JTextField(20);
        JTextField txtColor = new JTextField(20);
        JComboBox<String> comboMarca = new JComboBox<>(marcas);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Modelo:"));
        panel.add(txtModelo);
        panel.add(new JLabel("Año:"));
        panel.add(txtAño);
        panel.add(new JLabel("Precio:"));
        panel.add(txtPrecio);
        panel.add(new JLabel("Kilómetros:"));
        panel.add(txtKilometros);
        panel.add(new JLabel("Color:"));
        panel.add(txtColor);
        panel.add(new JLabel("Marca:"));
        panel.add(comboMarca);

        int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Vehículo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String modelo = txtModelo.getText().trim();
            int año = Integer.parseInt(txtAño.getText().trim());
            int precio = Integer.parseInt(txtPrecio.getText().trim());
            int kilometros = Integer.parseInt(txtKilometros.getText().trim());
            String color = txtColor.getText().trim();
            String marca = (String) comboMarca.getSelectedItem();

            // Aquí puedes guardar los datos del vehículo o hacer lo que necesites
            JOptionPane.showMessageDialog(this,
                    "Vehículo agregado correctamente:\n" +
                            "Modelo: " + modelo + "\n" +
                            "Marca: " + marca + "\n" +
                            "Año: " + año + "\n" +
                            "Precio: $" + precio + "\n" +
                            "Kilómetros: " + kilometros + " km\n" +
                            "Color: " + color);
        }
    }

    private void buscarVehiculo(String marcaSeleccionada) {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Modelo");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Año");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Kilómetros");
        modeloTabla.addColumn("Color");

        for (Vehiculo vehiculo : vehiculos) {
            if (marcaSeleccionada == null || marcaSeleccionada.isEmpty() || vehiculo.getMarca().equals(marcaSeleccionada)) {
                modeloTabla.addRow(vehiculo.toArray());
            }
        }

        JTable tablaVehiculos = new JTable(modeloTabla);
        tablaVehiculos.setFillsViewportHeight(true);
        tablaVehiculos.getTableHeader().setReorderingAllowed(false);
        tablaVehiculos.setDefaultEditor(Object.class, null);
        tablaVehiculos.setBorder(new LineBorder(Color.BLACK));
        tablaVehiculos.setGridColor(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(tablaVehiculos);
        JOptionPane.showMessageDialog(this, scrollPane, "Vehículos disponibles", JOptionPane.PLAIN_MESSAGE);
    }

    private void seleccionarMarca() {
        JComboBox<String> comboMarcas = new JComboBox<>(marcas);
        JButton btnVerTodo = new JButton("Ver Todo");

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new JLabel("Seleccione la marca:"));
        panel.add(comboMarcas);
        panel.add(btnVerTodo);

        btnVerTodo.addActionListener(e -> buscarVehiculo(null));

        int result = JOptionPane.showConfirmDialog(null, panel, "Buscar Vehículo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String seleccion = (String) comboMarcas.getSelectedItem();
            if (!seleccion.equals("Ver Todo")) {
                buscarVehiculo(seleccion);
            } else {
                buscarVehiculo(null);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AutomotoraGUI gui = new AutomotoraGUI();
            gui.setVisible(true);
        });
    }
}