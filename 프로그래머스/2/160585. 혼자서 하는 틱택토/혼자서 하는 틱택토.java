public class Solution {
    public int solution(String[] board) {
        int oCount = 0;
        int xCount = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'O') {
                    oCount++;
                } else if (board[i].charAt(j) == 'X') {
                    xCount++;
                }
            }
        }

        if (oCount == 0) {
            return (xCount == 0) ? 1 : 0;
        } else if (xCount > oCount) {
            return 0;
        } else if (oCount - xCount > 1) {
            return 0;
        }

        boolean oBingo = checkBingo('O', board);
        boolean xBingo = checkBingo('X', board);

        if (oBingo && xBingo) {
            return 0;
        } else if (oBingo && oCount == xCount) {
            return 0;
        } else if (xBingo && oCount != xCount) {
            return 0;
        }

        return 1;
    }

    public boolean checkBingo(char target, String[] board) {
        // Horizontal
        for (int i = 0; i < board.length; i++) {
            if (board[i].charAt(0) == target &&
                board[i].charAt(1) == target &&
                board[i].charAt(2) == target) {
                return true;
            }
        }

        // Vertical
        for (int i = 0; i < board.length; i++) {
            if (board[0].charAt(i) == target &&
                board[1].charAt(i) == target &&
                board[2].charAt(i) == target) {
                return true;
            }
        }

        // Diagonal
        if (board[0].charAt(0) == target &&
            board[1].charAt(1) == target &&
            board[2].charAt(2) == target) {
            return true;
        }

        if (board[0].charAt(2) == target &&
            board[1].charAt(1) == target &&
            board[2].charAt(0) == target) {
            return true;
        }

        return false;
    }
}
