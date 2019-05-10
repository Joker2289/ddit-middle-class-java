package homework;

public class HorseRacing {
   public static String strRank="";
   public static int[] cursors= {0,0,0,0,0,0,0,0,0,0};
   public static boolean[] flags=new boolean[10];
   public static int cnt=0;
   public static void main(String[] args) {
      Horse5[] ths = new Horse5[10];
      
      for(int i=0;i<ths.length;i++) {
         ths[i]=new Horse5((i+1)+"번말");
         ths[i].start();
      }
      HorseStatePrint hsp = new HorseStatePrint();
      hsp.start();
      for(int i=0;i<ths.length;i++) {
         try {
            ths[i].join();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      try {
         hsp.join();
      } catch (InterruptedException e1) {
         e1.printStackTrace();
      }
      
      System.out.println("들어온 순서 : ");
      System.out.println(strRank);
   }
}

class Horse5 extends Thread{
   private String horseName;
   public Horse5(String horseName) {
      this.horseName = horseName;
   }

   @Override
   public void run() {
      int horseNum=Integer.parseInt(horseName.replace("번말",""))-1;
      int num=(int)((Math.random()*5)+1);
      while(HorseRacing.cursors[horseNum]<50) {
         HorseRacing.cursors[horseNum]+=num;
         try {
            Thread.sleep(500);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      HorseRacing.strRank+=horseName+" ";
   }
}

class HorseStatePrint extends Thread{
   @Override
   public void run() {
      while(HorseRacing.cnt<10) {
         HorseRacing.cnt=0;
         for(int i=0;i<HorseRacing.cursors.length;i++) {
            System.out.print((i+1)+"번말"+" : ");
            if(HorseRacing.cursors[i]>=50) {
               HorseRacing.cnt++;
            }
            printState(HorseRacing.cursors[i]);
         }
         System.out.println();
         System.out.println(HorseRacing.cnt);
         try {
            Thread.sleep(500);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
   void printState(int cursor) {
      for(int j=0;j<50;j++) {
         if(cursor==j) {
            System.out.print(">");
         }else {
            System.out.print("-");
         }
      }
      System.out.println();
   }
}