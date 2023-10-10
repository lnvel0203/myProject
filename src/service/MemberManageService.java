package service;

import java.util.HashMap;

import controller.CustomerImpl;
import dto.MemberInfo;
import dto.Refreshments;
import util.MessagePrinter;
import util.MessageWriter;

public class MemberManageService {

	private static MemberManageService instanceM = new MemberManageService();
	public HashMap<String, MemberInfo> member;
	private MessagePrinter out;
	private MessageWriter in;
	public HashMap<MemberInfo, Refreshments> buyMember;

	private MemberManageService() {
		this.buyMember = new HashMap<MemberInfo, Refreshments>();
		this.member = new HashMap<String, MemberInfo>();
		this.in = new MessageWriter();
		this.out = new MessagePrinter();
	}

	public static MemberManageService getInstance() {
		if(instanceM == null) {
			instanceM = new MemberManageService();
		}
		return instanceM;
	}

	public void memberRemove() {
		String key =in.write();
		member.remove(key);
		out.systemMessage("탈퇴완료");

	}

	public void memberInfo() {
		out.printAlert(member.toString());

	}

	public void memberUpdate() {
		boolean back = true;
		do {
			
			out.systemMessage("변경할 고객의 아이디를 입력하세요.");
			String key =in.write();
			out.printAlert("수정할 정보를 고르세요 |[1] 비밀번호 변경| [2] 주소 변경 | [0] 뒤로가기 |");
			int select = in.writeInt();
			switch(select) {
			case 1 : out.systemMessage("비밀번호를 변경합니다.");
			String password = in.write();
			member.get(key).setPassword(password);
			break;
			case 2 : out.systemMessage("주소를 변경합니다.");
			String address = in.write();
			member.get(key).setPassword(address);
			break;
			case 0 : 
				back = false;
			break;
			default : out.systemMessage("잘못누르셨습니다.");
			}
		}while(back);
		
	}


}
