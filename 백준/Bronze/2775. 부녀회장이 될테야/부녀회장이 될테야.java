import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int[][] apt = new int[15][15];

        init_apt(apt);

        for(int i=1; i<apt.length; i++){
            for(int j=0; j<apt[i].length; j++){
                int sum = 0;
                for(int p=0; p<=j; p++){
                    sum += apt[i-1][p];
                }
                apt[i][j] = sum;
            }
        }

//        for(int i=1; i<apt.length; i++){
//            for(int j=0; j<apt[i].length; j++){
//                System.out.print(apt[i][j] + " ");
//            }
//            System.out.println("");
//        }

        int T = in.nextInt();

        for(int i=0; i<T; i++){
            int k = in.nextInt();
            int n = in.nextInt();
            System.out.println(apt[k][n]);
        }

    }

    public static void init_apt(int[][] arr){
        for(int i=0; i<arr[0].length; i++){
            arr[0][i] = i;
        }
    }
}
