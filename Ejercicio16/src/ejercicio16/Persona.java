package ejercicio16;

public class Persona {
    // Atributos por defecto son publicos
    private String nombre;
    private String apellido;
    // float estatura;
    private boolean online; // Por defecto se inicializa en false
    protected int id; // Se hereda

    //*Constructor Cuando no tenemos constructor, hereda un constructor de la clase objetc
    public Persona() {
        this.nombre = "";
        this.apellido = "";

    }

    //*Getters y Setters.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    //*Metodo toString
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "Apellido: " + apellido + "\n" +
                "Vivo: " + online + "\n" +
                "ID: " + id + "\n";
    }

}
