package fftl.fftl02backSpring.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {

    @GeneratedValue
    @Id
    private Long boardId;

    private String title;
    private String content;
    private String nickname;


}
