package Pack;


/**
 * <h1>Tablero.java</h1><hr>
 * <p>Esta clase represanta fin representa un tablero</p>
 *
 * @author Adrián Suárez Valdayo
 * @vesrion 23-may-2024
 */
public class Tablero {

    private static final int TAM = 3;
    Casilla[][] casillas;

    public Tablero() {
        this.casillas = new Casilla[3][3];

        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                casillas[i][j] = new Casilla();
            }
        }

    }

    @Override
    public String toString() {

        return  "|" + this.casillas[2][0].toString() + "|" + this.casillas[2][1].toString() + "|" + this.casillas[2][2].toString() + "|\n" +
                "|" + this.casillas[1][0].toString() + "|" + this.casillas[1][1].toString() + "|" + this.casillas[1][2].toString() + "|\n" +
                "|" + this.casillas[0][0].toString() + "|" + this.casillas[0][1].toString() + "|" + this.casillas[0][2].toString() + "|\n" +
                "\n";
    }

    /**
     * Metodo para colocar una ficha en el tablero
     * @param num Posición en la que se va a colocar
     * @param simbolo El simbolo de la ficha que se va a colocar
     * @return boolean. Segun si se ha podido colocar la ficha (true) o no (false)
     */
    public boolean colocarFicha(int num, char simbolo) {
        boolean ret;
        int[] coordenadas = new int[2];
        int fila, columna;

        coordenadas = seleccionarCasilla(num);

        fila = coordenadas[0];
        columna = coordenadas[1];

        if (this.casillas[fila][columna].getSimbolo() == '-') {
            this.casillas[fila][columna].setSimbolo(simbolo);
            ret = true;
        }else{
            System.out.println("Casilla ocupada");
            ret = false;
        }

        return ret;
    }

    /**
     * Metodo auxiliar, traduce la posición a coordenadas para tratar la matriz
     * @param num el numero que se va a traducir
     * @return int[2]. El primer valor representa la fila y el segundo la columna
     */
    private int[] seleccionarCasilla(int num) {

        int fila = -1, columna = -1, cont = 0;
        int[] ret = new int[2];

        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                cont ++;
                if(cont == num){
                    fila = i;
                    columna = j;
                    break;
                }

            }
            if(cont == num)
                break;


        }


        //System.out.println(fila + " " + columna);
        ret[0] = fila;
        ret[1] = columna;

        return ret;
    }

    /**
     * Metodo auxiliar, comprueba si se ha ganado de forma diagonal
     * @param simbolo El simbolo que se busca
     * @return boolean. Si se ha hecho 3 en raya da true, de lo contrario false
     */
    private boolean comprobarCentro(char simbolo){

        int fila = 1, columna = 1;
        int iAux, jAux;
        boolean win = false;

        if(simbolo == this.casillas[fila][columna].getSimbolo()) {
            for (int i = 0; i < TAM; i++) {
                for (int j = 0; j < TAM; j++) {
                    if ((i != fila) && (j != columna)) {

                        if (simbolo == this.casillas[i][j].getSimbolo()) {
                            iAux = (TAM - 1) - i;
                            jAux = (TAM - 1) - j;

                            if (simbolo == this.casillas[iAux][jAux].getSimbolo()) {
                                win = true;
                                //System.out.println("[" + i + "][" + j + "] " + simbolo + "[" + iAux + "][" + jAux + "]");
                                break;
                            }

                        }

                    }
                }
            }
        }

        return win;
    }

    /**
     * Metodo auxiliar, comprueba si se ha ganado de forma recta
     * @param fila Fila que se comprueba
     * @param columna Columna que se comprueba
     * @param simbolo Simbolo que se busca
     * @return  boolean. Si se ha hecho 3 en raya da true, de lo contrario false
     */
    private boolean comprobarRecto(int fila, int columna, char simbolo){

        boolean win = false;
        int contEnFila = 0, contEnColumna = 0;


        //System.out.println(simbolo);
        for (int i = 0; i < TAM; i++) {
            if(simbolo == this.casillas[i][columna].getSimbolo()){
                contEnFila++;
                if (contEnFila == TAM)
                    break;
            }
            if(simbolo == this.casillas[fila][i].getSimbolo()){
                contEnColumna++;
                if(contEnColumna == TAM)
                    break;
            }

        }

        if((contEnFila == TAM) || (contEnColumna == TAM)){
            //System.out.println(contEnFila + " " + contEnColumna);
            win = true;
        }

        return win;
    }

    /**
     * Metodo para comprobar si se ha hecho el 3 en raya
     * @param num La posición que se comprueba
     * @return  boolean. Si se ha hecho 3 en raya da true, de lo contrario false
     */
    public boolean comprobar(int num){

        int[] coordenadas = new int[2];
        int fila, columna;
        boolean win = false;
        char simbolo;

        coordenadas = seleccionarCasilla(num);

        fila = coordenadas[0];
        columna = coordenadas[1];

        simbolo= this.casillas[fila][columna].getSimbolo();

        if(simbolo != '-') {

            win = comprobarRecto(fila, columna, simbolo);

            if (!win) {
                System.out.println("BUSCAR CENTRO");
                win = comprobarCentro(simbolo);
            }
        }else
            System.out.println("Casilla vacía");


        return win;
    }

    public void mostrarInfo(){

        System.out.println("(i) Para colocar las fichas, debeis usar como referencia el teclado numerico.");

        System.out.println( "|7|8|9|\n" +
                            "|4|5|6|\n" +
                            "|1|2|3|\n");

        System.out.println("Jugador 1: \'x\'\nJugador 2: \'o\'\n\n\n");

    }


}
