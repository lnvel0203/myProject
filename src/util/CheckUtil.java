package util;

import java.util.HashMap;
import controller.Admin;
import dto.MemberInfo;
import service.MemberManageService;

public class CheckUtil {
	private final int MAX = 20;
	private final int MIN = 8;
	private final String REGEX = "^((?=.*\\d)(?=.*[a-zA-Z])(?=.*[\\W]).{" + MIN + "," + MAX + "})$";
	private MessageWriter in;
	private MessagePrinter out;
	private MemberInfo memberInfo;

	public CheckUtil(){
		this.in = new MessageWriter();
		this.out = new MessagePrinter();

	}
	public boolean registercheck() {
		out.systemMessage("ID를 입력하세요.");
		String id = in.write();
		
			if(id.equals(Admin.ID)) {
				out.systemMessage("사용할 수 없는 ID입니다.");
				return false;
				
			}else if(MemberManageService.getInstance().member.containsKey(id)){
				out.systemMessage("중복된 ID입니다.");
				return false;
				
			}else {
				out.systemMessage("비밀번호를 입력하세요. 대소문자와 특수문자를 혼합한 8~20자의 비밀번호를 입력해주세요.");
				String password = in.write();
				
				if(!password.equals("")|| password == null) {
					if(password.matches(REGEX)) {
		
						out.systemMessage("이름을 입력하세요.");
						String name = in.write();
						out.systemMessage("나이를 입력하세요.");
						int age = in.writeInt();
						out.systemMessage("핸드폰 번호를 입력하세요. * - *는 붙이지마세요.");
						int tellnum = in.writeInt();
						out.systemMessage("주소를 입력하세요.");
						String address = in.write();
											
						MemberManageService.getInstance().member.put(id, new MemberInfo(id, password,name,age,tellnum,address));
						out.println(id + " 님의 가입을 축하합니다.");
						return true;
					
					}else {
						out.systemMessage("대소문자와 특수문자를 혼합한 8~20자의 비밀번호를 입력해주세요.");
						return false;
					}
				}else {
					out.systemMessage("공백을 제외한 대소문자와 특수문자를 혼합한 8~20자의 비밀번호를 입력해주세요.");
					return false;
				}
			}

	
	}
	//로그인 체크 메소드
	//부석현 - 23/1/28
	public boolean adLoginCheck(String id, String password) {
		if(Admin.ID.equals(id)) {
			if(Admin.PASSWORD.equals(password)) {
				out.printAlert("		    환영합니다.");
				return true;
			}else {
				out.println("아이디와 비밀번호가 틀립니다.");
				return false;
			}
		}else {
			out.println("아이디가 존재하지 않습니다.");
			return false;
		}
	}
	public boolean custLoginCheck(String id, String password) {

		if(MemberManageService.getInstance().member.containsKey(id)){
			if(password.equals(password)) {
				out.printAlert("		    환영합니다.");
				return true;
			}else {
				out.println("아이디와 비밀번호가 틀립니다.");
				return false;
			}
		}else {
			out.println("아이디가 존재하지 않습니다.");
			return false;
		}
	}


}
