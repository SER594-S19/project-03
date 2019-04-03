package BCI.Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;
import java.util.HashMap;
import ClientHeartRateTeam.FileOutputObserver;

public class NewSubscriber extends Observable implements Runnable {

	private boolean stop;
	private String Ip;
	private int port;
	private String data;
	private Writer file;
	private int num;
	private HashMap<Integer, Integer> map;

	NewSubscriber(String Ip, int port) {
		this.stop=false;
		this.Ip = Ip;
		this.port = port;
		this.num=0;
		this.map= new HashMap<>();
	}

	public String getIp() {
		return Ip;
	}

	public void setIp(String ip) {
		Ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	protected synchronized void setData(String data) {
		this.data = data;
	}

	public synchronized Object getObject() {
		return this.data;
	}

	public Writer getFile() {
		return file;
	}

	public void setFile(Writer file) {
		this.file = file;
	}

	public void stop() {
		stop = true;
	}

	@Override
	public void run() {
		Socket client = null;
		ObjectInputStream ois = null;
		BufferedReader input = null;
		stop = false;
		String measureLocal;
		boolean serverCheck = false;
		boolean serverRunning = false;


		try {
			client = new Socket(InetAddress.getByName(Ip.trim()), port);
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			client.setSoTimeout(1000);
			if(!map.containsKey(port)){
				map.put(port,1);
			}else{
				map.put(port,map.get(port)+1);
			}
			for(Integer p:map.keySet()) {
				num=Math.max(num,map.get(p));
			}

			file = new Writer(num);
			this.addObserver(file);
			serverCheck = true;
			serverRunning = true;
		} catch (IOException ex) {
			stop = true;
		}

		while (!stop) {
			try {
				measureLocal = input.readLine();
			} catch (IOException sce) {
				measureLocal = null;
			}
			if (measureLocal == null) {
				stop = true;
				serverRunning = false;
			} else {
				System.out.println(measureLocal);
				setData(measureLocal);
				if(measureLocal.contains("Channel 5")) {
					String[] tokens=measureLocal.split(":");
					PADCalculator.pleasure=Double.parseDouble(tokens[1]);
					VectorProject2.vectorSeries.remove(0);
					VectorProject2.vectorSeries.add(0, 0, PADCalculator.pleasure, PADCalculator.arousal);
					if(PADCalculator.pleasure > 0.5 && PADCalculator.arousal <0.5) {
						PlotPanel.getIntance().setHappyFace();
						
					}
					if(PADCalculator.pleasure > 0.5 && PADCalculator.arousal >0.5) {
						PlotPanel.getIntance().setNeutralFace();
					}
					if(PADCalculator.pleasure < 0.5 && PADCalculator.arousal >0.5) {
						PlotPanel.getIntance().setSadFace();
					}

					if(PADCalculator.pleasure < 0.5 && PADCalculator.arousal <0.5) {
						PlotPanel.getIntance().setNeutralFace();
					}
				}
				
				if(measureLocal.contains("Heart Rate")) {
					
					String[] tokens=measureLocal.split(",")[1].split("=");
					PADCalculator.arousal=Double.parseDouble(tokens[1]);
					PADCalculator.arousal=PADCalculator.arousal<100?PADCalculator.arousal/100:PADCalculator.arousal/1000;
					VectorProject2.vectorSeries.remove(0);
					VectorProject2.vectorSeries.add(0, 0, PADCalculator.pleasure, PADCalculator.arousal);
					System.out.println("heart rate: "+PADCalculator.arousal);
					
				}
				
				setChanged();
				notifyObservers();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				System.out.println("Exception: " + ex);
			}
		}

		try {
			if (ois != null)
				ois.close();
			if (input != null)
				input.close();
			if (client != null)
				client.close();
		} catch (IOException e) {
			System.out.println("Exception: " + e);
		}
		if (!serverCheck)
			setData("FAIL");
		else if (!serverRunning)
			setData("STOPPED");
		else
			setData("FIN");
		setChanged();
		notifyObservers();
	}

}
