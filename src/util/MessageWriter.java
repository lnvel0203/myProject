package util;

import java.util.InputMismatchException;
import java.util.Scanner;




public class MessageWriter {
	private Scanner sc;

	public MessageWriter() {
		this.sc = new Scanner(System.in);
	}

	public MessageWriter(Scanner sc) {
		this.sc = sc;
	}
	
	
	public String next() {
		return sc.next();
	}
	

	public String writeString() {
		String str = "";
		do {
			try {
				System.out.print("입력란 : ");
				str = sc.next();
				break;
			}catch(InputMismatchException e) {
				System.err.println("잘못입력하셨습니다");
				sc.next();
				continue;
			}
			
		}while(true);

		return str;
	}
	
	
	public String write() {
		String str = "";
		do {
			try {
				System.out.print("입력란 : ");
				str = sc.next();
				break;
			}catch(InputMismatchException e) {
				System.err.println("잘못입력하셨습니다");
				sc.next();
				continue;
			}
			
		}while(true);

		return str;
	}
	
	
	
	public int writeInt() {
		
		int num = 0;					
		do {
			try {
				System.out.print("입력란 : ");
				num = sc.nextInt();
				break;
			}catch(InputMismatchException e) {
				System.err.println("잘못입력하셨습니다");
				sc.next();
				continue;
			}
			
			
		}while(true);

		return num;

	}
	
	public void close() {
		sc.close();
	}
	

	
}
