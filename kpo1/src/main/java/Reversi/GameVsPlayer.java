package Reversi;

import java.util.ArrayList;
import java.util.Scanner;

public class GameVsPlayer {
    public static int start() {
        int moves1 = 0, moves2 = 0;
        int order = 0;
        String[] disp = {"o", "x"};
        String[][] board_past1 = null;
        ArrayList<Point> needed_points;
        String[][] board_past2 = null;
        int passed_white = 0;
        Point game_score;
        Scanner in = new Scanner(System.in);
        Table Playground = new Table();
        ArrayList<Point> pos1 = null;
        ArrayList<Point> pos2 = null;
        pos1 = Playground.possible_moves("x");
        while (true) {
            if (pos1.size() > 0) {
                if (passed_white == 0) {
                    System.out.println("Возможные ходы для черных:");
                    int i = 0;
                    System.out.print(++i);
                    System.out.print(") ");
                    System.out.println("Отменить предыдущий ход");
                    ArrayList<Point> unique1 = new ArrayList<Point>();
                    unique1.add(pos1.get(0));
                    int contains = 0;
                    for (int n = 1; n < pos1.size(); n++) {
                        for (Point myPair : unique1) {
                            if ((pos1.get(n).first() == myPair.first()) && (pos1.get(n).second() == myPair.second())) {
                                contains = 1;
                            }
                        }
                        if (contains == 0) {
                            unique1.add(pos1.get(n));
                        }
                        contains = 0;
                    }
                    for (Point po : unique1) {
                        System.out.print(++i);
                        System.out.print(") ");
                        System.out.print("(");
                        System.out.print(po.first() + 1);
                        System.out.print("; ");
                        System.out.print(po.second() + 1);
                        System.out.print(")");
                        System.out.println();
                    }
                    order = order + 1;
                    Playground.display(disp[order % 2]);
                    System.out.println("Сделайте ход(Пример ввода:1):");
                    int move = in.nextInt();
                    if (move == 1) {
                        if (moves1 > 0) {
                            Playground.setBoard(board_past1);
                            pos1 = Playground.possible_moves("x");
                            order = order + 1;
                            game_score = Score.get(Playground);
                            System.out.printf("Черные: %d, Белые: %d\n", game_score.first(), game_score.second());
                            continue;
                        } else {
                            while (move == 1) {
                                System.out.println("Невозможно отменить ход, выберите другой ход");
                                move = in.nextInt();
                            }
                        }
                    }
                    moves1++;
                    board_past1 = Playground.getBoard();
                    for (Point myPair : pos1) {
                        if ((myPair.first() == pos1.get(move - 2).first()) && (myPair.second() == pos1.get(move - 2).second())) {
                            Playground.make_move(myPair, "x");
                        }
                    }
                    game_score = Score.get(Playground);
                    System.out.printf("Черные: %d, Белые: %d\n", game_score.first(), game_score.second());
                }
            } else {
                order++;
                System.out.println("У черных нет возможных ходов!");
            }

            pos2 = Playground.possible_moves("o");
            if (pos2.size() > 0) {
                System.out.println("Возможные ходы для белых:");
                int i = 0;
                System.out.print(++i);
                System.out.print(") ");
                System.out.println("Отменить предыдущий ход");
                ArrayList<Point> unique = new ArrayList<Point>();
                unique.add(pos2.get(0));
                int contains = 0;
                for (int n = 1; n < pos2.size(); n++) {
                    for (Point myPair : unique) {
                        if ((pos2.get(n).first() == myPair.first()) && (pos2.get(n).second() == myPair.second())) {
                            contains = 1;
                        }
                    }
                    if (contains == 0) {
                        unique.add(pos2.get(n));
                    }
                    contains = 0;
                }
                for (Point po : unique) {
                    System.out.print(++i);
                    System.out.print(") ");
                    System.out.print("(");
                    System.out.print(po.first() + 1);
                    System.out.print("; ");
                    System.out.print(po.second() + 1);
                    System.out.print(")");
                    System.out.println();
                }
                order = order + 1;
                Playground.display(disp[order % 2]);
                System.out.println("Сделайте ход(Пример ввода:1):");
                int move = in.nextInt();
                if (move == 1) {
                    if (moves2 > 0) {
                        passed_white = 1;
                        Playground.setBoard(board_past2);
                        pos1 = Playground.possible_moves("o");
                        order = order + 1;
                        game_score = Score.get(Playground);
                        System.out.printf("Черные: %d, Белые: %d\n", game_score.first(), game_score.second());
                        continue;
                    } else {
                        while (move == 1) {
                            System.out.println("Невозможно отменить ход, выберите другой ход");
                            move = in.nextInt();
                        }
                    }
                }
                moves2++;
                board_past2 = Playground.getBoard();
                passed_white = 0;
                for (int l = 0; l < pos2.size(); l++) {
                    if ((pos2.get(l).first() == pos2.get(move - 2).first()) && (pos2.get(l).second() == pos2.get(move - 2).second())) {
                        Playground.make_move(pos2.get(l), "o");
                    }
                }
                game_score = Score.get(Playground);
                System.out.printf("Черные: %d, Белые: %d\n", game_score.first(), game_score.second());
            } else {
                order++;
                System.out.println("У белых нет возможных ходов!");
            }
            pos2 = Playground.possible_moves("o");
            pos1 = Playground.possible_moves("x");
            if ((pos1.size() < 1) && (pos2.size() < 1)) {
                System.out.println("Игра закончена!");
                game_score = Score.get(Playground);
                if (game_score.first() > game_score.second()) {
                    System.out.println("Победили черные!");
                } else if (game_score.first() < game_score.second()) {
                    System.out.println("Победили белые!");
                } else System.out.println("Ничья!");
                break;
            }
        }
        return Math.max(game_score.first(), game_score.second());
    }
}
