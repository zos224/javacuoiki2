package View;

import java.awt.Dimension;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Controller.Client;

public class ChartYear {
	ChartPanel chartPanel;
    JFreeChart chart;
    static DefaultCategoryDataset dataset;
    public ChartYear()
    {
        try
        {
            int i = 1;
            dataset = new DefaultCategoryDataset();
            Client.load_data_year();
            chart = ChartFactory.createBarChart("Biểu đồ thống kê số ca khám mỗi năm trong 3 năm gần đây","Năm","Số ca khám",
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
