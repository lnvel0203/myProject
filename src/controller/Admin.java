package controller;

public interface Admin {

	public static final String ID = "admin";
	public static final String PASSWORD = "admin";
	//관리자 재고 관리
	public void productSelect(); // 상품목록
	public void productAdd( ); // 상품추가
	public void productUpdate( );// 상품 수정
	public void productRemove(); //상품 삭제
	
	//관리자 주문 관리
	public void orderSelect(); // 주문 목록
	public void orderConfirm(); // 결제승인 --> 확정구매에 대해서
	public void orderCancle(); // -> 결제취소 --> 결제취소거부에관해
	
	//관리자 결산
	public void saleTotal(); // 결산
}

