package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
 			10마리의 말들이 경주하는 경마 프로그램 작성하기

			말은 Horse라는 이름의 클래스로 구성하고,
			이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
			그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
			기능이 있다.( Comparable 인터페이스 구현)
			
			경기 구간은 1~50구간으로 되어 있다.
			
			경기 중 중간중간에 각 말들의 위치를 나타내시오.
			예)
			1번말 --->------------------------------------
			2번말 ----->----------------------------------
			...
			
			경기가 끝나면 등수 순으로 출력한다.
 */
public class HorseGame {
	public static int rank = 1;			  //rank에 쓸 변수
	//순위계산을 하기위해 ArrayList에 Horse 클래스를 담는다
	static List<Horse> horse = new ArrayList();
	
	public static void main(String[] args) {
		Horse_print hp = new Horse_print();
		
		hp.setDaemon(true); //출력 Thread를 자동종료 하기위해 Daemon Thread로 설정
		
		//말생성
		horse.add(new Horse("키드밀리		"));
		horse.add(new Horse("루피		"));
		horse.add(new Horse("나플라		"));
		horse.add(new Horse("슈퍼비		"));
		horse.add(new Horse("PH-1		"));
		horse.add(new Horse("저스디스		"));
		horse.add(new Horse("빈지노		"));
		horse.add(new Horse("YoungB		"));
		horse.add(new Horse("더콰이엇		"));
		horse.add(new Horse("NOEL		"));

		hp.start();
		
		for(Horse hs : horse) {
			hs.start();
		};
		
		//마지막에 순위를 출력하기 위해 main Thread의 작업을 잠시 중단한다
		for(Horse hs2 : horse) {
			try {
				hs2.join();
			} catch (InterruptedException e) { }
		}
		
		Collections.sort(horse); //정렬
		System.out.println();
		//순위 출력
		System.out.println("순위");
		for(int i=0; i<horse.size(); i++) {
			System.out.println(horse.get(i));
		}
	}
}

class Horse extends Thread implements Comparable<Horse>{
	private String horse_name; 
	private int	horse_rank;
	private boolean arrive;
	ArrayList<String> race = new ArrayList();
	//생성자
	public Horse(String horse_name) {
		super();
		this.horse_name = horse_name;
		this.arrive = false;
		//생성과 동시에 죄표셋팅
		for(int i=0; i<40; i++) {
			race.add(i, "-");
		}
	}	

	@Override
	public void run() {
		for(int i=0; i<race.size(); i++) {
			String str = race.set(i, ">");
			if(i>=1) {
				race.set(i-1, str);
			}
			
			try {
				Thread.sleep((int)(Math.random()*800)); //Random Delay
			} catch (Exception e) { }
		}
		//발생 후 rank증가와 도착여부 표시 
		horse_rank = HorseGame.rank++;
		this.arrive = true;
	}

	public String getHorse_name() {
		return horse_name;
	}

	public void setHorse_name(String horse_name) {
		this.horse_name = horse_name;
	}

	public int getHorse_rank() {
		return horse_rank;
	}

	public void setHorse_rank(int horse_rank) {
		this.horse_rank = horse_rank;
	}

	public ArrayList<String> getRace() {
		return race;
	}

	public void setRace(ArrayList<String> race) {
		this.race = race;
	}

	public boolean isArrive() {
		return arrive;
	}

	public void setArrive(boolean arrive) {
		this.arrive = arrive;
	}

	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(this.getHorse_rank(), horse.getHorse_rank());
	}
	
	@Override
	public String toString() {
		return horse_rank + "등 " + horse_name;
	}
}

//말의 좌표 출력 Thread
class Horse_print extends Thread {
	@Override
	public void run() {
		while(true) {
			//전에 출력된 창을 위로 올리기위한 로직
			for(int i=0; i<20; i++) {
				System.out.println();
			}
			
			for(int i=0; i<HorseGame.horse.size(); i++) {
				System.out.print(HorseGame.horse.get(i).getHorse_name() + "   ");
				for(int x=0; x<HorseGame.horse.get(i).race.size(); x++) {
					System.out.print(HorseGame.horse.get(i).race.get(x));
				}
				//도착하면 좌표끝에 도착 출력
				if(HorseGame.horse.get(i).isArrive() == true) {
					System.out.print("도착");
				}
				System.out.println();
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}