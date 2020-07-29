package lesson2.database.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "costs")
public class Cost {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "date_cost")
    private Date date;

    @Column(name = "cost")
    private Double cost;
}
