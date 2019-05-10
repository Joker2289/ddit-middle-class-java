package basic;
/*
 		3(개)명의 쓰레드가 각각 알파벳 대문자를 출력하는데 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기 
 */
public class DisplayCharacterTest {
	public static String strRank = "";
	
	public static void main(String[] args) {
		DisplayCharacter[] disChars = new DisplayCharacter[] {
				new DisplayCharacter("Blair"),
				new DisplayCharacter("Jake"),
				new DisplayCharacter("Tom")
		};
		for(int i=0; i<disChars.length; i++) {
			disChars[i].start();
		}
		
		for(DisplayCharacter dc : disChars) {
			try {
				dc.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("경기 끝");
		System.out.println("--------------------");
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순위 > " + strRank);
		 
		
	}
}

class DisplayCharacter extends Thread { 
	private String name;
	
	//생성자 
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++) {
			System.out.println(name + "의 출력문자 > " + ch);
			try {
				Thread.sleep((int)(Math.random()*301 + 200));
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + "출력 끝...");
		DisplayCharacterTest.strRank += name + " ";
	}
}