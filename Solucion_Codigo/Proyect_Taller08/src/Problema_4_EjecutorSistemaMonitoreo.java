import java.util.Random;

/**
 * Problema 4: Una red de monitoreo ambiental tiene como objetivo registrar,
 * analizar y reportar los impactos del cambio climático en diferentes regiones.
 * En cada ubicación se instalan dispositivos capaces de medir distintos
 * indicadores climáticos como temperatura, precipitación, calidad del aire, y
 * humedad del suelo. Dependiendo de la región (costa, sierra y oriente), los
 * dispositivos pueden variar en capacidades y protocolos de recolección.
 *
 * Requisitos funcionales:
 *  - Representar diferentes tipos de dispositivos y sus especializaciones.
 *  - Implementar métodos polimórficos que procesen los datos según el tipo
 *    de dispositivo y su región.
 *  - Generar reportes dinámicos según el riesgo ambiental detectado.
 *
 * Nota: para la generación de reportería se usa el toString() base de cada
 * dispositivo, combinado con el riesgo calculado.
 *
 * @author Matthew Castillo
 * @version 1.0 
 */


abstract class Dispositivo {

    protected String nombreDispositivo;
    protected String ubicacion;
    protected String fechaLectura;

    public Dispositivo(String nombreDispositivo, String ubicacion, String fechaLectura) {
        this.nombreDispositivo = nombreDispositivo;
        this.ubicacion = ubicacion;
        this.fechaLectura = fechaLectura;
    }


    public abstract void recolectarDatos();

    public abstract String evaluarRiesgo();

    public String generarReporte(){
        return this.toString() + "Riesgo: " + evaluarRiesgo();
    }

    @Override
    public String toString() {
        return "Dispositivo{" + "nombre=" + nombreDispositivo
                + ", ubicacion=" + ubicacion
                + ", fecha=" + fechaLectura + '}';
    }
}


class Costa extends Dispositivo {

    private double temperatura;
    private String calidadAire;

    public Costa(String nombreDispositivo, String ubicacion, String fechaLectura) {
        super(nombreDispositivo, ubicacion, fechaLectura);
    }

    @Override
    public void recolectarDatos() {
        Random ale = new Random();
        this.temperatura = ale.nextDouble() * 40; 
        int opcion = ale.nextInt(3) + 1;
        switch (opcion) {
            case 1:
                this.calidadAire = "Aire no contaminado";
                break;
            case 2:
                this.calidadAire = "Aire mas o menos contaminado";
                break;
            case 3:
                this.calidadAire = "Aire contaminado PELIGRO";
                break;
        }
    }

    @Override
    public String evaluarRiesgo() {
        if (calidadAire.equals("Aire contaminado PELIGRO")) {
            return "Contaminacion del aire critica";
        } else if (temperatura > 32) {
            return "Probable sequia por altas temperaturas";
        }
        return "Sin riesgos relevantes";
    }

    @Override
    public String toString() {
        return "Costa{" + "temperatura=" + String.format("%.1f", temperatura)
                + ", calidadAire=" + calidadAire + '}' + super.toString();
    }
}


class Sierra extends Dispositivo {

    private double temperatura;
    private double humedadSuelo;

    public Sierra(String nombreDispositivo, String ubicacion, String fechaLectura) {
        super(nombreDispositivo, ubicacion, fechaLectura);
    }

    @Override
    public void recolectarDatos() {
        Random ale = new Random();
        this.temperatura = ale.nextDouble() * 25; 
        this.humedadSuelo = ale.nextDouble() * 100; 
    }

    @Override
    public String evaluarRiesgo() {
        if (humedadSuelo < 30.0) {
            return "Probable sequia, suelo seco";
        } else if (humedadSuelo > 75.0) {
            return "Riesgo de deslizamiento por exceso de humedad";
        }
        return "Humedad del suelo optima";
    }

    @Override
    public String toString() {
        return "Sierra{" + "temperatura=" + String.format("%.1f", temperatura)
                + ", humedadSuelo=" + String.format("%.1f", humedadSuelo) + '}' + super.toString();
    }
}


class Oriente extends Dispositivo {

    private double precipitacion; 
    private String calidadAire;

    public Oriente(String nombreDispositivo, String ubicacion, String fechaLectura) {
        super(nombreDispositivo, ubicacion, fechaLectura);
    }

    @Override
    public void recolectarDatos() {
        Random ale = new Random();
        this.precipitacion = ale.nextDouble() * 150; 
        int opcion = ale.nextInt(2) + 1;
        this.calidadAire = (opcion == 1) ? "Aire no contaminado" : "Aire mas o menos contaminado";
    }

    @Override
    public String evaluarRiesgo() {
        if (precipitacion > 100) {
            return "Riesgo de deslizamiento por lluvias intensas";
        } else if (precipitacion < 20) {
            return "Riesgo de sequia por baja precipitacion";
        }
        return "Precipitacion en niveles normales";
    }

    @Override
    public String toString() {
        return "Oriente{" + "precipitacion=" + String.format("%.1f", precipitacion)
                + ", calidadAire=" + calidadAire + '}' + super.toString();
    }
}


public class Problema_4_EjecutorSistemaMonitoreo {

    public static void main(String[] args) {


        Dispositivo[] red = {
            new Costa("D-COSTA-01", "Manta", "2026-07-04"),
            new Sierra("D-SIERRA-01", "Loja", "2026-07-04"),
            new Oriente("D-ORIENTE-01", "Tena", "2026-07-04")
        };

        System.out.println("=== RED DE MONITOREO AMBIENTAL - ECUADOR ===\n");

        for (Dispositivo d : red) {
            d.recolectarDatos();              
            System.out.println(d.generarReporte());
            System.out.println("=========");
        }
    }
}
/**
 * Run:
 * === RED DE MONITOREO AMBIENTAL - ECUADOR ===

Costa{temperatura=19,1, calidadAire=Aire mas o menos contaminado}Dispositivo{nombre=D-COSTA-01, ubicacion=Manta, fecha=2026-07-04}Riesgo: Sin riesgos relevantes
=========
Sierra{temperatura=24,7, humedadSuelo=28,1}Dispositivo{nombre=D-SIERRA-01, ubicacion=Loja, fecha=2026-07-04}Riesgo: Probable sequia, suelo seco
=========
Oriente{precipitacion=59,9, calidadAire=Aire mas o menos contaminado}Dispositivo{nombre=D-ORIENTE-01, ubicacion=Tena, fecha=2026-07-04}Riesgo: Precipitacion en niveles normales
=========
BUILD SUCCESSFUL (total time: 0 seconds)
 */