package co.edu.uniquindio.Subdidiis.Repositorio;

import co.edu.uniquindio.Subdidiis.Model.Documents.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String> {
}
