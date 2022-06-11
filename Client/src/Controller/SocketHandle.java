package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;

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
				System.out.println(messageslip[0]);
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
								vTitle.add(messageslip[i]);
							}
						}
						if (i == (messageslip.length - 1))
						{
							messageslip[i] = messageslip[i].substring(0,messageslip[i].length());
						}
						Vector row = new Vector<>();
						for (int j = 1; j <= Num_Info_Patient; j++, i++)
						{
							row.add(messageslip[i]);
						}
						vData.add(row);
					}
					MainGUI.reload(vData, vTitle);
				}
				else if (message.equals("Delete-complete")) {
					System.out.println("xoa thanh cong");
					JOptionPane.showMessageDialog(null, "Đã xóa thành công!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
				}
				else if (message.equals("Insert-complete"))
				{
					JOptionPane.showMessageDialog(null, "Đã thêm thành công! Vui lòng nhấn nút Reload để load lại dữ liệu", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
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
