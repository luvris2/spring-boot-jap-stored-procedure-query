package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="testTable")
public class ProcedureResultSet {
    @Id
    private int id;
    @Column
    private String username;
    @Column
    private String address;
}