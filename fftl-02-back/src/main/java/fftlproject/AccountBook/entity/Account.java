package fftlproject.AccountBook.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @OneToOne
    @JoinColumn(name="categoryId")
    private Category category;

    private String accountName;
    private int price;

    private LocalDateTime date;
    private LocalDateTime time;
}
