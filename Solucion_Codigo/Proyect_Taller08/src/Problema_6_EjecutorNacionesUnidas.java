
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Problema 6: La ONU le solicita desarrollar un simulador de conflictos bélicos
 * mundiales en el lenguaje de alto nivel orientado a objetos Java, considerando
 * sus cuatro pilares fundamentales: abstracción, encapsulamiento, herencia y
 * polimorfismo, cumpliendo con los siguientes lineamientos:
 *
 * Requisitos Funcionales De manera general, cada nación debe ser representada
 * con la siguiente información: Nombre de la nación, Número de habitantes de la
 * nación, Cantidad de recursos económicos disponibles, Nivel de poder militar
 * (valor entre 1 y 100), su estado de conflicto que indica si la nación está
 * actualmente en conflicto o no, y cualquier otra información que usted
 * considere necesaria. No olvide implementar los métodos y/o constructores
 * básicos para procesar esta información dados todos los requerimientos. A su
 * vez se requiere la información de las naciones desarrolladas con alta
 * tecnología militar, como: Si la nación dispone de tecnología avanzada. Para
 * estas naciones avanzadas, implementar el cálculo del impacto, el cual
 * considera un bono de tecnología para el incremento de su poder militar (no
 * olvide que para este último la restricción es de 1-100, y en el caso de sobre
 * pasar, asigne directamente 100). De igual forma se necesita conocer de las
 * naciones en vías de desarrollo su nivel de recursos limitados (recursos
 * económicos y poder militar por cada N habitantes), así como la implementación
 * del cálculo del impacto, el cual reduce el impacto en el conflicto debido a
 * sus recursos limitados. Queda a su criterio matemático y/o estadístico el
 * planteamiento del modelo matemático (con las variables/parámetros que tenga a
 * bien) para calcular este factor de impacto. Para las naciones desarrolladas o
 * en vías de desarrollo, considere sus naciones aliadas, lo cual es decisivo
 * para incrementar o decrementar su nivel de impacto directamente a su poder
 * militar, pero solo si tiene aliados disponibles. El programa debe permitir
 * declarar conflictos entre dos naciones seleccionadas con un proceso
 * aleatorio/randomico. Calcular las consecuencias del conflicto utilizando
 * polimorfismo y la implementación de cálculo de impacto. Consecuencias del
 * conflicto: Reducción del 5% de población por cada diferencia en los niveles
 * de poder militar. Reducción del 10% de recursos de la nación derrotada. Si
 * las naciones tienen el mismo nivel de poder militar, ambas pierden el 5% de
 * recursos. Al finalizar el programa, debe mostrar un reporte con el estado
 * actual de cada nación (población, recursos y estado de conflicto, etc), así
 * como el total de conflictos que se simularon entre N naciones. Note
 *
 * Reporte Final
 *
 * Al finalizar la simulación, debe generarse un reporte general que contenga:
 * Estado actual de cada nación (población, recursos, estado de conflicto, etc.)
 * Total de conflictos simulados entre N naciones.
 *
 * @author Matthew Castillo
 * @version 1.0
 */
abstract class Nacion {
 
    public String nombreNacion;
    public double numeroHabitantes;
    public double cantidadRecursos;
    public int poderMilitar; 
    public boolean estadoConflicto;
    public List<Nacion> aliados;
 
    public Nacion(String nombreNacion, double numeroHabitantes, double cantidadRecursos, int poderMilitar) {
        this.nombreNacion = nombreNacion;
        this.numeroHabitantes = numeroHabitantes;
        this.cantidadRecursos = cantidadRecursos;
        this.poderMilitar = limitarPoder(poderMilitar);
        this.estadoConflicto = false;
        this.aliados = new ArrayList<>();
    }
 
    public int limitarPoder(int poder) {
        if (poder > 100) return 100;
        if (poder < 1) return 1;
        return poder;
    }
 
    public void agregarAliado(Nacion aliado) {
        this.aliados.add(aliado);
    }
 
    public abstract int calcularImpacto();
 
    public void aplicarEfectoAliados() {
        if (!aliados.isEmpty()) {
            Random ale = new Random();
            int efecto = ale.nextInt(11) - 5; 
            int nuevoPoder = this.poderMilitar + (efecto * aliados.size());
            this.poderMilitar = limitarPoder(nuevoPoder);
        }
    }
 
    public void reducirPoblacion(double porcentaje) {
        double habitantesPerdidos = this.numeroHabitantes * porcentaje;
        this.numeroHabitantes = this.numeroHabitantes - habitantesPerdidos;
    }
 
    public void reducirRecursos(double porcentaje) {
        this.cantidadRecursos -= this.cantidadRecursos * porcentaje;
    }
 
    @Override
    public String toString() {
        return "Nacion{" + "nombre=" + nombreNacion
                + ", habitantes=" + numeroHabitantes
                + ", recursos=" + String.format("%.2f", cantidadRecursos)
                + ", poderMilitar=" + poderMilitar
                + ", enConflicto=" + estadoConflicto
                + ", aliados=" + aliados.size() + '}';
    }
}
 

class NacionDesarrollada extends Nacion {
 
    public boolean tecnologiaAvanzada;
 
    public NacionDesarrollada(String nombre, double habitantes, double recursos,
                               int poderMilitar, boolean tecnologiaAvanzada) {
        super(nombre, habitantes, recursos, poderMilitar);
        this.tecnologiaAvanzada = tecnologiaAvanzada;
    }
 
    @Override
    public int calcularImpacto() {
        int bono = tecnologiaAvanzada ? 15 : 0;
        return limitarPoder(poderMilitar + bono);
    }
 
    @Override
    public String toString() {
        return "NacionDesarrollada{tecnologiaAvanzada=" + tecnologiaAvanzada + "} " + super.toString();
    }
}
 

class NacionEnViasDeDesarrollo extends Nacion {
 
    public NacionEnViasDeDesarrollo(String nombre, double habitantes, double recursos, int poderMilitar) {
        super(nombre, habitantes, recursos, poderMilitar);
    }
 
    public double factorRecursosLimitados() {
        double recursosPorHabitante = (cantidadRecursos / numeroHabitantes) * 1_000_000;
        // normalizamos: entre mas bajo el recurso por habitante, mayor penalizacion (max 30%)
        double penalizacion = Math.min(0.30, 1000.0 / (recursosPorHabitante + 1));
        return penalizacion;
    }
 
    @Override
    public int calcularImpacto() {
        double penalizacion = factorRecursosLimitados();
        int poderReducido = (int) (poderMilitar * (1 - penalizacion));
        return limitarPoder(poderReducido);
    }
}
 

public class Problema_6_EjecutorNacionesUnidas {
 
    static int conflictosSimulados = 0;
 
    public static void main(String[] args) {
 
        List<Nacion> naciones = new ArrayList<>();
        naciones.add(new NacionDesarrollada("Alemania", 83_000_000.0, 950.0, 78, true));
        naciones.add(new NacionDesarrollada("Japon", 125_000_000.0, 880.0, 70, true));
        naciones.add(new NacionEnViasDeDesarrollo("Bolivia", 12_000_000.0, 15.0, 40));
        naciones.add(new NacionEnViasDeDesarrollo("Ecuador", 18_000_000.0, 20.0, 45));
 
        naciones.get(0).agregarAliado(naciones.get(1)); 
 
        Random ale = new Random();
        int numeroConflictos = 3; 
 
        for (int i = 0; i < numeroConflictos; i++) {
            int indiceA, indiceB;
            do {
                indiceA = ale.nextInt(naciones.size());
                indiceB = ale.nextInt(naciones.size());
            } while (indiceA == indiceB); 
 
            Nacion nacionA = naciones.get(indiceA);
            Nacion nacionB = naciones.get(indiceB);
 
            declararConflicto(nacionA, nacionB);
        }
 
        System.out.println("\n=== REPORTE FINAL DE LA ONU ===");
        for (Nacion n : naciones) {
            System.out.println(n);
        }
        System.out.println("\nTotal de conflictos simulados: " + conflictosSimulados);
    }
 
    static void declararConflicto(Nacion a, Nacion b) {
        conflictosSimulados++;
        a.estadoConflicto = true;
        b.estadoConflicto = true;
 
        a.aplicarEfectoAliados();
        b.aplicarEfectoAliados();
 
        int impactoA = a.calcularImpacto();
        int impactoB = b.calcularImpacto();
 
        System.out.println("\n--- CONFLICTO: " + a.nombreNacion + " vs " + b.nombreNacion + " ---");
        System.out.println(a.nombreNacion + " -> impacto: " + impactoA);
        System.out.println(b.nombreNacion + " -> impacto: " + impactoB);
 
        int diferencia = Math.abs(impactoA - impactoB);
 
        if (impactoA == impactoB) {
            a.reducirRecursos(0.05);
            b.reducirRecursos(0.05);
            System.out.println("Empate: ambas naciones pierden 5% de recursos.");
        } else {
            Nacion ganador = (impactoA > impactoB) ? a : b;
            Nacion perdedor = (impactoA > impactoB) ? b : a;
 
            double porcentajePoblacion = diferencia * 0.05; 
            perdedor.reducirPoblacion(porcentajePoblacion);
            perdedor.reducirRecursos(0.10);
 
            System.out.println("Ganador: " + ganador.nombreNacion);
            System.out.println(perdedor.nombreNacion + " pierde " + String.format("%.1f", porcentajePoblacion * 100)
                    + "% de poblacion y 10% de recursos.");
        }
 
        a.estadoConflicto = false;
        b.estadoConflicto = false;
    }
}
/**
 * Run:
 * --- CONFLICTO: Japon vs Ecuador ---
Japon -> impacto: 85
Ecuador -> impacto: 31
Ganador: Japon
Ecuador pierde 270,0% de poblacion y 10% de recursos.

--- CONFLICTO: Bolivia vs Ecuador ---
Bolivia -> impacto: 28
Ecuador -> impacto: 31
Ganador: Ecuador
Bolivia pierde 15,0% de poblacion y 10% de recursos.

--- CONFLICTO: Japon vs Alemania ---
Japon -> impacto: 85
Alemania -> impacto: 93
Ganador: Alemania
Japon pierde 40,0% de poblacion y 10% de recursos.

=== REPORTE FINAL DE LA ONU ===
NacionDesarrollada{tecnologiaAvanzada=true} Nacion{nombre=Alemania, habitantes=8.3E7, recursos=950,00, poderMilitar=78, enConflicto=false, aliados=1}
NacionDesarrollada{tecnologiaAvanzada=true} Nacion{nombre=Japon, habitantes=7.5E7, recursos=792,00, poderMilitar=70, enConflicto=false, aliados=0}
Nacion{nombre=Bolivia, habitantes=1.02E7, recursos=13,50, poderMilitar=40, enConflicto=false, aliados=0}
Nacion{nombre=Ecuador, habitantes=-3.06E7, recursos=18,00, poderMilitar=45, enConflicto=false, aliados=0}

Total de conflictos simulados: 3
BUILD SUCCESSFUL (total time: 0 seconds)
 */
 