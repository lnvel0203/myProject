package mainModel;

import controller.Admin;
import service.AdminService;
import service.CustomerService;
import util.MessagePrinter;
import util.MessageWriter;
import util.CheckUtil;

public class modelView {

	private MessagePrinter out;
	private MessageWriter in;
	private CustomerService customerView;
	private AdminService adminView;
	private CheckUtil check;

	public modelView(){
		this.in = new MessageWriter();
		this.out = new MessagePrinter();
		this.adminView = new AdminService();
		this.customerView = new CustomerService();
		this.check = new CheckUtil();
	}

	public void mainView() {
		do {
			out.printAlert("	    35P 다과 카페에 오신것을 환영합니다.");
			out.println("    | [1] 로그인 | [2] 회원가입 | [3] 사이트 종료 |\n");
			int select = in.writeInt();
			if(select == 1) {
				out.printAlert("	    아이디와 비밀번호를 입력해주세요.");
				// 로그인 체크 메소드 호출
				login();

			}else if(select == 2) {
				out.println("회원가입 페이지 입니다.");
				//회원가입 메소드 호출
				register();
			}else if(select == 3) {
				//프로그램 종료 메소드 호출
				exit();
				break;
			}else {
				out.println("잘못입력하셨습니다.");
			}
		}while(true);

	}
	//로그인 체크 메소드
	public void login() {
		String id = in.write();
		String password = in.write();
		if(Admin.ID.equals(id)) {
			if(check.adLoginCheck(id, password) == true) {
				//관리자 화면 호출
				adminView();
			}
		}else {
			if(check.custLoginCheck(id, password) == true) {
				//고객 화면 호출
				customerView();
			}
		}
	}
	// 회원가입 메소드
	public void register() {
		
		check.registercheck();		
	}

	// 고객 화면 메소드
	public void customerView() {
		customerView.customerInit();

	}
	// 고객 화면 메소드
	public void adminView() {
		adminView.adminInit();

	}
	// 프로그램 종료 메소드
	public void exit() {
		out.printAlert("		프로그램을 종료합니다.");
		in.close();
	}

}
