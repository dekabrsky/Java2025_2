import java.io.*; 

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PiePlot3D; 
import org.jfree.data.general.DefaultPieDataset; 

public class Graph3D {

   public static void main( String[ ] args ) throws Exception {
      DefaultPieDataset dataset = new DefaultPieDataset( );             
      dataset.setValue( "IPhone 5s" ,20);             
      dataset.setValue( "SamSung Grand" , 20);             
      dataset.setValue( "MotoG" , 40);             
      dataset.setValue( "Nokia Lumia" , 10); 

      JFreeChart chart = ChartFactory.createPieChart3D( 
         "Mobile Sales" ,  // chart title                   
         dataset ,         // data 
         true ,            // include legend                   
         true, 
         false);

      final PiePlot3D plot = ( PiePlot3D ) chart.getPlot( );             
      plot.setStartAngle( 270 );             
      plot.setForegroundAlpha( 0.60f );             
      plot.setInteriorGap( 0.02 );             
   }
}
