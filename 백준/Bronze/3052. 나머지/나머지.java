
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        List<Integer> arr = new ArrayList<>();

        for(int i=0; i<10; i++){
            int num = in.nextInt();
            int rest = num % 42;
            if(!arr.contains(rest)){
                arr.add(rest);
            }
        }

        System.out.println(arr.size());

    }
}


