package com.aquaq.scb.entities.projects;
import com.aquaq.scb.entities.audits.AuditChildModel;
import com.aquaq.scb.entities.users.UsersModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="projects")
public class ProjectsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="details")
    private String details;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_projects",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<UsersModel> user;

    @Column(name="created_by_user_id")
    private Integer createdByUserId;

}
