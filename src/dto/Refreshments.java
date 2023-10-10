package dto;
//DTO(Data Transfer Object)

public class Refreshments {

	private int id;
	private String name;
	private int stock; 
	private int price;

	
	public Refreshments() {
		
	}

	public Refreshments(int id ,String name, int stock, int price) {
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.price = price;
	}


	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	//투스트링 or 출력정보
	@Override
	public String toString() {
		return "[상품 코드 : " + id + " 상품명 : " + name + " 상품가격 : " + price + " 상품재고 : " + stock + "]";
	}
}
