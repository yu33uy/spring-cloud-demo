package spring.cloud.auth.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Frank on 2017/6/16.
 */
@Data
@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="user_id")
    private Integer userId;
    @Column(name="name")
    private String name;
}
