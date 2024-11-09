package co.edu.uniquindio.Subdidiis.Repositorio;


import co.edu.uniquindio.Subdidiis.Model.Documents.DetalleReserva;
import co.edu.uniquindio.Subdidiis.Model.Documents.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetalleReservaRepo extends MongoRepository<DetalleReserva, String> {
    Optional<DetalleReserva> findById(String id);
    Optional<Menu> findByMenu(String idMenu);
}
