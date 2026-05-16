package com.nicson.financasapi.controller;

import com.nicson.financasapi.dto.TransacaoDTO;
import com.nicson.financasapi.dto.TransacaoResponseDTO;
import com.nicson.financasapi.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST para gerenciamento de transações.
 */
@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TransacaoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<TransacaoResponseDTO>> listarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(service.listarPorTipo(tipo));
    }

    @PostMapping
    public ResponseEntity<TransacaoResponseDTO> criar(@Valid @RequestBody TransacaoDTO transacaoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(transacaoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody TransacaoDTO transacaoDTO) {
        return ResponseEntity.ok(service.atualizar(id, transacaoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
