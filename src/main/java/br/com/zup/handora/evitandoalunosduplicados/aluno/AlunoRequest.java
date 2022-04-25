package br.com.zup.handora.evitandoalunosduplicados.aluno;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.br.CPF;

public class AlunoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    @Size(max = 6)
    private String numeroMatricula;

    @NotNull
    @PastOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataMatricula;

    public AlunoRequest() {}

    public AlunoRequest(@NotBlank String nome, @NotBlank @CPF String cpf,
                        @NotBlank String numeroMatricula,
                        @NotNull @PastOrPresent LocalDate dataMatricula) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroMatricula = numeroMatricula;
        this.dataMatricula = dataMatricula;
    }

    public Aluno toModel() {
        return new Aluno(nome, cpf, numeroMatricula, dataMatricula);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

}
