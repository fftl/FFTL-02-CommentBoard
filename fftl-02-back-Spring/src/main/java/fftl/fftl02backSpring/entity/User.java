package fftl.fftl02backSpring.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    private String userName;
    private String passWord;

    private String joinDate;
    private String nickName;

    //private List<Comment> Comments = new ArrayList<>();
    //private List<Board> Boards = new ArrayList<>();
}
