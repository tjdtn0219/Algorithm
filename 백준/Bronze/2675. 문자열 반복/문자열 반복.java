import java.util.*;

public class Main{
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int T = in.nextInt();

        for(int i=0; i<T; i++){
            int N = in.nextInt();
            String S = in.nextLine();
            printString(S, N);
        }
    }

    public static void printString(String S, int N){
        for(int i=1; i<S.length(); i++){
            for(int j=0; j<N; j++){
                System.out.print(S.charAt(i));
            }
        }
        System.out.println("");
    }
}