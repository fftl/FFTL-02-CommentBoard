package fftlproject.AccountBook.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    /**
     * 로그인정보
     * */
    private String userName;
    private String passWord;

    private String nickName;

    @OneToMany
    @JoinColumn(name="accountId")
    private List<Account> accounts = new ArrayList<>();

}
