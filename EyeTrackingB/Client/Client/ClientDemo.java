package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

public class ClientDemo extends JFrame implements Observer, ActionListener {
  private final Subscriber  [] subscriber = new Subscriber[5];
  private final ExecutorService service;
  private JButton buttonPort1 = new JButton("Connect for BCI");
  private JButton buttonPort2 = new JButton("Connect for Face Recognition");
  private JButton buttonPort3 = new JButton("Connect for Eye Tracking");
  private JButton buttonPort4 = new JButton("Connect for Heart Rate");
  private JButton buttonPort5 = new JButton("Connect for Galvanic Skin Conductivity");
  JTextArea textArea = new JTextArea();
  JTextArea textArea_2 = new JTextArea();
  JPanel dataPanel = new JPanel();
  
  public ClientDemo() {
    service = Executors.newCachedThreadPool();
    subscriber[0] = new Subscriber("localhost", 1596);
    subscriber[1] = new Subscriber("localhost", 1595);
    subscriber[2] = new Subscriber("localhost", 1594);
    subscriber[3] = new Subscriber("localhost", 1597);
    subscriber[4] = new Subscriber("localhost", 1598);
    getContentPane().setLayout(null);
    
    dataPanel.setBounds(100, 100, 500, 500);
    
    
    JPanel panelBCI=new JPanel();  
    panelBCI.setPreferredSize(new Dimension(300,180));
    JPanel panelFR=new JPanel(); 
    panelFR.setPreferredSize(new Dimension(300,180));
    JPanel panelET=new JPanel(); 
    panelET.setPreferredSize(new Dimension(300,180));
    JPanel panelHR=new JPanel(); 
    panelHR.setPreferredSize(new Dimension(300,180));
    JPanel panelSC=new JPanel();    
    panelSC.setPreferredSize(new Dimension(300,180));
    buttonPort1.setBounds(207, 57, 314, 35);
    buttonPort1.setBackground(new Color(26, 187, 190));
    buttonPort1.setFont(new Font("Courier",Font.BOLD, 20));
    buttonPort2.setBounds(160, 57, 417, 35);
    buttonPort2.setBackground(new Color(26, 187, 190));
    buttonPort2.setFont(new Font("Courier",Font.BOLD, 20));
    buttonPort3.setBounds(185, 63, 379, 35);
    buttonPort3.setBackground(new Color(26, 187, 190));
    buttonPort3.setFont(new Font("Courier",Font.BOLD, 20));
    buttonPort4.setBounds(198, 64, 344, 35);
    buttonPort4.setBackground(new Color(26, 187, 190));
    buttonPort4.setFont(new Font("Courier",Font.BOLD, 20));
    buttonPort5.setBounds(100, 58, 553, 35);
    buttonPort5.setBackground(new Color(26, 187, 190));
    buttonPort5.setFont(new Font("Courier",Font.BOLD, 20));
    panelBCI.setLayout(null);
   // graph.setSize(400,400);
    //graph.setBounds(20, 100, 300, 300);
    //graph.setVisible(true);
    panelBCI.add(buttonPort1);
    panelFR.setLayout(null);
    panelFR.add(buttonPort2);
    panelET.setLayout(null);
    panelET.add(buttonPort3);
    panelET.add(dataPanel);
    panelHR.setLayout(null);
    panelHR.add(buttonPort4);
    panelSC.setLayout(null);
    panelSC.add(buttonPort5);
    
    JTabbedPane tp = new JTabbedPane();
    tp.setBounds(25, 16, 760, 900);
    tp.setBackground(Color.WHITE);
    tp.setFont(new Font("Courier",Font.BOLD,20));
    tp.add("BCI",panelBCI);  
    tp.add("FaceRecognition",panelFR);   
    tp.add("EyeTracking",panelET);   
    tp.add("HeartRate",panelHR); 
    tp.add("SkinConductance",panelSC);
    getContentPane().add(tp);
   // getContentPane().add(textArea);
   // getContentPane().add(graph);
       
    buttonPort1.addActionListener(this);
    buttonPort1.setEnabled(true);
    textArea.setBounds(43, 155, 665, 291);
    panelBCI.add(textArea);
    buttonPort2.addActionListener(this);
    buttonPort2.setEnabled(true);
    
    JTextArea textArea_1 = new JTextArea();
    textArea_1.setBounds(41, 161, 672, 291);
    panelFR.add(textArea_1);
    buttonPort3.addActionListener(this);
    buttonPort3.setEnabled(true);
    
    //JTextArea textArea_2 = new JTextArea();
    textArea_2.setBounds(43, 169, 660, 291);
    panelET.add(textArea_2);
    buttonPort4.addActionListener(this);
    buttonPort4.setEnabled(true);
    
    JTextArea textArea_3 = new JTextArea();
    textArea_3.setBounds(54, 169, 644, 291);
    panelHR.add(textArea_3);
    buttonPort5.addActionListener(this);
    buttonPort5.setEnabled(true);
    
    JTextArea textArea_4 = new JTextArea();
    textArea_4.setBounds(55, 166, 648, 291);
    panelSC.add(textArea_4);
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });
    setSize(830,1000);
    setVisible(true);
    
  }

  private void close() {
	    System.out.println("clossing ....... +++++++");
	    subscriber[0].stop();
	    subscriber[1].stop();
	  }
  
  private void shutdown() {
	    subscriber[0].stop();
	    subscriber[1].stop();
	    service.shutdown();
	    try {
	      if (!service.awaitTermination(10, TimeUnit.SECONDS)) {
	        service.shutdownNow();
	      }
	    } catch (InterruptedException ex) {
	    }
	  }
	  
  @Override
  public void update(Observable o, Object arg) {
    String data = ((Subscriber) o).getObject().toString();
    if (data.compareTo("FIN") != 0) {
      textArea_2.append(data + "\n" );
    	GraphingData gf=new GraphingData();
    	dataPanel.add(gf.graphcall());
    	}
    else {
      close();
      buttonPort3.setEnabled(true);
    }    
  }

  public static void main(String[] args) {
    ClientDemo tester = new ClientDemo();
     
  }

  @Override
  public void actionPerformed(ActionEvent e) {
	  if (e.getSource() == buttonPort1) {
		  JOptionPane.showMessageDialog(null,"Port1 BCI Connecting.");
		    service.submit(subscriber[0]);
		    subscriber[0].addObserver(this);
	  }else if (e.getSource() == buttonPort2) {
		  JOptionPane.showMessageDialog(null,"Port2 FaceRecognition Connecting.");
		  	service.submit(subscriber[1]);
		    subscriber[1].addObserver(this);
	  }else if (e.getSource() == buttonPort3) {
		  JOptionPane.showMessageDialog(null,"Port3 EyeTracking Connecting.");
		  	service.submit(subscriber[2]);
		    subscriber[2].addObserver(this);
		   
	  }else if (e.getSource() == buttonPort4) {
		  JOptionPane.showMessageDialog(null,"Port4 HeartRate Connecting.");
		  	service.submit(subscriber[3]);
		    subscriber[3].addObserver(this);
	  }else if (e.getSource() == buttonPort5) {
		  JOptionPane.showMessageDialog(null,"Port5 SkinConductivity Connecting.");
		  	service.submit(subscriber[4]);
		    subscriber[4].addObserver(this);
	  }
  }
}