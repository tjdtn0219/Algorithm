import java.io.*;
import java.util.HashMap;

public class Main {

    int n, d, k, c;
    int[] sushi;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       
            String[] n_d_k_c = br.readLine().split(" ");
            n = Integer.parseInt(n_d_k_c[0]);
            d = Integer.parseInt(n_d_k_c[1]);
            k = Integer.parseInt(n_d_k_c[2]);
            c = Integer.parseInt(n_d_k_c[3]);
            sushi = new int[2*n];
            for(int i=0; i<n; i++) {
                int num = Integer.parseInt(br.readLine());
                sushi[i] = num;
                sushi[n+i] = num;
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    private void printSushi() {
        for(int i=0; i<sushi.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for(int num : sushi) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public void solve() {
        // printSushi();
        twoPointer();
    }

    public void twoPointer() {
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        for(int i=0; i<k; i++) {
            int sushiNum = sushi[i];
            int cnt = cntMap.getOrDefault(sushiNum, 0);
            cntMap.put(sushiNum, cnt+1);
        }

        int st = 0;
        int en = k-1;

        // for(int key : cntMap.keySet()) {
        //     System.out.println("key : " + key + ", val : " + cntMap.get(key));
        // }

        int maxCnt = 0;
        for(int i=0; i<2*n-k; i++) {
            // System.out.println("st : " + st + ", en : " + en);
            int kindCnt = cntMap.keySet().size();
            if(!cntMap.keySet().contains(c)) kindCnt++;
            maxCnt = Math.max(maxCnt, kindCnt);
            
            int sushiNum = sushi[st++];
            if(cntMap.get(sushiNum) == 1) {
                cntMap.remove(sushiNum);
            } else {
                cntMap.put(sushiNum, cntMap.get(sushiNum) - 1);
            }

            sushiNum = sushi[++en];
            cntMap.put(sushiNum, cntMap.getOrDefault(sushiNum, 0) + 1);
        }

        System.out.println(maxCnt);

    }

}