import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        List<Integer> arr = new ArrayList<>();

        int N = in.nextInt();
        int X = in.nextInt();

        for(int i=0; i<N; i++){
            int num = in.nextInt();
            arr.add(num);
        }

        for(int num : arr){
            if(num < X){
                System.out.print(num + " ");
            }
        }
    }
}