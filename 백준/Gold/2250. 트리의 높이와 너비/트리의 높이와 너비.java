import java.io.*;
import java.util.*;

public class Main {

    int n;
    HashMap<Integer, TreeNode> nodeMap;
    int[][] board;
    int[] parent;
    int maxDep;
    int idx;

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
            nodeMap = new HashMap<>();
            maxDep = -1;
            idx = 0;
            parent = new int[n+1];
            Arrays.fill(parent, -1);
            for(int i=0; i<n; i++) {
                String[] tmp = br.readLine().split(" ");
                int root = Integer.parseInt(tmp[0]);
                int left = Integer.parseInt(tmp[1]);
                int right = Integer.parseInt(tmp[2]);
                TreeNode rootNode = nodeMap.getOrDefault(root, new TreeNode(root));
                if(left != -1) {
                    TreeNode leftNode = nodeMap.getOrDefault(left, new TreeNode(left));
                    rootNode.left = leftNode;
                    nodeMap.put(left, leftNode);
                    parent[left] = root;
                }
                if(right != -1){
                    TreeNode rightNode = nodeMap.getOrDefault(right, new TreeNode(right));
                    rootNode.right = rightNode;
                    nodeMap.put(right, rightNode);
                    parent[right] = root;
                }
                nodeMap.put(root, rootNode);
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        for(int i=1; i<=n; i++) {
            if(parent[i] == -1) {
                findMaxDep(nodeMap.get(i), 0);
                this.board = new int[maxDep+1][n];
                inOrderTraversal(nodeMap.get(i), 0);
            }
        }
        int[] ans = getMaxWidth();
        System.out.println((ans[0]+1) + " " + ans[1]);
    }

    public int[] getMaxWidth() {
        int maxWidth = 0;
        int maxLevel = maxDep;
        for(int i=0; i<=maxDep; i++) {
            int left = -1;
            int right = -1;
            for(int j=0; j<n; j++) {
                if(board[i][j] > 0) {
                    left = j;
                    break;
                }
            }
            for(int j=n-1; j>=0; j--) {
                if(board[i][j] > 0) {
                    right = j;
                    break;
                }
            }
            int width = right - left + 1;
            if(maxWidth < width) {
                maxLevel = i;
                maxWidth = width;
            }
        }
        int[] ans = new int[2];
        ans[0] = maxLevel;
        ans[1] = maxWidth;
        return ans;
    }

    public void findMaxDep(TreeNode node, int dep) {
        //중위순회
        if(node == null) return ;

        findMaxDep(node.left, dep+1);
        maxDep = Math.max(maxDep, dep);
        findMaxDep(node.right, dep+1);
    }

    public void inOrderTraversal(TreeNode node, int dep) {
        //중위순회
        if(node == null) return ;

        inOrderTraversal(node.left, dep+1);
        board[dep][idx++] = node.val;
        inOrderTraversal(node.right, dep+1);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}