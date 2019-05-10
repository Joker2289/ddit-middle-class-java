package basic;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
		
		//Properties > 컬렉션을 담을수 있는 class > 담아서 파일 입출력을 지원한다 
		//Hashtables 의 하위 클래스 이다
		
		//Properties 는 Map보다 축소된 기능의 객체라고 할 수 있다
		//Map은 모든 형태의 객체 데이터를 key와 value값으로 사용할 수 있지만 
		//Properties는 key와 value값으로 String 만 사용할 수 있다
		
		//Map은 put(), get()메서드를 이용하여 데이터를 입출력하지만
		//Properties는 setProperty(), getProperty()메서드를 통해서 데이터를 입출력한다
		
		Properties prop = new Properties();
		
		prop.setProperty("name", "Jake");
		prop.setProperty("tel", "010-1111-2222");
		prop.setProperty("addr", "USA");
		prop.setProperty("Jake", "Boy");
		
		
		
		String name = prop.getProperty("name");
		String tel = prop.getProperty("tel");
		
		System.out.println("Jake > " + prop.getProperty("Jake"));
		System.out.println("name > " + name);
		System.out.println("tel > " + tel );
		System.out.println("address > " + prop.getProperty("addr"));
		
		
				
	}

}
