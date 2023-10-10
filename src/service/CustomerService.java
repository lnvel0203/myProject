package service;

import java.util.Map;

import controller.AdminImpl;
import controller.CustomerImpl;
import dto.Refreshments;
import util.MessagePrinter;
import util.MessageWriter;


public class CustomerService {

	private MessageWriter in;
	private MessagePrinter out;
	private CustomerImpl customer;
	private MemberManageService memberManage;
	public CustomerService(){
		this.in = new MessageWriter();
		this.out = new MessagePrinter();
		this.memberManage = MemberManageService.getInstance();
		this.customer = CustomerImpl.getInstance();
	}

	public void customerInit() {

		do {

			out.printAlert("		    진열된 다과 목록 ");
			for(Map.Entry<Integer, Refreshments> entry : AdminImpl.getInstance().adGood.entrySet()) {
				System.out.println(entry.toString());
			}

			out.printAlert("     | [1] 장바구니 | [2] 구매및 환불 | [3] 마이페이지 | [4] 로그아웃 |\n");
			int num = in.writeInt();

			if(num == 1) {
				boolean back = true;
				do {
					out.printAlert(" | [1] 장바구니 목록 | [0] 뒤로가기 |\n ");
					int select = in.writeInt();
					switch(select) {
					case 1 : out.printAlert("		      장바구니");
					customer.cartList();
					break;
					case 0 : 
						back = false;
						break;
					default : 
						out.printAlert("잘못 입력하셨습니다.");
					}
				}while(back);

			}else if(num == 2) {
				out.printAlert(" | [1] 다과구매 | [2] 구매목록 | [0] 뒤로가기 |\n ");
				int select = in.writeInt();
				switch(select) {
				case 1 : out.printAlert("		      다과구매");
				customer.nowBuy();
				break;
				case 2 : out.printAlert("		      구매목록");
				customer.refund();
				break;
				case 0 : 
					break;
				default : 
					out.printAlert("잘못 입력하셨습니다.");
				}
			}else if(num == 3) {
				out.printAlert(" | [1] 회원정보 | [2] 회원수정 | [3] 회원삭제 | [0] 뒤로가기 |\n ");
				int select = in.writeInt();
				switch(select) {
				case 1 : out.printAlert("		      회원정보");
				memberManage.memberInfo();
				break;
				case 2 : out.printAlert("		      회원정보수정");
				memberManage.memberRemove();
				break;
				case 3 : out.printAlert("		      회원삭제");
				memberManage.memberRemove();
				break;
				case 0 : 
					break;
				default : 
					out.printAlert("잘못 입력하셨습니다.");
				}
			

				
				
			}else if(num == 4) {
				out.printAlert("		      로그아웃");
				break;
			}
		}while(true);

	}

}
