package kr.or.test;

import java.util.Scanner;
class Tire {
	public void run(){
		System.out.println("일반 타이어가 굴러갑니다.");
	}
}
class SnowTire extends Tire{
	@Override
	public void run () {
		System.out.println(" 스노우 타이어가 굴러갑니다");
	}
}

public class HelloWorld {

	public static void main(String[] args) {
			SnowTire snowTire =new SnowTire();
			Tire tire = snowTire;
			
			snowTire.run();
			tire.run();
			
		/*
		 * int score=85; String result = (!(score>90))? "가":"나"; // score가 90보다 크면 가 /
		 * 90보다 작으면 나 if (!(score>90)) { result="가"; }else{ result="나"; }
		 * System.out.println(result); int x = 10; int y = 5;
		 * System.out.println((x>7)&&(y<=5)); System.out.println((x<7)&&(y<=5));
		 * System.out.println((x<7)||(y<=5));
		 * 
		 * int hap = 0; for (int i = 1; i <= 100; i++) { hap += i; }
		 * System.out.println("1부터 100까지의 합은 " + hap + "입니다"); int cnt=1; int hop=0;
		 * while(cnt <=100) { hop = hop+cnt; cnt = cnt+1; } System.out.println(hop);
		 * 
		 * boolean run = true; int balance = 0; Scanner scanner = new
		 * Scanner(System.in);
		 * 
		 * while (run) { System.out.println("-------------------------");
		 * System.out.println(" 1.입금 |2.출금 |3.잔고 |4.종료");
		 * System.out.println("-------------------------");
		 * System.out.println("↑위 번호를 입력 해주세요>:"); int menuNum = scanner.nextInt();
		 * switch (menuNum) { case 1: System.out.println("입금액을 입력하세요>:"); balance +=
		 * scanner.nextInt(); break; case 2: System.out.println("출금액>:"); balance -=
		 * scanner.nextInt(); break; case 3: System.out.print("잔고>:");
		 * System.out.println(balance); break; case 4: run=false; break; default :
		 * System.out.println(" 해당 사항 없음 "); break; } System.out.println("종료"); }
		 */
		
	}
}