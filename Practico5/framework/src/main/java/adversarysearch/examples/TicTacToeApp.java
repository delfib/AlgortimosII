package adversarysearch.examples;

import java.util.Scanner;


import adversarysearch.engines.MinMaxABengine;
import adversarysearch.engines.MinMaxEngineAdversary;

public class TicTacToeApp {
    public static void main(String[] args) {
        TicTacToeProblem sp = new TicTacToeProblem(false);  // empieza jugando el adversario

        MinMaxABengine<TicTacToeProblem, TicTacToeState> engineMinMaxAB = new MinMaxABengine<TicTacToeProblem,TicTacToeState>(sp, 9);
        MinMaxEngineAdversary<TicTacToeProblem, TicTacToeState> engineMinMax = new MinMaxEngineAdversary<TicTacToeProblem,TicTacToeState>(sp, 9);

        TicTacToeState currentState = sp.initialState();

        try (Scanner scanner = new Scanner(System.in)) {
            while(!currentState.end()){ // mientras que no se termine el juego
                if (!currentState.isMax()){ // juega el adversario
                    System.out.println();
                    System.out.println(currentState.toString());
                    System.out.println("Your turn!");
                    System.out.println();
                    // La fila y columna ingresadas deben estar separadas por un espacio, ej: 1 0
                    System.out.print("Enter the row and column (from 0-2): ");
                    int row = scanner.nextInt();
                    int column = scanner.nextInt();

                    if (currentState.getBoard()[row][column] != 0) {
                        System.out.println("Invalid input, please try again :)");
                        continue;
                    }

                    currentState.getBoard()[row][column] = 2;
                    currentState.setPlayer(true);   // es nuestro turno ahora
                }
                else { // jugamos nosotros
                    TicTacToeState bestMove = engineMinMaxAB.computeSuccessor(currentState);
                    currentState = bestMove;
                }
            }
        }
        
        // Game over
        System.out.println(currentState.toString());
        if (currentState.isSuccess()){
            System.out.println("You lost :( "); 
        }
        else if (currentState.end() && currentState.adversaryIsSuccess()) {
            System.out.println("You won :)");
        }
        else {
            System.out.println("It's a tie!");
        }
    }
}
