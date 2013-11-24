package by.zti;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	private static ArrayList<Mobile> list = new ArrayList<Mobile>();
	private static Random r = new Random();
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		list = (ArrayList<Mobile>) SerializationManager.deSerializeData("mobile-list", "ser", "");
//		for(int i=0;i<5;i++){
//			list.add(new Mobile(r.nextInt(9999999),"abc"+r.nextInt(100)));
//		}
		for(Mobile m: list){
			System.out.println(m.getNumber()+" "+m.getName());
		}
		SerializationManager.serializeData(list, "mobile-list", "ser", "");
	}

}
