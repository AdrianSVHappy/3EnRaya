package Pack;

/**
 * <h1>PlayerCPU.java</h1><hr>
 * <p>Esta clase represanta un contrincante para jugar en solitario</p>
 *
 * @author Adrián Suárez Valdayo
 * @vesrion 02-jun-2024
 */
public class PlayerCPU {

    private char simbolo;
    private int poscicion;

    public PlayerCPU() {
        this.simbolo = 'o';
    }

    public int colocarFicha(int dificultad, Tablero tablero, int antPos){
        int pos = 0;

        switch (dificultad) {
            case 1:
                pos = colocarFacil(tablero);
            break;
            case 2:
                pos = colocarMedio(tablero, antPos);
                break;
        }



        return pos;
    }


    private int colocarFacil(Tablero tablero){

        int pos;

        do{

            pos = (int)(Math.random()*9)+1;

        }while (!tablero.colocarFicha(pos, this.simbolo));


        return pos;
    }

    private int colocarMedio (Tablero tablero, int antPos){
        int pos;

        do {
            do {
                if ((int) (Math.random() * 2) == 0) {
                    pos = antPos - 1;
                } else {
                    pos = antPos + 1;
                }
            }while (pos < 1 || pos > 9);
        }while (!tablero.colocarFicha(pos, this.simbolo));

        return pos;
    }

}
