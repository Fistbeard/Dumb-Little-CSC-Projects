/*
 * Zachary Ninteau
 * CSC225
 * Problem Set 7
 * Problem 1 - Sum Rows + Columns
 * I don't understand why math + CS do matrixes down then across 
 * rather than across and down, like a book.
 * 
 * As an aside - this problem made me feel like I was taking crazy pills by the time I was done
 * Also it's probably full of errors
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class SumRowCol {
    
    public static void main(String args[]){
        int matrix[][] = initMatrix();
        printData(matrix); //Uncomment to print array
        
        int longestLength = 0, sum =0;
        
        longestLength = longestLength(matrix);
        int[] sums = new int[longestLength];
        sums = sumCol(matrix, longestLength);
        
        for (int i = 0; i < matrix.length; i++)
        {
            sum = sumRow(matrix, i);
            System.out.println("Sum of row #" + i + ":" + sum);
        }//end print row sums
        
        for (int i = 0; i < longestLength; i++)
        {
            System.out.println("Sum of col #" + i + ":" + sums[i]);
        }//end col print

    }// end main
    
        public static int longestRow(int mat[][])
    {
        int longestLength = 0, longestRow = 0;
        
        //iterates by row
        for (int i = 0; i < mat.length; i++)
        {
            //if length of each row is longer than last longest length, update longest row and longest length.
            if (mat[i].length > longestLength)
            {
                longestLength = mat[i].length;
                longestRow = i;
            }
        }//end length checker
        
        return longestRow;
    }
    
    public static int longestLength(int mat[][])
    {
        int longestLength = 0, longestRow = 0;
        
        //iterates by row
        for (int i = 0; i < mat.length; i++)
        {
            //if length of each row is longer than last longest length, update longest row and longest length.
            if (mat[i].length > longestLength)
            {
                longestLength = mat[i].length;
            }
        }//end length checker
        
        return longestLength;
    }
    
    //I know you wanted c to indicate a column and sum by columns but... ehhhhh.
    //Full disclosure, I had to do A LOT of googling to find good examples of how to solve this problem 
    public static int[] sumCol(int mat[][], int c)
    {
        int longestLength = 0;
        
        //just for clarity
        longestLength = c;
        
        //this array will hold the sums for each column - not my original idea -> 
        //I realize this is really the meat of the "algorithm" portion of this assignment but... there's only so long I can stare at the screen before i look elsewhere for help
        int[] sums = new int[longestLength];
        
        //from http://stackoverflow.com/questions/24518180/calculating-sum-of-columns-in-uneven-2d-array 
        //goes through each row and adds each term of i to sums[i], accumulating each column sum row by row.
        for (int r = 0; r < mat.length; r++)
        {
            for (int i = 0; i < mat[r].length; i++)
            {
                    //
                    sums[i] += mat[r][i];
            }//end summation loop
        }
        //sends this back to the other sums[] in main.
        return sums;
    }//end sumCol
    
    public static int sumRow(int mat[][], int r)
    {
        int sum = 0;
        
        for (int i = 0; i < mat[r].length; i++)
        {
            sum += mat[r][i];
        }
        
        return sum;
    }//end sumRow
    
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
