import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int MAX_M = 100;
        int MAX_N = 100;
        boolean[][] sketchBoard = new boolean[MAX_N][MAX_M];
        init_matrix(sketchBoard, MAX_N, MAX_M);

        int N = Integer.parseInt(bf.readLine());

        for(int i=0; i<N; i++){
            String[] inputs = bf.readLine().split(" ");
            int x = Integer.parseInt(inputs[0]);
            int y = Integer.parseInt(inputs[1]);
            paintSquare(sketchBoard, x, y);
        }
        getArea(sketchBoard, MAX_N, MAX_M);

    }

    public static void init_matrix(boolean[][] matrix, int N, int M){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                matrix[i][j] = false;
            }
        }
    }

    public static void paintSquare(boolean[][] sketchBoard, int x, int y){
        int size = 10;
        for(int i=x; i<x+size; i++){
            for(int j=y; j<y+size; j++){
                sketchBoard[i][j] = true;
            }
        }
    }

    public static void getArea(boolean[][] sketchBoard, int N, int M){
        int area = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(sketchBoard[i][j]){
                    area++;
                }
            }
        }
        System.out.println(area);
    }
}