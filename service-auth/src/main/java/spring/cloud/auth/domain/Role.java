package spring.cloud.auth.domain;

import lombok.Data;
import spring.cloud.commons.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Frank on 2017/6/17.
 */
@Entity
@Table(name = "role")
@Data
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;
}
