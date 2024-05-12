package Automotora;

class Cliente {
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String rut;

    public Cliente(String nombre, String direccion, String telefono, String correo, String rut) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRut() {
        return rut;
    }
}