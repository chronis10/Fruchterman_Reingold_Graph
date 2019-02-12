/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domes;

/**
 *
 * @author hroni
 */
public class FromTo {
    private int from_node;
    private int to_node;

    public FromTo(int from_node, int to_node) {
        this.from_node = from_node;
        this.to_node = to_node;
    }

    public int getFrom_node() {
        return from_node;
    }

    public void setFrom_node(int from_node) {
        this.from_node = from_node;
    }

    public int getTo_node() {
        return to_node;
    }
    
    public void setTo_node(int to_node) {
        this.to_node = to_node;
    }
    
    
}