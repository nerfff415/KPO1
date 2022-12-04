package Reversi;

import java.util.Objects;

public class Rfunction {
    public static float calculate(ComputerPoint po, Table Playground, String current) {
        float weight = 0;
        int count_points = 0;
        int i = 0;
        for (int j = 0; j < po.keys().size(); j ++) {
            if (Objects.equals(po.keys().get(j), "R")) {
                i = 1;
                while (!Objects.equals(Playground.getBoard()[po.first()][po.second() + i], current)) {
                    if ((po.first() == 0) || (po.first() == 7) || (po.second() == 0) || (po.second() == 7)) {
                        weight = weight + 2;
                    } else {
                        weight = weight + 1;
                    }
                    i++;
                    if (po.second() + i > 7) {
                        break;
                    }
                }
            }
            if (Objects.equals(po.keys().get(j), "L")) {
                i = 1;
                while (!Objects.equals(Playground.getBoard()[po.first()][po.second() - i], current)) {
                    if ((po.first() == 0) || (po.first() == 7) || (po.second() == 0) || (po.second() == 7)) {
                        weight = weight + 2;
                    } else {
                        weight = weight + 1;
                    }
                    i++;
                    if (po.second() - i < 0) {
                        break;
                    }
                }
            }
            if (Objects.equals(po.keys().get(j), "B")) {
                i = 1;
                while (!Objects.equals(Playground.getBoard()[po.first() + i][po.second()], current)) {
                    if ((po.first() == 0) || (po.first() == 7) || (po.second() == 0) || (po.second() == 7)) {
                        weight = weight + 2;
                    } else {
                        weight = weight + 1;
                    }
                    i++;
                    if (po.first() + i > 7) {
                        break;
                    }
                }
            }
            if (Objects.equals(po.keys().get(j), "T")) {
                i = 1;
                while (!Objects.equals(Playground.getBoard()[po.first() - i][po.second()], current)) {
                    if ((po.first() == 0) || (po.first() == 7) || (po.second() == 0) || (po.second() == 7)) {
                        weight = weight + 2;
                    } else {
                        weight = weight + 1;
                    }
                    i++;
                    if (po.first() - i < 0) {
                        break;
                    }
                }
            }
            if (Objects.equals(po.keys().get(j), "TR")) {
                i = 1;
                while (!Objects.equals(Playground.getBoard()[po.first() - i][po.second() + i], current)) {
                    if ((po.first() == 0) || (po.first() == 7) || (po.second() == 0) || (po.second() == 7)) {
                        weight = weight + 2;
                    } else {
                        weight = weight + 1;
                    }
                    i++;
                    if ((po.first() - i < 0) || (po.second() + i > 7)) {
                        break;
                    }
                }
            }
            if (Objects.equals(po.keys().get(j), "TL")) {
                i = 1;
                while (!Objects.equals(Playground.getBoard()[po.first() - i][po.second() - i], current)) {
                    if ((po.first() == 0) || (po.first() == 7) || (po.second() == 0) || (po.second() == 7)) {
                        weight = weight + 2;
                    } else {
                        weight = weight + 1;
                    }
                    i++;
                    if ((po.first() - i < 0) || (po.second() - i < 0)) {
                        break;
                    }
                }
            }
            if (Objects.equals(po.keys().get(j), "BR")) {
                i = 1;
                while (!Objects.equals(Playground.getBoard()[po.first() + i][po.second() + i], current)) {
                    if ((po.first() == 0) || (po.first() == 7) || (po.second() == 0) || (po.second() == 7)) {
                        weight = weight + 2;
                    } else {
                        weight = weight + 1;
                    }
                    i++;
                    if ((po.first() + i > 7) || (po.second() + i > 7)) {
                        break;
                    }
                }
            }
            if (Objects.equals(po.keys().get(j), "BL")) {
                i = 1;
                while (!Objects.equals(Playground.getBoard()[po.first() + i][po.second() - i], current)) {
                    if ((po.first() == 0) || (po.first() == 7) || (po.second() == 0) || (po.second() == 7)) {
                        weight = weight + 2;
                    } else {
                        weight = weight + 1;
                    }
                    i++;
                    if ((po.first() + i > 7) || (po.second() - i < 0)) {
                        break;
                    }
                }
            }
        }
        if (((po.first() == 0) && (po.second() == 0)) ||
                ((po.first() == 0) && (po.second()== 7)) || ((po.first()== 7) && (po.second() == 0)) || ((po.first() == 7) && (po.second() == 7))) {
            weight = weight + 0.8f;
        } else if ((po.first() == 0) || (po.first() == 7) || (po.second() == 0) || (po.second() == 7)) {
            weight = weight + 0.4f;
        }
        return weight;
    }
}