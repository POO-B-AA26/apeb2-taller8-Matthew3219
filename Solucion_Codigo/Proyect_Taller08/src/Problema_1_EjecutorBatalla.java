
import java.util.Random;


abstract class Personaje {

    public double vidas;
    public int experiencia;
    public int batallasGanadas;
    public int batallasPerdidas;

    public Personaje(double vidas) {
        this.vidas = vidas;
    }

    public abstract boolean atacar(Personaje personaje);

    public abstract boolean defensa(Personaje personaje);

    @Override
    public String toString() {
        return "Personaje{" + "vidas=" + vidas + ", experiencia=" + experiencia + ", batallasGanadas=" + batallasGanadas + ", batallasPerdidas=" + batallasPerdidas + '}';
    }

}

class Guerrero extends Personaje {

    public int fuerzas;

    public Guerrero(int fuerzas, double vidas) {
        super(vidas);
        this.fuerzas = fuerzas;
    }

    public boolean atacar(Personaje personaje) {
        Random ale = new Random();
        boolean lucha = ale.nextBoolean();
        if(lucha){
            this.batallasGanadas ++;
            this.experiencia++;
            personaje.batallasPerdidas--;
            personaje.vidas--;
        }else{
            personaje.batallasGanadas++;
            this.vidas--;
            this.batallasPerdidas ++;
            this.experiencia--;
        }
        return lucha;
    }

    public boolean defensa(Personaje personaje) {
        return false;
    }

    @Override
    public String toString() {
        return "Guerrero{" + "fuerzas=" + fuerzas + '}' + super.toString();
    }

}

class Mago extends Personaje {

    public String hechizo;

    public Mago(String hechizo, double vidas) {
        super(vidas);
        this.hechizo = hechizo;
    }

    
    public boolean atacar(Personaje personaje) {
        return false;
    }

    public boolean defensa(Personaje personaje) {
        return false;
    }

    @Override
    public String toString() {
        return "Mago{" + "hechizo=" + hechizo + '}' + super.toString();
    }

}

class Arquero extends Personaje {

    public int precision;
    public int cantidadFlechas;

    public Arquero(int precision, int cantidadFlechas, double vidas) {
        super(vidas);
        this.precision = precision;
        this.cantidadFlechas = cantidadFlechas;
    }

    
    public boolean atacar(Personaje personaje) {
        return false;
    }

    public boolean defensa(Personaje personaje) {
        return false;
    }

    @Override
    public String toString() {
        return "Arquero{" + "precision=" + precision + ", cantidadFlechas=" + cantidadFlechas + '}' + super.toString();
    }

}

public class Problema_1_EjecutorBatalla {
    public static void main(String[] args) {
        Personaje guerrero = new Guerrero(19, 3);
        Personaje Mago = new Mago("Fire Boll", 3);
        Personaje arquero = new Arquero(5, 10, 8);
        guerrero.atacar(Mago);
        System.out.println(guerrero.atacar(Mago));
        System.out.println(guerrero);
        System.out.println(Mago);
        
        guerrero.atacar(arquero);
        System.out.println(guerrero.atacar(arquero));
        System.out.println(guerrero);
        System.out.println(arquero);
    }
}
