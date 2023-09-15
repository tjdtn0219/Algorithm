import java.util.*;
import java.io.*;

public class Main {
    static List<Long> list = new ArrayList<>();
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        {0,1,2,3,4,5,6,7,8,9}의 감소하는 수의 경우의 수는 총 부분집합의 개수와 같다.
//        예를 들어 {1,5,9,8}의 부분집합에서 감사하는 수는 1개만 존재하기 때문이다.
        N = Integer.parseInt(br.readLine());
        if(N <= 10) {
            System.out.print(N);
            return;
        } else if (N >= 1023) {
            System.out.print(-1);
            return;
        }

        for(int i = 0; i < 10; i++) {
            solution(i);
        }

        Collections.sort(list);
        System.out.print(list.get(N));
    }

    public static void solution(long num) {
        list.add(num);
        long modValue = num % 10;
        if (modValue == 0) {
            return;
        }

        for (long i = modValue - 1; i >= 0; i--) {
            long newValue = num * 10 + i;
            solution(newValue);
        }
    }
}