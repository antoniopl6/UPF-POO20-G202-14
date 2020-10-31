
import java.awt.Color;
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
public class Turtle {
    private int x, y;
    private double dirX, dirY;
    private boolean pen;
    
    public Turtle(int x, int y, double dx, double dy){
        this.x = x;
        this.y = y;
        dirX = dx;
        dirY = dy;
        pen = true;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public double getDirX(){
        return dirX;
    }
    public double getDirY(){
        return dirY;
    }
    
    
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setDirX(double dirX) {
        this.dirX = dirX;
    }

    public void setDirY(double dirY) {
        this.dirY = dirY;
    }
    public void setDir(double dx, double dy) {
        this.dirX = dx;
        this.dirY = dy;
    }
    
    public void forward(double distance, Graphics g){
        int xOld = x;
        int yOld = y;
        this.x = x + (int) (dirX*distance);
        this.y = y + (int) (dirY*distance);
        if (isPenOn() == true){
            g.drawLine(xOld, yOld, x, y);
        }
    }
    public void turn(double a){
        double dirXold = dirX;
        this.dirX = Math.cos(a*Math.PI/180)*dirXold - Math.sin(a*Math.PI/180)*dirY;
        this.dirY = Math.sin(a*Math.PI/180)*dirXold + Math.cos(a*Math.PI/180)*dirY;
    }
    public void setPen(boolean on){
        this.pen = on;
    }
    public boolean isPenOn(){
        return this.pen;
    }
    public void draw(Graphics g){
        int[] xc = new int[3];
        int[] yc = new int[3];
        xc[0] = (int)(x+8*dirY);
        xc[1] = (int)(x-8*dirY);
        xc[2] = (int)(x+16*dirX);
        yc[0] = (int)(y-8*dirX);
        yc[1] = (int)(y+8*dirX);
        yc[2] = (int)(y+16*dirY);
        g.setColor(Color.blue);
        g.drawPolygon(xc, yc, 3);
    }
}

