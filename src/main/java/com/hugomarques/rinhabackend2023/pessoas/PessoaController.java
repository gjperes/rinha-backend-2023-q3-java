package com.hugomarques.rinhabackend2023.pessoas;

import java.net.URI;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PessoaController {
    @Autowired
    private PessoaRepository repository;

    @ExceptionHandler
    protected ResponseEntity unprocessableEntity(final HttpMessageNotReadableException e) {
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler
    protected ResponseEntity badRequest(final MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler
    protected ResponseEntity duplicated(final DataIntegrityViolationException e) {
        return ResponseEntity.unprocessableEntity().build();
    }

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> novo(@RequestBody final Pessoa pessoa) {
        if (!pessoaIsValid(pessoa)) {
            return ResponseEntity.unprocessableEntity().build();
        }

        final UUID uuid = UUID.randomUUID();
        pessoa.setId(uuid);
        repository.save(pessoa);
        return ResponseEntity.created(URI.create("/pessoas/" + uuid)).build();
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> consultar(@PathVariable final UUID id) {
        final var pessoa = repository.findById(id);
        if (pessoa.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoa.get());
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> buscar(@RequestParam(name = "t") final String term) {
        if (term == null || term.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.findAllByTerm(term));
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity contagem() {
        return ResponseEntity.ok(repository.count());
    }

    private boolean pessoaIsValid(final Pessoa pessoa) {
        if (pessoa == null || pessoa.getNascimento().length() != 10) return false;

        try {
            DateTimeFormatter.ofPattern("yyyy[-MM[-dd]]").parseBest(pessoa.getNascimento(), LocalDate::from, YearMonth::from, Year::from);
        } catch (DateTimeParseException e) {
            return false;
        }

        return (
                (pessoa.getStack() != null
                        ? pessoa.getStack().stream().allMatch(v -> v != null && v.length() < 32)
                        : true)
                        && pessoa.getNome() != null
                        && !pessoa.getNome().isBlank()
                        && pessoa.getNome().length() < 100
                        && pessoa.getApelido() != null
                        && !pessoa.getApelido().isBlank()
                        && pessoa.getApelido().length() < 32
        );
    }
}
