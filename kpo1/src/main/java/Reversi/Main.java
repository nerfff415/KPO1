package Reversi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int max_score = 0;
        int score;
        System.out.println("-------------------------------");
        System.out.println("Меню");
        System.out.print("Выберите соперника:\n1) Компьютер(легкий)\n2) Компьютерный(сложный)\n3) Два игрока\n4) Наилучший результат за сессию:\n5) Закончить сессию\n");
        Scanner in = new Scanner(System.in);
        int vs = in.nextInt();
        while (true) {
            if (vs == 1) {
                score = GameVsComputer.start(1);
                if (max_score < score) {
                    max_score = score;
                }
            } else if (vs == 2) {
                score = GameVsComputer.start(2);
                if (max_score < score) {
                    max_score = score;
                }
            } else if (vs == 3) {
                score = GameVsPlayer.start();
                if (max_score < score) {
                    max_score = score;
                }
            } else if (vs == 4) {
                System.out.println(max_score);
            } else if (vs == 5) {
                break;
            }
            System.out.println("-------------------------------");
            System.out.println("Меню");
            System.out.println("Выберите соперника:\n1) Компьютер(легкий)\n2) Компьютерный(сложный)\n3) Два игрока\n4) Наилучший результат за сессию:\n5) Закончить сессию\n");
            vs = in.nextInt();
        }
    }
}