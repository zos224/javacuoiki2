package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

import Model.Benhnhan;
import Service.AccountService;
import Service.DAOService;

public class Server {
	private static DataInputStream dataInputStream;
	private static DataOutputStream dataOutputStream;
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(4444);
			Socket socket = serverSocket.accept();
			System.out.print("Ket noi thanh cong");
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			while (true) {
				String message = dataInputStream.readUTF();
				String[] messageslip = message.split(",");
				if (messageslip[0].equals("login-request"))
				{
					String account_decrypt = Decryption.decryption(messageslip[1]);
					String[] account = account_decrypt.split(",");
					Boolean check = AccountService.checkaccount(account[0].trim(), account[1].trim());
					if (check == true)
					{
						dataOutputStream.writeUTF("login-success");
						dataOutputStream.flush();
					}
					else 
					{
						dataOutputStream.writeUTF("login-fail");
						dataOutputStream.flush();
					}
 				}
				else if (messageslip[0].equals("load data"))
				{
					ArrayList<String> arrayList = DAOService.getData();
					dataOutputStream.writeUTF("Data," + arrayList);
					dataOutputStream.flush();
				}
				else if (messageslip[0].equals("delete-request"))
				{
					DAOService.Delete_Patient(messageslip[1]);
					dataOutputStream.writeUTF("Delete-complete");
					dataOutputStream.flush();
				}
				else if (messageslip[0].equals("insert-request"))
				{
					messageslip[1] = messageslip[1].substring(1);
					String hotendem = messageslip[1].trim();
					String ten = messageslip[2].trim();
					String diachi = messageslip[3].trim();
					java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(messageslip[4].trim());
					Date ngaykham = new Date(utilDate.getTime());
					String bacsi = messageslip[5].trim();
					String ketluan = messageslip[6].trim();
					String dieutri = messageslip[7].trim();
					Date ngaynhapvien = null;
					if (!messageslip[8].equals(" null"))
					{
						utilDate =  new SimpleDateFormat("yyyy-MM-dd").parse(messageslip[8]);
						ngaynhapvien = new Date(utilDate.getTime());
					}
					Date ngayravien = null;
					messageslip[9] = messageslip[9].substring(0,messageslip[9].length()-1);
					if (!messageslip[9].equals(" null"))
					{
						utilDate =  new SimpleDateFormat("yyyy-MM-dd").parse(messageslip[9]);
						ngayravien = new Date(utilDate.getTime());
					}
					Benhnhan benhnhan = new Benhnhan(hotendem, ten, diachi, ngaykham, bacsi, ketluan, dieutri, ngaynhapvien, ngayravien);
					DAOService.Insert_Patient(benhnhan);
					dataOutputStream.writeUTF("Insert-complete");
					dataOutputStream.flush();
 				}
				else if (messageslip[0].equals("update-request"))
				{
					messageslip[1] = messageslip[1].substring(1);
					int id = Integer.parseInt(messageslip[1].trim());
					String hotendem = messageslip[2].trim();
					String ten = messageslip[3].trim();
					String diachi = messageslip[4].trim();
					java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(messageslip[5].trim());
					Date ngaykham = new Date(utilDate.getTime());
					String bacsi = messageslip[6].trim();
					String ketluan = messageslip[7].trim();
					String dieutri = messageslip[8].trim();
					Date ngaynhapvien = null;
					if (!messageslip[9].equals(" null"))
					{
						utilDate =  new SimpleDateFormat("yyyy-MM-dd").parse(messageslip[9]);
						ngaynhapvien = new Date(utilDate.getTime());
					}
					Date ngayravien = null;
					messageslip[10] = messageslip[10].substring(0,messageslip[10].length()-1);
					if (!messageslip[10].equals(" null"))
					{
						utilDate =  new SimpleDateFormat("yyyy-MM-dd").parse(messageslip[10]);
						ngayravien = new Date(utilDate.getTime());
					}
					Benhnhan benhnhan = new Benhnhan(id,hotendem, ten, diachi, ngaykham, bacsi, ketluan, dieutri, ngaynhapvien, ngayravien);
					DAOService.Update_Patient(benhnhan);
					dataOutputStream.writeUTF("Update-complete");
					dataOutputStream.flush();
				}
				else if (messageslip[0].equals("load data dieutri"))
				{
					ArrayList<String> arrayList = DAOService.getData_dieutri();
					dataOutputStream.writeUTF("Data," + arrayList);
					dataOutputStream.flush();
				}
				else if (messageslip[0].equals("get year"))
				{
					ArrayList<String> arrayList = DAOService.getYear();
					dataOutputStream.writeUTF("Year," + arrayList);
					dataOutputStream.flush();
				}
				
				else if (messageslip[0].equals("load data dieutri theonam"))
				{
					ArrayList<String> arrayList = DAOService.getData_dieutri_theonam(messageslip[1]);
					dataOutputStream.writeUTF("Data," + arrayList);
					dataOutputStream.flush();
				}
				
				else if (messageslip[0].equals("load data month"))
				{
					Vector vector = DAOService.getData_thongke_theothang();
					dataOutputStream.writeUTF("Data thongke thang," + vector);
					dataOutputStream.flush();
				}
				
				else if (messageslip[0].equals("load data year"))
				{
					Vector vector = DAOService.getData_thongke_theonam();
					dataOutputStream.writeUTF("Data thongke nam," + vector);
					dataOutputStream.flush();
				}
				
				else if (messageslip[0].equals("load col name"))
				{
					ArrayList<String> arrayList = DAOService.get_col_name();
					dataOutputStream.writeUTF("Col name," + arrayList);
					dataOutputStream.flush();
				}
				
				else if (messageslip[0].equals("load data export"))
				{
					ArrayList<String> arrayList = DAOService.get_data_export(messageslip[1]);
					dataOutputStream.writeUTF("Data export," + arrayList);
					dataOutputStream.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
