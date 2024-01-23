package giaodien;

import java.awt.EventQueue;

public class Mainn {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Giaodienchinh1 frame = new Giaodienchinh1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
