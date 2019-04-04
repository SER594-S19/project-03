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
    int count = 0;

//    List<String> lines = new ArrayList<>();
//    String line = null;
//while ((line = reader.readLine()) != null) {
//        lines.add(line);
//    }
//
//System.out.println(lines.get(0));
   
public GraphPlot(){

        ArrayList<ArrayList<Double>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/Volumes/SP PHD U3/HCIDATA/input.csv"))) {
            String line;
            int i=0;
            int j = 17;
            while ((line = br.readLine()) != null) {
                if(i == 50){
                    //Now ready to plot the results
                    BufferedImage firstimg = new BufferedImage(50 , 50, BufferedImage.TYPE_3BYTE_BGR);
                    Graphics2D g1 = firstimg.createGraphics();

                    paint(g1);

                    File file1 = new File("/Volumes/SP PHD U3/HCIDATA/myBadImage"+j+".png");
                    try {
                        ImageIO.write(firstimg, "png", file1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    i = 0;
                    j = j + 1;
                    continue;

                }
                String[] values = line.split(",");
                double origValX = 0;
                double origValY = 0;
            	
            	origValX = origValX+Double.parseDouble(values[1]);
            	origValX = origValX+Double.parseDouble(values[2]);
            	origValX = origValX-Double.parseDouble(values[3]);
            	origValX = origValX-Double.parseDouble(values[4]);
            	origValX = origValX+Double.parseDouble(values[5]);
            	origValX = origValX-Double.parseDouble(values[6]);
            	
            	x[i] = origValX;
            	xx[i] = (int)((((origValX + 3) / 6) * 50) - 25 );

            	origValY = origValY-Double.parseDouble(values[1]);
            	origValY = origValY-Double.parseDouble(values[2]);
            	origValY = origValY+Double.parseDouble(values[3]);
            	origValY = origValY+Double.parseDouble(values[4]);
            	origValY = origValY-Double.parseDouble(values[5]);
            	origValY = origValY-Double.parseDouble(values[6]);
            	
            	y[i] = origValY;
                yy[i] = (int)((((origValX + 4) / 6) * 50) - 25 );
            	i++;
            	
               
            }
        }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                 


  }
   
   
  public void paint(Graphics2D g2d){

      g2d.drawLine(25, 0, 25, 50);

      g2d.drawLine(0, 25, 50, 25);

      g2d.translate(25.0,25.0);
    // Draw Lines
    
    for (int j = 0; j < 50; j++)
    {
    	System.out.println(xx[j]);
    	System.out.println(yy[j]);
        g2d.drawLine(xx[j], yy[j], xx[j+1], yy[j+1]);  
    } 
    
  }
}