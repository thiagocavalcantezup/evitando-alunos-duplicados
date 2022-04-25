package br.com.zup.handora.evitandoalunosduplicados.aluno;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(AlunoController.BASE_URI)
public class AlunoController {

    public final static String BASE_URI = "/alunos";

    private final AlunoRepository alunoRepository;

    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid AlunoRequest alunoRequest,
                                    UriComponentsBuilder ucb) {
        if (alunoRepository.existsByNumeroMatriculaAndDataMatricula(
            alunoRequest.getNumeroMatricula(), alunoRequest.getDataMatricula()
        )) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY, "Já existe um aluno com essa matrícula nessa data."
            );
        }

        Aluno aluno = alunoRepository.save(alunoRequest.toModel());

        URI location = ucb.path(BASE_URI + "/{id}").buildAndExpand(aluno.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
