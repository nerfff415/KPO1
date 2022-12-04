package Reversi;

import java.util.ArrayList;
import java.util.Objects;

public class Table {
    private final String[][] board = new String[8][8];

    Table() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = "-";
            }
        }
        board[3][3] = "o";
        board[4][4] = "o";
        board[3][4] = "x";
        board[4][3] = "x";
    }

    Table(String[][] array) {
        this.setBoard(array);
    }

    public String[][] getBoard() {
        String[][] res = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Objects.equals(board[i][j], "x")) {
                    res[i][j] = "x";
                } else if (Objects.equals(board[i][j], "o")) {
                    res[i][j] = "o";
                } else if (Objects.equals(board[i][j], "-")) {
                    res[i][j] = "-";
                }
            }
        }
        return res;
    }

    public ArrayList<Point> possible_moves(String current) {
        ArrayList<Point> res = new ArrayList<>();
        String opposite_current;
        if (current == "-") {
            opposite_current = "-";
        } else if (current == "x") {
            opposite_current = "o";
        } else {
            opposite_current = "x";
        }
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (board[row][column].equals("-")) {
                    int check_furtherR = 1, check_furtherL = 1, check_furtherT = 1, check_furtherB = 1, check_furtherTL = 1, check_furtherTR = 1, check_furtherBL = 1, check_furtherBR = 1;
                    if (column + check_furtherR < 7) {
                        while (board[row][column + check_furtherR].equals(opposite_current) && (column + check_furtherR < 7)) {
                            check_furtherR++;
                        }
                        if (board[row][column + check_furtherR].equals(current) && check_furtherR > 1) {
                            Point point = new Point(row, column, "R", check_furtherR);
                            res.add(point);
                        }
                    }
                    if (column - check_furtherL > 0) {
                        while (board[row][column - check_furtherL].equals(opposite_current) && (column - check_furtherL > 0)) {
                            check_furtherL++;
                        }
                        if (board[row][column - check_furtherL].equals(current) && check_furtherL > 1) {
                            Point point = new Point(row, column, "L", check_furtherL);
                            res.add(point);
                        }
                    }
                    if (row + check_furtherB < 7) {
                        while (board[row + check_furtherB][column].equals(opposite_current) && (row + check_furtherB < 7)) {
                            check_furtherB++;
                        }
                        if (board[row + check_furtherB][column].equals(current) && check_furtherB > 1) {
                            Point point = new Point(row, column, "B", check_furtherB);
                            res.add(point);
                        }
                    }
                    if (row - check_furtherT > 0) {
                        while (board[row - check_furtherT][column].equals(opposite_current) && (row - check_furtherT > 0)) {
                            check_furtherT++;
                        }
                        if (board[row - check_furtherT][column].equals(current) && check_furtherT > 1) {
                            Point point = new Point(row, column, "T", check_furtherT);
                            res.add(point);
                        }
                    }
                    if ((row - check_furtherTR > 0) && (column + check_furtherTR < 7)) {
                        while (board[row - check_furtherTR][column + check_furtherTR].equals(opposite_current) && ((row - check_furtherTR > 0) && (column + check_furtherTR < 7))) {
                            check_furtherTR++;
                        }
                        if (board[row - check_furtherTR][column + check_furtherTR].equals(current) && check_furtherTR > 1) {
                            Point point = new Point(row, column, "TR", check_furtherTR);
                            res.add(point);
                        }
                    }
                    if ((row - check_furtherTL > 0) && (column - check_furtherTL > 0)) {
                        while (board[row - check_furtherTL][column - check_furtherTL].equals(opposite_current) && ((row - check_furtherTL > 0) && (column - check_furtherTL > 0))) {
                            check_furtherTL++;
                        }
                        if (board[row - check_furtherTL][column - check_furtherTL].equals(current) && check_furtherTL > 1) {
                            Point point = new Point(row, column, "TL", check_furtherTL);
                            res.add(point);
                        }
                    }
                    if ((row + check_furtherBL < 7) && (column - check_furtherBL > 0)) {
                        while (board[row + check_furtherBL][column - check_furtherBL].equals(opposite_current) && ((row + check_furtherBL < 7) && (column - check_furtherBL > 0))) {
                            check_furtherBL++;
                        }
                        if (board[row + check_furtherBL][column - check_furtherBL].equals(current) && check_furtherBL > 1) {
                            Point point = new Point(row, column, "BL", check_furtherBL);
                            res.add(point);
                        }
                    }
                    if ((row + check_furtherBR < 7) && (column + check_furtherBR < 7)) {
                        while (board[row + check_furtherBR][column + check_furtherBR].equals(opposite_current) && ((row + check_furtherBR < 7) && (column + check_furtherBR < 7))) {
                            check_furtherBR++;
                        }
                        if (board[row + check_furtherBR][column + check_furtherBR].equals(current) && check_furtherBR > 1) {
                            Point point = new Point(row, column, "BR", check_furtherBR);
                            res.add(point);
                        }
                    }
                }
            }
        }
        return res;
    }

    public void display(String current) {
        ArrayList<Point> possi;
        possi = this.possible_moves(current);
        String[][] board2 = new String[8][8];
        board2 = this.getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (Point myPair : possi) {
                    if ((i == myPair.first()) && (j == myPair.second())) {
                        board2[i][j] = "+";
                    }
                }
            }
        }
        int[] row_indicator = new int[9];
        int row_count = 1;
        for (int i = 1; i <= 8; i++) {
            row_indicator[i] = row_count;
            row_count++;
        }
        int[] column_indicator = new int[9];
        int column_count = 1;
        for (int i = 1; i <= 8; i++) {
            column_indicator[i] = column_count;
            column_count++;
        }
        System.out.print("\n  ");
        for (int i = 1; i <= 8; i++) {
            System.out.print(" " + column_indicator[i]);
        }
        System.out.println("");
        for (int x = 1; x <= 8; x++) {
            System.out.print(" " + row_indicator[x]);
            for (int i = 1; i <= 8; i++) {
                System.out.print(" " + board2[x - 1][i - 1]);
            }
            System.out.println();
        }
    }

    public void setBoard(String[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Objects.equals(board[i][j], "o")) {
                    this.board[i][j] = "o";
                } else if (Objects.equals(board[i][j], "x")) {
                    this.board[i][j] = "x";
                } else this.board[i][j] = "-";
            }
        }
    }

    public void make_move(Point point, String current) {
        String opposite_current;
        if (Objects.equals(current, "-")) {
            opposite_current = "-";
        } else if (Objects.equals(current, "x")) {
            opposite_current = "o";
        } else {
            opposite_current = "x";
        }
        int row = point.first();
        int column = point.second();
        int i = 1;
        if (Objects.equals(point.key(), "R")) {
            i = 1;
            while (!Objects.equals(board[row][column + i], current)) {
                board[row][column + i] = current;
                i++;
            }
            board[row][column] = current;
        }
        if (Objects.equals(point.key(), "L")) {
            i = 1;
            while (!Objects.equals(board[row][column - i], current)) {
                board[row][column - i] = current;
                i++;
            }
            board[row][column] = current;
        }
        if (Objects.equals(point.key(), "B")) {
            i = 1;
            while (!Objects.equals(board[row + i][column], current)) {
                board[row + i][column] = current;
                i++;
            }
            board[row][column] = current;
        }
        if (Objects.equals(point.key(), "T")) {
            i = 1;
            while (!Objects.equals(board[row - i][column], current)) {
                board[row - i][column] = current;
                i++;
            }
            board[row][column] = current;
        }
        if (Objects.equals(point.key(), "TR")) {
            i = 1;
            while (!Objects.equals(board[row - i][column + i], current)) {
                board[row - i][column + i] = current;
                i++;
            }
            board[row][column] = current;
        }
        if (Objects.equals(point.key(), "TL")) {
            i = 1;
            while (!Objects.equals(board[row - i][column - i], current)) {
                board[row - i][column - i] = current;
                i++;
            }
            board[row][column] = current;
        }
        if (Objects.equals(point.key(), "BR")) {
            i = 1;
            while (!Objects.equals(board[row + i][column + i], current)) {
                board[row + i][column + i] = current;
                i++;
            }
            board[row][column] = current;
        }
        if (Objects.equals(point.key(), "BL")) {
            i = 1;
            while (!Objects.equals(board[row + i][column - i], current)) {
                board[row + i][column - i] = current;
                i++;
            }
            board[row][column] = current;
        }
    }
}