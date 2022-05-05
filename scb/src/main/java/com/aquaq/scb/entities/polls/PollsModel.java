package com.aquaq.scb.entities.polls;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="polls")
public class PollsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="details")
    private String details;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "poll", fetch = FetchType.LAZY)
    private Set<PollOptionsModel> pollOptions = new HashSet<>();

}