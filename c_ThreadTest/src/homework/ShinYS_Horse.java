package homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ShinYS_Horse {
	
	
/*
 * 10마리의 말들이 경주하는 경마 프로그램 작성하기

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

경기가 끝나면 등수 순으로 출력한다. */
	
	public static int rank000 = 1;
	
	
	
	public static List<Horse7> horseList = new ArrayList<Horse7>();
	public static PlayOut po = new PlayOut();
	
	
	
	public static void main(String[] args) {
		//po.setDaemon(true);
		
		int rules = 10;
		
		horseList.add(new Horse7("신유수",rules));
		horseList.add(new Horse7("강현욱",rules));
		horseList.add(new Horse7("박정권",rules));
		horseList.add(new Horse7("1번말",rules));
		horseList.add(new Horse7("2번말",rules));
		horseList.add(new Horse7("3번말",rules));
		
	

		
		for(Horse7 a : horseList) {
			a.start();
		}
		
		po.start();
		
		
		
		
		for(Horse7 a : horseList) {
			try {
				a.join();
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		po.interrupt();		
		
		

		Collections.sort(horseList);
		System.out.println(horseList);
		

	}
}

class Horse7 extends Thread implements Comparable<Horse7>{

	private String name2;
	private int rank;
	private String race[];
	
	private String outs;
	
	

	public String getOuts() {
		return outs;
	}



	public void setOuts(String outs) {
		this.outs = outs;
	}



	public Horse7(String name2,int rail) {
		super();
		this.name2 = name2;
		this.race = new String[rail+1];
		
		for(int i=0; i<this.race.length; i++) {
			race[i] = "-";	
		}
		
	}


	
	@Override
	public void run() {
		
		for(char ch=1; ch<race.length; ch++) {
	
			outs = "";
			race[ch]=">";
			race[ch-1]="-";
			

			for(int i=1; i<race.length; i++) {
				outs += race[i];
			}
			
			//System.out.println(this.name2 + "말 : " + outs);
			try {
				//sleep()메서드의 값을 200~500사이의 난수로 한다.
				Thread.sleep((int)(Math.random()*301+200));
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}//end for
		
		this.rank = ShinYS_Horse.rank000 ++;
		

		

	}
	
	
	public String getName2() {
		return name2;
	}

	public void setName2(String name) {
		this.name2 = name;
	}

	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "말이름 : "+ name2 +"  (랭킹 : " + rank + ")\n";
	}

	@Override
	public int compareTo(Horse7 o) {
		return Integer.compare(this.getRank(), o.getRank());
	}
	
}


class PlayOut extends Thread{
	
	

	@Override
	public void run() {
		
		boolean flag = true;
		while(flag) {
			
			
			
			if(Thread.interrupted()){// interrupt()메서드가 호출되면 
                // true
				System.out.println("[인터럽트 발동] if: Thread.interrupted() 에서 인터럽트 발동!");
				flag = false;
				
			}
			
			
			for(int i=0; i<50; i++) {
				System.out.println();
			}
			
			for(int i=0; i<ShinYS_Horse.horseList.size(); i++) {
				System.out.println(ShinYS_Horse.horseList.get(i).getName2() + "말 : " + ShinYS_Horse.horseList.get(i).getOuts());
			}
			
			try {
				//sleep()메서드의 값을 200~500사이의 난수로 한다.
				Thread.sleep(200);
			}catch(InterruptedException e) {
				System.out.println("[인터럽트 발동] sleep : try-catch에서 발동");
				break;
			}
			
			
		}
	

	}
	
	
}