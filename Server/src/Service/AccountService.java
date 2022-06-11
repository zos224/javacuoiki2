package Service;

import java.util.ArrayList;

public class AccountService {
	public static boolean checkaccount(String username, String password)
	{
		ArrayList<String> usernamelist = DAOService.getusername();
		ArrayList<String> passwordlist = DAOService.getpassword();
		for (int i = 0; i < usernamelist.size(); i++)
		{
			if (usernamelist.get(i).equals(username) && passwordlist.get(i).equals(password))
			{
				return true;
			}
		}
		return false;
	}
}
