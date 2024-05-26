import java.io.*;
import java.util.*;

public class Main {

    int n, m, c;
    List<Delivery> deliveries;

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
            deliveries = new ArrayList<>();
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            c = Integer.parseInt(tmp[1]);
            m = Integer.parseInt(br.readLine());
            for(int i=0; i<m; i++) {
                tmp = br.readLine().split(" ");
                int sender = Integer.parseInt(tmp[0]);
                int receiver = Integer.parseInt(tmp[1]);
                int weight = Integer.parseInt(tmp[2]);
                deliveries.add(new Delivery(sender, receiver, weight));
            }
            Collections.sort(deliveries, (o1, o2) -> {
                if(o1.receiver == o2.receiver) {
                    return o1.sender - o2.sender;
                }
                return o1.receiver - o2.receiver;
            });
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int[] weights = new int[n+1]; //각 마을에서 담을 수 있는 최대 용량
        Arrays.fill(weights, c);

        int answer = 0;

        for(Delivery delivery : deliveries) {

            int maxWeight = Integer.MAX_VALUE;

            //source ~ dest 까지 담을 수 있는 최대 무게 계산
            for(int i=delivery.sender; i<delivery.receiver; i++) {
                maxWeight = Math.min(maxWeight, weights[i]);
            }

            if(maxWeight >= delivery.weight) {
                for(int i=delivery.sender; i<delivery.receiver; i++) {
                    weights[i] -= delivery.weight;
                }
                answer += delivery.weight;
            } else {
                for(int i=delivery.sender; i<delivery.receiver; i++) {
                    weights[i] -= maxWeight;
                }
                answer += maxWeight;
            }
        }

        System.out.println(answer);
    }
}

class Delivery {
    int sender;
    int receiver;
    int weight;

    public Delivery(int sender, int receiver, int weight) {
        this.sender = sender;
        this.receiver = receiver;
        this.weight = weight;
    }
}
