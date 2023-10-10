package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dto.Refreshments;
import util.MessagePrinter;
import util.MessageWriter;

//싱글톤으로 만들기
public class CustomerImpl implements Customer{

	private static CustomerImpl instanceC = new CustomerImpl();
	private MessageWriter in;
	private MessagePrinter out;
	HashMap<Integer, Refreshments> custGood;
	HashMap<Integer, Refreshments> buyGood;
	HashMap<Integer, Refreshments> refGood;
	HashMap<Integer, Refreshments> returnGood;
	public Map<Integer, Refreshments> map;
	List<Refreshments> menuList;
	private int i = 0;

	private CustomerImpl() {
		in = new MessageWriter();
		out = new MessagePrinter();
		custGood = new HashMap<Integer, Refreshments>();
		buyGood = new HashMap<Integer, Refreshments>();
		refGood = new HashMap<Integer, Refreshments>();
		returnGood = new HashMap<Integer, Refreshments>();
		map = new HashMap<Integer, Refreshments>();
		menuList = new ArrayList<Refreshments>();
	
	}
	//부석현 23/1/28 싱글톤 메소드 정의
	public static CustomerImpl getInstance() {
		if(instanceC == null) {
			instanceC = new CustomerImpl();

		}
		return instanceC;
	}


	// 부석현 / 1월 10일 / 장바구니 UI 메소드 정의
	@Override
	public void cartList() {

		do {
			if(custGood.size()<=0) {
				out.systemMessage("		목록이 없습니다.	");
			}else {
				out.systemMessage("장바구니 목록");
				for(Map.Entry<Integer, Refreshments> entry : custGood.entrySet()) {
					out.println(entry.toString());
				}
			}

			out.printAlert("| [1] 장바구니 추가 | [2] 장바구니 구매 | [3] 장바구니 삭제 | [0] 뒤로가기 |");
			int select = in.writeInt();
			if(select == 1) {

				out.systemMessage("장바구니 추가");
				cartAdd();

			}else if(select == 2) {
				out.systemMessage("장바구니 구매");
				cartbuy();

			}else if(select == 3) {
				out.systemMessage("장바구니 삭제");
				cartRemove();

			}else if(select == 0){
				break;
			}else {
				out.systemMessage("잘못 입력하셨습니다.");
			}
		}while(true);

	}
	// 부석현 / 1월 10일 / 장바구니 추가 메소드 정의
	@Override
	public void cartAdd() {

		do {
			out.printAlert("장바구니에 넣을 다과 코드를 입력하세요. "+ "| [0] 뒤로가기 |");
			int key = in.writeInt();

			if(AdminImpl.getInstance().adGood.containsKey(key)) {
				Refreshments custgood = AdminImpl.getInstance().adGood.get(key); 
				custGood.put(key, custgood);
				out.systemMessage("추가성공");
			}else if(key == 0){
				break;
			}else {
				out.systemMessage("잘못 입력하셨습니다.");
			}
		}while(true);
	}

	// 부석현 / 1월 10일 / 장바구니 삭제 메소드 정의
	@Override
	public void cartRemove() {

		if(custGood.size()<=0) {
			out.systemMessage("장바구니에 추가한 다과가 없습니다.");
		}else {
			for(Map.Entry<Integer, Refreshments> entry : custGood.entrySet()) {
				out.println(entry.toString());

				out.systemMessage("장바구니에 추가할 다과 코드를 입력하세요." + "[0 뒤로가기]");
				int key = in.writeInt();
				if(custGood.containsKey(key)) {

					custGood.remove(key);

					out.systemMessage("삭제 완료.");
					break;

				}else {
					out.systemMessage("잘못 입력하셨습니다.");
				}
			}
		}
	}
	// 부석현 / 1월 10일 / 장바구니 결제 메소드 정의
	@Override
	public void cartbuy() {
		do {
			if(custGood.size() <= 0) {
				out.printAlert("장바구니에 추가한 다과가 없습니다.");
				break;
			}else {

				out.printAlert("구매할 다과 코드를 입력하세요. " + " [0] 뒤로가기 ");
				int key = in.writeInt();

				if(custGood.containsKey(key)) {

					out.printAlert("구매할 수량을 입력하세요.");
					int csStock = in.writeInt();

					if(AdminImpl.getInstance().adGood.get(key).getStock() <= csStock) {

						out.systemMessage("재고가 부족합니다.");
						break;

					}else {

						buyGood.put(key, new Refreshments(custGood.get(key).getId(), custGood.get(key).getName(),csStock,custGood.get(key).getPrice()));

						menuList.add(i, buyGood.get(key));
						int num = i++;
						for (Refreshments item : menuList) {

							map.put(num, new Refreshments(item.getId(), item.getName(), item.getStock(), item.getPrice()));
						}
						out.systemMessage("구매완료");	
						buyGood.clear();
										
					}

				}else if(key == 0){
					break;
				}else {
					out.systemMessage("장바구니에 추가한 목록이 아닙니다.");
				}
			}
		}while(true);

	}


	// 부석현 / 1월 10일 / 바로구매 메소드 정의
	@Override
	public void nowBuy() {

		do {
			out.printAlert("구매할 다과 코드를 입력하세요. " + " [0] 뒤로가기 ");
			int key = in.writeInt();

			if(AdminImpl.getInstance().adGood.containsKey(key)) {

				out.printAlert("구매할 수량을 입력하세요.");
				int csStock = in.writeInt();

				if(AdminImpl.getInstance().adGood.get(key).getStock() <= csStock) {

					out.systemMessage("재고가 부족합니다.");
					break;

				}else {

					buyGood.put(key, new Refreshments(AdminImpl.getInstance().adGood.get(key).getId(),
							AdminImpl.getInstance().adGood.get(key).getName(),
							csStock,
							AdminImpl.getInstance().adGood.get(key).getPrice()));

					menuList.add(i, buyGood.get(key));
					int num = i++;
					for (Refreshments item : menuList) {

						map.put(num, new Refreshments(item.getId(), item.getName(), item.getStock(), item.getPrice()));
					}
					out.systemMessage("구매완료");
					buyGood.clear();
							
				}

			}else if(key == 0){
				break;
			}else {
				out.systemMessage("진열된 다과 목록이 없습니다.");
			}

		}while(true);

	}
	// 부석현 / 1월 28일 / 구매요청 목록 확인 및 환불 메소드 정의
	@Override
	public void refund() {
		do {

			out.printAlert(" | [1] 구매 요청 목록 | [2] 환불 | [0] 뒤로가기 | ");
			int select = in.writeInt();
			if(select == 1) {
				if(menuList.size() <= 0) {
					out.systemMessage("구매 요청 목록이 없습니다.");
				}else {
					out.systemMessage("구매 요청 목록");
					for(Map.Entry<Integer,Refreshments> entry : map.entrySet()) {
						System.out.println(entry.toString());
					}
				}

			}else if(select == 2) {

				if(refGood.size()<=0) {
					out.systemMessage("		목록이 없습니다.	");
				}else {
					do {
						out.systemMessage("구매 목록");
						for(Map.Entry<Integer,Refreshments> entry : refGood.entrySet()) {
							System.out.println(entry.toString());
						}
											
						out.systemMessage("환불할 다과 코드를 입력하세요. [999] 뒤로가기");
						int key = in.writeInt();
						if(refGood.containsKey(key)) {
							returnGood.put(key, refGood.get(key));
							out.systemMessage("환불 요청 완료.");

						}else if(key == 999){
							break;
						}else {
							out.systemMessage("환불할 다과 목록이 없습니다.");
						}
					}while(true);

				}

			}else if(select == 0) {
				break;
			}else {
				out.systemMessage("잘못 입력하셨습니다.");
			}
		}while(true);


	}

}
