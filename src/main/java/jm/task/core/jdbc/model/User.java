package jm.task.core.jdbc.model;

import jm.task.core.jdbc.util.Util;

import javax.persistence.*;
import java.sql.SQLException;
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public long getId() throws SQLException {
        return id;
    }

    public void setId(Long id) {
    }

    public String getName() {
       return name;
    }

    public void setName(String name) {
    }

    public String getLastName() {
       return lastName;
    }

    public void setLastName(String lastName) {
    }

    public Byte getAge() {
       return age;
    }

    public void setAge(Byte age) {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
