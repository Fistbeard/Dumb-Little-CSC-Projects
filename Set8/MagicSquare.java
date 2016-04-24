/*
 * Zachary Ninteau
 * CSC225
 * Problem Set 7 Question 2
 * Magic Square
 * I had quite a bit of difficult with question 1 and had to move on for now
 * if you're wondering why there are no comments in this... it's 5 AM.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class MagicSquare {
    
    public static void main(String args[]){
        int matrix[][] = initMatrix();
        //printData(matrix); //Uncomment to print array
        
        isMagic(matrix);
    }

    public static boolean isMagic(int mat[][])
    {
        int sum = 0, sum2 = 0;
        isSquare(mat);
        
        
        for (int i = 0; i < mat.length; i++)
        {
            for (int j = 0; j < mat[i].length; j++)
            {
                sum = sumRow(mat, i);
                sum2 = sumCol(mat, j);
                if (sum != sum2)
                {
                    return false;
                }
            }
        }
        System.out.printf("This is a magic square.\nThe sum of all columns and rows is %d.", sum);
        return true;
    }//end isMagic
    
    //makes sure number of rows is equal to size of each column. Will catch protrustions on any side.
    public static boolean isSquare(int mat[][])
    {
        for (int i = 0; i < mat.length; i++)
        {
            if (mat.length != mat[i].length)
            {
                System.out.println("Is not even a square, let alone magic!");
                return false;
            }
        }
        return true;
    }//end isSquare
    
    //takes matrix, row number, returns sum of all numbers in row
    public static int sumRow(int mat[][], int r)
    {
        int sum = 0;
        
        for (int i = 0; i < mat.length; i++)
        {
            sum += mat[r][i];
        }
        return sum;
    }// end row sum
    
    //takes matrix, column number, returns sum of all numbers in column
    public static int sumCol(int mat[][], int c)
    {
        int sum = 0;
        
        for (int i = 0; i < mat[c].length; i++)
        {
            sum += mat[i][c];
        }
        return sum;
    }//end col sum
    
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
