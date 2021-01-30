package generarmatrículas;

/**
 * Clase que genera una lista de matrículas
 * 
 * @author davibern
 * @version 1.0
 */
public class EliminarMatricula extends Thread {
   
    // Atributos
    private final Matriculas matriculas;
    private int cantidad;
    
    /**
     * Método que genera una lista de matrículas
     * @param matriculas lista o recurso compartido
     */
    public EliminarMatricula(Matriculas matriculas, int cantidad) {
        this.matriculas = matriculas;
        this.cantidad = cantidad;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < this.cantidad; i++) {
            this.matriculas.borrarRandmonMatricula();
        }
                
    }
    
    
}
