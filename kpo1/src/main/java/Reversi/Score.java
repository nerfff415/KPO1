package Reversi;

import java.util.Objects;

public class Score {
    public static Point get(Table Playground) {
        int score1 = 0;
        int score2 = 0;
        String[][] board = Playground.getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Objects.equals(board[i][j], "x")) {
                    score1++;
                } else if (Objects.equals(board[i][j], "o")) {
                    score2++;
                }
            }
        }
        return new Point(score1, score2, "No Value", -1);
    }
}
