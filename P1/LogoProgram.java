/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ojitos Rizados
 */
public class LogoProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Program p = new Program("Square");
        p.addInstruction("REP", 4);
        p.addInstruction("FWD", 100);
        p.addInstruction("ROT", 90);
        p.addInstruction("END", 1);
        if (p.isCorrect()) {
            p.restart();
            while (!p.hasFinished()) {
                Instruction instr = p.getNextInstruction();
                System.out.println(instr.info());
            }
        }

    }

}
