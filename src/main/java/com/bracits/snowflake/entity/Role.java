package com.bracits.snowflake.entity;


import com.bracits.snowflake.entity.common.CommonColumn;

import javax.persistence.*;
import java.util.Set;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/24/2020
 */
@Entity
@Table(name = "roles")
public class Role extends CommonColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "role_right",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "right_id"))
    Set<Right> rights;

    public Role() {
    }

    public Role(String name, String description, Set<Right> rights) {
        this.name = name;
        this.description = description;
        this.rights = rights;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Right> getRights() {
        return rights;
    }

    public void setRights(Set<Right> rights) {
        this.rights = rights;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + ", desc=" + description + "]";
    }
}
