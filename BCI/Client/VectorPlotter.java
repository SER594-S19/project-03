package BCI.Client;

public class VectorPlotter implements Runnable {
	
	VectorProject2 vectorPlot=new VectorProject2("Vector");
	@Override
	public void run() {
		vectorPlot.pack();
		vectorPlot.setVisible(true);
	}

}
