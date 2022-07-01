package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import Controller.Client;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;
import java.util.jar.Attributes.Name;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainGUI extends JFrame implements MouseListener, ActionListener, ItemListener {

	long millis = System.currentTimeMillis();
    Date time = new Date(millis);
    static JTable table;
    JLabel timkiemlb;
    JRadioButton loc;
    JTextField timkiem;
    static DefaultTableModel model;
    JScrollPane sp;
    JButton them, xoa, chinhsua, thongke, xuatdl;
    static Vector vData = new Vector();
    static Vector vTitle = new Vector<>();
    static JComboBox nam;
    TableRowSorter<DefaultTableModel> sorter;
    int selectedrow, viewrow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI(){
		try {
            // TOP
            JPanel top = new JPanel();
            top.setLayout(new GridLayout(2, 1));
            // Chucnang
            JPanel cn = new JPanel();
            them = new JButton("Thêm");
            them.setIcon(new ImageIcon("image//add.png"));
            them.setBackground(Color.GREEN);
            them.addActionListener(this);
            xoa = new JButton("Xóa");
            xoa.setIcon(new ImageIcon("image//delete.png"));
            xoa.setBackground(Color.RED);
            xoa.addActionListener(this);
            chinhsua = new JButton("Chỉnh sửa");
            chinhsua.setBackground(Color.YELLOW);
            chinhsua.setIcon(new ImageIcon("image//edit.png"));
            chinhsua.addActionListener(this);
            thongke = new JButton("Thống kê");
            thongke.setIcon(new ImageIcon("image//chart.png"));
            thongke.addActionListener(this);
            thongke.setBackground(Color.CYAN);
            xuatdl = new JButton("Xuất dữ liệu");
            xuatdl.setIcon(new ImageIcon("image//export.png"));
            xuatdl.addActionListener(this);
            xuatdl.setBackground(Color.LIGHT_GRAY);
            cn.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Chức năng", TitledBorder.CENTER, TitledBorder.TOP));
            top.add(cn);
            
            GroupLayout gl_cn = new GroupLayout(cn);
            gl_cn.setHorizontalGroup(
            	gl_cn.createParallelGroup(Alignment.LEADING)
            		.addGroup(gl_cn.createSequentialGroup()
            			.addGap(117)
            			.addComponent(them, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
            			.addGap(118)
            			.addComponent(xoa, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
            			.addGap(112)
            			.addComponent(chinhsua, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
            			.addGap(89)
            			.addComponent(thongke, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
            			.addGap(98)
            			.addComponent(xuatdl, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
            			.addGap(103))
            );
            gl_cn.setVerticalGroup(
            	gl_cn.createParallelGroup(Alignment.LEADING)
            		.addGroup(gl_cn.createSequentialGroup()
            			.addGap(5)
            			.addGroup(gl_cn.createParallelGroup(Alignment.BASELINE)
            				.addComponent(xuatdl)
            				.addComponent(thongke)
            				.addComponent(chinhsua)
            				.addComponent(xoa)
            				.addComponent(them)))
            );
            cn.setLayout(gl_cn);

            // Tìmkiem
            JPanel tk = new JPanel();
            timkiemlb = new JLabel("Nhập vào đây để tìm kiếm:");
            tk.add(timkiemlb);
            timkiem = new JTextField(40);
            timkiem.getDocument().addDocumentListener(
                    new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            table.clearSelection();
                            Filter();
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            table.clearSelection();
                            Filter();
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            table.clearSelection();
                            Filter();
                        }
                    }
            );
            tk.add(timkiem);
            loc = new JRadioButton("Lọc nhanh bệnh nhân đang điều trị: ");
            loc.addItemListener(this);
            tk.add(loc);
            nam = new JComboBox();
            nam.addItem("Theo năm");
            Client.get_year();
            nam.addItemListener(this);
            nam.setEnabled(false);
            tk.add(nam);
            tk.setLayout(new GridBagLayout());
            top.add(tk);
            JPanel p1 = new JPanel();
            p1.setBorder(BorderFactory.createTitledBorder("Danh sách hồ sơ bệnh án"));
            model = new DefaultTableModel(vData, vTitle) {
                Class[] types = {Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class};

                @Override
                public Class getColumnClass(int columnIndex) {
                    return this.types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table = new JTable(model) {
                public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                    Component c = super.prepareRenderer(renderer, row, column);
                    if (isRowSelected(row)) {
                        c.setBackground(getSelectionBackground());
                    } else {
                        if (table.getValueAt(row, 9) == "" && table.getValueAt(row, 8) != "") {
                            c.setBackground(new Color(249, 255, 148));
                        } else {
                            c.setBackground(null);
                        }
                    }
                    return c;
                }
            };
            table.addMouseListener(this);
            table.getTableHeader().setBackground(new Color(153, 187, 247));
            sorter = new TableRowSorter<DefaultTableModel>(model);
            table.setRowSorter(sorter);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    model.addTableModelListener(new TableModelListener() {
                        @Override
                        public void tableChanged(TableModelEvent e) {
                            selectedrow = viewrow = 0;
                        }
                    });
                    selectedrow = table.convertRowIndexToModel(viewrow);
                }
            });
            sp = new JScrollPane(table);
            p1.setLayout(new CardLayout());
            p1.add(sp);
            getContentPane().add(top, "North");
            getContentPane().add(p1);
            this.setTitle("Quản lý hồ sơ bệnh án");
            this.setSize(1200, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}

	public static void reload(Vector Data, Vector Title)
	{
		if (vData.isEmpty() && vTitle.isEmpty())
		{
			vData = Data;
			vTitle = Title;
		}
		else {
			model.setDataVector(Data, Title);
		}
	}

	public static void add_year(Vector<String> years)
	{
		for (int i = 0; i < years.size(); i++)
		{
			nam.addItem(years.elementAt(i));
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Xóa")) {
			int result = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa hồ sơ bệnh án này chứ?", "Xác nhận",
	                JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
	        if (result == JOptionPane.YES_OPTION) 
	        {
				Vector row = (Vector) vData.elementAt(selectedrow);
				Client.delete_request(row.elementAt(0).toString());
				vData.remove(selectedrow);
				model.fireTableDataChanged();
	        }
        }
		else if (e.getActionCommand().equals("Thêm"))
		{
			new UpdateForm("Thêm hồ sơ bệnh án", 0, "", "", "", time, "", "", "", time, time);
		}
		else if (e.getActionCommand().equals("Chỉnh sửa"))
		{
			Vector st = (Vector) vData.elementAt(selectedrow);
            if (!(st.elementAt(8) == "") && st.elementAt(9) == "") {
                new UpdateForm("Chỉnh sửa hồ sơ bệnh án", Integer.parseInt((String) st.elementAt(0)), (String) st.elementAt(1), (String) st.elementAt(2),
                        (String) st.elementAt(3), Date.valueOf((String) st.elementAt(4)), (String) st.elementAt(5), (String) st.elementAt(6),
                        (String) st.elementAt(7), Date.valueOf((String) st.elementAt(8)), null);
            } else if (!(st.elementAt(8) == "" && st.elementAt(9) == "")) {
                new UpdateForm("Chỉnh sửa hồ sơ bệnh án", Integer.parseInt((String) st.elementAt(0)), (String) st.elementAt(1), (String) st.elementAt(2),
                        (String) st.elementAt(3), Date.valueOf((String) st.elementAt(4)), (String) st.elementAt(5), (String) st.elementAt(6),
                        (String) st.elementAt(7), Date.valueOf((String) st.elementAt(8)), Date.valueOf((String) st.elementAt(9)));
            } else {
                new UpdateForm("Chỉnh sửa hồ sơ bệnh án", Integer.parseInt((String) st.elementAt(0)), (String) st.elementAt(1), (String) st.elementAt(2),
                        (String) st.elementAt(3), Date.valueOf((String) st.elementAt(4)), (String) st.elementAt(5), (String) st.elementAt(6),
                        (String) st.elementAt(7), null, null);
            }
		}
		else if (e.getActionCommand().equals("Thống kê")) {
			new Chart();
		}
		else if (e.getActionCommand().equals("Xuất dữ liệu"))
		{
			new Export().View();
		}
	}

	public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == 1) {
            nam.setEnabled(true);
            Client.loadData_dieutri();
            int index = nam.getSelectedIndex();
            if (index != 0) {
                Client.load_data_dieutri_theonam((String) nam.getSelectedItem());
            }
        }
        else
        {
            nam.setEnabled(false);
            Client.loadData();
        }
        model.fireTableDataChanged();
    }
	
	public void Filter() {
        RowFilter<DefaultTableModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(timkiem.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sorter.setRowFilter(rf);
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		viewrow = selectedrow = table.getSelectedRow();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
