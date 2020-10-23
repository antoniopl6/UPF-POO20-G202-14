/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package logoprogram;
import java.util.LinkedList;
/**
 *
 * @author Toni
 */
public class Program {
    private LinkedList<Instruction> instructions;
    private int currentLine;
    private int loopIteration;
    private String programName;

    public Program(String name) {
        this.programName = name;
        instructions = new LinkedList<>();
        loopIteration = 1;
        currentLine = 0;
    }

    public String getName() {
        return programName;
    }

    public boolean addInstruction(String c, double p) {
        Instruction new_instruction = new Instruction(c, p);

        if (instructions.contains(new_instruction)) {
            return false;
        } else {
            instructions.addLast(new_instruction);
        }
        return true;
    }

    public void restart() {
        currentLine = 0;
    }

    public boolean hasFinished() {
        int nextLine = currentLine + 1;
        if (nextLine == (instructions.size()) && this.loopIteration == 1){
            return true;
        }
        return false;
    }

    public Instruction getNextInstruction() {
        //Comprobaremos que tipo de instruccion tenemos
        Instruction current_instruction = instructions.get(currentLine);
        if (current_instruction.isRepInstruction()) {
            //Si nuestra siguiente instruccion es un Rep, deberemos iniciar un
            //loop, por lo que asignamos el valor de Rep a nuestro loopIteration
            if (current_instruction.getCode().equals("REP")) {
                loopIteration = (int) Math.round(current_instruction.getParam());
            } //Si nuestra siguiente instruccion es un End, primero habra que
            //comprobar si estamos terminando con una seccion de repeticiones
            else {
                if (loopIteration != 1) {
                    this.goToStartLoop();
                }
            }
        }
        currentLine++;
        return instructions.get(currentLine);
        
    }

    public boolean isCorrect() {
        for (int i = 0; i < instructions.size(); i++) {
            if (!instructions.get(i).isCorrect()) {
                return false;
            }
        }
        return true;
    }

    public void printErrors() {
        for (int i = 0; i < instructions.size(); i++) {
            Instruction actual_instruction = instructions.get(i);
            if (!actual_instruction.isCorrect()) {
                System.out.println("Instruction number " + (i + 1) + " has error code: " + actual_instruction.getCode() + "\n");
            }
        }
    }

    private void goToStartLoop() {
        //Vamos a usar un loop para cuando nos encontramos en una instruccion END
        //y queremos encontrar la instruccion REP que le precede
        //Estamos suponiendo que no habra loops anidados

        for (int i = currentLine; i >= 0; i--) {
            Instruction current_instruction = instructions.get(i);
            if (current_instruction.getCode().equals("REP")) {
                currentLine = i;
            }
        }
        loopIteration--;
    }
}
