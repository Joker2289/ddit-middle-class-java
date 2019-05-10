package homework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class lotto {
	Set<Integer> num = new TreeSet<>();
	
	public void getNum() {
		while(num.size() < 6) {
			num.add((int)(Math.random() * 45)+1);
		}
	}

	@Override
	public String toString() {
		Iterator<Integer> it = num.iterator();
		// 문자열 담을 변수
		String str = "";
		// 이터레이터로 set하나씩 가져오기
		while (it.hasNext()) {
			str += it.next() + ", ";
		}
		// 문자열 마지막 2자리 자르기
		str = str.substring(0, str.length() - 2);
		return str;
	}// End Lotto_Class
}

public class Set_Lotto {
	int pay; 	// 투입금액
	int money; 	// 거스름돈
	int count; 	// 출력 횟수
	
	public void in_pay(int pay) {
		this.pay = pay;
		this.money = pay%1000;
		this.count = pay/1000;
	}

	//구매메서드
	public List<lotto> buying(){ 
		List<lotto> get_lotto = new ArrayList<>();
		System.out.println();
		System.out.println("구매중..");
		System.out.println("1000원당 로또번호 6개");
		System.out.print("구매금액 > ");
		Scanner s = new Scanner(System.in);
		int m = s.nextInt();
		in_pay(m);
		
		for(int i=0; i<this.count; i++) {
			lotto lt = new lotto();
			lt.getNum();
			get_lotto.add(lt);
		}
		return get_lotto;
	}
	
	public void out_data(List<lotto> get_lotto) {
		Iterator<lotto> it = get_lotto.iterator();
		System.out.println("------------------------------");
		int i = 0;
		while(it.hasNext()) {
			i++;
			System.out.println(i+"번 로또 > "+it.next());
		}
		System.out.println("------------------------------");
		System.out.println("구매금액 : " + (this.pay - this.money));
		System.out.println("잔돈 : " + money);
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		boolean button = true;
		while(button) {
			System.out.println("===========================");
			System.out.println("Lotto 프로그램");
			System.out.println("---------------------------");
			System.out.println("1.Lotto 구입");
			System.out.println("2.프로그램 종료");
			System.out.println("===========================");
			System.out.print("SELECT > ");
			int i = s.nextInt();
			switch(i) {
				case 1:
					Set_Lotto sl = new Set_Lotto();
					sl.out_data(sl.buying());
					break;
				case 2:
					button = false;
					System.out.println("종료");
					break;
			}
		}
	}
}





