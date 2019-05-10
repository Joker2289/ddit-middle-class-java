package kr.or.ddit.watson.tts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

/**
 * IBM Text to Speech 서비스는 IBM의 음성합성기능을 사용하여
 * 다양한 언어, 방언 및 음성으로 텍스트를 자연스러운 발음으로
 * 합성하는 API를 제공한다.
 * 이 서비스는 각 언어에 대해 남성 또는 여성, 때로는 둘다를 
 * 지원한다.
 * @author HelloJava
 *
 */
public class TestTextToSpeech {
	
	static final String USER_NAME = "8d4ca723-7f3a-42c4-af7f-3d987b663cd0";
	static final String PASSWORD = "7KUEqHTB3TtO";

	private TextToSpeech service; // TextToSpeech 서비스
	
	/**
	 * 서비스 설정
	 */
	private void setService() {
		service = new TextToSpeech();
		service.setUsernameAndPassword(USER_NAME, PASSWORD);
	}
	
	
	/**
	 * 서비스 헤더 설정
	 * - watson은 기본적으로 서비스 사용에 대한 로그를 남겨
	 *   서비스를 개선하는데 사용하고 있다.
	 *   만약 watson에서 서비스의 내용을 바꾸길 원하지 않는다면
	 *   헤더에 내용을 명시해 주어야 한다.
	 */
	private void setHeader() {
		Map<String, String> headers = new HashMap<String, String>();
		// true는 허용, false는 불허
		headers.put("X-Watson-Learning-Opt-Out", "false");
		service.setDefaultHeaders(headers);
		
	}

	/**
	 * 음성 타입 검색
	 */
	private void getVoice() {
		ServiceCall<List<Voice>> serviceCall = service.getVoices();
		List<Voice> voiceList = serviceCall.execute();
		
		// watson에서 제공하는 음성타입
		for(Voice voice : voiceList) {
			System.out.println(voice);
		}
	}
	
	
	/**
	 * 서비스 실행
	 */
	private void executeService() {
		//아래의 문구가 저장경로에 올란 Wav파일에 음성으로 녹음
		String text = "fuck you man";
		InputStream stream = 
				service.synthesize(text, 
								   Voice.EN_ALLISON, // 음성종류
								   AudioFormat.WAV  // 오디오 포맷
								   ).execute();
		
		// 음성데이터를 재생
		try {

			InputStream in = WaveUtils.reWriteWaveHeader(stream);
			//File file = new File(URLDecoder.decode(getClass().getResource("").getPath())+"/hello_world.wav");
			File file = new File("/Users/pjk/Documents/hello_world.wav");
			OutputStream os = new FileOutputStream(file);
			byte[] tmp = new byte[1024];
			int length;
			while ((length = in.read(tmp)) != -1) {
				os.write(tmp, 0, length);
			}
			os.close();
			in.close();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestTextToSpeech textToSpeech = new TestTextToSpeech();
		textToSpeech.setService();
		textToSpeech.setHeader();
		//textToSpeech.getVoice();
		textToSpeech.executeService();
	}
}
