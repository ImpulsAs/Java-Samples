package by.zti.main;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import by.zti.SerializationManager;

public class MainComponent {
	private static MainWindow main_window;
	private static ArrayList<UserData> profiles = new ArrayList<UserData>();
	private static String pass;
	private static Connector connection;

	public static void main(String[] args)
	{
//		initialiseServerConection();
//		loadData();
//		String tem_pass = JOptionPane.showInputDialog(null, "Enter a password");
//		if(tem_pass==null){
//			System.exit(0);
//		}
//		if(tem_pass.contentEquals(pass)&&tem_pass!=null){
//			setMain_window(new MainWindow());
//			saveData();
//			
//		}else{
//			JOptionPane.showMessageDialog(null, "Your password is incorrect, exiting program");
//			System.exit(0);
//		}
		System.out.println(ArrayList.class.getSimpleName());
	}

	private static void initialiseServerConection() {
		connection = new Connector("127.0.0.1", 1995);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void saveData()
	{
		SerializationManager.serializeData(pass, "pw", "ser", "");
		connection.sendObject(new ServerRquest("save", profiles));
	}
	
	@SuppressWarnings("unchecked")
	public static void loadData()
	{
		pass = (String)SerializationManager.deSerializeData("pw", "ser", "");
		connection.sendObject(new ServerRquest("load", null));
		profiles = (ArrayList<UserData>)connection.getRead_object();
	}
	
	public static ArrayList<UserData> getProfiles() {
		return profiles;
	}

	public static void setProfiles(ArrayList<UserData> profiles) {
		MainComponent.profiles = profiles;
	}

	public static MainWindow getMain_window() {
		return main_window;
	}

	public static void setMain_window(MainWindow main_window) {
		MainComponent.main_window = main_window;
	}


}
