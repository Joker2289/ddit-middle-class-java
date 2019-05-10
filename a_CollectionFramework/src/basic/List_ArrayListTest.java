package basic;

import java.util.ArrayList;
import java.util.Scanner;

public class List_ArrayListTest {
	
	//문제 5명의 사람 이름을 입력하여 ArrayList에 저장하고 이 중에 '김'씨 성의 이름을 출력하시오 (단,입력은 Scanne를 이용하여 입력받는다)
	public static void main(String[] args) {
		ArrayList<String> namelist = new ArrayList();
		Scanner s = new Scanner(System.in);
		for(int i=0; i<5; i++) {
			System.out.println((i+1) + "번쩨 이름 입력 > ");
			String str = s.nextLine();
			namelist.add(str);
		}
		//equals이용
		System.out.println("김씨성을 가진 사람들======equals");
		for(int i=0; i<namelist.size(); i++) {
			if(namelist.get(i).substring(0, 1).equals("김")) {
				System.out.println(namelist.get(i));
			}
		}
		//startsWith이용
		System.out.println("김씨성을 가진 사람들======startsWith");
		for(int i=0; i<namelist.size(); i++) {
			String name2 = namelist.get(i);
			if(name2.startsWith("김")) {
				System.out.println(name2);
			}
		}
		//indexof이용 -- 미완성
		System.out.println("김씨성을 가진 사람들======indexof");
		for(int i=0; i<namelist.size(); i++) {
			if(namelist.indexOf("김") == -1) {
				System.out.println(namelist.get(i));
			}
		}
	}

}
