package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Set_Lotto2 {
		
	private Set<Integer> lottoNumSet;  // 로또번호 저장
	private int lottoCnt;  	// 로또 횟수
	private int amount; // 받은돈
	private int change; // 거스름돈
	
	Set_Lotto2(int amount){
		this.amount = amount;
		this.lottoCnt = amount/1000;
		this.change = amount%1000;
	}
	
	
	/**
	 * 로또번호 가져오기
	 */
	public void getLottoNum() {
		
		for(int i = 0; i < lottoCnt; i++) {
			
			lottoNumSet = new HashSet<>(5); // 초기화
			
			while(lottoNumSet.size() < 5) {
				lottoNumSet.add((int)(Math.random()*45)+1); // 1~45 사이의 난수 가져오기
			}
			
			List<Integer> lottoNumList = new ArrayList<>(lottoNumSet); // 로또번호 정렬처리을 위한 임시 리스트
			
			Collections.sort(lottoNumList); // 로또번호 정렬처리
			
			System.out.print("로또번호"+ (i+1) + " : ");
			
			int cnt = 0;
			Iterator<Integer> it = lottoNumList.iterator();
			while(it.hasNext()) {
				if(cnt == 0) { // 첫번째 숫자인 경우...
					System.out.print(it.next());
				}else {
					System.out.print(", " + it.next());
					
				}
				
				cnt++;
			}
			
			System.out.println(); // 빈줄 출력
			
		}
		
	}
	
	/**
	 * 최종결과 출력
	 */
	public void printTotal() {
		
		System.out.println("받은 금액은 " + this.amount + "원이고 거스름돈은 " + this.change + "원입니다.");
	}
	
	public static void main(String[] args) {
		Set_Lotto2 lottoPrint = null;
		Scanner scan = new Scanner(System.in);
		
		int menuNum = 0; // 메뉴번호
		do {

			displayMenu(); // 기본메뉴 출력
			
			menuNum = scan.nextInt();
			
			switch (menuNum) {
			case 1:
				System.out.println();
				System.out.println("Lotto 구입 시작");
				System.out.println();
				System.out.println("(1000원에 로또번호 하나입니다.)");
				System.out.println("금액 입력 :");
				
				int amount = scan.nextInt();
				
				if(amount >= 1000) { // 로또 최소금액보다 크거나 같은 경우에 로또구매 시작
					
					lottoPrint = new Set_Lotto2(amount);// 내가모르던거
					
					System.out.println("행운의 로또번호는 아래와 같습니다.");
					
					lottoPrint.getLottoNum(); // 로또번호 출력
					
					lottoPrint.printTotal(); // 토탈금액 정보 출력
				}else {
					System.out.println("입력금액이 1000원보다 많아야 합니다.");
				}
				break;
				
			default:
				break;
			}
			
		}while(menuNum != 2); // 프로그램 종료 선택할때까지 반복
		
		System.out.println("감사합니다.");
		
		
	}
	
	/**
	 * 초기 메뉴출력
	 */
	public static void displayMenu() {
		System.out.println("==========================");
		System.out.println("      Lotto 프로그램                 ");
		System.out.println("--------------------------");
		System.out.println("  1. Lotto 구입");
		System.out.println("  2. 프로그램 종료");
		System.out.println("==========================");
		System.out.println("메뉴선택 :");
		
	}
	
	
}
