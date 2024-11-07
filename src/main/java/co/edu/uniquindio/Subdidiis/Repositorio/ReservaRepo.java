package co.edu.uniquindio.Subdidiis.Repositorio;

import co.edu.uniquindio.Subdidiis.Model.Documents.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepo extends MongoRepository<Reserva, String> {
}
