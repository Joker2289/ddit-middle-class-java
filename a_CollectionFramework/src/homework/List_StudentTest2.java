package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class List_StudentTest2 {
//	문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
//    생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
// 
// 	이 Student객체들은 List에 저장하여 관리한다.
// 	List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과 총점의 역순으로 정렬하는 부분을 프로그램 하시오.
// 	(총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
// 	(학번 정렬기준은 Student클래스 자체에서 제공하도록 하고, 총점 정렬기준은 외부클래스에서 제공하도록 한다)
	public static void main(String[] args) {
		List_StudentTest2 stdTest = new List_StudentTest2();
		ArrayList<Student02> s = new ArrayList<>();
		s.add(new Student02("0001", "박정권", 50, 60, 10));
		s.add(new Student02("0005", "신유수", 100, 65, 78));
		s.add(new Student02("0004", "박용현", 89, 75, 54));
		s.add(new Student02("0002", "임지연", 89, 76, 100));
		s.add(new Student02("0003", "신유수", 100, 65, 78));
		
		stdTest.setRank(s);
		System.out.println("정렬 전");
		for(Student02 std : s) {
			System.out.println(std);
		}
		System.out.println("==========================");
		System.out.println("학번의 오름차순으로 정령");
		Collections.sort(s);
		for(Student02 std : s) {
			System.out.println(std);
		}
		System.out.println("==========================");
		System.out.println("총점의 내림차순 & 등수의 오름차순으로 정렬");
		Collections.sort(s, new sortDesc());
		for(Student02 std : s) {
			System.out.println(std);
		}
		
			
	}
	public void setRank(ArrayList<Student02> s) {
		
		for(Student02 std : s) {
			int rank = 1;
			for(Student02 std2 : s) {
				if(std.getTotal() < std2.getTotal()) {
					rank++;
				}
			}
			std.setRank(rank);
		}
	}
}


class Student02 implements Comparable<Student02>{
	String num;
	String name;
	int kor;
	int eng;
	int math;
	int total;
	int rank;
	
	public Student02(String num, String name, int kor, int eng, int math) {
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
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", total=" + total + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student02 o) {
		if(Integer.parseInt(this.getNum()) > Integer.parseInt(o.getNum())) {
			return 1;
		}else if(getRank() == o.getRank()) {
			return 0;
		}else {
			return -1;
		}
	}
	
}

class sortDesc implements Comparator<Student02>{

	@Override
	public int compare(Student02 o1, Student02 o2) {

		
//	if(o1.getRank() > o2.getRank()) {
//		return 1;
//	}else if(o1.getRank() == o2.getRank()) {
//		return 0;
//	}else {
//		return -1;
//	}
		

	if(o1.getTotal() == o2.getTotal()) {
		return o1.getNum().compareTo(o2.getNum()) * -1;
	}else {
	return Integer.compare(o1.getTotal(), o2.getTotal()) * -1;
	}
		
	}
	
}
