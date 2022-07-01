package View;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Chart extends JFrame implements ItemListener {
    JCheckBox month,year;
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    public Chart()
    {
        month = new JCheckBox("Thống kê theo tháng");
        month.addItemListener(this);
        year = new JCheckBox("Thống kê theo năm");
        year.addItemListener(this);
        p1.add(month);
        p1.add(year);
        p1.setLayout(new FlowLayout());
        this.add(p1,"North");
        this.add(p2);
        p2.setBorder(BorderFactory.createTitledBorder("Biểu đồ"));
        this.setSize(800,600);
        this.setVisible(true);
        this.setTitle("Thống kê");
        this.setLocationRelativeTo(null);
    }

    public void itemStateChanged(ItemEvent e)
    {
        if (month.isSelected())
        {
            year.setEnabled(false);
            ChartMonth chartMonth = new ChartMonth();
            p2.add(chartMonth.chartPanel);
            p2.validate();
            p2.repaint();
        }
        else if (year.isSelected())
        {
            month.setEnabled(false);
            ChartYear chartYear = new ChartYear();
            p2.add(chartYear.chartPanel);
            p2.validate();
            p2.repaint();
        }
        else
        {
            month.setEnabled(true);
            year.setEnabled(true);
            p2.removeAll();
            p2.repaint();
        }
    }

}
