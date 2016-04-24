/*
 * Zachary Ninteau
 * CSC225
 * Problem Set 7, Question 3
 * Latin Square
 * So... I read the directions and wrote this the first time at 6 AM... you do NOT want to see what it looked like before I realized you weren't expecting this all to be in one method.
 * Also, I know that the isLatin method doesn't need to be a boolean, but I started it as one and figured it didn't hurt/made it importable.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class LatinSquare {
    
    public static void main(String args[]){
        int matrix[][] = initMatrix();
        //printData(matrix); //Uncomment to print array
        
        isLatin(matrix);
    }//end main
    
    //takes a matrix, returns false if it violates rules for latin squares, true otherwise.
    public static boolean isLatin(int mat[][])
    {
        boolean[] present = new boolean[mat.length];
        int value = 0;
        
        //sets all flags in present to false;
        presentSet(present);
        
        //sends array to be checked for R = C
        if (!isSquare(mat))
        {
            return false;
        }
        
        //Loop through each row and column
        for (int j = 0; j < mat.length; j++)
        {
            for (int h = 0; h < mat[j].length; h++)
            {
                //Sets value to 0 indexed position.
                value = mat[j][h] - 1;
                //sends to check for values > size of array - these are inheirently invalid
                if (!isValueWithinN(mat, value, j, h))
                {
                    return false;
                }
                //sets 0 index for each value to true if it appears eg finding a 1 sets present[0] to true, finding a 2 sets present[1] to true, etc.
                present[value] = true;
            }
            //read through present, check that they're all true.
            for (int k = 0; k < present.length; k++)
            {
                //if a value ISNT present, then we've got ourselves an invalid latin square, and need to find the duplicate.
                if (!present[k])
                {
                    System.out.printf("Not a latin square!\n");
                    duplicateFinder(mat, j);
                    return false;
                }
            }
            //reset flags for next row examination
            presentSet(present);
        }
        //If it reachs this point, it hasn't violated any rules. Print confirmation and return true.
        System.out.println("That's a latin square all right!");
        return true;
    }//end isLatin
    
    //scans through the offending row to find the duplicate term. Compares each term to every other term until it finds a match. Not very efficient but meh. I haven't taken algorithms yet.
    public static void duplicateFinder(int mat[][], int row)
    {
        for (int x = 0; x < mat[row].length; x++)
        {
            for (int y = x+1; y < mat[row].length; y++)
            {
                if (mat[row][x] == mat[row][y])
                {
                    System.out.printf("Duplicate %d found at row %d column %d!\n", mat[row][y], row, y);
                    return;
                }
            }
        }
    }
    
    //takes value, row, column, matrix, and value and returns true if value is never larger than the row length. (a 3x3 arrays highest value can be 3, etc)
    public static boolean isValueWithinN(int mat[][], int val, int row, int col)
    {
        //compares value to size of rows - it can't be bigger than the size.
        if (val > mat[row].length-1)
        {
            System.out.printf("Value greater than array size found at [%d][%d]!", row, col);
            return false;
        }
        return true;
    }//end value within N
    
    //makes sure number of rows is equal to size of each column. Will catch protrustions on any side.
    public static boolean isSquare(int mat[][])
    {
        for (int i = 0; i < mat.length; i++)
        {
            if (mat.length != mat[i].length)
            {
                System.out.println("Is not even a square, let alone Latin!");
                return false;
            }
        }
        return true;
    }//end isSquare
    
    //makes present[] = {false, false, ..., false} takes the boolean 1D array pointer and returns nothing.
    public static void presentSet(boolean p[])
    {
        for (int i = 0; i < p.length; i++)
        {
            p[i] = false;
        }
    }//end bool P
    
    public static int[][] initMatrix(){
        int matrix[][];
        Scanner filein = null;
        try {
            filein = new Scanner(new File("matrix.txt"));
            int numRows = Integer.parseInt(filein.nextLine());
            matrix = new int[numRows][];
            parseData(matrix, filein);
            filein.close();
            return matrix;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            if(filein != null)
                filein.close();
            return null;
        }
    }
    
    public static void parseData(int matrix[][], Scanner in){
        for(int r = 0; r < matrix.length; r++){
            String splitLine[] = in.nextLine().split(" ");
            matrix[r] = new int[splitLine.length];
            for(int c = 0; c < matrix[r].length; c++){
                matrix[r][c] = Integer.parseInt(splitLine[c]);
            }
        }
    }
    
    public static void printData(int matrix[][]){
        for(int r = 0; r < matrix.length; r++){
            for(int c = 0; c < matrix[r].length; c++){
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

}
