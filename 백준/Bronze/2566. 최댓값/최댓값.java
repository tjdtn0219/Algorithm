import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[][] matrix = new int[9][9];

        for(int i=0; i<9; i++){
            String[] strs = bf.readLine().split(" ");
            for(int j=0; j<9; j++){
                int num = Integer.parseInt(strs[j]);
                matrix[i][j] = num;
            }
        }
        int max = -1;
        int max_i = -1;
        int max_j = -1;
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(matrix[i][j] > max){
                    max = matrix[i][j];
                    max_i = i+1;
                    max_j = j+1;
                }
            }
        }

        System.out.println(max);
        System.out.println(max_i + " " + max_j);
    }
}