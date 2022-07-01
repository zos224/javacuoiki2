package View;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controller.Export_Excel;

public class Export extends JFrame implements ActionListener, ItemListener {
	String filepath;
	static JRadioButton theongay,theomhs;
    JButton excel;
    static JTextField ngay,mhs;
    JLabel label,chuthich1,chuthich2;
    public Export() {}
    public void View()
    {
        JPanel p1 = new JPanel();
        label = new JLabel("Chọn kiểu dữ liệu bạn muốn xuất theo: ");
        this.add(label,"North");
        theongay = new JRadioButton("Xuất theo ngày");
        theongay.addItemListener(this);
        p1.add(theongay);
        ngay = new JTextField(20);
        p1.add(ngay);
        ngay.setEnabled(false);
        p1.setLayout(new GridBagLayout());
        chuthich1 = new JLabel("Để xuất theo từng mã hồ sơ, ghi lần lượt từng mã hồ sơ, các mã hồ sơ cách nhau bởi dấu , (VD: 1,4,5)");
        chuthich1.setFont(new Font("Times New Roman", Font.ITALIC,14));
        chuthich2 = new JLabel("Để xuất nhiều mã hồ sơ liên tục, sử dụng dấu - (VD: 10-20)");
        chuthich2.setFont(new Font("Times New Roman", Font.ITALIC,14));
        theomhs = new JRadioButton("Xuất theo mã hồ sơ");
        theomhs.addItemListener(this);
        JPanel p2 = new JPanel();
        p2.add(theomhs);
        mhs = new JTextField(20);
        p2.add(mhs);
        mhs.setEnabled(false);
        p2.add(chuthich1);
        p2.add(chuthich2);
        JPanel p3 = new JPanel();
        p3.add(p1);
        p3.add(p2);
        p3.setLayout(new GridLayout(2,1));
        // Xuat:
        JPanel xuat = new JPanel();
        excel = new JButton("Xuất ra Excel");
        excel.addActionListener(this);
        excel.setEnabled(false);
        xuat.add(excel);
        xuat.setLayout(new FlowLayout());
        this.add(p3);
        this.setTitle("Xuất dữ liệu");
        this.add(xuat,"South");
        this.setVisible(true);
        this.setSize(630,250);
        this.setLocationRelativeTo(null);
    }
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(theongay))
        {
            if (e.getStateChange() == 1)
            {
                theomhs.setEnabled(false);
                ngay.setEnabled(true);
                excel.setEnabled(true);
            }
            else
            {
                theomhs.setEnabled(true);
                ngay.setEnabled(false);
                excel.setEnabled(false);
            }
        }
        else if (e.getSource().equals(theomhs))
        {
            if (e.getStateChange() == 1)
            {
                theongay.setEnabled(false);
                mhs.setEnabled(true);
                excel.setEnabled(true);
            }
            else
            {
                theongay.setEnabled(true);
                mhs.setEnabled(false);
                excel.setEnabled(false);
            }
        }
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Xuất ra Excel"))
        {
            JFileChooser file = new JFileChooser();
            file.setDialogTitle("Lưu");
            int userselection = file.showSaveDialog(this);
            if (userselection == JFileChooser.APPROVE_OPTION)
            {
                File filesave = file.getSelectedFile();
                filepath = filesave.getAbsolutePath();
                Export_Excel.Export(filepath);
            }
        }
	}
	
	public static boolean get_status_theongay() {
		return theongay.isSelected();
	}
	
	public static boolean get_status_theomhs() {
		return theomhs.isSelected();
	}
	
	public static String get_ngaytf() {
		return ngay.getText();
	}
	
	public static String get_mhstf() {
		return mhs.getText();
	}
}
