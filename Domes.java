/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author hroni
 */
public class Domes {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Programm started");
        
        Scanner userinp = new Scanner(System.in);
        
        // TODO code application logic here
        double w = 1280;
        double h = 720;
        double T = 72;    
        int epoaseis = 100;
        int n_nodes  = 6;
        PrintWriter writer = new PrintWriter("output.dot", "UTF-8");
	String newline = System.getProperty("line.separator");
        Random random = new Random(); 
        ArrayList<FromTo> edges = new ArrayList<FromTo>();
        ArrayList<String> inp_instr = new ArrayList<String>();
        if(args.length>0){
            try {

                File f = new File(args[0]);

                BufferedReader b = new BufferedReader(new FileReader(f));

                String readLine = "";

                System.out.println("Reading file");

                while ((readLine = b.readLine()) != null) {
                    inp_instr.add(readLine);
                }

            } catch (IOException e) {
                System.out.println("Error on file read");
            }
            
            
             for(String inp : inp_instr){
			String firstLetter = String.valueOf(inp.charAt(0));

                	if(firstLetter.contains("p")){
                    		String[] parts = inp.split(" ");				
                    		n_nodes =  Integer.parseInt(parts[2]);
	

                	}else if(firstLetter.contains("e")){
				
                    		String[] parts = inp.split(" ");
								
                    		edges.add(new FromTo(Integer.parseInt(parts[1]),Integer.parseInt(parts[2])));                   
				
                	}  
                	
			
		}
        }else{
        System.out.println("Demo mode");

           edges.add(new FromTo(1,2));
           edges.add(new FromTo(2,3));
           edges.add(new FromTo(2,4));
           edges.add(new FromTo(5,1));
           edges.add(new FromTo(6,1));
        
        
        }

        System.out.println("Please insert the number of incubations");
        epoaseis = userinp.nextInt();

        ArrayList<Node> nodes = new ArrayList<Node>();
        for(int i=0;i<n_nodes;++i){
            nodes.add(new Node());
        }
        //Set connections in all nodes 
        for(FromTo con:edges){   
  
            nodes.get(con.getFrom_node() -1).add_neigh(con.getTo_node() -1);        
        }        
        // Set random pos 
        for(Node v: nodes){

            v.setPosx(1 + (w - 1) * random.nextDouble());
            v.setPosy(1 + (h - 1) * random.nextDouble());

        }
        double k = 0.5*Math.sqrt(((float)w*h)/n_nodes);        
        //epoaseis
        for(int i = 0; i<epoaseis;++i){
            // calculate repulsive forces
            for(Node v: nodes){
                for(Node u: nodes){
                    if(v==u){
                        continue;                    
                    }
                    double dx = v.getPosx()-u.getPosx();
                    double dy = v.getPosy()-u.getPosy();
                    double distance = Math.sqrt(Math.pow(dy,2)+Math.pow(dx,2));
                    if(distance > 0){                        
                        double rep = (Math.pow(k,2))/distance;  
                        v.setDispx(v.getDispx()+((rep*dx)/distance));
                        v.setDispy(v.getDispy()+((rep*dy)/distance));
                    }                    
                }
            }
            // calculate attractive forces
            for(Node v: nodes){
                ArrayList<Integer> edg = v.getNeigh();
                if(edg.size()>0){
                    for(int index : edg){   
                        double dx = v.getPosx()-nodes.get(index).getPosx();
                        double dy = v.getPosy()-nodes.get(index).getPosy();
                        double distance = Math.sqrt(Math.pow(dy,2)+Math.pow(dx,2));
                        double atr =(Math.pow(distance,2))/k;
                        if(distance>0){
                            v.setDispx(v.getDispx()-((atr*dx)/distance));
                            v.setDispy(v.getDispy()- ((atr*dy)/distance));
                            nodes.get(index).setDispx(nodes.get(index).getDispx()+((atr*dx)/distance));
                            nodes.get(index).setDispy(nodes.get(index).getDispy()+ ((atr*dy)/distance));
                        }
                    }
                }
            }
          
            // apply temperature and frame restrictions
            for(Node v: nodes){
                v.setDispx(Math.max(Math.min(v.getDispx(),T),-T));
                v.setDispy(Math.max(Math.min(v.getDispy(),T),-T));
                v.setPosx(v.getPosx()+v.getDispx());
                v.setPosy(v.getPosy()+v.getDispy());
                //limit by frame
                v.setPosx(Math.min(w,Math.max(0,v.getPosx())));
                v.setPosy(Math.min(h,Math.max(0,v.getPosy())));
            }
            //cooldown
            T -=T/(epoaseis);
        }

        int index = 1;
        writer.println("graph G {");
        writer.println("graph [bb=\"0,0,1280,720\"];");
        writer.println("node [label=\"\\N\"];");            
        
        for(Node v:nodes){
        

            writer.println(index+" [height=1,width=1,pos=\""+v.getPosx()+","+v.getPosy()+"!\"]");    
            index+=1;
            
        }
        index = 1;
        for(Node v:nodes){
            ArrayList<Integer> edg = v.getNeigh();                
                if(edg.size()>0){
                    for(int i : edg){ 
                        writer.println(index+" -- "+(i+1)+";");    
                        
                    }
                        
                }  
            
            

            index+=1;
            
        }
        writer.println("}");
        writer.close();   
        
        System.out.println("Created file output.dot");
    }

    

}
