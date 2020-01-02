package io.tlor.spring.api;

import io.tlor.spring.api.repository.OrderRepository;
import io.tlor.spring.api.service.OrderService;
import io.tlor.spring.domain.config.CommonConfiguration;
import io.tlor.spring.domain.model.Address;
import io.tlor.spring.domain.model.Member;
import io.tlor.spring.domain.model.Order;
import io.tlor.spring.domain.model.OrderStatus;
import io.tlor.spring.domain.model.item.Album;
import io.tlor.spring.domain.model.item.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CommonConfiguration.class})
public class OrderServiceTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void orderItems() throws Exception {

        Member member = createMember();
        Item item = createAlbum("Obsession", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 주문(ORDER)이다.", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * 2, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, item.getStockQuantity());
    }

    @Test
    public void quantity() throws Exception {
        Member member = createMember();
        Item item = createAlbum("Obsession", 10000, 10);

        // 재고보다 많은 수량
        int orderCount = 11;

        orderService.order(member.getId(), item.getId(), orderCount);

        fail("재고 수량 부족 예외가 발생해야 한다.");
    }

    @Test
    public void orderCancel() {

        Member member = createMember();
        Item item = createAlbum("Obsession", 10000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockQuantity());

    }

    private Member createMember() {
        Member member = new Member();
        member.setName("김종인");
        member.setAddress(new Address("전라남도", "순천시", "123-123"));
        entityManager.persist(member);
        return member;
    }

    private Album createAlbum(String name, int price, int stockQuantity) {
        Album album = new Album();
        album.setName(name);
        album.setStockQuantity(stockQuantity);
        album.setPrice(price);
        entityManager.persist(album);

        return album;
    }
}
