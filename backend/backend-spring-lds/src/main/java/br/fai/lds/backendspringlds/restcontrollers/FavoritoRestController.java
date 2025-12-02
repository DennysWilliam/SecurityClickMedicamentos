package br.fai.lds.backendspringlds.restcontrollers;

import br.fai.lds.backend.usecases.favorito.FindFavorito;
import br.fai.lds.backend.usecases.favorito.CreateFavorito;
import br.fai.lds.domain.FavoritoModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/favorito")
public class FavoritoRestController {

    private final FindFavorito findFavorito;
    private final CreateFavorito createFavorito;

    public FavoritoRestController(FindFavorito findFavorito, CreateFavorito createFavorito) {
        this.findFavorito = findFavorito;
        this.createFavorito = createFavorito;
    }

    @GetMapping
    public ResponseEntity<List<FavoritoModel>> findAll() {
        return ResponseEntity.ok(findFavorito.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoritoModel> findById(@PathVariable Long id) {
        FavoritoModel f = findFavorito.findById(id);
        if (f == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(f);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<FavoritoModel>> findByUsuario(@PathVariable Long usuarioId) {
        System.out.println("Chegou aqui favorito " + usuarioId);
        return ResponseEntity.ok(findFavorito.findByUsuario(usuarioId));
    }

    @PostMapping("/create")
    public ResponseEntity<FavoritoModel> create(@RequestBody FavoritoModel favorito) {
        FavoritoModel created = createFavorito.create(favorito);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = createFavorito.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/usuario/{usuarioId}/medicamento/{medicamentoId}")
    public ResponseEntity<Void> deleteByUsuarioAndMedicamento(@PathVariable Long usuarioId, @PathVariable Long medicamentoId) {
        boolean deleted = createFavorito.deleteByUsuarioAndMedicamento(usuarioId, medicamentoId);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
