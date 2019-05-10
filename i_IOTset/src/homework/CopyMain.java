package homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyMain {
	public static void main(String[] args) throws IOException {
		String path = CopyMain.class.getResource("").getPath();
		System.out.println(path);
		
		FileInputStream fr = new FileInputStream("/Users/pjk/Documents/IO_test/"+"Japan8.jpg");
		BufferedInputStream bin = new BufferedInputStream(fr,10000);
		FileOutputStream fw = new FileOutputStream("/Users/pjk/Documents/IO_test/복사본_Japan8.jpg");
		BufferedOutputStream bout =new BufferedOutputStream(fw,10000);
		int c;
		while((c=bin.read())!=-1) {
			bout.write(c);
		}
		fw.flush();
		bout.flush();
		fw.close();
		fr.close();
	}
}
