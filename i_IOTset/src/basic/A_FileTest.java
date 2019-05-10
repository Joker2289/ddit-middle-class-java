package basic;

import java.io.File;

/*
		# new File(String 파일 또는 경로명)  >  Directory 사이 or File 사이의 구분 문자는 '\'를 사용하거나 '/'를 사용
       	- 파일명.getName() : 파일명 반환
       	- 파일명.isFile() : 파일 여부 true / false 반환
       	- 파일명.isDirectory() : directory(폴더) 여부 true / false 반환
       	
       	# new File(File parent, String child)  >  'parent' 디렉토리 안에 있는 'child' 파일 또는 디렉토리를 갖는다
       	
       	# new File(String parent, String child)
       	- 파일명.getAbsolutePath() : (절대경로) 경로 처음부터 끝까지 풀로 써준 경로
       	- 파일명.getPath() : (상대경로) 파일의 위치가 바뀌어도 이동 할 수 있다 
       	- 파일명.length()	 : 파일의 용량크기
       	
       	# 디렉토리(폴더만들기)
		- mkdir() 	> File객체의 경로중 마지막 위치의 디렉토리를 만든다
		- mkdirs() 	> 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후 마지막 위치의 디렉토리를 만들어 준다
	
		=> 위 두 메서드가 모두 만들기를 성공하면 ture , 실패하면 false 반환
		
		./ : 현재 디렉토리
		.. : 이전 디렉토리
 */

public class A_FileTest {
	public static void main(String[] args) {
		
		File file = new File("/Users/pjk/Documents/IO_test/test.txt"); 
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일여부 : " + file.isFile());
		System.out.println("디렉토리(폴더) 여부 : " + file.isDirectory());
		System.out.println("---------------------------------------------");
		
		
		//파일 or 디렉토리 체크
		File file2 = new File("/Users/pjk/Documents/IO_test");
		//File file2 = new File("/Users/test.txt");
		System.out.println(file2.getName() + "은 ");
		if(file2.isFile()) {
			System.out.println("파일");
		} else if(file2.isDirectory()) {
			System.out.println("디렉토리(폴더)");
		}
		System.out.println("---------------------------------------------");
		
		
		File file3 = new File(file2, "test.txt");
		System.out.println(file3.getName() + "의 용량크기 : " + file3.length() + "bytes");
		
		
		File file4 = new File("/Users/pjk/Documents/IO_test", "test.txt");
		System.out.println("절대 경로 : " + file4.getAbsolutePath());			// 절대경로 : 경로 처음부터 끝까지 풀로 써준 경로
		System.out.println("상대 경로 : " + file4.getPath());				 	// 상대경로 : 파일의 위치가 바뀌어도 이동 할 수 있다 
		System.out.println("---------------------------------------------");
		
		//directory(폴더)만들기
		File file5 = new File("/Users/pjk/Documents/IO_test/gg/");
		if(file5.mkdir()) {
			System.out.println(file5.getName() + " 만들기 성공!!");
		} else {
			System.out.println(file5.getName() + " 만들기 실패!!");
		}
		System.out.println("---------------------------------------------");
		
		
		File file6 = new File("/Users/pjk/Documents/IO_test/java/src");
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + "만들기 성공!!");
		} else {
			System.out.println(file6.getName() + "만들기 실패!!");
		}
	}
}
