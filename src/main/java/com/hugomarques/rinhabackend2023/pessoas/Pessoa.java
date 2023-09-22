package com.hugomarques.rinhabackend2023.pessoas;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;
    @Column(name = "apelido", nullable = false, unique = true, length = 32)
    private String apelido;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "nascimento", nullable = false)
    private String nascimento;
    @Column(name = "stack", columnDefinition = "text")
    @Convert(converter = StringListConverter.class)
    private List<String> stack = Collections.emptyList();

    public Pessoa() {
    }

    public Pessoa(String apelido, String nome, String nascimento, List<String> stack) {
        this.apelido = apelido;
        this.nome = nome;
        this.nascimento = nascimento;
        this.stack = stack;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }
}
