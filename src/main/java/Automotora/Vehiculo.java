package Automotora;

class Vehiculo {
    private String nombre;
    private int año;
    private int precio;
    private int kilometros;
    private String color;
    private String marca;

    public Vehiculo(String nombre, int año, int precio, int kilometros, String color, String marca) {
        this.nombre = nombre;
        this.año = año;
        this.precio = precio;
        this.kilometros = kilometros;
        this.color = color;
        this.marca = marca;
    }

    public Object[] toArray() {
        return new Object[]{nombre, marca, año, precio, kilometros, color};
    }

    public String getMarca() {
        return marca;
    }
}