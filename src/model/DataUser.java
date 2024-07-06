package model;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class DataUser extends JFrame {
	public static ArrayList<User> users;

	public DataUser() {
		users = new ArrayList<User>();
//		 Cập nhật dữ liệu người chơi
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				updateData();
			}
		});
	}

	// lưu trữ thông tin người chơi
	public static void updateData() {
		BufferedWriter bw = null;
		try {
			FileWriter fw = new FileWriter("src/model/data.txt");
			bw = new BufferedWriter(fw);

			for (User u : users) {
				bw.write(u.getName() + " " + u.getLevel());
				bw.newLine();
			}

		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}

	// đọc dữ liệu từ file text và lưu trữ lại
	public static void readData() {
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader("src/model/data.txt");
			br = new BufferedReader(fr);

			String line = null;
			while ((line = br.readLine()) != null) {
				String[] str = line.split(" ");
				users.add(new User(str[0], str[1]));
			}

			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}
}
