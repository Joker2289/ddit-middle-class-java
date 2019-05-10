package basic;

import javafx.application.Application;
import javafx.stage.Stage;

/*
 * 		Stage(무대) => window창
 * 		Scene(장면) => 무대에서는 하나의 장면이 배치된다
 * 
 * 		# JavaFx가 실행되는 순서
 *   	 main()매서드 => launch()매서드 => 해당객체의 생성자 매서드
 *   	 => init()매서드 => start()매서드 => 사용 후 종료
 *    	 => stop()매서드
 */

public class a_JavaFxLifeCycle extends Application {
   
   public a_JavaFxLifeCycle() {
      System.out.println(Thread.currentThread().getName() + " : 생성자 함수 호출");
      
      
   }
   @Override
   public void init() throws Exception{
      System.out.println(Thread.currentThread().getName() + " : init()매서드 호출");
   }
   

   @Override
   public void start(Stage primaryStage) throws Exception {
      System.out.println(Thread.currentThread().getName() + " : start매서드 호출");
      primaryStage.show(); //창(윈도우) 보이기
      
   }
   
   @Override
   public void stop() throws Exception {
      System.out.println(Thread.currentThread().getName() + " : stop매서드 호출");
      
   }
   
   
   public static void main(String[] args) {
      System.out.println(Thread.currentThread().getName() + " : main()매서드 호출");
      launch(args);	//a_JavaFxLifeCycle 객체 생성 및 메인 윈도우 생성
      
   }

}