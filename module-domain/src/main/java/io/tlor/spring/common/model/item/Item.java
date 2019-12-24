package io.tlor.spring.common.model.item;

import io.tlor.spring.common.exception.NotEnoughStockException;
import io.tlor.spring.common.model.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter
@Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;        // 이름
    private int price;          // 가격
    private int stockQuantity;  // 재고 수량

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
