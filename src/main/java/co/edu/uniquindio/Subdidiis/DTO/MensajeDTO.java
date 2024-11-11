package co.edu.uniquindio.Subdidiis.DTO;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
