package ejercicio16;

//* Herencia. Empelado hereda atributos y metodos de persona
public class Empleado extends Persona {
    private String NSS;
    private Puesto actividad; // !Esto no es herencia, es una variable tipo objeto Puesto

    public Empleado()
    {
        this.id = 0;
    }

    public Empleado(int id,String Online,String Nombre, String Apellido,String NSS,String Descricpion,int Salario)
    {
        setId(id);
        setNombre(Nombre);
        setApellido(Apellido);
        setOnline(Boolean.valueOf(Online)); //Convertir String a boolean
        setNSS(NSS);
        this.setActividad(new Puesto(Descricpion,Salario));
    }    
    //* Getters y Setters
    public Puesto getActividad() {
        return actividad;
    }

    public void setActividad(Puesto actividad) {
        this.actividad = actividad;
    }

    public String getNSS() {
        return NSS;
    }

    public void setNSS(String nSS) {
        NSS = nSS;
    }

    //* Metodo toString
    @Override
    public String toString() {
        return "Persona[" + 
       "nombre = " + getNombre() + 
       "\tapellido = " + getApellido() + 
      "\tonline = " + getOnline() + "]\n" + 
       "Empleado[" + 
       "NSS = " + NSS + 
       "\tactividad = " + actividad + "]\n";
    }


}
