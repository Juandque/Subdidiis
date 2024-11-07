package co.edu.uniquindio.Subdidiis.Repositorio;

import co.edu.uniquindio.Subdidiis.Model.Documents.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepo extends MongoRepository<Persona, String> {
}
