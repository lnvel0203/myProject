package dto;

public class MemberInfo {
	
	private String id;
	private String password;
	private String name;
	private int age;
	private int tellnum;
	private String address;

	public MemberInfo() {
		
	}
	
	public MemberInfo(String id) {
		this.id = id;
	
	}
	
	public MemberInfo(String id, String password, String name, int age, int tellnum, String address) {

		this.id = id;
		this.password = password;
		this.name = name;
		this.age = age;
		this.tellnum = tellnum;
		this.address = address;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getTellnum() {
		return tellnum;
	}

	public void setTellnum(int tellnum) {
		this.tellnum = tellnum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "회원님의 정보 : \n"
				+ "[ 아이디 : " + id +" ]\n"
				+ "[ 비밀번호 : " + password +"]\n"
				+ "[ 이름 : " + name + "]\n"
				+ "[ 나이 : " + age + "]\n"
				+ "[ 핸드폰번호 : " + tellnum + "]\n"
				+ "[ 주소 : " + address + "]";
	}

}
