import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        int num = -1;
        for(int i=0; i<1667; i++){
            for(int j=0; j<1001; j++){
                if((5*i+3*j) == N){
                    num = i+j;
                }
            }
        }
        System.out.println(num);
    }
}
