package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

public class UpdateForm extends JFrame implements ActionListener, ItemListener{
	private JLabel mahslb, hotendemlb, tenlb, diachilb, ngaykhamlb, bacsilb, ketluanlb, dieutrilb, ngaynhapvienlb, ngayravienlb;
    private JTextField Mahs, Hotendem, Ten, Diachi, Ngaykham, Bacsi, Ketluan;
    private JDateChooser Ngaynhapvien, Ngayravien;
    private JComboBox Dieutri;
    private JButton ok, cancel;
    private int mahs;
    private Date ngaynhapvien = null,ngayravien = null;
    private  int ssdate,checkdate;
    public UpdateForm(String s, Integer mahs, String hotendem, String ten, String diachi, Date ngaykham, String bacsi, String ketluan, String dieutri,
                      Date ngaynhapvien, Date ngayravien) {
        super(s);
        Container p = this.getContentPane();
        getContentPane().setLayout(null);
        mahslb = new JLabel("Mã hồ sơ:");
        mahslb.setBounds(0, 5, 105, 41);
        p.add(mahslb);
        Mahs = new JTextField(mahs.toString());
        Mahs.setBounds(133, 5, 251, 41);
        p.add(Mahs);
        Mahs.setEditable(false);
        hotendemlb = new JLabel("Họ tên đệm:");
        hotendemlb.setBounds(0, 46, 105, 41);
        p.add(hotendemlb);
        Hotendem = new JTextField(hotendem);
        Hotendem.setBounds(133, 46, 251, 41);
        p.add(Hotendem);
        tenlb = new JLabel("Tên:");
        tenlb.setBounds(0, 87, 105, 41);
        p.add(tenlb);
        Ten = new JTextField(ten);
        Ten.setBounds(133, 87, 251, 41);
        p.add(Ten);
        diachilb = new JLabel("Địa chỉ:");
        diachilb.setBounds(0, 128, 105, 41);
        p.add(diachilb);
        Diachi = new JTextField(diachi);
        Diachi.setBounds(133, 128, 251, 41);
        p.add(Diachi);
        ngaykhamlb = new JLabel("Ngày khám:");
        ngaykhamlb.setBounds(0, 169, 105, 41);
        p.add(ngaykhamlb);
        Ngaykham = new JTextField(String.valueOf(ngaykham));
        Ngaykham.setBounds(133, 169, 251, 41);
        p.add(Ngaykham);
        bacsilb = new JLabel("Bác sĩ:");
        bacsilb.setBounds(0, 210, 105, 41);
        p.add(bacsilb);
        Bacsi = new JTextField(bacsi);
        Bacsi.setBounds(133, 210, 251, 41);
        p.add(Bacsi);
        ketluanlb = new JLabel("Kết luận:");
        ketluanlb.setBounds(0, 251, 105, 41);
        p.add(ketluanlb);
        Ketluan = new JTextField(ketluan);
        Ketluan.setBounds(133, 251, 251, 41);
        p.add(Ketluan);
        dieutrilb = new JLabel("Điều trị:");
        dieutrilb.setBounds(0, 292, 192, 41);
        p.add(dieutrilb);
        Dieutri = new JComboBox();
        Dieutri.setBounds(133, 292, 251, 41);
        Dieutri.addItem("");
        Dieutri.addItem("Nhập viện");
        Dieutri.addItem("Cho thuốc uống tại nhà");
        Dieutri.addItem("Không");
        Dieutri.addItemListener(this);
        p.add(Dieutri);
        ngaynhapvienlb = new JLabel("Ngày nhập viện:");
        ngaynhapvienlb.setBounds(0, 333, 105, 41);
        p.add(ngaynhapvienlb);
        ngayravienlb = new JLabel("Ngày ra viện:");
        ngayravienlb.setBounds(0, 375, 105, 41);
        p.add(ngayravienlb);
        Dieutri.setSelectedItem(dieutri);
        ok = new JButton("OK");
        ok.setBounds(0, 420, 192, 41);
        p.add(ok);
        ok.addActionListener(this);
        cancel = new JButton("Hủy");
        cancel.setBounds(192, 420, 192, 41);
        p.add(cancel);
        
        Ngaynhapvien = new JDateChooser();
        Ngaynhapvien.setBounds(133, 333, 251, 41);
        getContentPane().add(Ngaynhapvien);
        Ngaynhapvien.setEnabled(false);
        JDateChooser Ngayravien = new JDateChooser();
        Ngayravien.setBounds(133, 380, 251, 36);
        Ngayravien.setEnabled(false);
        getContentPane().add(Ngayravien);
        cancel.addActionListener(this);
        this.mahs = mahs;
        this.setSize(400, 500);
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK"))
        {
            checkdate = 0;
            ssdate = 0;
            if (Ngaynhapvien.isEnabled() && Ngayravien.isEnabled())
            {
                ngaynhapvien = (Date) Ngaynhapvien.getDate();
                if (!Ngayravien.getDate().equals(null))
                {
                    ngayravien = (Date) Ngayravien.getDate();
					ssdate = ngaynhapvien.compareTo(ngayravien);
                }
            }
            insertDB();
            if (!(Hotendem.getText().equals("") || Ten.getText().equals("") || Diachi.getText().equals("") || Ngaykham.getText().equals("")
                    || Bacsi.getText().equals("") || Ketluan.getText().equals("") || Dieutri.getSelectedItem().equals("") ||
                    (Ngaynhapvien.isEnabled() && Ngaynhapvien.getDate().equals(null)) || ssdate > 0 || checkdate == 1))
            {
                this.dispose();
            }
        } else {
            this.dispose();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        int index = Dieutri.getSelectedIndex();
        if (index == 0) {
            Ngaynhapvien.setEnabled(false);
            Ngayravien.setEnabled(false);
        } else if (index == 1) {
            Ngaynhapvien.setEnabled(true);
            Ngayravien.setEnabled(true);
        } else if (index == 2) {
            Ngaynhapvien.setEnabled(false);
            Ngayravien.setEnabled(false);
        } else if (index == 3) {
            Ngaynhapvien.setEnabled(false);
            Ngayravien.setEnabled(false);
        }
    }
    
    public void insertDB()
    {
    	if (Hotendem.getText().equals("") || Ten.getText().equals("") || Diachi.getText().equals("") || Ngaykham.getText().equals("")
                || Bacsi.getText().equals("") || Ketluan.getText().equals("") || Dieutri.getSelectedItem().equals("") ||
                (Ngaynhapvien.isEnabled() && Ngaynhapvien.getDate().equals(null)) || ssdate > 0 || checkdate == 1)
        {
            JOptionPane.showMessageDialog(null, "Dữ liệu thiếu hoặc không hợp lệ!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        else {
        	String hotendem = Hotendem.getText();
            String ten = Ten.getText();
            String diachi = Diachi.getText();
            Date ngaykham = Date.valueOf(Ngaykham.getText());
            String bacsi = Bacsi.getText();
            String ketluan = Ketluan.getText();
            String dieutri = (String) Dieutri.getSelectedItem();
        }
    }
  
}
	    
