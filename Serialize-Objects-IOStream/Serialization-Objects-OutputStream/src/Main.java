import java.util.ArrayList;
import javax.swing.JOptionPane;
import by.zti.SerializationManager;


public class Main {
	private static ArrayList<Profile> profiles = new ArrayList<Profile>();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		profiles = (ArrayList<Profile>) SerializationManager.deSerializeData("profiles", "ser", "");
		System.out.println(profiles.size());
		Profile profile = new Profile();
		profile.setName(JOptionPane.showInputDialog(null, "¬ведите ваше им€"));
		profile.setSurname(JOptionPane.showInputDialog(null, "¬ведите вашу фамилию"));
		profiles.add(profile);
		for (Profile p : profiles) {
			System.out.println(p.getName()+" "+p.getSurname());
		}
		System.out.println(profiles.size());
		SerializationManager.serializeData(profiles, "profiles", "ser", "");
	}
}
