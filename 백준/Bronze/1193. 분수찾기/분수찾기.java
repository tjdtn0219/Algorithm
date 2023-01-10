import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        if(N == 1){
            System.out.println("1/1");
            return ;
        }

        int i=2;
        int j=2;

        while(true){
            if(i <= N && N < i+j){
                N = N-i+1;
                if(j%2 ==1){
                    System.out.println((j+1-N) + "/" + N);
                } else {
                    System.out.println(N + "/" + (j+1-N));
                }
                break;
            }
            i += j++;

        }
    }
}