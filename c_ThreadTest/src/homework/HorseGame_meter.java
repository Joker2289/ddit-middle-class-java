package homework;

import javax.swing.JOptionPane;

public class HorseGame_meter{
   public static void main(String[] args) {
      // 말 쓰레드 5개 생성
      Horse1 horse1 = new Horse1("길동", new Rank());
      Horse1 horse2 = new Horse1("영희", new Rank());
      Horse1 horse3 = new Horse1("철수", new Rank());
      Horse1 horse4 = new Horse1("미희", new Rank());
      Horse1 horse5 = new Horse1("지희", new Rank());

      // 말 쓰레드 5개 실행
      horse1.start();
      horse2.start();
      horse3.start();
      horse4.start();
      horse5.start();

      Thread mythread = null;
      for (int i = 1; i < 5; i++) {
         mythread = new Horse1(i + "번", new Rank());
         mythread.start();
      }
   }
}

class Horse1 extends Thread {
   // 말의이름
   String h_name = " ";

   // 총길이
   int meter = 100;

   // 등수 매길 static 변수
   static int count = 1;
   Rank rank;

   // 기본 생성자 생성
   public Horse1() {  }
   public Horse1(String h_name, Rank rank) {
      this.h_name = h_name;
      this.rank = rank;
   }

   @Override
   public void run() {
      try {
         while(true) {
            sleep((int)(Math.random() * 2000+1));
            System.out.println(h_name + "말의 남은거리 :" + meter);
            
            //말이 20미터 단위로 이동
            meter -= 20;
            
            //meter가 0이 되면 결승점이기 때문에 rank.finishLine(말의이름)실행
            if(meter== 0) {
               this.rank.finishLine(h_name);
               break;
            }
         }
      }
      catch (Exception e) {
      }
   }
}

class Rank {
   int rank;
   public Rank() {  }

   public void finishLine(String name) {
      rank = Horse1.count++;
      System.out.println(name + "말" + rank + "등으로 결승점 도착");

      if (rank == 1) {
         JOptionPane.showMessageDialog(null, "1등말은" + name + "입니다!");
      }
   }
}