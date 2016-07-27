package cn.szse.users.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cn.szse.users.pojo.User;

public class UserCache {
	private static List<User> users = new ArrayList<User>();
	static {
		try {
			String parentPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			File f = new File(parentPath+"/users.txt");
			BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF-8"));
			String line;
			while((line = fr.readLine()) != null) {
				User u = parse(line);
				users.add(u);
			}
			fr.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<User> getUsers() {
		List<User> result = new ArrayList<User>();
		result.addAll(users);
		return result;
	}
	
	private static User parse(String line) {
		String[] f = line.split(";");
		User user = new User(f[0], f[1], Integer.valueOf(f[2]), f[3], f[4]);
		return user;
	}
	
	public static void main(String args[]) {
		for(User u : UserCache.getUsers()) {
			System.out.println(u);
		}
	}
}
