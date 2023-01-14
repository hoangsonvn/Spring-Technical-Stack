package com.example.replication.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity
@Data
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long age;
}
