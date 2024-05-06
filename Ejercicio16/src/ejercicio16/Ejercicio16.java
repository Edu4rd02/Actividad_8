/*
 * Becerra Flores Jose Eduardo
 * Ejercicio 16. Empleados con CSV
 * Damos de alta empleados y agregamos a estos a un CSV
 * O extraemos los empleados ya almacenados en el CSV 
 */

package ejercicio16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Ejercicio16 {
    public static ArrayList<Puesto> arr = new ArrayList<Puesto>();
    public static ArrayList<Empleado> empleados = new ArrayList<>();

    //*MAIN
    public static void main(String[] args) throws IOException {
        Scanner read = new Scanner(System.in);
        String op = "";
        int opPuesto;

        Leer("src\\ejercicio16\\Puestos.csv");
        Leer(empleados, "src\\ejercicio16\\empleado.csv");

        do {
            //Limpiar pantalla
            System.out.print("\033[H\033[2J");
            System.out.flush();
            menu();
            op =read.nextLine();
            switch (op) {
                case "1":
                    //Pedimos los datos y agregamos un nuevo empleado a nuestro ArrayList
                    Empleado tmp = new Empleado();
                    
                    System.out.println("\nALTA EMPLEADO");
                    System.out.print("Nombre: ");
                    tmp.setNombre(read.nextLine());
                    
                    System.out.print("Apellido: ");
                    tmp.setApellido(read.nextLine());
            
                    System.out.print("Numero IMSS: ");
                    tmp.setNSS(read.nextLine());

                    tmp.setOnline(true);

                    System.out.println("--PUESTOS DISPONIBLES--");
                    for (int i = 0; i < arr.size(); i++) {
                        System.out.println((i+1)+". "+arr.get(i));
                    }
                    System.out.print("Elige un puesto: ");
                    opPuesto = Integer.parseInt(read.nextLine());
                    tmp.setActividad(arr.get(opPuesto-1));
                    empleados.add(tmp);

                    int id = empleados.size();
                    empleados.get(id-1).setId(id);

                    System.out.println("\n**Empleado registrado exitosamente");
                    pausar();
                    break;

                case "2":
                    //Cambia el estado de los empleados a False
                    System.out.println("\n**Dar de baja empleado");
                    System.out.print("Empleado numero: ");
                    int empleado = Integer.parseInt(read.nextLine());
                    empleados.get(empleado-1).setOnline(false);
                    System.out.println("Ha sido dado de baja: "+empleados.get(empleado-1).getNombre());
                    pausar();  
                    break;
                case "3":
                    //Imprime una lista de los empleados. //!Solo si estos estan ONLINE
                    System.out.println("\n**Empleados registrados: ");
                    for (int i = 0; i < empleados.size(); i++) {
                        if (empleados.get(i).getOnline()) {
                            System.out.println(empleados.get(i));     
                        }
                    }
                    pausar();
                    break;
                case "4":
                    //Guardar en el archivo CSV a los empleados nuevos
                    System.out.println("\n**Guardar personas dadas de alta en lista de datos: ");
                    guardar(empleados);
                    System.out.println("\nPersonas guardadas con exito");
                    pausar();

                    break;
                
                case "5":
                    System.out.println("Saliendo...");
                    break;
                default:
                    break;
            }
        } while (!op.equals("5"));
        guardar(empleados); //Por si las moscas, capaz se sale y no guarde
        System.out.println("\nPersonas guardadas con exito. Por si acaso");
    }

    public static void pausar(){
        Scanner read = new Scanner(System.in);
        System.out.println("Presiona ENTER para continuar");
        read.nextLine();
    }

    //*Funcion para mostrar el menu al ususario
    public static void menu()
    {
        System.out.println("MENU");
        System.out.println("1. Dar de alta.");
        System.out.println("2. Dar de baja al empleado.");
        System.out.println("3. Mostrar lista.");
        System.out.println("4. Guardar archivo. ");
        System.out.println("5. Salir.");
        System.out.print("Opcion: ");
    }

    //*Funcion guardar en un archivo CSV a nuestra lista de empleados
    public static void guardar(ArrayList<Empleado> empleados) throws IOException{
        File file = new File("src\\ejercicio16\\empleado.csv");
        FileWriter fw = new FileWriter(file);
        for (int x =0; x<empleados.size(); x++) {
                String linea = empleados.get(x).getId()+","
                               +empleados.get(x).getOnline()+","
                               +empleados.get(x).getNombre()+","
                               +empleados.get(x).getApellido()+","
                               +empleados.get(x).getNSS()+","
                               +empleados.get(x).getActividad().getDescripcion()+","
                               +empleados.get(x).getActividad().getSalario()+"\n";
                
                System.out.println(linea);
                fw.write(linea);
        }
        fw.close();
    }
    //*Funcion para Leer todo el archivo CSV y agregar los datos a un ArrayList
    //*Sobrecarga por cambio de parametros */
    public static void Leer(ArrayList<Empleado> arr, String archivo)
    {
        BufferedReader br =null;
        String linea = "";

        //Forzosamente para usar un bufferedReader necesitamos un try-catch o definir las expeciones en el main
        //Esto se hace para que en caso de que surja un error, el codigo se pueda seguir ejecutando
        try {
            br = new BufferedReader(new FileReader(archivo));
            
            //Gracias al bufferedReader podemos leer la linea del archivo CSV
            //Podemos usar una variable String para leer esa linea del archivo y luego imprimirla
            while ((linea = br.readLine()) != null) {
                //Como sabemos que la informacion esata dividida por comas
                //Usamos el comando split para dividir nuestra linea en subcadenas que son divididas o separadas por las comas
                String[] informacion = linea.split(",");

                Empleado em = new Empleado(Integer.parseInt(informacion[0]),informacion[1],informacion[2],informacion[3],informacion[4],informacion[5],Integer.parseInt(informacion[6]));

                arr.add(em);

                //*System.out.println(linea); Manera alternativa de imprimir la linea completa de un archivo CSV

            }
        //Error en caso de que el archivo no se encuentre
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Error en caso de que el br.readLine no funcione
        catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    //*Funcion para Leer todo el archivo CSV, agregar los datos al array List de Puestos y mostrarlos en Main
    public static void Leer(String archivo)
    {
        BufferedReader br =null;
        String linea = "";

        //Forzosamente para usar un bufferedReader necesitamos un try-catch o definir las expeciones en el main
        //Esto se hace para que en caso de que surja un error, el codigo se pueda seguir ejecutando
        try {
            br = new BufferedReader(new FileReader(archivo));
            
            //Gracias al bufferedReader podemos leer la linea del archivo CSV
            //Podemos usar una variable String para leer esa linea del archivo y luego imprimirla
            while ((linea = br.readLine()) != null) {
                //Como sabemos que la informacion esata dividida por comas
                //Usamos el comando split para dividir nuestra linea en subcadenas que son divididas o separadas por las comas
                String[] informacion = linea.split(",");

                //El arreglo informacion contiene los datos separados por comas, el primer elemento del arrelgo es el nombre de la persona y el segundo la altura
                // System.out.print("Puesto de la persona: "+informacion[0]+"\t\t");
                // System.out.println("Salario de la persona: "+informacion[1]+"\n");
                Puesto pr = new Puesto(informacion[0].trim(),Integer.parseInt(informacion[1].trim()));

                arr.add(pr);
                // System.out.println(linea); //Manera alternativa de imprimir la linea completa de un archivo CSV

            }
        //Error en caso de que el archivo no se encuentre
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Error en caso de que el br.readLine no funcione
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
