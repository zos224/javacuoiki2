package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import View.Chart;
import View.ChartMonth;
import View.ChartYear;
import View.LoginGUI;
import View.MainGUI;

public class SocketHandle {
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private Socket socket;
	
	public void run()
	{
		try {
			socket = new Socket("localhost",4444);
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			String message;
			while(true)
			{
				message = dataInputStream.readUTF();
				String[] messageslip = message.split(",");
				if (messageslip[0].equals("login-success"))
				{	
					LoginGUI.closeGUI();
					MainGUI.main(null);
					Client.loadData();
				}
				else if (messageslip[0].equals("login-fail"))
				{
					JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không chính xác", "Thông báo",JOptionPane.ERROR_MESSAGE);
				}
				else if (messageslip[0].equals("Data"))
				{
					Vector vData = new Vector<>();
					Vector vTitle = new Vector<>();
					final int Num_Info_Patient = 10;
					for (int i = 1; i < messageslip.length;)
					{
						if (i == 1)
						{
							messageslip[i] = messageslip[i].substring(1);
							for (int k = 1; k <= Num_Info_Patient; k++, i++)
							{
								vTitle.add(messageslip[k].trim());
							}
						}
						Vector row = new Vector<>();
						for (int j = 1; j <= Num_Info_Patient; j++, i++)
						{
							if (i == (messageslip.length - 1))
							{
								messageslip[i] = messageslip[i].substring(0,messageslip[i].length()-1);
							}
							if (messageslip[i].trim().equals("null"))
							{
								row.add("");
							}
							else {
								row.add(messageslip[i].trim());
							} 		
						}
						vData.add(row);
					}
					MainGUI.reload(vData, vTitle);
				}
				else if (messageslip[0].equals("Delete-complete")) {
					JOptionPane.showMessageDialog(null, "Đã xóa thành công!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
				}
				else if (messageslip[0].equals("Insert-complete"))
				{
					Client.loadData();
					JOptionPane.showMessageDialog(null, "Đã thêm thành công!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
				}
				else if (messageslip[0].equals("Update-complete"))
				{
					Client.loadData();
					JOptionPane.showMessageDialog(null, "Đã cập nhật!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (messageslip[0].equals("Year"))
				{
					Vector<String> years = new Vector<>();
					for (int i = 1; i < messageslip.length; i++)
					{
						if (i == 1)
						{
							messageslip[i] = messageslip[i].substring(1);
						}
						if (i == (messageslip.length - 1))
						{
							messageslip[i] = messageslip[i].substring(0,messageslip[i].length()-1);
						}
						years.add(messageslip[i]);
					}
					MainGUI.add_year(years);
				}
				else if (messageslip[0].equals("Data thongke thang"))
				{
					Vector data = new Vector<>();
					for (int i = 1; i < messageslip.length; i++)
					{
						if (i == 1)
						{
							messageslip[i] = messageslip[i].substring(1);
						}
						if (i == (messageslip.length - 1))
						{
							messageslip[i] = messageslip[i].substring(0,messageslip[i].length()-1);
						}
						data.add(messageslip[i].trim());
					}
					ChartMonth.setData(data);
				}
				
				else if (messageslip[0].equals("Data thongke nam"))
				{
					Vector data = new Vector<>();
					for (int i = 1; i < messageslip.length; i++)
					{
						if (i == 1)
						{
							messageslip[i] = messageslip[i].substring(1);
						}
						if (i == (messageslip.length - 1))
						{
							messageslip[i] = messageslip[i].substring(0,messageslip[i].length()-1);
						}
						data.add(messageslip[i].trim());
					}
					ChartYear.setData(data);
				}
				
				else if (messageslip[0].equals("Col name")) 
				{
					ArrayList<String> arrayList = new ArrayList<>();
					for (int i = 1; i < messageslip.length; i++)
					{
						if (i == 1)
						{
							messageslip[i] = messageslip[i].substring(1);
						}
						if (i == (messageslip.length - 1))
						{
							messageslip[i] = messageslip[i].substring(0,messageslip[i].length()-1);
						}
						arrayList.add(messageslip[i]);
					}
					Export_Excel.create_col_name(arrayList);
				}
				
				else if (messageslip[0].equals("Data export"))
				{
					Vector<String> data = new Vector<>();
					for (int i = 1; i < messageslip.length; i++)
					{
						if (i == 1)
						{
							messageslip[i] = messageslip[i].substring(1);
						}
						if (i == (messageslip.length - 1))
						{
							messageslip[i] = messageslip[i].substring(0,messageslip[i].length()-1);
						}
						data.add(messageslip[i]);
					}
					Export_Excel.set_data_export(data);
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public DataInputStream getDataInputStream()
	{
		return dataInputStream;
	}
	
	public DataOutputStream getDataOutputStream()
	{
		return dataOutputStream;
	}
}
