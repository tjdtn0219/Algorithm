import java.io.*;
import java.util.*;

public class Main {

    public static List<List<Integer>> tree;
    public static boolean[] isDeleted;
    public static int count = 0;
    public static int del = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        isDeleted = new boolean[n];
        tree = new ArrayList<>();
        int root = 0;

        for(int i=0; i<n; i++) {
            tree.add(new ArrayList<>());
        }

        String[] strings = br.readLine().split(" ");
        for(int child=0; child<n; child++) {
            int parent = Integer.parseInt(strings[child]);
            if(parent==-1) root = child;
            else tree.get(parent).add(child);
        }

        del = Integer.parseInt(br.readLine());

        delete(del);
        dfss(root);

        System.out.println(count);
    }

    public static void delete(int cur) {
        isDeleted[cur] = true;
        for(int child : tree.get(cur)) {
            if(!isDeleted[child]) {
                delete(child);
            }
        }
    }

    public static void dfss(int cur) {
        int cnt = 0;
        if(isDeleted[cur]) return ;

        for(int child : tree.get(cur)) {
            if(!isDeleted[child]) {
                dfss(child);
                cnt++;
            }
        }
        if(!isDeleted[cur] && cnt==0) {
            count++;
        }
    }

//    public static void dfs(int cur) {
//        int cnt = 0;
//        if(isDeleted[cur]) return;
//
//        for(int child : graph.get(cur)) {
//            if(!isDeleted[child]) {
//                dfs(child);
//                cnt++;
//            }
//        }
//        if(!isDeleted[cur] && cnt==0) {
//            count++;
//        }
//    }
//
//    public static void deleteDfs(int cur) {
//        isDeleted[cur] = true;
//        for(int child : graph.get(cur)) {
//            if(!isDeleted[child]) {
//                deleteDfs(child);
//            }
//        }
//    }
}
