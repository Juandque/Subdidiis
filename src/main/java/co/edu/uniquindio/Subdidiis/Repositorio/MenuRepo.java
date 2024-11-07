package co.edu.uniquindio.Subdidiis.Repositorio;

import co.edu.uniquindio.Subdidiis.Model.Documents.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepo extends MongoRepository<Menu, String> {
}
