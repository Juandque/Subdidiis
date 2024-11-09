package co.edu.uniquindio.Subdidiis.Repositorio;

import co.edu.uniquindio.Subdidiis.Model.Documents.DetalleReserva;
import co.edu.uniquindio.Subdidiis.Model.Documents.Reserva;
import co.edu.uniquindio.Subdidiis.Model.Documents.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface ReservaRepo extends MongoRepository<Reserva, String> {
    Optional<DetalleReserva>findByDetalleReserva(DetalleReserva reserva);

    Optional<Reserva> findById(String id);
    Optional<Reserva> findByUsuario(String id);
    Optional<Reserva> findByHoraReserva(LocalTime localTime);

}
