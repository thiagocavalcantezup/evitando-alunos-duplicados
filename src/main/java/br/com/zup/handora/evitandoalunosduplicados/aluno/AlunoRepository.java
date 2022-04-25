package br.com.zup.handora.evitandoalunosduplicados.aluno;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByNumeroMatriculaAndDataMatricula(String numeroMatricula,
                                                    LocalDate dataMatricula);

}
