package BCI.Client;

import org.jfree.data.xy.VectorSeries;
import org.jfree.data.xy.VectorSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.renderer.xy.VectorRenderer;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;

/**
* A simple introduction to using JFreeChart.
*/
public class VectorProject2 extends ApplicationFrame{
	public static double pleasure=0.8;
	public static double arousal=1; 
	public static VectorSeries vectorSeries;
	public VectorProject2(String applicationTitle) {
		super(applicationTitle);
		VectorSeriesCollection dataSet= new VectorSeriesCollection();
		
		vectorSeries=new VectorSeries("First Series");

		vectorSeries.add(0, 0,pleasure,arousal);
		

		dataSet.addSeries(vectorSeries);	



		VectorRenderer r = new VectorRenderer();
		//r.setBasePaint(Color.white);
		//r.setSeriesPaint(0, Color.blue);

		XYPlot xyPlot = new XYPlot(dataSet, new NumberAxis("Axis X"), new NumberAxis("Axis Y"), r);
		 NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
	        domain.setRange(0.00, 1.00);
	        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
	        range.setRange(0.00, 1.00);
		// Create a Chart
		JFreeChart theChart;

		theChart = new JFreeChart(xyPlot);
		theChart.setTitle("PA vector");
		 ChartPanel chartPanel = new ChartPanel( theChart );
		 chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
		  setContentPane( chartPanel ); 


	}
/**
* .
*
* @param args ignored.
*/
public static void main(String[] args) {
	
	VectorProject2 chart = new VectorProject2("Browser Usage Statistics"
	         );
	      chart.pack( );          
	      RefineryUtilities.centerFrameOnScreen( chart );          
	      chart.setVisible( true ); 
}
}