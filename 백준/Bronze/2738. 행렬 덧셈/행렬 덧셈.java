import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = bf.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        int[][] m1 = new int[N][M];

        for(int i=0; i<N; i++){
            String[] rows = bf.readLine().split(" ");
            for(int j=0; j<M; j++){
                m1[i][j] = Integer.parseInt(rows[j]);
            }
        }

        int[][] m2 = new int[N][M];

        for(int i=0; i<N; i++){
            String[] rows = bf.readLine().split(" ");
            for(int j=0; j<M; j++){
                m2[i][j] = Integer.parseInt(rows[j]);
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(m1[i][j]+m2[i][j] + " ");
            }
            System.out.println("");
        }

    }
}