
import java.util.Random;

/**
 * Problema03: Se desea realizar una aplicación que permita a un periodista
 * deportivo llevar las estadísticas de los jugadores de un equipo de fútbol
 * para poder valorar su actuación en el partido.
 *
 * Cada jugador se identifica por su nombre, número de dorsal y Rut
 *
 * Los jugadores se dividen en tres categorías:
 *
 * Atacantes Defensores Porteros Para todos los jugadores se desea contabilizar
 * el número de goles marcados, además en el caso de los jugadores de campo se
 * contabilizan los pases realizados con éxito y el número de balones
 * recuperados. En el caso de los porteros se contabilizan las atajadas
 * realizadas. Valoración del jugador Cálculo base para todos los jugadores:
 *
 * valor_goles = goles * 30 Valor adicional según tipo de jugador:
 *
 * Atacantes
 *
 * valor += recuperaciones * 3 Defensores
 *
 * valor += recuperaciones * 4 Porteros
 *
 * valor += atajadas * 5 Note
 *
 * Se debe aplicar polimorfismo mediante la aplicación de herencia,
 * encapsulamiento de atributos y comportamientos comunes, y especializar
 * comportamiento según el tipo de jugador.
 *
 * @author Matthew Castillo
 * @version 1.0
 */
abstract class Jugador {

    protected int contadorGol;
    public String nombre;
    public int numeroDorsal;
    public double Rut;

    public Jugador(String nombre, int numeroDorsal, double Rut) {
        this.nombre = nombre;
        this.numeroDorsal = numeroDorsal;
        this.Rut = Rut;
    }

    abstract public double valor_Jugador();

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", numeroDorsal=" + numeroDorsal + ", Rut=" + Rut + '}';
    }
}

class Atacantes extends Jugador {

    public int recuperaciones;
    public int pasesBuenos;
    public double valor;

    public Atacantes(String nombre, int numeroDorsal, double Rut) {
        super(nombre, numeroDorsal, Rut);
    }

    public double marcador_Goles() {
        Random Ale = new Random();
        boolean gol = Ale.nextBoolean();
        if (gol) {
            this.contadorGol++;
        }
        return this.contadorGol;
    }

    public double valor_pases() {
        Random Ale = new Random();
        boolean pases = Ale.nextBoolean();
        if (pases) {
            this.pasesBuenos++;
        }
        return this.pasesBuenos;
    }

    public double valor_recuperaciones() {
        Random Ale = new Random();
        boolean recuperacionB = Ale.nextBoolean();
        if (recuperacionB) {
            this.recuperaciones++;
            this.valor = recuperaciones * 3;
        }
        return this.valor;
    }

    public double valor_Jugador() {
        return (contadorGol * 30) + valor;
    }

    @Override
    public String toString() {
        return "Atacantes{" + "contadorGol=" + contadorGol + ", recuperaciones=" + recuperaciones + ", pasesBuenos=" + pasesBuenos + ", valor=" + valor + '}';
    }

}

class Defensores extends Jugador {

    public int recuperaciones;
    public int pasesBuenos;
    public double valor;

    public Defensores(String nombre, int numeroDorsal, double Rut) {
        super(nombre, numeroDorsal, Rut);
    }

    public double marcador_Goles() {
        Random Ale = new Random();
        boolean gol = Ale.nextBoolean();
        if (gol) {
            this.contadorGol++;
        }
        return this.contadorGol;
    }

    public double valor_pases() {
        Random Ale = new Random();
        boolean pases = Ale.nextBoolean();
        if (pases) {
            this.pasesBuenos++;
        }
        return this.pasesBuenos;
    }

    public double valor_recuperaciones() {
        Random Ale = new Random();
        boolean recuperacionB = Ale.nextBoolean();
        if (recuperacionB) {
            this.recuperaciones++;
            this.valor = recuperaciones * 4;
        }
        return this.valor;
    }

    public double valor_Jugador() {
        return (contadorGol * 30) + valor;
    }

    @Override
    public String toString() {
        return "Defensores{" + "contadorGol=" + contadorGol + ", recuperaciones=" + recuperaciones + ", pasesBuenos=" + pasesBuenos + ", valor=" + valor + '}';
    }

}

class Portero extends Jugador {

    public int contadorAtajadas;
    public double valor;

    public Portero(String nombre, int numeroDorsal, double Rut) {
        super(nombre, numeroDorsal, Rut);
    }

    public double valor_Atajadas() {
        Random Ale = new Random();
        boolean atajadas = Ale.nextBoolean();
        if (atajadas) {
            this.contadorAtajadas++;
            this.valor = contadorAtajadas * 5;
        }
        return this.valor;
    }

    public double valor_Jugador() {
        return (contadorGol * 30) + valor;
    }

    @Override
    public String toString() {
        return "Portero{" + "contadorGol=" + contadorGol + ", contadorAtajadas=" + contadorAtajadas + ", valor=" + valor + '}';
    }

}

public class Problema_3_EjecutorEstadisticas {

    public static void main(String[] args) {
        Atacantes atacante = new Atacantes("Mauricio", 5, 11055451);
        Defensores defensa = new Defensores("Arturo", 15, 110554854);
        Portero portero = new Portero("Vicente", 23, 110541142);
        
        System.out.println("======= ESTADISTICAS DEL ATACANTE =======");
        System.out.println("Goles: "+ atacante.marcador_Goles());
        System.out.println("Pases: "+ atacante.valor_pases());
        System.out.println("Recuperaciones: " + atacante.valor_recuperaciones());
        System.out.println("Valor Jugador: " + atacante.valor_Jugador());
        
        System.out.println("======= ESTADISTICAS DEL DEFENSOR =======");
        System.out.println("Goles: "+ defensa.marcador_Goles());
        System.out.println("Pases: "+ defensa.valor_pases());
        System.out.println("Recuperaciones: " + defensa.valor_recuperaciones());
        System.out.println("Valor Jugador: " + defensa.valor_Jugador());    
        
        System.out.println("======= ESTADISTICAS DEL PORTERO =======");
        System.out.println("Atajadas: " + portero.valor_Atajadas());
        System.out.println("Valor Jugador: " + portero.valor_Jugador());        
    }

}
/**
 * Run:
 * run-single:
======= ESTADISTICAS DEL ATACANTE =======
Goles: 0.0
Pases: 1.0
Recuperaciones: 3.0
Valor Jugador: 3.0
======= ESTADISTICAS DEL DEFENSOR =======
Goles: 0.0
Pases: 0.0
Recuperaciones: 4.0
Valor Jugador: 4.0
======= ESTADISTICAS DEL PORTERO =======
Atajadas: 0.0
Valor Jugador: 0.0
BUILD SUCCESSFUL (total time: 0 seconds)
 */
