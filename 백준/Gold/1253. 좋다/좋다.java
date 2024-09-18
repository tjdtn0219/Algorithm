import java.io.*;
import java.util.*;

public class Main {

    int n;
    int[] arr;
    HashMap<Integer, Integer> cntMap;
    HashMap<Integer, Integer> ansMap;
    HashSet<Integer> ansSet;

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
            n = Integer.parseInt(br.readLine());
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            cntMap = new HashMap<>();
            ansMap = new HashMap<>();
            ansSet = new HashSet<>();
            for(int num : arr) {
                int cnt = cntMap.getOrDefault(num, 0);
                cntMap.put(num, cnt + 1);
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        Arrays.sort(arr);

        // int ans = 0;
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                int num = arr[i] + arr[j];
                if(cntMap.containsKey(num)) {
                    if(arr[i]==0 && arr[j]==0) {
                        if(cntMap.get(num) >= 3) {
                            ansSet.add(num);
                        }
                    } else if(arr[i]==0 || arr[j]==0) {
                        if(cntMap.get(num) >= 2) {
                            ansSet.add(num);
                        }
                    } else {
                        ansSet.add(num);
                    }
                    
                }
            }
        }

        int ans = 0;
        for(int num : ansSet) {
            ans += cntMap.get(num);
        }
        System.out.println(ans);
    }

}