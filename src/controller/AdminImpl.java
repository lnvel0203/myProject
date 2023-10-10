package controller;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import dto.MemberInfo;
import dto.Refreshments;
import service.MemberManageService;
import util.MessagePrinter;
import util.MessageWriter;

public class AdminImpl implements Admin{

	//싱글톤
	//인터페이스 구현
	
	private static AdminImpl instanceA = new AdminImpl();
	private MessageWriter in;
	private MessagePrinter out;
	private Random random;

	public HashMap<Integer, Refreshments> adGood;
	private int total = 0;

	private AdminImpl() {
		this.in = new MessageWriter();
		this.out = new MessagePrinter();
		this.adGood = new HashMap<Integer, Refreshments>();
		this.random = new Random();

	}
	//부석현 23/1/28 싱글톤 메소드 정의
	public static AdminImpl getInstance() {
		if(instanceA == null) {
			instanceA = new AdminImpl();

		}
		return instanceA;
	}

	//부석현 23/1/28 상품목록 확인 메소드 정의
	@Override
	public void productSelect() {
		if(adGood.size() < 0) {
			out.systemMessage("입고된 상품이 없습니다.");
		}else {
			for(Map.Entry<Integer, Refreshments> entry : adGood.entrySet()) {
				System.out.println(entry.toString());
			}	
		}
	}

	//부석현 23/1/28 상품추가 메소드 정의
	@Override
	public void productAdd() {

		do{
			out.printAlert("| [1] 상품입고 | [0] 뒤로가기");
			int select = in.writeInt();

			if(select == 1) {
				out.systemMessage("상품의 코드는 난수로 정해집니다.");
				int id = random.nextInt(1000);
				out.systemMessage("다과 이름을 입력해주세요.");
				String name = in.write();
				out.systemMessage("다과 수량을 입력해주세요.");
				int stock = in.writeInt();
				out.systemMessage("다과 가격을 입력해주세요.");
				int price = in.writeInt();
				adGood.put(id, new Refreshments(id,name,stock, price));
			}else if(select == 0){
				break;
			}else {
				out.systemMessage("잘못입력하셨습니다.");
			}
		}while(true);
	}

	//부석현 23/1/28 상품수정 메소드 정의
	@Override
	public void productUpdate() {
		int num = 0;
		if(adGood.size() <= 0) {
			out.systemMessage("입고된 상품이 없습니다.");

		}else {
			for(Map.Entry<Integer, Refreshments> entry : adGood.entrySet()) {
				System.out.println(entry.toString());
			}	
			do {
				out.systemMessage("변경할 다과의 코드를 입력해 주세요.");
				num = in.writeInt();

				try {
					out.printInt(adGood.get(num).getId());
					break;
				}catch(NullPointerException e) {
					System.err.println("잘못입력하셨습니다");
					continue;
				}catch(InputMismatchException e) {
					System.err.println("잘못입력하셨습니다");
					in.next();
					continue;
				}
			}while(true);

			boolean back = true;
			do {
				out.systemMessage(" | [1] 상품명 | [2] 상품재고 | [3] 상품가격 | [0] 뒤로가기 | ");
				int set = in.writeInt();
				switch(set) {
				case 1 : 
				{
					out.systemMessage(" [1] 변경하기 | [0] 뒤로가기");
					int select = in.writeInt();							
					if(select == 1) {

						if(select == 0) {

						}else {
							out.printAlert("다과 이름을 변경합니다.");
							String name =  in.write();
							adGood.get(num).setName(name);
							out.printAlert("변경 완료");
						}

					}else {
						break;
					}
					break;
				}

				case 2 :
				{
					out.systemMessage(" [1] 변경하기 | [0] 뒤로가기");
					int select = in.writeInt();							
					if(select == 1) {
						if(select == 0) {
						}else {
							out.printAlert("다과 재고을 변경합니다.");
							int stock = in.writeInt();
							adGood.get(num).setStock(stock);
							out.printAlert("변경 완료");
						}

					}else {
						break;
					}
					break;
				}
				case 3 :
				{
					out.systemMessage(" [1] 변경하기 | [0] 뒤로가기");
					int select = in.writeInt();							
					if(select == 1) {

						if(select == 0) {

						}else {
							out.printAlert("다과 가격을 변경합니다.");
							int price = in.writeInt();
							adGood.get(num).setPrice(price);
							out.printAlert("변경 완료");
						}

					}else {
						break;
					}
					break;
				}

				case 0 : 
					back = false;
					break;

				default : out.systemMessage("잘못 입력하셨습니다.");

				}
			}while(back);
		}
	}

	//부석현 23/1/28 상품제거 메소드 정의
	@Override
	public void productRemove() {
		if(adGood.size() <= 0) {
			out.systemMessage("입고된 상품이 없습니다.");
		}else {
			for(Map.Entry<Integer, Refreshments> entry : adGood.entrySet()) {
				System.out.println(entry.toString());
			}	
			do {
				out.systemMessage("삭제할 다과의 코드를 입력해 주세요.");
				int num = in.writeInt();

				try {
					adGood.remove(adGood.get(num).getId());
					out.systemMessage("삭제 완료");
					break;
				}catch(NullPointerException e) {
					System.err.println("잘못입력하셨습니다");
					continue;
				}catch(InputMismatchException e) {
					System.err.println("잘못입력하셨습니다");
					in.next();
					continue;
				}
			}while(true);
		}

	}
	
	//부석현 23/1/28 결제대기 목록 메소드 정의
	@Override
	public void orderSelect() {
		out.printAlert("구매 대기중");

		for(Map.Entry<Integer,Refreshments> entry : CustomerImpl.getInstance().map.entrySet()) {
			System.out.println(entry);
		}

		do {
			out.printAlert("| [1] 결제승인 | [2] 결제취소 | [3] 결산 | [0] 뒤로가기 |");
			int select = in.writeInt();
			if(select == 1) {

				out.systemMessage("결제승인 요청");
				orderConfirm();

			}else if(select == 2) {
				out.systemMessage("결제취소 요청");
				orderCancle();

			}else if(select == 3) {
				out.systemMessage("결산");
				saleTotal();

			}else if(select == 0){
				break;
			}else {
				out.systemMessage("잘못 입력하셨습니다.");
			}
		}while(true);

	}
	
	// 부석현 23/1/28 결제승인 메소드 정의
	@Override
	public void orderConfirm() {
		
		if(CustomerImpl.getInstance().map.size() <= 0) {
			out.systemMessage("승인요청이 없습니다.");

		}else {

			do {

				out.systemMessage("구매 승인할 다과의 번호를 입력하세요. [999] 뒤로가기");
				int key = in.writeInt();

				if(key == 999){

					break;

				}else {

					if(CustomerImpl.getInstance().map.containsKey(key)) {
						if(adGood.get(CustomerImpl.getInstance().map.get(key).getId()).getStock() <  CustomerImpl.getInstance().map.get(key).getStock() ) {
							out.systemMessage("재고가 부족합니다.");
							break;
						}else {
							//결제금액 정산 코드
							out.systemMessage("결제승인");
							int sumprice = CustomerImpl.getInstance().map.get(key).getPrice() * CustomerImpl.getInstance().map.get(key).getStock();
							total = total + sumprice;
							
							//결제재고 정산 코드
							int adStock = adGood.get(CustomerImpl.getInstance().map.get(key).getId()).getStock() - CustomerImpl.getInstance().map.get(key).getStock();
							CustomerImpl.getInstance().refGood.put(key,CustomerImpl.getInstance().map.get(key));
							adGood.get(CustomerImpl.getInstance().map.get(key).getId()).setStock(adStock);
							
							//결제목록 삭제 코드
							CustomerImpl.getInstance().map.remove(key);
						}
					}else {
						out.systemMessage("잘못된 상품 코드입니다.");
					}
				}
			}while(true);
		}


	}
	// 부석현 23/1/28 환불 메소드 정의
	@Override
	public void orderCancle() {

		if(CustomerImpl.getInstance().returnGood.size() <= 0) {

			out.systemMessage("승인요청이 없습니다.");

		}else {

			do {
				out.printAlert("환불요청 대기중");
				for(Map.Entry<Integer, Refreshments> entry : CustomerImpl.getInstance().returnGood.entrySet()) {
					System.out.println(entry.toString());
				}	

				out.systemMessage("구매 승인할 다과의 번호를 입력하세요. [999] 뒤로가기");
				int key = in.writeInt();

				if(key == 999){

					break;

				}else {

					if(CustomerImpl.getInstance().returnGood.containsKey(key)) {
						
						out.systemMessage("환불승인");
						//환불금액정산 코드
						int sumprice = CustomerImpl.getInstance().returnGood.get(key).getPrice() * CustomerImpl.getInstance().returnGood.get(key).getStock();
						total = total - sumprice;
						
						//환불재고정산 코드
						int adStock = adGood.get(CustomerImpl.getInstance().returnGood.get(key).getId()).getStock() + CustomerImpl.getInstance().returnGood.get(key).getStock();
						adGood.get(CustomerImpl.getInstance().returnGood.get(key).getId()).setStock(adStock);
						
						//환불 구매목록 제거 코드
						CustomerImpl.getInstance().refGood.remove(key);
						CustomerImpl.getInstance().returnGood.remove(key);
					}else {
						out.systemMessage("잘못된 상품 코드입니다.");
					}
				}
			}while(true);
		}	

	}
	//부석현 23/1/28 결산체크 메소드 정의
	@Override
	public void saleTotal() {

		out.priceprint(total);
		out.printAlert("			 판매 내역");
	    for(Map.Entry<Integer, Refreshments> entry : CustomerImpl.getInstance().refGood.entrySet()) {
	    	out.PrintList(entry);
	    }
	}

}
