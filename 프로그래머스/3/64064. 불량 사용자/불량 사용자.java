import java.util.*;

class Solution {
    String[] userIds;
    String[] bannedIds;
    boolean[] visited;
    HashSet<HashSet<String>> result = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        userIds = user_id;
        bannedIds = banned_id;
        visited = new boolean[userIds.length];

        dfs(new HashSet<>(), 0);
        
//         HashSet<String> s1 = new HashSet<>();
//         s1.add("abc");
//         s1.add("bbb");
//         HashSet<String> s2 = new HashSet<>();
//         s2.add("abc");
//         s2.add("bbb");
        
//         result.add(s1);
//         result.add(s2);
        
        // System.out.println(System.identityHashCode(s1));
        // System.out.println(System.identityHashCode(s2));

        return result.size();
    }

    void dfs(HashSet<String> set, int depth) {
        if (depth == bannedIds.length) {
            result.add(set);
            return;
        }

        for (int i = 0; i < userIds.length; i++) {
            if (set.contains(userIds[i])) {
                continue;
            }

            if (check(userIds[i], bannedIds[depth])) {
                if(depth == 0) {
                    System.out.println("put : " + userIds[i]);
                }
                set.add(userIds[i]);
                dfs(new HashSet<>(set), depth + 1);
                set.remove(userIds[i]);
            }
        }
    }

    boolean check(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) {
            return false;
        }

        boolean match = true;
        for (int i = 0; i < userId.length(); i++) {
            if (bannedId.charAt(i) != '*' && userId.charAt(i) != bannedId.charAt(i)) {
                match = false;
                break;
            }
        }

        return match;
    }
}