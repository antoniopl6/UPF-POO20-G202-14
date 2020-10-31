
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Toni
 */
public class Logo {
    private int width, height;
    private Turtle t;
    
    public Logo(int w, int h){
        this.height = h;
        this.width = w;
        //Por defecto, pondremos el tutle en una posicion que sea visible, ya que lo situamos 
        //en el punto 0,0 empezara en una esquina
        //Tambien hemos decididio que empieze con su direccion hacia la derecha en horizontal
        t = new Turtle(300,175,1,0);
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void resetTurtle(){
        t.setCoord(300, 175);
        t.setDir(1, 0);
    }
    public void execute(Program p, Graphics g){
        if ( p.isCorrect ( )){
            p.restart( ) ;
            while (true) {
                Instruction instruction = p.getCurrentInstruction();
                //Consideraremos las instrucciones básicas listadas en el seminario 1
                switch (instruction.getCode()) {
                    case "PEN":
                        if(instruction.getParam() == 1){
                            t.setPen(true);
                        } else {
                            t.setPen(false);
                        }   break;
                    case "FWD":
                        t.forward(instruction.getParam(), g);
                        break;
                    case "ROT":
                        t.turn(instruction.getParam());
                        break;
                    default:
                        break;
                }
                if(!p.hasFinished()){
                    p.getNextInstruction();
                }else {
                    break;
                }
            }
        }
        t.draw(g);
    }
}
