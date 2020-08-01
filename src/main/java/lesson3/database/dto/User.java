package lesson3.database.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Bet> bet;

    public User() {}

    public User(Long id, String name, Bet bet) {
        this.id = id;
        this.name = name;
        //this.bet = bet;
    }

    public Long getId() {
        return id;
    }
}
