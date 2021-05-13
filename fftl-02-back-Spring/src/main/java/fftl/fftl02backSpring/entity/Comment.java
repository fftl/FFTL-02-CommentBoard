package fftl.fftl02backSpring.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @GeneratedValue
    @Id
    private Long commentId;

    private String comment;
    private String regDate;

}
