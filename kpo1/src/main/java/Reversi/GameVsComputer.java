package Reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class GameVsComputer {
    public static int start(int mode) {
        int moves1 = 0, moves2 = 0;
        int order = 0;
        String[] disp = {"o", "x"};
        String[][] board_past1 = null;
        Point game_score;
        String player_color = null;
        String computer_color;
        int comp_first = 0;
        Scanner in = new Scanner(System.in);
        Table Playground = new Table();
        ArrayList<Point> pos_player = null;
        ArrayList<Point> pos_computer = null;
        ComputerPoint movecomp = null;
        System.out.println("Выберите цвет:\n1) Белые 2) Черные");
        while (true) {
            int color = in.nextInt();
            if (color == 1) {
                player_color = "o";
                computer_color = "x";
                break;
            } else if (color == 2) {
                player_color = "x";
                computer_color = "o";
                break;
            } else {
                System.out.println("Повторите попытку.");
            }
        }
        if (player_color == "o") {
            comp_first = 1;
        }
        pos_player = Playground.possible_moves(player_color);
        while (true) {
            if (comp_first == 0) {
                if (pos_player.size() > 0) {
                    System.out.println("Возможные ходы:");
                    int i = 0;
                    System.out.print(++i);
                    System.out.print(") ");
                    System.out.println("Отменить предыдущий ход");
                    ArrayList<Point> unique1 = new ArrayList<Point>();
                    unique1.add(pos_player.get(0));
                    int contains = 0;
                    for (int n = 1; n < pos_player.size(); n++) {
                        for (Point myPair : unique1) {
                            if ((pos_player.get(n).first() == myPair.first()) && (pos_player.get(n).second() == myPair.second())) {
                                contains = 1;
                            }
                        }
                        if (contains == 0) {
                            unique1.add(pos_player.get(n));
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
                    game_score = Score.get(Playground);
                    System.out.printf("Черные: %d, Белые: %d\n", game_score.first(), game_score.second());
                    Playground.display(disp[order % 2]);
                    System.out.println("Сделайте ход(Пример ввода:1):");
                    int move = in.nextInt();
                    while ((move < 1) || (move > i)) {
                        System.out.println("Некорректный ввод.Повторите попытку.");
                        move = in.nextInt();
                    }
                    if (move == 1) {
                        if (moves1 > 0) {
                            Playground.setBoard(board_past1);
                            pos_player = Playground.possible_moves(player_color);
                            order = order + 1;
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
                    for (Point myPair : pos_player) {
                        if ((myPair.first() == pos_player.get(move - 2).first()) && (myPair.second() == pos_player.get(move - 2).second())) {
                            Playground.make_move(myPair, player_color);
                        }
                    }
                    game_score = Score.get(Playground);
                    System.out.printf("Черные: %d, Белые: %d\n", game_score.first(), game_score.second());
                } else {
                    order++;
                    System.out.println("У черных нет возможных ходов!");
                }
            }
            pos_computer = Playground.possible_moves(computer_color);
            if (pos_computer.size() > 0) {
                ArrayList<ComputerPoint> unique = new ArrayList<ComputerPoint>();
                List<String> keys_s = new ArrayList<String>();
                keys_s.add(pos_computer.get(0).key());
                ComputerPoint cell = new ComputerPoint(pos_computer.get(0).first(), pos_computer.get(0).second(), keys_s, 0);
                unique.add(cell);
                int contain = 0;
                for (int q = 1; q < pos_computer.size(); q++) {
                    for (ComputerPoint computerPoint : unique) {
                        if ((pos_computer.get(q).first() == computerPoint.first()) && (pos_computer.get(q).second() == computerPoint.second())) {
                            contain = 1;
                            computerPoint.addkey(pos_computer.get(q).key());
                            computerPoint.addcount(pos_computer.get(q).count() - 1);
                        }
                    }
                    if (contain == 0) {
                        List<String> keys_s1 = new ArrayList<String>();
                        keys_s1.add(pos_computer.get(q).key());
                        ComputerPoint cell1 = new ComputerPoint(pos_computer.get(q).first(), pos_computer.get(q).second(), keys_s1, 0);
                        unique.add(cell1);
                    }
                }
                if (mode == 1) {
                    movecomp = LightComputer.move(Playground, computer_color, unique);
                } else movecomp = HardComputer.move(Playground, computer_color, unique);
                Playground.display(disp[(order+1) % 2]);
                for (int g = 0; g < Objects.requireNonNull(movecomp).keys().size(); g++) {
                    Point tmp = new Point(movecomp.first(), movecomp.second(), movecomp.keys().get(g), 0);
                    Playground.make_move(tmp, computer_color);
                }
                order = order + 1;
            } else {
                order++;
                System.out.println("У белых нет возможных ходов!");
            }
            comp_first = 0;
            pos_computer = Playground.possible_moves(computer_color);
            pos_player = Playground.possible_moves(player_color);
            if ((pos_player.size() < 1) && (pos_computer.size() < 1)) {
                System.out.println("Игра закончена!");
                game_score = Score.get(Playground);
                if (game_score.first() > game_score.second()) {
                    System.out.println("Победили черные!");
                } else if (game_score.first() < game_score.second()) {
                    System.out.println("Победили белые!");
                } else System.out.println("Ничья!");
                game_score = Score.get(Playground);
                System.out.printf("Черные: %d, Белые: %d\n", game_score.first(), game_score.second());
                Playground.display(disp[order % 2]);
                break;
            }
        }
        if (player_color == "x") {
            return game_score.first();
        } else {
            return game_score.second();
        }
    }
}