package lesson3.database.dto;

import org.hibernate.annotations.OptimisticLock;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rate")
    //@OptimisticLock(excluded = true)
    private BigDecimal rate;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "user_id")
    //@OptimisticLock(excluded = true)
    private User user;

    public Bet() {}

    public Bet(String name, BigDecimal rate, User user) {
        this.name = name;
        this.rate = rate;
        this.user = user;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
