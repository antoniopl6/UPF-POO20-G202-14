/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package logoprogram;

/**
 *
 * @author Toni
 */
public class Instruction {
    private String code;
    private double param;

    public Instruction(String c, double p) {
        code = c;
        param = p;
    }

    public String getCode() {
        return code;
    }

    public Double getParam() {
        return param;
    }

    public Boolean isRepInstruction() {
        if (code.equals("REP") || code.equals("END")) {
            return true;
        }
        return false;
    }

    public Boolean isCorrect() {
        if (this.errorCode() == 0) {
            return true;
        }
        return false;
    }

    public int errorCode() {
        if (!code.equals("PEN") && !code.equals("FWD") && !code.equals("ROT") && !code.equals("REP") && !code.equals("END")) {
            return 1;
        } else if (code.equals("FWD") && ((param < -1000) || (param > 1000))) {
            return 2;
        } else if (code.equals("PEN") && ((param != 0) || (param != 1))) {
            return 3;
        } else if (code.equals("ROT") && ((param >= 360) || (param <= -360))) {
            return 4;
        } else if (code.equals("REP") && ((param >= 1000) || (param <= 0))) {
            return 5;
        }
        return 0;
    }

    public String info() {
        if (this.isRepInstruction()) {
            return ("");
        }
        return (code + " " + String.valueOf(param));
    }

}
