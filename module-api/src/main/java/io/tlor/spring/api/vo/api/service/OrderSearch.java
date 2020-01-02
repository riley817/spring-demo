package io.tlor.spring.api.vo.api.service;

import io.tlor.spring.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName;              // 회원이름
    private OrderStatus orderStatus;        // 주문상태 (ORDER, CANCEL)
}
