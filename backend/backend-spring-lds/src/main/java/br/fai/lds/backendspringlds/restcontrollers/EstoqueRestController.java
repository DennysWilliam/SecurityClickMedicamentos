package br.fai.lds.backendspringlds.restcontrollers;

import br.fai.lds.backend.usecases.estoque.FindEstoque;
import br.fai.lds.backend.usecases.estoque.UpdateEstoque;
import br.fai.lds.domain.EstoqueModel;
import br.fai.lds.domain.MedicationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/estoques")
public class EstoqueRestController {

    private final FindEstoque findEstoque;
    private final UpdateEstoque updateEstoque;

    public EstoqueRestController(FindEstoque findEstoque, UpdateEstoque updateEstoque) {
        this.findEstoque = findEstoque;
        this.updateEstoque = updateEstoque;
    }

    @GetMapping
    public ResponseEntity<List<EstoqueModel>> findAll() {
        List<EstoqueModel> estoques = findEstoque.findAll();
        return estoques.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(estoques);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueModel> findById(@PathVariable Long id) {
        EstoqueModel estoque = findEstoque.findById(id);
        return estoque != null ? ResponseEntity.ok(estoque) : ResponseEntity.notFound().build();
    }

    @GetMapping("/farmacia/{farmaciaId}")
    public ResponseEntity<List<EstoqueModel>> findByFarmacia(@PathVariable Long farmaciaId) {
        List<EstoqueModel> estoques = findEstoque.findByFarmacia(farmaciaId);
        return estoques.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(estoques);
    }

    @PostMapping
    public ResponseEntity<EstoqueModel> create(@RequestBody EstoqueModel estoque) {
        EstoqueModel created = updateEstoque.create(estoque);
        return created != null ? ResponseEntity.ok(created) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody EstoqueModel estoque) {
        estoque.setId(id);
        boolean updated = updateEstoque.update(estoque);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = updateEstoque.delete(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/medicamento/{medicamentoId}/quantidade")
    public ResponseEntity<Integer> getQuantidadePorMedicamento(@PathVariable Long medicamentoId) {
        Integer quantidade = findEstoque.getQuantidadePorMedicamento(medicamentoId);
        return ResponseEntity.ok(quantidade != null ? quantidade : 0);
    }

    @GetMapping("/farmacia/{farmaciaId}/disponibilidade")
    public ResponseEntity<List<EstoqueModel>> getDisponibilidadePorFarmacia(@PathVariable Long farmaciaId) {
        List<EstoqueModel> estoques = findEstoque.findMedicamentosComEstoque(farmaciaId);
        return estoques.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(estoques);
    }
}
