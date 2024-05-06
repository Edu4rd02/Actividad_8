package ejercicio16;

public class Puesto {
    private int salario;
    private String descripcion;

    //*Constructor
    public Puesto(){
        this.salario = 2500;
        this.descripcion = "Administracion";
    }
    
    //*Constructor con argumentos
    public Puesto(String descripcion, int salario){
        this.salario = salario;
        this.descripcion = descripcion;
    }

    //*Getters y Setters.
    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //*Metodo toString
    @Override
    public String toString() {
        return "Puesto [salario=" + salario + ", descripcion=" + descripcion + "]";
    }

    
}
