package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		ArrayList<member2> memlist = new ArrayList<>();
		
		memlist.add(new member2(4, "박정권", "010-4433-2289"));
		memlist.add(new member2(1, "edga", "010-0093-2289"));
		memlist.add(new member2(3, "문소현", "010-7473-0020"));
		memlist.add(new member2(2, "blair", "010-0020-7473"));
		
		System.out.println("정렬 전");
		for(member2 mem : memlist) {
			System.out.println(mem);
		}
		
		System.out.println("정렬 후");
		Collections.sort(memlist);
		for(member2 mem : memlist) {
			System.out.println(mem);
		}
		
		System.out.println("정렬 후");
		Collections.sort(memlist, new sortName());
		for(member2 mem : memlist) {
			System.out.println(mem);
		}
	}
}
class sortName implements Comparator<member2>{

	@Override
	public int compare(member2 name1, member2 name2) {
		return name1.getName().compareTo(name2.getName());
	}
	
}


class member2 implements Comparable<member2>{
	int num;
	String name;
	String tel;
	
	public member2(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "[num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	@Override
	public int compareTo(member2 mem) {
		return Integer.compare(getNum(), mem.getNum()) * -1;
	}
}