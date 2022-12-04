package Reversi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HardComputer {
    static ComputerPoint move(@NotNull Table Playground, String computer_color, ArrayList<ComputerPoint> points) {
        ArrayList<Point> pos1;
        String player_color;
        if (Objects.equals(computer_color, "x")) {
            player_color = "o";
        } else {
            player_color = "x";
        }
        if (points.size() < 1) return null;
        float weight1 = 0;
        float weight2 = 0;
        float max = -1;
        ComputerPoint res1 = null;
        for (ComputerPoint po : points) {
            weight1 = Rfunction.calculate(po, Playground, computer_color);
            Table Playground2 = new Table(Playground.getBoard());
            if (po.keys().size() > 1) {
                Point po2 = null;
                for (int u = 0; u < po.keys().size(); u++) {
                    po2 = new Point(po.first(), po.second(), po.keys().get(u), 0);
                    Playground2.make_move(po2, computer_color);
                }
            } else {
                Point po2 = null;
                po2 = new Point(po.first(), po.second(), po.keys().get(0), 0);
                Playground2.make_move(po2, computer_color);
            }
            pos1 = Playground2.possible_moves(player_color);
            if (pos1.size() < 1) {
                return po;
            }
            ArrayList<ComputerPoint> points1 = new ArrayList<ComputerPoint>();
            List<String> keyy = new ArrayList<String>();
            keyy.add(pos1.get(0).key());
            ComputerPoint cell = new ComputerPoint(pos1.get(0).first(), pos1.get(0).second(), keyy, 0);
            points1.add(cell);
            int contain = 0;
            for (int q = 1; q < pos1.size(); q++) {
                for (ComputerPoint computerPoint : points1) {
                    if ((pos1.get(q).first() == computerPoint.first()) && (pos1.get(q).second() == computerPoint.second())) {
                        contain = 1;
                        computerPoint.addkey(pos1.get(q).key());
                        computerPoint.addcount(pos1.get(q).count() - 1);
                    }
                }
                if (contain == 0) {
                    List<String> keyy1 = new ArrayList<String>();
                    keyy1.add(pos1.get(q).key());
                    ComputerPoint cell1 = new ComputerPoint(pos1.get(q).first(), pos1.get(q).second(), keyy1, 0);
                    points1.add(cell1);
                }
            }
            float max2 = -1;
            res1 = null;
            for (ComputerPoint po1 : points1) {
                weight2 = Rfunction.calculate(po, Playground, computer_color);
                if (weight2 > max2) {
                    max2 = weight2;
                    res1 = po;
                }
            }
            if (weight1 - weight2 > max) {
                max = weight1;
                res1 = po;
            }
        }
        return res1;
    }
}