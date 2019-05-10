package basic;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/*
		- 파일명.exists() 			: 파일 존재여부 ture / false 반환 
		- 파일명.createNewFile()  	: 파일 생	성 성공시 true / 실패시 false 반환
		- 디렉토리명.listFiles() 		: 디렉토리 안에 있는 모든 (파일+디렉토리)를 객채를 Object 타입으로 반환
		- 디렉토리명.list()			: 디렉토리 안에 있는 모든 (파일+디렉토리)의 이름을 String 타입으로 반환
		- 파일명.canRead()			: 파일이 읽기가능 인지 여부 true / false
		- 파일명.canWrite()			: 파일이 쓰기가능 인지 여부 true / false
		- 파일명.isHidden()			: 파일이 숨겨져 있는지 여부 true / false
		- 파일명.lastModified()		: 마지막 수정된 시간을 반환
		
		- 날짜 포맷을 지정 		 : SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		- 파일의 마지막 수정날짜 출력 : sdf.format(new Date(f1.lastModified())); 
 */
public class B_FileTest {

	public static void main(String[] args) {
		File f1 = new File("/Users/pjk/Documents/IO_test/sample2.txt");
		File f2 = new File("/Users/pjk/Documents/IO_test/test.txt");
		
		System.out.println(new Date(f1.lastModified()));
		
		if(f1.exists()){
			System.out.println(f1.getAbsolutePath() + "은 존재합니다.");
		}else{
			System.out.println(f1.getAbsolutePath() + "은 없는 파일입니다.");
			try {
				if(f1.createNewFile()){
					System.out.println(f1.getAbsolutePath() + "파일을 새로 만들었습니다.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(f2.exists()){
			System.out.println(f2.getAbsolutePath() + "은 존재합니다.");
		}else{
			System.out.println(f2.getAbsolutePath() + "은 없는 파일입니다.");
		}
		System.out.println("-------------------------------------------------");
		System.out.println("# Object 타입으로 반환");
		File f3 = new File("/Users/pjk/Documents/IO_test");
		File[] files = f3.listFiles();
		for(int i=0; i<files.length; i++){
			System.out.print(files[i].getName() + " => " );
			if(files[i].isFile()){
				System.out.println("파일");
			}else if(files[i].isDirectory()){
				System.out.println("디렉토리");
			}
		}
		
		System.out.println("============================================");
		System.out.println("# String 타입으로 반환");
		String[] strFiles = f3.list();
		for(int i=0; i<strFiles.length; i++){
			System.out.println(strFiles[i]);
		}
		System.out.println("---------------------------------------");
		System.out.println();
		
		File f4 = new File("/Users/pjk/Documents/");   
		displayFileList(f4);  // 메서드 호출
	}  // main메서드 끝
	
	// 지정된 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주는 메서드
	public static void displayFileList(File dir){
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리의 내용");
		
		// 디렉토리 안의 모든 파일 목록을 가져온다.
		File[] files = dir.listFiles();
		
		// 하위 디렉토리 정보를 저장할 ArrayList 생성(File배열의 인덱스 저장)
		ArrayList<Integer> subDirList = new ArrayList<Integer>();
		
		// 날짜를 출력하기 위한 형식 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
		for(int i=0; i<files.length; i++){
			String attr = ""; // 파일의 속성(읽기, 쓰기, 히든, 디렉토리 구분)
			String size = ""; // 파일 용량
			
			if(files[i].isDirectory()){
				attr = "<DIR>";
				subDirList.add(i);  // 첨자를 List에 추가
			}else{
				size = files[i].length() + " ";
				attr = files[i].canRead() ? "R" : " ";
				attr += files[i].canWrite() ? "W" : " ";
				attr += files[i].isHidden() ? "H" : " ";
			}
			
			//"%s %5s %12s ... = 글자 자릿수 지정
			System.out.printf("%s %5s %12s %s\n",
					sdf.format(new Date(files[i].lastModified())), attr,
					size, files[i].getName() );
		} // for문 끝..
		
		int dirCount = subDirList.size(); 			// 폴더안의 하위폴더 개수구하기
		int fileCount = files.length - dirCount; 	// 폴더안의 파일 개수 구하기
		
		System.out.println(fileCount + "개의 파일, " + dirCount + "개의 디렉토리");
		System.out.println();
		
		// 하위 폴더의 내용들도 출력하기 위해 
		// 현재 메서드를 재귀호출하여 처리한다
		for(int i=0; i<subDirList.size(); i++){
			displayFileList(files[subDirList.get(i)]);
		}
	}
}








