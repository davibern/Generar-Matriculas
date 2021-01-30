package generarmatrículas;

// Librerías
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que ejecuta el programa.
 * 
 * - Lee de un fichero las intrucciones
 * - Pasas dichas instrucciones al programa que genera matrículas
 * 
 * @author davibern
 * @version 1.0
 */
public class Main {
    
    private static File file = new File ("matriculas.txt");
    private static FileReader fr = null;
    private static BufferedReader br = null;
    
    public static void main(String[] args) {
        
        String cadena = null;
        List<String> fichero = new LinkedList<>();
        Matriculas matriculas = new Matriculas(fichero);
        List<Thread> hilos = new LinkedList<>();
        
        if (file.exists()) {
         
            try {
                fr = new FileReader(file);
                br = new BufferedReader(fr);

                while ((cadena = br.readLine()) != null) {
                    String action = cadena.substring(0, 3);
                    String times = cadena.substring(4, cadena.length());
                    System.out.printf("Action: %s, Times: %s\n", action, times);

                    switch (action) {
                        case "add":
                            GenerarMatricula generar = new GenerarMatricula(matriculas, Integer.parseInt(times));
                            hilos.add(generar);
                            break;
                        case "del":
                            EliminarMatricula eliminar = new EliminarMatricula(matriculas, Integer.parseInt(times));
                            hilos.add(eliminar);
                            break;
                    }
                }

            } catch (FileNotFoundException ex) {
                System.err.println(ex.getMessage());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            } catch (NullPointerException ex) {
                System.err.println(ex.getMessage());
            } finally {
                try {
                    br.close();
                    fr.close();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            for (Thread hilo : hilos) {
                hilo.start();
            }

            for (Thread hilo : hilos) {
                try {
                    hilo.join();
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            System.out.println("Ejecución terminada.");
            System.out.println("Lista de matrículas restantes:");
            System.out.println(fichero);
            
        } else {
            System.err.println("Error -> No se puede abrir el fichero.");
        }
        
    }
    
}
