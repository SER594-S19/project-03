package Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClientDemo extends JFrame implements Observer, ActionListener {

  private final Subscriber  [] subscriber = new Subscriber[5];
  private final ExecutorService service;

  //JTextField myTextField = new JTextField("Team Awesome!");


  private JTextArea textArea = new JTextArea();
  private JTextArea ipNum1 = new JTextArea(1,10);
  private JTextArea portNum1 = new JTextArea(1,5);
  private JButton buttonConnect1 = new JButton("Connect");
  private JTextArea ipNum2 = new JTextArea(1,10);
  private JTextArea portNum2 = new JTextArea(1,5);
  private JButton buttonConnect2 = new JButton("Connect");
  private JTextArea ipNum3 = new JTextArea(1,10);
  private JTextArea portNum3 = new JTextArea(1,5);
  private JButton buttonConnect3 = new JButton("Connect");
  private JTextArea ipNum4 = new JTextArea(1,10);
  private JTextArea portNum4 = new JTextArea(1,5);
  private JButton buttonConnect4 = new JButton("Connect");
  private JTextArea ipNum5 = new JTextArea(1,10);
  private JTextArea portNum5 = new JTextArea(1,5);
  private JButton buttonConnect5 = new JButton("Connect");
  private JPanel popup = new JPanel();

  public ClientDemo() {

    service = Executors.newCachedThreadPool();

    // TO TEST, RUN TWO SERVERS IN PORTS 1594 and 1595

    //subscriber[0] = new Subscriber("localhost", 1594);
    //subscriber[1] = new Subscriber("localhost", 1595);

    JLabel ipLabel1 = new JLabel("IP Address: ");
    JLabel portLabel1 = new JLabel("Port Number: ");
    JLabel ipLabel2 = new JLabel("IP Address: ");
    JLabel portLabel2 = new JLabel("Port Number: ");
    JLabel ipLabel3 = new JLabel("IP Address: ");
    JLabel portLabel3 = new JLabel("Port Number: ");
    JLabel ipLabel4 = new JLabel("IP Address: ");
    JLabel portLabel4 = new JLabel("Port Number: ");
    JLabel ipLabel5 = new JLabel("IP Address: ");
    JLabel portLabel5 = new JLabel("Port Number: ");
    JPanel topPanel= new JPanel();
    topPanel.setLayout(new GridLayout(5,5,10,10));

    JPanel inputPanel1 = new JPanel(new BorderLayout());
    JPanel ipPanel1 = new JPanel(new BorderLayout());
    JPanel portPanel1 = new JPanel(new BorderLayout());

    //ipNum1.setLineWrap(true);
    //ipNum1.setPreferredSize(new Dimension(10, 10));

    portNum1.setBorder(BorderFactory.createLineBorder(Color.black));
    ipNum1.setBorder(BorderFactory.createLineBorder(Color.black));

    JPanel inputPanel2 = new JPanel(new BorderLayout());
    JPanel ipPanel2 = new JPanel(new BorderLayout());
    JPanel portPanel2 = new JPanel(new BorderLayout());

    portNum2.setBorder(BorderFactory.createLineBorder(Color.black));
    ipNum2.setBorder(BorderFactory.createLineBorder(Color.black));

    JPanel inputPanel3 = new JPanel(new BorderLayout());
    JPanel ipPanel3 = new JPanel(new BorderLayout());
    JPanel portPanel3 = new JPanel(new BorderLayout());

    portNum3.setBorder(BorderFactory.createLineBorder(Color.black));
    ipNum3.setBorder(BorderFactory.createLineBorder(Color.black));

    JPanel inputPanel4 = new JPanel(new BorderLayout());
    JPanel ipPanel4 = new JPanel(new BorderLayout());
    JPanel portPanel4 = new JPanel(new BorderLayout());

    portNum4.setBorder(BorderFactory.createLineBorder(Color.black));
    ipNum4.setBorder(BorderFactory.createLineBorder(Color.black));

    JPanel inputPanel5 = new JPanel(new BorderLayout());
    JPanel ipPanel5 = new JPanel(new BorderLayout());
    JPanel portPanel5 = new JPanel(new BorderLayout());

    portNum5.setBorder(BorderFactory.createLineBorder(Color.black));
    ipNum5.setBorder(BorderFactory.createLineBorder(Color.black));

    setLayout(new BorderLayout());

    ipPanel1.add(ipLabel1,BorderLayout.WEST);
    ipPanel1.add(ipNum1,BorderLayout.CENTER);
    ipPanel1.setBorder(new EmptyBorder(5, 5, 5, 5));

    portPanel1.add(portLabel1,BorderLayout.WEST);
    portPanel1.add(portNum1,BorderLayout.CENTER);
    portPanel1.setBorder(new EmptyBorder(5, 5, 5, 5));

    ipPanel2.add(ipLabel2,BorderLayout.WEST);
    ipPanel2.add(ipNum2,BorderLayout.CENTER);
    ipPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));

    portPanel2.add(portLabel2,BorderLayout.WEST);
    portPanel2.add(portNum2,BorderLayout.CENTER);
    portPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));

    ipPanel3.add(ipLabel3,BorderLayout.WEST);
    ipPanel3.add(ipNum3,BorderLayout.CENTER);
    ipPanel3.setBorder(new EmptyBorder(5, 5, 5, 5));

    portPanel3.add(portLabel3,BorderLayout.WEST);
    portPanel3.add(portNum3,BorderLayout.CENTER);
    portPanel3.setBorder(new EmptyBorder(5, 5, 5, 5));

    ipPanel4.add(ipLabel4,BorderLayout.WEST);
    ipPanel4.add(ipNum4,BorderLayout.CENTER);
    ipPanel4.setBorder(new EmptyBorder(5, 5, 5, 5));

    portPanel4.add(portLabel4,BorderLayout.WEST);
    portPanel4.add(portNum4,BorderLayout.CENTER);
    portPanel4.setBorder(new EmptyBorder(5, 5, 5, 5));

    ipPanel5.add(ipLabel5,BorderLayout.WEST);
    ipPanel5.add(ipNum5,BorderLayout.CENTER);
    ipPanel5.setBorder(new EmptyBorder(5, 5, 5, 5));

    portPanel5.add(portLabel5,BorderLayout.WEST);
    portPanel5.add(portNum5,BorderLayout.CENTER);
    portPanel5.setBorder(new EmptyBorder(5, 5, 5, 5));

    //northPanel.add(ipPanel, BorderLayout.NORTH);
    //northPanel.add(portPanel, BorderLayout.CENTER);
    inputPanel1.setBorder(new EmptyBorder(5, 5, 5, 5));
    inputPanel1.add(ipPanel1,BorderLayout.WEST);
    inputPanel1.add(portPanel1,BorderLayout.CENTER);
    inputPanel1.add(buttonConnect1,BorderLayout.EAST);


    inputPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
    inputPanel2.add(ipPanel2,BorderLayout.WEST);
    inputPanel2.add(portPanel2,BorderLayout.CENTER);
    inputPanel2.add(buttonConnect2,BorderLayout.EAST);

    inputPanel3.setBorder(new EmptyBorder(5, 5, 5, 5));
    inputPanel3.add(ipPanel3,BorderLayout.WEST);
    inputPanel3.add(portPanel3,BorderLayout.CENTER);
    inputPanel3.add(buttonConnect3,BorderLayout.EAST);

    inputPanel4.setBorder(new EmptyBorder(5, 5, 5, 5));
    inputPanel4.add(ipPanel4,BorderLayout.WEST);
    inputPanel4.add(portPanel4,BorderLayout.CENTER);
    inputPanel4.add(buttonConnect4,BorderLayout.EAST);

    inputPanel5.setBorder(new EmptyBorder(5, 5, 5, 5));
    inputPanel5.add(ipPanel5,BorderLayout.WEST);
    inputPanel5.add(portPanel5,BorderLayout.CENTER);
    inputPanel5.add(buttonConnect5,BorderLayout.EAST);


    textArea.setEditable(false); // set textArea non-editable
    JScrollPane scroll = new JScrollPane(textArea);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

//    topPanel.add(inputPanel1);
//    topPanel.add(inputPanel2);
//    topPanel.add(inputPanel3);
//    topPanel.add(inputPanel4);
//    topPanel.add(inputPanel5);


    topPanel.add(ipPanel1);
    topPanel.add(portPanel1);
    topPanel.add(buttonConnect1);
    topPanel.add(ipPanel2);
    topPanel.add(portPanel2);
    topPanel.add(buttonConnect2);
    topPanel.add(ipPanel3);
    topPanel.add(portPanel3);
    topPanel.add(buttonConnect3);
    topPanel.add(ipPanel4);
    topPanel.add(portPanel4);
    topPanel.add(buttonConnect4);
    topPanel.add(ipPanel5);
    topPanel.add(portPanel5);
    topPanel.add(buttonConnect5);


    add(topPanel, BorderLayout.NORTH);
    add(scroll, BorderLayout.CENTER);

    //add(buttonConnect, BorderLayout.SOUTH);

    buttonConnect1.addActionListener(this);
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        //shutdown();
        System.exit(0);
      }
    });
    setSize(500,500);
    setVisible(true);

    buttonConnect2.addActionListener(this);
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        //shutdown();
        System.exit(0);
      }
    });
    setSize(500,500);
    setVisible(true);

  }

  private void close() {
    System.out.println("clossing ....... +++++++");
    for (int i=0;i<5;i++) {
      subscriber[i].stop();
    }
  }

  private void shutdown() {
    for (int i=0;i<5;i++) {
      subscriber[i].stop();
    }
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
    if (data.compareTo("FIN") != 0)
      textArea.append(data + "\n" );
    else {
      close();
      buttonConnect1.setEnabled(true);
    }
  }

  public static void main(String[] args) {
    ClientDemo tester = new ClientDemo();
    tester.setPreferredSize(new Dimension(500,500));
    tester.setMinimumSize(frame.getPreferredSize());

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int ports;
    String ips;


    if (e.getSource() == buttonConnect1) {
      if (buttonConnect1.getText().compareTo("Connect") == 0) {
        System.out.println("start");

        if(ipNum1.getText().compareTo("")==0)
        {
          JOptionPane.showMessageDialog(null,"Please enter a valid IP Address.");
          return;
        }

        try{
        ports = Integer.parseInt(portNum1.getText());
        }
        catch(Exception e1){
          JOptionPane.showMessageDialog(null,"Please enter a valid port number.");
          return;
        }

        ips = ipNum1.getText();



//        if(portNum1.getText().compareTo("") == 0)
//          JOptionPane.showMessageDialog(popup, "Insert a valid IP Address","Error",JOptionPane.PLAIN_MESSAGE);
//        //dataGen();
        subscriber[0] = new Subscriber(ips, ports);
        service.submit(subscriber[0]);
        subscriber[0].addObserver(this);        
        buttonConnect1.setText("Disconnect");
      } else if (buttonConnect1.getText().compareTo("Disconnect") == 0) {
        System.out.println("stop");
        subscriber[0].deleteObserver(this);
        buttonConnect1.setText("Connect");
      }
    }

    if (e.getSource() == buttonConnect2) {
      if (buttonConnect2.getText().compareTo("Connect") == 0) {
        System.out.println("start");
        
        if(ipNum2.getText().compareTo("")==0)
        {
          JOptionPane.showMessageDialog(null,"Please enter a valid IP Address.");
          return;
        }

        try{
        ports = Integer.parseInt(portNum2.getText());
        }
        catch(Exception e1){
          JOptionPane.showMessageDialog(null,"Please enter a valid port number.");
          return;
        }
        ports = Integer.parseInt(portNum2.getText());
        ips = ipNum2.getText();
        //dataGen();
        subscriber[1] = new Subscriber(ips, ports);
        service.submit(subscriber[1]);
        subscriber[1].addObserver(this);
        buttonConnect2.setText("Disconnect");
      } else if (buttonConnect2.getText().compareTo("Disconnect") == 0) {
        System.out.println("stop");
        subscriber[1].deleteObserver(this);
        buttonConnect2.setText("Connect");
      }
    }

    if (e.getSource() == buttonConnect3) {
      if (buttonConnect3.getText().compareTo("Connect") == 0) {
        System.out.println("start");
        if(ipNum3.getText().compareTo("")==0)
        {
          JOptionPane.showMessageDialog(null,"Please enter a valid IP Address.");
          return;
        }

        try{
        ports = Integer.parseInt(portNum3.getText());
        }
        catch(Exception e1){
          JOptionPane.showMessageDialog(null,"Please enter a valid port number.");
          return;
        }
        ports = Integer.parseInt(portNum3.getText());
        ips = ipNum3.getText();
        //dataGen();
        subscriber[2] = new Subscriber(ips, ports);
        service.submit(subscriber[2]);
        subscriber[2].addObserver(this);
        buttonConnect3.setText("Disconnect");
      } else if (buttonConnect3.getText().compareTo("Disconnect") == 0) {
        System.out.println("stop");
        subscriber[2].deleteObserver(this);
        buttonConnect3.setText("Connect");
      }
    }

    if (e.getSource() == buttonConnect4) {
      if (buttonConnect4.getText().compareTo("Connect") == 0) {
        System.out.println("start");
        if(ipNum4.getText().compareTo("")==0)
        {
          JOptionPane.showMessageDialog(null,"Please enter a valid IP Address.");
          return;
        }

        try{
        ports = Integer.parseInt(portNum4.getText());
        }
        catch(Exception e1){
          JOptionPane.showMessageDialog(null,"Please enter a valid port number.");
          return;
        }
        ports = Integer.parseInt(portNum4.getText());
        ips = ipNum4.getText();
        //dataGen();
        subscriber[3] = new Subscriber(ips, ports);
        service.submit(subscriber[3]);
        subscriber[3].addObserver(this);
        buttonConnect4.setText("Disconnect");
      } else if (buttonConnect4.getText().compareTo("Disconnect") == 0) {
        System.out.println("stop");
        subscriber[3].deleteObserver(this);
        buttonConnect4.setText("Connect");
      }
    }

    if (e.getSource() == buttonConnect5) {
      if (buttonConnect5.getText().compareTo("Connect") == 0) {
        System.out.println("start");
        if(ipNum5.getText().compareTo("")==0)
        {
          JOptionPane.showMessageDialog(null,"Please enter a valid IP Address.");
          return;
        }

        try{
        ports = Integer.parseInt(portNum5.getText());
        }
        catch(Exception e1){
          JOptionPane.showMessageDialog(null,"Please enter a valid port number.");
          return;
        }
        ports = Integer.parseInt(portNum5.getText());
        ips = ipNum5.getText();
        //dataGen();
        subscriber[4] = new Subscriber(ips, ports);
        service.submit(subscriber[4]);
        subscriber[4].addObserver(this);
        buttonConnect5.setText("Disconnect");
      } else if (buttonConnect5.getText().compareTo("Disconnect") == 0) {
        System.out.println("stop");
        subscriber[4].deleteObserver(this);
        buttonConnect5.setText("Connect");
      }
    }

  }

}
