package fftl.fftl02backSpring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Comment {

    @GeneratedValue
    @Id
    private Long cid;

    private String nickname;
    private String comment;
    private String cregdate;

    private Long uid;
    private Long bid;

}
