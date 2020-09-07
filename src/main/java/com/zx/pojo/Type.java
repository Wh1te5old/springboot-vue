package com.zx.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "type")
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;

    private String tname;

}
