package Reversi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LightComputer {
    static ComputerPoint move(@NotNull Table Playground, String computer_color, ArrayList<ComputerPoint> points) {
        Point res = null;
        ComputerPoint res1 = null;
        if (points.size() < 1) {
            return null;
        }

        float max = -1;
        float weight = 0;
        for (ComputerPoint po : points) {
            weight = Rfunction.calculate(po, Playground, computer_color);
            if (weight > max) {
                max = weight;
                res1 = po;
            }
        }
        return res1;
    }
}
