package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
10마리의 말들이 경주하는 경마 프로그램 작성하기. 말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말 이름(String), 등수(int)를 멤버변수로 갖는다. 그리고, 이 클래스에는 등수를 
오름차순으로 처리할 수 있는 기능이 있다.( Comparable 인터페이스 구현) -> 정렬기능.
경기 구간은 1~50구간으로 되어 있다. 경기 중 중간중간에 각 말들의 위치를 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수 순으로 출력한다.
*/

public class HorseRacingTest {
	
	public static boolean race = true;
	public static ArrayList<Horse3> hList = new ArrayList<>();
	public static Horse3 h1 = new Horse3("1번 말");
	public static Horse3 h2 = new Horse3("2번 말");
	public static Horse3 h3 = new Horse3("3번 말");
	public static Horse3 h4 = new Horse3("4번 말");
	public static Horse3 h5 = new Horse3("5번 말");
	public static Horse3 h6 = new Horse3("6번 말");
	public static Horse3 h7 = new Horse3("7번 말");
	public static Horse3 h8 = new Horse3("8번 말");
	public static Horse3 h9 = new Horse3("9번 말");
	public static Horse3 h10 = new Horse3("10번 말");
	public static int nowRank = 0;
	
	public static void main(String[] args) {
		
		HorseRacingTest hrt = new HorseRacingTest();
		Scanner s = new Scanner(System.in);
		
		hrt.hList.add(h1);
		hrt.hList.add(h2);
		hrt.hList.add(h3);
		hrt.hList.add(h4);
		hrt.hList.add(h5);
		hrt.hList.add(h6);
		hrt.hList.add(h7);
		hrt.hList.add(h8);
		hrt.hList.add(h9);
		hrt.hList.add(h10);
		
		Thread th1 = new HRun();
			
		System.out.println("\n==== DDIT 경마장 ====");
		s.nextLine();
		System.out.println("10마리의 말들이 출발선에서 대기하고 있습니다.");
		System.out.println("enter키를 눌러 경기를 시작하세요!");
		s.nextLine();
		
		th1.start();	
		
		
//		boolean flag = true; // 이렇게 만들고싶은데 메인쓰레드랑 쓰레드 th1이랑 동시에 실행돼서 제대로 돌아가지가 않네.
//		while(flag) {		
//			System.out.println("\n==== DDIT 경마장 ====");
//			System.out.println("  1. 경기 시작");
//			System.out.println("  2. 프로그램 종료");
//			System.out.println("=====================");
//			System.out.print("번호 입력 : ");
//			String menu = s.nextLine();
//			
//			switch(menu){
//			case "1" :
//				Thread th1 = new HRun();
//				
//				System.out.println("\n10마리의 말들이 출발선에서 대기하고 있습니다.");
//				System.out.println("enter키를 눌러 경기를 시작하세요!");
//				s.nextLine();
//				
//				th1.start();	
//				
//				break;
//				
//			case "2" :
//				System.out.println("프로그램을 종료합니다. 감사합니다.");
//				flag = false;
//				break;
//				
//			default :
//				System.out.println("알맞은 번호를 입력해주세요.");
//				break;	
//			
//			}
//			
//		}
		
	}

}

class HRun extends Thread{
	@Override
	public void run() {
		HorseRacingTest hrt = new HorseRacingTest();
		Scanner s = new Scanner(System.in);
		int hNum = 0;
		Horse3 temp = new Horse3("temp");
		
		boolean flag = true;
		while(flag) {
			for(int i = 0; i < 7; i++) {
				hNum = (int)(Math.random()*10 + 1);
				hrt.hList.get(hNum-1).setPosition(hrt.hList.get(hNum-1).getPosition() + 1);
				
				for(int j = 0; j<hrt.hList.size(); j++) {
					if(hrt.hList.get(j).getPosition()==49 && !hrt.hList.get(j).isRanked()) {
						hrt.hList.get(j).setRank(hrt.nowRank);
						hrt.hList.get(j).setRanked(true);
						hrt.nowRank++;
					}
				}
			}	
			display();
			
			try {
				Thread.sleep(150);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			if(hrt.nowRank == 10) {
				flag = false;
				
				// 결과
				System.out.println("\n모든 말이 다 도착했습니다!");
				System.out.println("enter키를 눌러 결과를 확인하세요.");
				s.nextLine();
				result();
				
				// 정렬
				sortRank();				
				
			}
			
		}
		
	}
	
	private void sortRank() {
		Scanner s = new Scanner(System.in);
		HorseRacingTest hrt = new HorseRacingTest();
		Collections.sort(hrt.hList);
		
		System.out.println("\nenter키를 눌러 랭킹을 확인하세요.");
		s.nextLine();
		
		System.out.println("===== Ranking =====");
		System.out.println();
		
		for(int j = 0; j < hrt.hList.size(); j++) {
			for(int i = 0; i < hrt.hList.size(); i++) {
				if(hrt.hList.get(i).getRank() == j) {
					if(j == hrt.hList.size() - 1) {
						System.out.println("  " + (hrt.hList.get(i).getRank()+1)+"등 : " + hrt.hList.get(i).gethName());
					}else {
						System.out.println("  " + (hrt.hList.get(i).getRank()+1)+"등  : " + hrt.hList.get(i).gethName());
					}					
				}
			}			
		}
		
		System.out.println("\nenter키를 눌러 프로그램을 종료합니다.");
		s.nextLine();
		System.out.println("감사합니다.");

	}

	private void result() {
		HorseRacingTest hrt = new HorseRacingTest();
		
		System.out.println("===== Result =====");
		System.out.println();
		for(int i = 0; i < hrt.hList.size(); i++) {
			if(i == hrt.hList.size() - 1) {
				System.out.println("  " + (i+1) + "번 말 : " + (hrt.hList.get(i).getRank()+1)+"등");							
			}else {
				System.out.println("  " + (i+1) + "번 말  : " + (hrt.hList.get(i).getRank()+1)+"등");							
			}
		}

	}

	public void display() {
		System.out.println("\n\n\n\n");
		HorseRacingTest hrt = new HorseRacingTest();
		System.out.println();
		
		for(int j=0; j<10; j++) {
			if(j==9) {
				System.out.print((j+1)+ "번 말 : ");				
			}else {
				System.out.print((j+1)+ "번 말  : ");				
			}
			
			for(int i = 0; i < 49; i++) {
				if(i == hrt.hList.get(j).getPosition()) {
					System.out.print(">");
				}
				System.out.print("-");
			}	
			
			if(hrt.hList.get(j).getPosition()>48) {
				System.out.print("> 도착!");
			}
			
			System.out.println();
		}
	}
}

class Horse3 implements Comparable<Horse3>{
	
	private String hName;
	private int rank = 0;
	private boolean isRanked = false;
	private int position = 0;
	
	public Horse3(String hName) {
		super();
		this.hName = hName;
	}

	public String gethName() {
		return hName;
	}

	public void sethName(String hName) {
		this.hName = hName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public boolean isRanked() {
		return isRanked;
	}

	public void setRanked(boolean isRanked) {
		this.isRanked = isRanked;
	}
	
	@Override
	public int compareTo(Horse3 ho) {
		return String.valueOf(getRank()).compareTo(String.valueOf(ho.getRank()));
	}
	
}







