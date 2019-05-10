package basic;

@FunctionalInterface
public interface LambdaTestInterface1 {
	//반환값이 없고 매개변수도 없는 추상메서드 선언
	public void test();
}

@FunctionalInterface //어노테이션이 필수는 아니지만 실수로 메서드를 더 선언할 수도있기때문에(선언하면 에러남)
interface LambdaTestInterface2 {
	//반환값이 없고 매개변수가 있는 추상메서드 선언
	public void test(int a);
}

@FunctionalInterface
interface LambdaTestInterface3 {
	//반환값이 있고 매개변수도 있는 추상메서드 선언
	public int test(int a, int b);
}
