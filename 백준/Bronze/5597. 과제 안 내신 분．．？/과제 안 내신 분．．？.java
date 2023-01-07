import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        List<Integer> arr = new ArrayList<>();

        for(int i=0; i<28; i++){
            int num = in.nextInt();
            arr.add(num);
        }

        for(int i=1; i<=30; i++){
            if(!arr.contains(i)){
                System.out.println(i);
            }
        }

    }
}


