package basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class Test2 {

	public static void main(String[] args) {
		//HashSet은 순서와 정렬 기능이 없으나
		//TreeSet은 자동 정렬 기능이 있다
		
		TreeSet<String> ts = new TreeSet();
		//알파 대문자를 문자열로 변환하여 거꾸로 TreeSet에 저장
		System.out.println("알파벳을 TreeSet에 거꾸로 저장");
		for(char ch = 'Z'; ch >= 'A'; ch--) {
			String tmp = String.valueOf(ch);
			ts.add(tmp);
		}
		System.out.println("TreeSet > " + ts);
		
		
		
	}

}
