package Pack;

/**
 * <h1>Casilla.java</h1><hr>
 * <p>Esta clase represanta una casilla</p>
 *
 * @author Adrián Suárez Valdayo
 * @vesrion 23-may-2024
 */
public class Casilla {

    private char simbolo;

    public Casilla() {
        simbolo = '-';
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }


    @Override
    public String toString() {
        return Character.toString(this.simbolo);
    }
}
