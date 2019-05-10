package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class List_studentTest {
//		문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
//	     생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
//	  
//	  	이 Student객체들은 List에 저장하여 관리한다.
//	  	List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과 총점의 역순으로 정렬하는 부분을 프로그램 하시오.
//	  	(총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
//	  	(학번 정렬기준은 Student클래스 자체에서 제공하도록 하고, 총점 정렬기준은 외부클래스에서 제공하도록 한다)
	public static void main(String[] args) {
		List_studentTest stdTest = new List_studentTest();
		ArrayList<Student> stdList = new ArrayList<>();
		
		stdList.add(new Student("201801", "홍길동", 50, 95, 65));
		stdList.add(new Student("201803", "성춘향", 95, 75, 70));
		stdList.add(new Student("201807", "강감찬", 90, 95, 80));
		stdList.add(new Student("201802", "변학도", 85, 90, 95));
		stdList.add(new Student("201804", "일지매", 55, 35, 80));
		stdList.add(new Student("201806", "이순신", 80, 85, 65));
		stdList.add(new Student("201805", "이몽룡", 75, 95, 85));
		
		stdTest.setRanking(stdList); // 등수를 구하는 메서드 호출
		
		System.out.println("정렬 전----------------------");
		for(Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("---------------------------");
		
		//학번의 오름차순으로 정렬하기
		Collections.sort(stdList);
		System.out.println("학번의 오름차순으로 정렬 후");
		for(Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("---------------------------");
		
		//총점의 내림차순으로 정렬하기
		Collections.sort(stdList, new SortbyTotal());
		System.out.println("총점의 내림차순으로 정렬 후");
		for(Student std : stdList) {
			System.out.println(std);
		}
	}
	
	//등수를 처리하는 메서드
	public void setRanking(ArrayList<Student> stdList) {
		for(Student std : stdList) {
			int rank = 1;
			for(Student std2 : stdList) {
				if(std.getTotal() < std2.getTotal()) {
					rank ++;
				}
			}
			std.setRank(rank);
		}
	}

}

class Student implements Comparable<Student>{
	private String num; 	//학번
	private String name;	//이름
	private int kor;		//국어점수
	private int eng;		//영어점수
	private int math;		//수학점수
	private int total;		//총합	
	private int rank;		//순위
	public Student(String num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = kor + eng + math;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rack) {
		this.rank = rack;
	}
	//학번을 기준으로 오름차순이 되도록 설정하기
	@Override
	public int compareTo(Student std) {
		return num.compareTo(std.getNum());
	}
	//화면에 학생정보 출력포맷을 정의
	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", total=" + total + ", rack=" + rank + "]";
	}
	
}
//총점의 역순으로 정렬하는데 총점이 같으면 학번의 내림차순으로 정렬되도록 한다 
class SortbyTotal implements Comparator<Student>{

	@Override
	public int compare(Student std1, Student std2) { 
		
		if(std1.getTotal() == std2.getTotal()) {
			return std1.getNum().compareTo(std2.getNum()) * -1;
		}else {
			return Integer.compare(std1.getTotal(), std2.getTotal()) * -1;
		}
	}
}







