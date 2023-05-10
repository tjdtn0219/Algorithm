import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final int MAX = 30;
//    public static List<Integer> tree[] = new ArrayList[MAX];
    public static char parent[] = new char[MAX];
    public static char left[] = new char[MAX];
    public static char right[] = new char[MAX];

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        for(int i=0; i<MAX; i++) {
            parent[i] = '.';
        }
        for (int i = 0; i < n; i++) {
            String[] strings = bf.readLine().split(" ");
            char rt = strings[0].charAt(0);
            char lc = strings[1].charAt(0);
            char rc = strings[2].charAt(0);

            if (lc != '.') parent[lc - 'A'] = rt;
            if (rc != '.') parent[rc - 'A'] = rt;
            left[rt - 'A'] = lc;
            right[rt - 'A'] = rc;
        }

        char root = '0';
        for(int i=0; i<MAX; i++) {
            if(parent[i] == '.') {
                root = (char)('A' + i);
                break;
            }
        }
        preorder(root);
        System.out.println("");
        inorder(root);
        System.out.println("");
        postorder(root);
        System.out.println("");
    }
    public static void preorder(char cur) {
        System.out.print(cur);
        if(left[cur-'A'] != '.') preorder(left[cur-'A']);
        if(right[cur-'A'] != '.') preorder(right[cur-'A']);
    }
    public static void inorder(char cur) {
        if(left[cur-'A'] != '.') inorder(left[cur-'A']);
        System.out.print(cur);
        if(right[cur-'A'] != '.') inorder(right[cur-'A']);
    }
    public static void postorder(char cur) {
        if(left[cur-'A'] != '.') postorder(left[cur-'A']);
        if(right[cur-'A'] != '.') postorder(right[cur-'A']);
        System.out.print(cur);

    }
}