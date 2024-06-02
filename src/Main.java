import Pack.PlayerCPU;
import Pack.Tablero;

import java.util.Scanner;

/**
 * <h1>Main.java</h1><hr>
 * <p>Esta clase represanta el main del programa</p>
 *
 * @author Adrián Suárez Valdayo
 * @vesrion 23-may-2024
 */
public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Tablero tablero = new Tablero();
        PlayerCPU cpu = new PlayerCPU();
        int turno = 1;
        int pos = 0;
        int difi = 1;
        boolean win = false;
        char simbolo;

        tablero.mostrarInfo();

        //Partida
        do {

            if(turno % 2 == 0) {
                simbolo = 'o';
                pos = cpu.colocarFicha(difi, tablero, pos);
            }
            else {
                simbolo = 'x';
                pos = interaccion(simbolo, tablero);
            }



            if(turno >= 5){

                win = tablero.comprobar(pos);
            }

            System.out.println("\nTurno: " + turno + "\n" + tablero);

            turno++;
        }while(!win && turno < 10);


        //Fin de la partida
        System.out.println("Fin de la partida:\n" + tablero);

        if (win) {
            System.out.println("Ha ganado el Jugador [" + simbolo + "]!!");
        }else
            System.out.println("Empate");


        System.out.println("Pulse \"enter\" para cerrar");
        sc.nextLine();//FFLUSH
        sc.nextLine();

    }


    /**
     * Este metodo se encarga de recoger la posición en la que queremos colocar la ficha, colocandola en el proceso y evitar posibles errores de usuario
     * @param simbolo El simbolo del jugador
     * @param tablero El tablero donde se juega
     * @return int. La posición donde se ha colocado la ultima ficha
     */
    static int interaccion (char simbolo, Tablero tablero){

        int pos;
        boolean rep;

        do {
            rep = false;
            System.out.print("Jugador [" + simbolo + "]: ");
            try {
                pos = sc.nextInt();
            }catch(Exception e) {
                System.out.println("NO se permiten letras, si tiene dudas revisa las reglas.");
                rep = true;
                pos = 0;
                sc.nextLine();
                continue;
            }


            if (pos < 1 || pos > 9) {
                System.out.println("Posición incorrecta");
                rep = true;
            }else{
                rep = !tablero.colocarFicha(pos, simbolo);
            }

        }while (rep);


        return pos;

    }
}