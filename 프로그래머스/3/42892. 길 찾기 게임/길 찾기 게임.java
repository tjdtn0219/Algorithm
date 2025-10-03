import java.awt.Point;
import java.util.*;

class Node {
    int x, y;
    int val;
    Node left;
    Node right;
    public Node(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
        this.left = null;
        this.right = null;
    }
    @Override
    public String toString() {
        return x + ", " + y + " | " + val;
    }
}

class Solution {
    
    Node root;
    List<Node> nodeList;
    Map<Point, Integer> idxMap;
    int[][] answer;
    int idx;
    
    public int[][] solution(int[][] nodeinfo) {
        
        init(nodeinfo);
        solve();
        return answer;
    }
    
    public void solve() {
        idx = 0;
        preOrder(root);
        idx = 0;
        postOrder(root);
    }
    
    public void preOrder(Node cur) {
        // 전위 순회
        if(cur == null) {
            return ;
        }
        answer[0][idx++] = cur.val;
        preOrder(cur.left);
        preOrder(cur.right);
    }
    
    public void inOrder(Node cur) {
        // 중위 순회
        if(cur == null) {
            return ;
        }
        inOrder(cur.left);
        // answer[0][idx++] = cur.val;
        inOrder(cur.right);
    }
    
    public void postOrder(Node cur) {
        // 후위 순회
        if(cur == null) {
            return ;
        }
        postOrder(cur.left);
        postOrder(cur.right);
        answer[1][idx++] = cur.val;
    }
    
    public void init(int[][] nodeInfos) {
        this.answer = new int[2][nodeInfos.length];
        
        idxMap = new HashMap<>();
        int idx = 1;
        for(int[] node : nodeInfos) {
            Point p = new Point(node[0], node[1]);
            idxMap.put(p, idx++);
        }
        
        Arrays.sort(nodeInfos, (o1, o2) -> {
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o2[1] - o1[1];
        });
        
        nodeList = new ArrayList<>();
        for(int[] node : nodeInfos) {
            int x = node[0];
            int y = node[1];
            nodeList.add(new Node(x, y, idxMap.get(new Point(x, y))));
        }
        
        root = nodeList.get(0);
        for(int i=1; i<nodeList.size(); i++) {
            // System.out.println(nodeList.get(i));
            insertNode(root, nodeList.get(i));
        }
    }
    
    public void insertNode(Node parent, Node child) {
        if(child.x < parent.x) {
            if(parent.left == null) {
                parent.left = child;
            } else {
                insertNode(parent.left, child);
            }
        } else {
            if(parent.right == null) {
                parent.right = child;
            } else {
                insertNode(parent.right, child);
            }
        }
    }
}