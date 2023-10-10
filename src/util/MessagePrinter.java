package util;

import java.util.Map.Entry;

import dto.Refreshments;

public class MessagePrinter{

	private final String alertLine = "==================================================\n";

	public void printInt(int num) {
		System.out.println(alertLine+ "\n" +num+"번 상품을 선택하셨습니다." + "\n" + alertLine);
	}
	public void priceprint(int num) {
		System.out.println("*** "+num+"원 ***");
	}
	
	public void PrintList(Entry<Integer, Refreshments> msg) {
		System.out.println(msg);
	}
	
	public void print(String msg) {
		System.out.print(msg);
	}
	public void println(String msg) {
		System.out.println(msg);
	}
	public void systemMessage(String msg) {
		System.out.println("*** "+msg+" ***");
	}
	public void printAlert(Refreshments menulist) {
		print(alertLine + menulist + "\n" + alertLine);
	}
	public void printAlert(String msg) {
		print(alertLine + msg + "\n" + alertLine);
	}
	public void printAlert(String msg, String menu) {
		print(alertLine + msg + "\n" + menu + "\n" + alertLine);
	}
}
