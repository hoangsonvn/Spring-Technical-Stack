package com.example.replication.entity;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long age;
}
