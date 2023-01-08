
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        List<Integer> arrList = new ArrayList<>();

        int N = in.nextInt();
        int cnt = 0;
        for(int i=1; i<=N; i++){
            if(is_Han(i)){
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    public static boolean is_Han(int num){
        if(num<100){
            return true;
        }

        List<Integer> list = new ArrayList<>();

        while(num>0){
            list.add(num%10);
            num /= 10;
        }

        int gap=0;

        for(int i=0; i<list.size()-1; i++){
            if(i == 0){
                gap = list.get(i) - list.get(i+1);
                continue;
            }
            if (gap != (list.get(i) - list.get(i+1))){
                return false;
            }
        }
        return true;
    }
}

