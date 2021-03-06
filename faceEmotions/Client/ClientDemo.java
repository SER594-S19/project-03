package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ClientDemo extends JFrame implements Observer, ActionListener {

  public static HashMap<Integer,String> port_panel_mapping = new HashMap<Integer,String>();
  private final Subscriber [] subscriber = new Subscriber[5];
  private final ExecutorService service;
  JTextArea panel1Text = new JTextArea(" ", 20, 30);
  JTextArea panel2Text = new JTextArea(" ", 30, 30);
  JTextArea panel3Text = new JTextArea(" ", 30, 30);
  JTextArea panel4Text = new JTextArea(" ", 30, 30);
  JTextArea panel5Text = new JTextArea(" ", 30, 30);

  private JTabbedPane tabbedPane1 = new JTabbedPane();
  private String ipFace;
  private String ipEyes;
  private String ipSkin;
  private String ipHeart;
  private String ipBCI;
  private String port_face;
  private String port_eyes;
  private String port_skin;
  private String port_heart;
  private String port_bci;
  private boolean connect_status1 = false;
  private boolean connect_status2 = false;
  private boolean connect_status3 = false;
  private boolean connect_status4 = false;
  private boolean connect_status5 = false;


  private JFrame frame = new JFrame();
  private JButton buttonConnect1 = new JButton("connect");
  private JButton buttonConnect2 = new JButton("connect");
  private JButton buttonConnect3 = new JButton("connect");
  private JButton buttonConnect4 = new JButton("connect");
  private JButton buttonConnect5 = new JButton("connect");
  private JComponent panel1 = makeTextPanel1("Face");
  private JComponent panel2 = makeTextPanel2("Eyes");
  private JComponent panel3 = makeTextPanel3("Skin");
  private JComponent panel4 = makeTextPanel4("Heart Rate");
  private JComponent panel5 = makeTextPanel5("BCI");
  
  
  String filePath = "C:/Users/subhr/Desktop/Desktop_Folders/SER594/input.csv";
  File file = new File(filePath);
  
  FileWriter outputfile;
  
  //  private JComponent panel1 = makeTextPanel("Panel #1");
//  tabbedPane.add
  public ClientDemo() {

    service = Executors.newCachedThreadPool();

    setLayout(new BorderLayout());
    //frame.add(textArea, BorderLayout.CENTER);


    panel1.add(panel1Text, BorderLayout.SOUTH);
    panel2.add(panel2Text, BorderLayout.SOUTH);
    panel3.add(panel3Text, BorderLayout.SOUTH);
    panel4.add(panel4Text, BorderLayout.SOUTH);
    panel5.add(panel5Text, BorderLayout.SOUTH);

    frame.add(tabbedPane1);
    tabbedPane1.addTab("Face",panel1);
    tabbedPane1.addTab("Eyes",panel2);
    tabbedPane1.addTab("Skin",panel3);
    tabbedPane1.addTab("Heart Rate",panel4);
    tabbedPane1.addTab("BCI",panel5);
    frame.setSize(500,500);
    frame.setVisible(true);


  }

  protected JComponent makeTextPanel1(String text) {
	buttonConnect1.setName("1");
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    JTextField textField = new JTextField();
    textField.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);

    // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect1);
    
    ipFace = textField.getText();
    port_face = spinner.getValue().toString();
    subscriber[0] = new Subscriber(ipFace, Integer.parseInt(port_face));
    Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_face), "textPanel1");
    
    spinner.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner spinner = (JSpinner) e.getSource();
            ipFace = textField.getText();
            port_face = spinner.getValue().toString();
            subscriber[0] = new Subscriber(ipFace, Integer.parseInt(port_face));
            Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_face), "textPanel1");
            System.out.println("New port is " + port_face);
        }
    });
    
    buttonConnect1.addActionListener(this);

    /*buttonConnect1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipFace = textField.getText();
        port_face = spinner.getValue().toString();
        subscriber[0] = new Subscriber(ipFace, Integer.parseInt(port_face));
        Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_face), "textPanel1");
        // To add for loop to connect other ports.

        subscriber[0] = new Subscriber(ipFace, Integer.parseInt(port_face));

      }
    });*/
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {

        shutdown();
        System.exit(0);

      }
    });
    return panel;
  }

  protected JComponent makeTextPanel2(String text) {
	  buttonConnect2.setName("2");
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    JTextField textField = new JTextField();
    textField.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);
    // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect2);
    
    port_eyes = spinner.getValue().toString();
    ipEyes = textField.getText();
    subscriber[1] = new Subscriber(ipEyes, Integer.parseInt(port_eyes));
    Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_eyes), "textPanel2");
    
    
    spinner.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner spinner = (JSpinner) e.getSource();
            port_eyes = spinner.getValue().toString();
            ipEyes = textField.getText();
            subscriber[1] = new Subscriber(ipEyes, Integer.parseInt(port_eyes));
            Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_eyes), "textPanel2");
            System.out.println("New port is " + port_eyes);
        }
    });
    
    buttonConnect2.addActionListener(this);

    /*buttonConnect2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipEyes = textField.getText();
        port_eyes = spinner.getValue().toString();
        subscriber[1] = new Subscriber(ipEyes, Integer.parseInt(port_eyes));
        Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_eyes), "textPanel2");
      }
    });*/
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {

        shutdown();
        System.exit(0);

      }
    });
    return panel;
  }
  protected JComponent makeTextPanel3(String text) {
	  buttonConnect3.setName("3");
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    JTextField textField = new JTextField();
    textField.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);

    // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect3);
    
    ipSkin = textField.getText();
    port_skin = spinner.getValue().toString();
    subscriber[2] = new Subscriber(ipSkin, Integer.parseInt(port_skin));
    Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_skin), "textPanel3");
    
    spinner.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner spinner = (JSpinner) e.getSource();
            ipSkin = textField.getText();
            port_skin = spinner.getValue().toString();
            subscriber[2] = new Subscriber(ipSkin, Integer.parseInt(port_skin));
            Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_skin), "textPanel3");
            System.out.println("New port is " + port_skin);
        }
    });
    
    
    buttonConnect3.addActionListener(this);
    
    
    /*buttonConnect3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipSkin = textField.getText();
        port_skin = spinner.getValue().toString();
        subscriber[2] = new Subscriber(ipSkin, Integer.parseInt(port_skin));
        Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_skin), "textPanel3");
      }
    });*/
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {

        shutdown();
        System.exit(0);

      }
    });
    return panel;
  }
  protected JComponent makeTextPanel4(String text) {
	  buttonConnect4.setName("4");
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    JTextField textField = new JTextField();
    textField.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);

    // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect4);
    
    ipHeart = textField.getText();
    port_heart =spinner.getValue().toString();
    subscriber[3] = new Subscriber(ipHeart, Integer.parseInt(port_heart));
    Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_heart), "textPanel4");
    
    spinner.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner spinner = (JSpinner) e.getSource();
            ipHeart = textField.getText();
            port_heart =spinner.getValue().toString();
            subscriber[3] = new Subscriber(ipHeart, Integer.parseInt(port_heart));
            Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_heart), "textPanel4");
            System.out.println("New port is " + port_heart);
        }
    });
    
    buttonConnect4.addActionListener(this);
    /*buttonConnect4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipHeart = textField.getText();
        port_heart =spinner.getValue().toString();
        subscriber[3] = new Subscriber(ipHeart, Integer.parseInt(port_heart));
        Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_heart), "textPanel4");
      }
    });*/
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {

        shutdown();
        System.exit(0);

      }
    });
    return panel;
  }
  protected JComponent makeTextPanel5(String text) {
	  buttonConnect5.setName("5");
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel("Enter IP address of Server ");
    filler.setHorizontalAlignment(JLabel.LEFT);
    JLabel filler1 = new JLabel("Select Port Number ");
    filler1.setHorizontalAlignment(JLabel.LEFT);
    JTextField textField = new JTextField();
    textField.setColumns(8);
    String[] portNumber = {"1594","1595","1596","1597","1598"};
    SpinnerListModel portModel = new SpinnerListModel(portNumber);
    JSpinner spinner = new JSpinner(portModel);




    // panel.setLayout(new GridLayout(4, 4));
    panel.add(filler);
    panel.add(textField);
    panel.add(filler1);
    panel.add(spinner);
    panel.add(buttonConnect5);
    
    ipBCI = textField.getText();
    port_bci = spinner.getValue().toString();
    subscriber[4] = new Subscriber(ipBCI, Integer.parseInt(port_bci));
    Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_bci), "textPanel5");
    
    spinner.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner spinner = (JSpinner) e.getSource();
            ipBCI = textField.getText();
            port_bci = spinner.getValue().toString();
            subscriber[4] = new Subscriber(ipBCI, Integer.parseInt(port_bci));
            Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_bci), "textPanel5");
            System.out.println("New port is " + port_bci);
        }
    });
    
    buttonConnect5.addActionListener(this);
    
    
    /*buttonConnect5.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ipBCI = textField.getText();
        port_bci = spinner.getValue().toString();
        subscriber[4] = new Subscriber(ipBCI, Integer.parseInt(port_bci));
        Client.ClientDemo.port_panel_mapping.put(Integer.parseInt(port_bci), "textPanel5");
      }
    });*/
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {

        shutdown();
        System.exit(0);

      }
    });
    return panel;
  }

  private void close() {
    System.out.println("clossing ....... +++++++");
    subscriber[0].stop();
    subscriber[1].stop();
    subscriber[2].stop();
    subscriber[3].stop();
    subscriber[4].stop();
  }

  private void shutdown() {
    subscriber[0].stop();
    subscriber[1].stop();
    subscriber[2].stop();
    subscriber[3].stop();
    subscriber[4].stop();

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
    int portNo = ((Subscriber) o).getPort();
    String data = ((Subscriber) o).getObject().toString();
    //double[] dataOrig= ((Subscriber) o).getObject();
    if (data.compareTo("FIN") != 0) {
    	
    	checkPaneltoAppend(portNo, data);
    	
		try {
			if (outputfile == null) {
			  outputfile = new FileWriter(file);
			}
			CSVUtils.writeLine1(outputfile, data);
		
  
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	
    	/*if(portNo == 1594 && connect_status1){
        checkPaneltoAppend(portNo, data);
      }
      else if(portNo == 1595 && connect_status2){
        checkPaneltoAppend(portNo, data);
      }
      else if(portNo == 1596 && connect_status3){
        checkPaneltoAppend(portNo, data);
      }
      else if(portNo == 1597 && connect_status4){
        checkPaneltoAppend(portNo, data);
      }
      else if(portNo == 1598 && connect_status5){
        checkPaneltoAppend(portNo, data);
      }*/

    }
    else {
	    try {
			outputfile.flush();
			outputfile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
      close();
      buttonConnect1.setEnabled(true);
      buttonConnect2.setEnabled(true);
      buttonConnect3.setEnabled(true);
      buttonConnect4.setEnabled(true);
      buttonConnect5.setEnabled(true);

    }
  }

  private void checkPaneltoAppend(int portNo, String data) {
    switch (Client.ClientDemo.port_panel_mapping.get(portNo)){
      case "textPanel1":
        panel1Text.append(data + "\n" );
        break;
      case "textPanel2":
        panel2Text.append(data + "\n" );
        break;
      case "textPanel3":
        panel3Text.append(data + "\n" );
        break;
      case "textPanel4":
        panel4Text.append(data + "\n" );
        break;
      case "textPanel5":
        panel5Text.append(data + "\n" );
        break;
    }
  }

  public static void main(String[] args) {
    Client.ClientDemo tester = new Client.ClientDemo();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == buttonConnect1) {
    	
    	connect_status1 = !connect_status1;
    	
      if(connect_status1)
      {
    	  
    	  buttonConnect1.setText("DisConnect");
    	  service.submit(subscriber[0]);
          subscriber[0].addObserver(this);
    	  
      }
      else
      {
    	  myFunc();
    	  buttonConnect1.setText("Connect");
      }
      
      
    }else if (e.getSource() == buttonConnect2) {
    	
    	connect_status2 = !connect_status2;
      
      if(connect_status2)
      {
    	  
    	  buttonConnect2.setText("DisConnect");
    	  service.submit(subscriber[1]);
          subscriber[1].addObserver(this);
    	  
      }
      else
      {
    	  myFunc();
    	  buttonConnect2.setText("Connect");
      }
      
      
    }else if (e.getSource() == buttonConnect3) {
    	
    	connect_status3 = !connect_status3;
    	
    	if(connect_status3)
    	{
    		  buttonConnect3.setText("DisConnect");
    	      service.submit(subscriber[2]);
    	      subscriber[2].addObserver(this);
    		
    	}
    	   else
    	      {
    	    	  
    	    	  buttonConnect3.setText("Connect");
    	      }

    }else if (e.getSource() == buttonConnect4) {
    	
    	connect_status4 = !connect_status4;
    	
    	if(connect_status4) {
    		
    		  buttonConnect4.setText("DisConnect");
    	      service.submit(subscriber[3]);
    	      subscriber[3].addObserver(this);
    		
    		
    	}
    	 else
	      {
	    	  
	    	  buttonConnect4.setText("Connect");
	      }
    
    }else if (e.getSource() == buttonConnect5) {
    	
    	connect_status5 = !connect_status5;
    	
    	if(connect_status5)
    	{	
    		  buttonConnect5.setText("DisConnect");
    	      service.submit(subscriber[4]);
    	      subscriber[4].addObserver(this);
    	}
   	 else
     {
   	  
   	  buttonConnect5.setText("Connect");
     }

    }


  }
  
  public void myFunc() {
	  
	GraphPlot plot = new GraphPlot();
	plot.setLayout(null);
	JFrame frame = new JFrame();
	frame.setBackground(Color.white);
	 
	frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	frame.addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent we) {
	System.exit(0);
	}
	});
	frame.getContentPane().add(plot);
	plot.updateUI();
	frame.setSize(300,400);
	frame.setVisible(true);
  
  } 
}