import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        if(N == 1) {
            System.out.println(1);
            return;
        }
        int left = 2;
        int i = 1;
        while(true){
            if(left <= N && N < left+6*i){
                System.out.println(i+1);
                break;
            }
            left = left + 6*i;
            i++;
        }
    }
}