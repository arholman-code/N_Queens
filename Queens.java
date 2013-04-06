/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package queens;

import java.util.Random;
import java.util.Vector;
/**
 *
 * @author Sarah-Andrew
 */
public class Queens {
    //Maximum board size = 30, steps = 5000
    public static int N = 30;
    public static int MAX_STEPS = 5000;
    //An int array that stores valid queen placement
    public static int[] queens = new int[N];
    
    //Store conflicts, problems, and randomizes placement
    public static Random random = new Random();
    //holds row conflicts
    public static int[] rCon = new int[N];
    //holds conflicts from diagonals
    public static int[] c1 = new int[2 * N - 1];
    public static int[] c2 = new int[2 * N - 1];
    //holds all problems.  Yes, all of them.
    public static Vector<Integer> problems = new Vector<Integer>();

    //Takes an int from the UI and sets N equal to it.
    //Initializes the NxN board with N queens and steps through.
    public static void changeN(int x){
        N = x;
        iBoard();
        int steps = 0;
        //while there are conflicts, ill placed queens, continue to search
        while (dangerZone()) {
            //If the steps taken is greater than allowed, call a new 
            //Board to finish.
            if (++steps > MAX_STEPS) {
                iBoard();
                steps = 0;
            }
        }
    }
    
    //Gets a new random variable and sets queen placement 0...(N-1).
    public static void iBoard() {
        random = new Random();
        for (int i = 0; i < N; i++) {
            queens[i] = i;
        }
    }

    //gets diagonal conflicts for a position
    public static int getD1Pos(int col, int row) {
        return col + row;
    }

    public static int getD2Pos(int col, int row) {
        return N - 1 + col - row;
    }

    public static boolean dangerZone() {
        //System.out.println("HIGHWAY TO THE DANGER ZONE...!");
        dogFight();
        //if there are no problems, return false;
        if (problems.isEmpty()) {
            return false;
        }
        //otherwise, choose a random problem
        int r = random.nextInt(problems.size());
        //columns with conflicts
        int conCol = problems.get(r);
        //current row
        int cRow = queens[conCol];
        //this will hold the best row, eventually.  for now, it's the current.
        int bestRow = cRow;
        //The minimal distance is equal to the movement capacity of a knight
        //That is shifted u/d/l/r by 2 and then l/r by 1.
        int minConfl = getProblems(conCol, queens[conCol]) - 3;
        //Counts total conflicts
        int tConCt;

        //for each conflict, gets the specific problem and finds a new row/home
        for (int i = 0; i < N; i++) {
            tConCt = getProblems(conCol, i);
            if (i != cRow && tConCt <= minConfl) {
                minConfl = tConCt;
                bestRow = i;
            }
        }
        //sets the queen into the best row, as a column is predefined.
        queens[conCol] = bestRow;
        return true;
    }

    //this is called to fix the problems.  the board is initialized diagonally
    // to start.
    public static void dogFight() {
        problems = new Vector<Integer>();
        rCon = new int[N];
        //diagonal conflicts changed
        c1 = new int[2 * N - 1];
        c2 = new int[2 * N - 1];

        //for each queen in the array, it fills an array for conflicts to be
        //dealt with
        for (int i = 0; i < N; i++) {
            int r = queens[i];
            rCon[r]++;
            c1[getD1Pos(i, r)]++;
            c2[getD2Pos(i, r)]++;
        }

        //builds the vector of potential positions to be dealt with
        for (int i = 0; i < N; i++) {
            int problemsCount = getProblems(i, queens[i]) - 3;
            if (problemsCount > 0) {
                problems.add(i);
            }
        }
    }

    //Takes a column and row and finds conflicts from placement
    public static int getProblems(int col, int row) {
        return rCon[row] + c1[getD1Pos(col, row)] + 
                c2[getD2Pos(col, row)];
    }

    //calls a new UI
    public static void main(String[] args) {
        Board b = new Board(N);
    }
}
