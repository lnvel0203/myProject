package service;

import controller.AdminImpl;
import util.MessagePrinter;
import util.MessageWriter;

public class AdminService {
	private MessageWriter in;
	private MessagePrinter out;
	private AdminImpl admin;

	public AdminService(){
		this.in = new MessageWriter();
		this.out = new MessagePrinter();
		this.admin = AdminImpl.getInstance();
	}

	public void adminInit() {
	
		do {
			out.printAlert("     | [1] 재고관리 | [2] 주문내역 | [3] 로그아웃 |\n");
			int num = in.writeInt();

			if(num == 1) {
				boolean back = true;
				do {
					out.printAlert(" | [1] 상품목록 | [2] 상품추가 | [3] 상품수정 | [4] 상품삭제 | [0] 뒤로가기 |\n ");

					int select = in.writeInt();
					switch(select) {
					case 1 : out.printAlert("		      상품목록");
					admin.productSelect();
					break;
					case 2 : out.printAlert("		      상품추가");
					admin.productAdd();
					break;
					case 3 : out.printAlert("		      상품수정");
					admin.productUpdate();
					break;
					case 4 : out.printAlert("		      상품삭제");
					admin.productRemove();
					break;
					case 0 : 
						back = false;
						break;
					default : 
						out.printAlert("잘못 입력하셨습니다.");
					}
				}while(back);

			}else if(num == 2) {
				boolean back = true;
				do {
					out.printAlert(" | [1] 주문목록 | [0] 뒤로가기 |\n ");
					int select = in.writeInt();
					switch(select) {
					case 1 : out.printAlert("		      주문목록");
					admin.orderSelect();
					break;
					case 0 : 
						back = false;
						break;
					default : 
						out.printAlert("잘못 입력하셨습니다.");
					}
				}while(back);
			}else if(num == 3) {
				out.printAlert("		      로그아웃");
				break;
			}
		}while(true);

	}


}
