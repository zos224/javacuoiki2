package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
					messageslip[1] = messageslip[1].substring(0,messageslip[1].length());
					String hotendem = messageslip[1];
					String ten = messageslip[2];
					String diachi = messageslip[3];
					Date ngaykham = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(messageslip[4]);
					String bacsi = messageslip[5];
					String ketluan = messageslip[6];
					String dieutri = messageslip[7];
					Date ngaynhapvien = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(messageslip[8]);
					Date ngayravien = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(messageslip[9]);
					Benhnhan benhnhan = new Benhnhan(hotendem, ten, diachi, ngaykham, bacsi, ketluan, dieutri, ngaynhapvien, ngayravien);
					DAOService.Insert_Patient(benhnhan);
					dataOutputStream.writeUTF("Insert-complete");
					dataOutputStream.flush();
 				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
