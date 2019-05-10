package homework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Map_PhoneBookTest {
	
	//문제) 이름, 주소, 전화번호 속성을 갖는 Phone클래스를 만들고, 이 Phone 클래스를 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오
	// 		> 이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체출력하는 기능이 있다.
	
	//		전체의 전화번호 정보는 Map을 이용하여 관리한다
	//		 > key는 '이름'으로 하고 value는 'Phone 클래스의 인스턴스'로 한다
	
	HashMap<String, Phone> info = new HashMap<>();
	public void main_page() {
		boolean button = true;
		while(button) {
			System.out.println("================================");
			System.out.println("\t전화번호 관리 프로그렘");
			System.out.println("================================");
			System.out.println("\t메뉴를 선택하세요");
			System.out.println("\t1.전화번호 등록");
			System.out.println("\t2.전화번호 수정");
			System.out.println("\t3.DATA 삭제");
			System.out.println("\t4.전화번호 검색");
			System.out.println("\t5.데이터 전체 출력");
			System.out.println("\t6.데이터 txt 출력");
			System.out.println("\t0.프로그램 종료");
			System.out.print("입력 > ");
			Scanner s = new Scanner(System.in);
			switch(s.nextInt()){
				case 1:
					input_data();
					break;
				case 2: 
					change_TEL();
					break;
				case 3: 
					del_DATA();
					break;
				case 4:
					search_TEL();
					break;
				case 5:
					out_ALLDATA();
					break;
				case 6:
					out_txt_ALLDATA();
					break;
				case 0:
					button = false;
					System.out.println();
					System.out.println("Program End..");
					break;
				default :
			}			
		}
	}
	
	//전화번호 등록
	public void input_data() {
		Scanner s = new Scanner(System.in);
		System.out.println();
		System.out.println("Input Your Data..");
		System.out.print("Name > ");
		String name = s.nextLine();
		System.out.print("Tel  > ");
		String TEL = s.nextLine();
		System.out.print("Address > ");
		String ADDRESS = s.nextLine();
		info.put(name, new Phone(TEL, ADDRESS)); // input Data in HashMap
		System.out.println();
		System.out.println("Iuput Data Complete !!");
		System.out.println();
	}
	
	//전화번호 수정
	public void change_TEL() {
		String name_key; //입력받은 이름을 저장할 변수
		String new_TEL;	 //수정할 전화번호를 저장할 변수
		System.out.println();
		System.out.println("Change Your Tel..");
		System.out.print("Your Name > ");
		Scanner s = new Scanner(System.in);
		name_key = s.nextLine();
		if(info.get(name_key) == null) { //Map에 데이터가 존재하는지 확인
			System.out.println();
			System.out.println("Not Found Data..");
			System.out.println();
		}else {
			System.out.println("Your Tel > " + info.get(name_key).getTel()); 
			System.out.print("New Tel > ");
			Scanner s2 = new Scanner(System.in);
			new_TEL = s2.nextLine();
			info.get(name_key).setTel(new_TEL); //전화번호 수정
			System.out.println();
			System.out.println("Change Tel Complete !!");
			System.out.println();
		}
	}
	
	//전화번호 삭제
	public void del_DATA() {
		String name_key;
		Scanner s = new Scanner(System.in);
		System.out.print("Your Name > ");
		name_key = s.nextLine();
		if(info.get(name_key) == null) {
			System.out.println();
			System.out.println("Not Found Data..");
			System.out.println();
		}else {
			System.out.println("Delete your information?? (YES = 0)");
			int yes = s.nextInt();
			if(yes == 0) {
				info.remove(name_key);
				System.out.println();
				System.out.println("Delete Complete !!");
				System.out.println();
			}
		}
	}
	
	//전화번호 검색
	public void search_TEL() {
		String name_key;
		Scanner s = new Scanner(System.in);
		System.out.print("Your Name > ");
		name_key = s.nextLine();
		if(info.get(name_key) == null) {
			System.out.println();
			System.out.println("Not Found Data..");
			System.out.println();
		}else {
			System.out.println();
			System.out.println("Search Complete !!");
			System.out.println("Your Tel > " + info.get(name_key).getTel());
			System.out.println();
		}
	}
	
	//데이터 전체 출력
	public void out_ALLDATA() {
		System.out.println("================================================");
		System.out.println("Num\tName\t   Tel\t\t  Address");
		System.out.println("================================================");
		int num = -1;
		Set<String> keySet = info.keySet();
		for(String key : keySet) {
			num++;
			System.out.println(" "+(num+1) + "\t" + key + "\t" + info.get(key).getTel() + "\t" + info.get(key).getAddr());
		}
		System.out.println("================================================");
		System.out.println();
	}
	
	//데이터 IO txt 파일로 출력
	public void out_txt_ALLDATA() {
		String tmp = "";
		try {
			FileWriter file = new FileWriter("/Users/pjk/Documents/IO_test/phonebook.txt");
			BufferedWriter bfw = new BufferedWriter(file);
			
				tmp += "================================================\n";
				tmp += "Num\tName\t   Tel\t\t  Address\n";
				tmp += "================================================\n";
				
				int num = 0;
				Set<String> keySet = info.keySet();
				for(String key : keySet) {
					num++;
					tmp += (" " + (num+1) + "\t" + key + "\t" + info.get(key).getTel() + "\t" + info.get(key).getAddr() + "\n");
				}
				
				tmp += "================================================\n";
				
				bfw.write(tmp);
				bfw.flush();
				bfw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//메인
	public static void main(String[] args) {
		Map_PhoneBookTest go = new Map_PhoneBookTest();
		go.main_page();
	}

}
//Phone class
class Phone {
	String tel;
	String addr;
	
	public Phone(String tel, String addr) {
		super();
		this.addr = addr;
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "TEL : " + tel + "  ADDRESS : " + addr;
	}
	
}
