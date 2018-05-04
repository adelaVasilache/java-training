package javatraining.training.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Adela Vasilache on 03.05.2018
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Double grade;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
