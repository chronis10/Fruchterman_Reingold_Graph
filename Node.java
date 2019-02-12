
package domes;

import java.util.ArrayList;

/**
 *
 * @author Christos Chronis
 */
public class Node {
    private double posx = 0;
    private double posy = 0;
    private double dispx = 0;
    private double dispy = 0;
    private ArrayList<Integer> neigh = new ArrayList<Integer>();

    
    public void add_neigh(int edge){
        this.neigh.add(edge);    
    }

    public ArrayList<Integer> getNeigh() {
        return neigh;
    }
    
    
    public double getPosx() {
        return posx;
    }

    public double getPosy() {
        return posy;
    }

    public double getDispx() {
        return dispx;
    }

    public double getDispy() {
        return dispy;
    }

    public void setPosx(double posx) {
        this.posx = posx;
    }

    public void setPosy(double posy) {
        this.posy = posy;
    }

    public void setDispx(double dispx) {
        this.dispx = dispx;
    }

    public void setDispy(double dispy) {
        this.dispy = dispy;
    }
    
    public void printall() {        

        System.out.print("["+this.posx+","+this.posy+"]");

    }
    
    
}
