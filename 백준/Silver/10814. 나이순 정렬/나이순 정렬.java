import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        List<Human> list = new ArrayList<>();

        for(int i=0; i<N; i++){
            String[] strs = bf.readLine().strip().split(" ");
            Human human = new Human(strs[1], Integer.parseInt(strs[0]), i);
            list.add(human);
        }

        Collections.sort(list, (final Human h1, final Human h2)->{
            if(h1.age == h2.age) return h1.order - h2.order;
            return h1.age - h2.age;
        });

        for(Human human : list){
            System.out.println(human.age + " " + human.name);
        }
    }
}

class Human{
    String name;
    Integer age;
    Integer order;

    public Human(String name, int age, int order){
        this.name = name;
        this.age = age;
        this.order = order;
    }
}