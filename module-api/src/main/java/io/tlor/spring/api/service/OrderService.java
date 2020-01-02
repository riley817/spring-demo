package io.tlor.spring.api.service;

import io.tlor.spring.api.repository.MemberRepository;
import io.tlor.spring.api.repository.OrderRepository;
import io.tlor.spring.api.vo.api.service.OrderSearch;
import io.tlor.spring.domain.model.Delivery;
import io.tlor.spring.domain.model.Member;
import io.tlor.spring.domain.model.Order;
import io.tlor.spring.domain.model.OrderItem;
import io.tlor.spring.domain.model.item.Item;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemService itemService;

    /**
     * 주문
     * @param memberId      주문 회원 아이디
     * @param itemId        주문 상품 아이디
     * @param count         주문 수량
     */
    public Long order(Long memberId, Long itemId, int count) {

        // 1. 회원 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemService.findOne(itemId);

        // 2. 배송정보 생성
        Delivery delivery = new Delivery(member.getAddress());

        // 3. 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 4. 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 5. 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 주문 취소
     * @param orderId       주문 아이디
     */
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    /**
     * 주문 검색
     * @param orderSearch       검색 조건
     */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
