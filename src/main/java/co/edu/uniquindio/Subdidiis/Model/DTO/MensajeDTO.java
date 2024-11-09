package co.edu.uniquindio.Subdidiis.Model.DTO;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
