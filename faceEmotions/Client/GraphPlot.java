package Client;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.util.Arrays;

class GraphPlot extends JPanel{
    double[] x = new double[100];
    double[] y = new double[100];
    int size = x.length;
    int[] xx = new int[size];
    int[] yy = new int[size];
   
public GraphPlot(){
 
        ArrayList<ArrayList<Double>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/subhr/Desktop/Desktop_Folders/SER594/input.csv"))) {
            String line;
            int i=0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double origValX = 0;
                double origValY = 0;
            	
            	origValX = origValX+Double.parseDouble(values[1]);
            	origValX = origValX+Double.parseDouble(values[2]);
            	origValX = origValX-Double.parseDouble(values[3]);
            	origValX = origValX-Double.parseDouble(values[4]);
            	origValX = origValX-Double.parseDouble(values[5]);
            	origValX = origValX-Double.parseDouble(values[6]);
            	
            	x[i] = origValX;
            	xx[i] = 20+(int)(origValX*100)/5;
       
            	origValY = origValY-Double.parseDouble(values[1]);
            	origValY = origValY-Double.parseDouble(values[2]);
            	origValY = origValY+Double.parseDouble(values[3]);
            	origValY = origValY+Double.parseDouble(values[4]);
            	origValY = origValY-Double.parseDouble(values[5]);
            	origValY = origValY-Double.parseDouble(values[6]);
            	
            	y[i] = origValY;
            	yy[i] = 100+(int)(origValY*100)/5;
            	
            	i++;
            	
               
            }
        }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                 
        //Now ready to plot the results
        BufferedImage firstimg = new BufferedImage(300 , 400, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g1 = firstimg.createGraphics();
        
        paint(g1);  
        
        File file1 = new File("C:/Users/subhr/Desktop/Desktop_Folders/SER594/myimage.png");
        try {
    		ImageIO.write(firstimg, "png", file1);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}

  }
   
   
  public void paint(Graphics g2d){   

     
    g2d.drawLine(120, 100, 120, 300);
     
    g2d.drawLine(20, 200, 220, 200);
     
    // Draw Lines
    
    for (int j = 0; j < size-1; j++)
    {
    	System.out.println(xx[j]);
    	System.out.println(yy[j]);
        g2d.drawLine(xx[j], yy[j], xx[j+1], yy[j+1]);  
    } 
    
  }
}