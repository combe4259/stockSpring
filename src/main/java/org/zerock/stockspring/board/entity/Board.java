package org.zerock.stockspring.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    @Column(length = 500, nullable = false)
    private String title;
    @Column(length=2000, nullable = false)
    private String content;
    @Column(length=50, nullable = false)
    private String writer;

    private Long stockCode;

    public void change(String title, String content){
        this.title=title;
        this.content=content;
    }
}
