package com.aquaq.scb.entities.audits;

import com.aquaq.scb.entities.events.EventsModel;
import com.aquaq.scb.entities.users.UsersModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "audit")
public class AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "table_changed")
    private String tableChanged;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "changed_by_user_id")
    private UsersModel usersModel;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "change_desc")
    private String changeDescription;

    @Column(name = "change_type")
    private String changeType;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="audit")
    private Set<AuditChildModel> auditChildModels;
}
