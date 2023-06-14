import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<Integer> nn = new ArrayList<>();
    static List<Integer> pn = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int ans = 0;
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(bf.readLine());
            if(n==1) ans++;
            else if (n > 0)
                pn.add(n);
            else
                nn.add(n);
        }

        Collections.sort(pn, Collections.reverseOrder());
        Collections.sort(nn);

        int i = 0;
        while (i < pn.size()) {
            if (i + 1 < pn.size())
                ans += pn.get(i++) * pn.get(i++);
            else
                ans += pn.get(i++);
        }

        i = 0;
//        while (i < nn.size()) {
//            if (i + 1 < nn.size())
//                ans += nn.get(i++) * nn.get(i++);
//            else
//                ans += nn.get(i++);
//        }
        for(i=1; i<nn.size(); i+=2) {
            ans += nn.get(i) * nn.get(i-1);
        }
        if(nn.size()%2==1) ans += nn.get(nn.size()-1);


        System.out.println(ans);
    }

}
