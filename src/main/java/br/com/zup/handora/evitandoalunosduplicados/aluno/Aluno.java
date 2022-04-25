package br.com.zup.handora.evitandoalunosduplicados.aluno;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "alunos", uniqueConstraints = {
        @UniqueConstraint(name = "UK_ALUNO_NUMERO_DATA_MATRICULA", columnNames = {
                "numeroMatricula", "dataMatricula"})})
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @CPF
    private String cpf;

    @Column(nullable = false, length = 6)
    @Size(max = 6)
    private String numeroMatricula;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDate dataMatricula;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Aluno() {}

    public Aluno(String nome, @CPF String cpf, @Size(max = 6) String numeroMatricula,
                 @PastOrPresent LocalDate dataMatricula) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroMatricula = numeroMatricula;
        this.dataMatricula = dataMatricula;
    }

    public Long getId() {
        return id;
    }

}
