package View;

import java.awt.Dimension;
import java.util.Date;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Controller.Client;

public class ChartMonth {
	ChartPanel chartPanel;
    JFreeChart chart;
    static DefaultCategoryDataset dataset;
    public ChartMonth()
    {
        try
        {
            Date date = new Date();
            int i = 1;
            int year = date.getYear() + 1900;
            dataset = new DefaultCategoryDataset();
            Client.load_data_month();
            chart = ChartFactory.createBarChart("Biểu đồ thống kê số ca khám mỗi tháng trong năm " + year,"Tháng","Số ca khám",
                    dataset, PlotOrientation.VERTICAL,false,false,false);
           chartPanel = new ChartPanel(chart);
           chartPanel.setPreferredSize(new Dimension(770,500));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static void setData(Vector vector)
    {
    	for (int i = 0; i < vector.size(); i = i + 2)
    	{
    		dataset.addValue(Double.parseDouble((String) vector.get(i + 1)), "Số ca khám" , vector.get(i).toString());
    	}
    }
}
