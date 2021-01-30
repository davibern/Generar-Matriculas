package generarmatrículas;

// Librerías
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que administra el fichero con las matrículas almacenadas
 * 
 * @author davibern
 * @version 1.0
 */
public class Matriculas {
    
    // Atributos de clase
    private List<String> matriculas = new LinkedList<>();
    private final String[] letras = {"B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
    private final int[] numeros = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    
    /**
     * Constructor del almacén de matrículas
     * @param matriculas lista que contiene las matrículas generadas
     */
    public Matriculas (List<String> matriculas) {
        this.matriculas = matriculas;
    }
    
    /**
     * Método que añade matrículas
     */
    public synchronized void agregarMatricula() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Matriculas.class.getName()).log(Level.SEVERE, null, ex);
        }
        String matricula = this.nuevaMatricula();
        this.matriculas.add(matricula);
        this.notify();
        System.out.println("Matrícula añadida: " + matricula);
    }
    
    /**
     * Método que extrae matrículas aleatorias
     */
    public synchronized void borrarRandmonMatricula() {
        Random rand = new Random();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Matriculas.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (this.matriculas.size() > 0) {
            int j = rand.nextInt(this.matriculas.size());
            System.out.println("Matrícula eliminada: " + this.matriculas.get(j));
            this.matriculas.remove(j); 
        } else {
            System.out.println("Error -> No hay matrículas.");
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Matriculas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.notify();
    }
    
    /**
     * Método que genera una matrícula nueva
     * @return matrícula que se añadirá a la lista
     */
    private String nuevaMatricula() {
        StringBuilder matricula = new StringBuilder();
        Random rand = new Random();
        
        do {
            for (int i = 0; i < 4; i++) {
                int j = rand.nextInt(numeros.length);
                matricula.append(j);
            }
        
            matricula.append(" ");
        
            for (int i = 0; i < 3; i++) {
                int j = rand.nextInt(letras.length);
                matricula.append(letras[j]);
            }
        } while (this.esDuplicado(matricula.toString()));
        
        return matricula.toString();
    }
    
    /**
     * Método que comprueba si la matrícula ya existe en el almacén de matrículas
     * @param matricula matrícula que se va a comparar con la lista
     * @return Falso si la matrícula no existe y Verdadero si la matrícula existe
     */
    private boolean esDuplicado(String matricula) {
        boolean flag = false;
        for (String posicion : matriculas) {
            if (posicion.equals(matricula)) flag = true;
        }
        return flag;
    }
    
    /**
     * Método que muestra todas las matrículas almacenadas
     * @return lista de matrículas
     */
    @Override
    public String toString() {
        return this.matriculas.toString();
    }
    
}
