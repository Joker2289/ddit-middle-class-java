package basic;

import java.util.ArrayList;
import java.util.Scanner;

public class List_ArrayListTest2 {
	
	public static void main(String[] args) {
		
		
		// 문제) 5명의 별명을 입력하여 ArrayList에 저장하고 별명의 길이가 제일 긴 별명을 출력
		
		//별명 입력
		ArrayList<String> name = new ArrayList();
		
		Scanner s = new Scanner(System.in);
		for(int i=0; i<3; i++) {
			System.out.println(i + "번 별명입력 > ");
			name.add(s.nextLine());
		}
		
		//제일 긴 별명 저장할 변수 
		/*String maxName = name.get(0);
		for(int i=0; i<name.size(); i++) {
			if(maxName.length() < name.get(i).length()) {
				maxName = name.get(i);
			}
		}
		System.out.println("제일 긴 별명 > " + maxName);*/
		
		
		
		
		// 문제) 문제 1에서 별명의 길이가 같은 것을 여러개 입력했을 때도 처리되도록 하시오 
		//제일 긴 변명의 길이를 저장할 변수
		int mni = name.get(0).length();
		for(int i=0; i<name.size(); i++) {
			if(mni < name.get(i).length()) {
				mni = name.get(i).length();
			}
		}
		
		int j=0;
		for(int i=0; i<name.size(); i++) {
			if(mni == name.get(i).length()) {
				j++;
				System.out.println("가장 긴 별명 " + j + " > " + name.get(i));
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
