package com.springrestmsr.projectspring.domain.model;

import lombok.Data;

@Data
public class Cliente {

    private Integer id;
    private String nome;
    private String email;
    private Long telefone;
}
