import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        List<Integer> arr = new ArrayList<>();
        int N = in.nextInt();

        for(int i=0; i<N; i++){
            int num = in.nextInt();
            arr.add(num);
        }

        int max = arr.get(0);
        int min = arr.get(0);

        for(int num : arr){
            if(max < num){
                max = num;
            }
            if(min > num){
                min = num;
            }
        }
        System.out.println(min + " " + max);
    }
}

