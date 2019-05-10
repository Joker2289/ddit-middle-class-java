package basic;

public class EnumTest {
	/*
	 * 열거형 > 상수값들을 선언하는 방법
	 * static final int A = 0;
	 * static final int B = 1;
	 * static final int C = 2;
	 * static final int D = 3;
	 * 
	 * enum Data {A, B, C, D}; //나중에도 값을 지정 안해주면 enum내부적으로 자동으로 0, 1, 2, 3...으로 지정된다
	 * 
	 * 열거형 선언하는 방법
	 * enum 열거형이름 {상수값1, 상수값2, ... 상수값n};
	 *
	 * enum은 class와 비슷하다
	 */
	
	// City 열거형 객체 선언 (기본값을 이용하는 열거형)
	public enum City {서울, 부산, 대구, 광주, 대전};
	
	// 데이터값을 임의로 지정한 열거형 객체 선언
	// 데이터값을 정해 줄 경우에는 생성자를 만들어서 괄호 속의 값이 변수에 저장되도록 해야한다
	public enum Season{
		봄("3월부터 5월까지"), 여름("6월부터 8월까지"), 가을("9월부터 11월까지"), 겨울("12월부터 2월까지");
		
		//괄호속의 값이 저장될 변수 선언
		private String str;
		
		//생성자 만들기 (열거형의 생성자는 제어자가 묵시적으로 'private'이다)
		Season(String data){ // > private Season(String data){ 와 같다 > enum자체에 private이 정의 되어있다
			str = data;
		}
		
		public String getStr() {
			return str;
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println(Card.CLOVER == Card.TWO); //true지만 false이어야 의미상 맞음
		//값은 같지만 타입이 다른경우..
		//System.out.println(EnumCard.Kind.CLOVER == EnumCard.Value.TWO);
		
		
		/*
		 * 열거형에 사용되는 메서드
		 * 1. name() > 열거형 상수의 이름을 문자열로 반환한다
		 * 2. ordinal() > 열거형 상수가 정의된 순서값을 반환한다(기본적으로 0부터 시작)
		 * 3. valueOf("열거형상수이름"); > 지정된 열거형에서 '열거형상수이름' 과 일치하는 열거형 상수를 반환함
		 */
		
		City mycity1; //열거형 객체변수 선언
		City mycity2;
		
		//열거형 객체변수에 값 저장하기
		mycity1 = City.valueOf("서울"); // City enum에서 '서울' 데이터를 가져온다
		mycity2 = City.서울;
		
		System.out.println("mycity1 > " + mycity1.name());
		System.out.println("mycity1의 ordinal > " + mycity2.ordinal());
		System.out.println("====================================");
		
		Season ss = Season.valueOf("여름"); // Season ss = Season.여름;
		System.out.println("name > " + ss.name());
		System.out.println("ordinal > " + ss.ordinal());
		System.out.println("get 메서드 > " + ss.getStr());
		System.out.println("====================================");
		
		//열거형이름.values() > 데이터를 배열로 가져온다
		Season[] enumArr = Season.values();
		for(int i=0; i<enumArr.length; i++) {
			System.out.println(enumArr[i].name() + " : " + enumArr[i].getStr());
		}
		System.out.println();
		
		for(City city : City.values()) {
			System.out.println(city + " : " + city.ordinal());
		}
		
		City city = City.대구;
		
		System.out.println(city == City.대전);
		System.out.println(city == City.대구);
		
		//compareTo > enum의 메서드 
		System.out.println("대구와 비교 > " + city.compareTo(city.대구));
		System.out.println("서울와 비교 > " + city.compareTo(city.서울));
		System.out.println("대전와 비교 > " + city.compareTo(city.대전));
	}

}

//enum이 생기기전에 (JDK1.5 이전) 쓰던 방식
class Card{
	static final int CLOVER = 0;
	static final int HEART = 1;
	static final int DIAMOND = 2;
	static final int SPADE = 3;
	
	static final int TWO = 0;
	static final int THREE = 1;
	static final int FOUR = 2;
	
	final int kind = 0;
	final int num = 0;
}

class EnumCard{
	enum Kind { CLOVER, HEART, DIAMOND, SPADE };
	enum Value { TWO, TREE, FOUR };
	
	final Kind kind = Kind.CLOVER;
	final Value value = Value.TWO;
}
