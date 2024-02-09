package hello.core.order;

public interface OrderService {

    // 주문 생성 (리턴값이 Order)
        // 설계도 보면(pdf p.13), 클라이언트는 주문을 생성할 때, 회원id/상품명/상품 가격을 파라미터로 넘겨야 한다고 나와 있음.
        // 그러면 return 으로 주문 결과를 반환한다고 나와 있음.
    Order createOrder(Long memberId, String itemName, int itemPrice);
}