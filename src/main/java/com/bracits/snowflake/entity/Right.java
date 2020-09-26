package com.bracits.snowflake.entity;

import com.bracits.snowflake.entity.common.CommonColumn;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rights")
public class Right extends CommonColumn{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "rights")
    Set<Role> roles;


    public Right() {
    }

    public Right(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Right [id=" + id + ", name=" + name + ", desc=" + description + "]";
    }
}
