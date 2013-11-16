package by.zti;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	private static ArrayList<A1> array = new ArrayList<A1>();
	private static Random r = new Random();
	
	public static void main(String[] args){
		for(int i=0;i<3;i++){
			array.add(new A1(r.nextInt(15), "abc"+r.nextInt(5)));
		}
		for(A1 x: array){
			System.out.println(x.getId()+" "+x.getName());
		}
	}
}
