package co.edu.uniquindio.Subdidiis.Repositorio;

import co.edu.uniquindio.Subdidiis.Model.Documents.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MenuRepo extends MongoRepository<Menu, String> {
    Optional<Menu> findById(String id);
    Optional<Menu> findByFechaDisponibilidad(LocalDate fechasDisponibilidad);
}
