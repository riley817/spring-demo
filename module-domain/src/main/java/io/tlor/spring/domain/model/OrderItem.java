package io.tlor.spring.domain.model;

import io.tlor.spring.domain.model.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;              // 주문 상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;            // 주문

    private int orderPrice;         // 주문 가격
    private int count;              // 주문 수량

    /**
     * 주문 상품 엔티티를 생성하고
     * 주문한 수량만큼 상품의 재고를 줄인다.
     * @param item              주문 상품
     * @param orderPrice        가격
     * @param count             수량
     */
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    /**
     * 주문취소
     * 취소한 주문 수량만큼 상품의 재고를 증가시킨다.
     */
    public void cancel() {
        getItem().addStock(count);
    }

    /**
     * 주문 가격에 수량을 곱한 값을 반환한다.
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", buyPrice=" + orderPrice +
                ", count=" + count +
                '}';
    }
}
