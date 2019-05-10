package basic;
/*
 * 제너릭 클래스 만드는 방법
 * 형식) Class 클래스명<타입글자> {
 * 			타입글자 변수명; // 변수 선언에 제너릭을 사용할 경우
 * 			...
 * 			타입글자 메서드명() { // 반환값이 있는 메서드에서 사
 * 				...
 * 				return 값;
 * 			}
 * 		}
 * 
 * 
 * 제너릭 타입 글자 
 * T > True
 * K > Key
 * V > Value
 * E > Element (자료구조에 들어가는 것들을 나타낼 때 사용)
 */

class NonGenericClass{
	private Object val;
	
	public Object getVal() {
		return val;
	}
	
	public void setVal(Object val) {
		this.val = val;
	}
}

class MyGeneric<T>{
	private T val;
	
	public T getVal() {
		return val;
	}
	
	public void setVal(T val) {
		this.val = val;
	}
}

public class GenericTest {
	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나타라마");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		String rtnNg1 = (String)ng1.getVal();
		System.out.println("문자열 반환값 rtnNG1 > " + rtnNg1);
		
		Integer irtnNg2 = (Integer) ng2.getVal();
		System.out.println("정수 반환값 irtnNg2 > " + irtnNg2);
		System.out.println();
		
		MyGeneric<String> mg1 = new MyGeneric();
		MyGeneric<Integer> mg2 = new MyGeneric();
		
		mg1.setVal("우리나라");
		mg2.setVal(500);;
		
		rtnNg1 = mg1.getVal();
		irtnNg2 = mg2.getVal();
		
		System.out.println("제너릭 문자열 반환값 > " + rtnNg1);
		System.out.println("제너릭 문자열 반환값 > " + irtnNg2);
	}
}
