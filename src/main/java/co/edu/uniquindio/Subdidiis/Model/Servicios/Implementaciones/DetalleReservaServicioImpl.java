package co.edu.uniquindio.Subdidiis.Model.Servicios.Implementaciones;

import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.DetalleReservaServicio;

public class DetalleReservaServicioImpl implements DetalleReservaServicio {
    @Override
    public double calcularSubtotal(CalcularSubtotalDTO calcularSubtotalDTO) throws Exception {
        return 0;
    }

    @Override
    public String agregarDetalleReserva(AgregarDetalleReservaDTO agregarDetalleReservaDTO) throws Exception {
        return "";
    }

    @Override
    public void actualizarDetalleReserva(ActualizarDetalleReservaDTO actualizarDetalleReservaDTO) throws Exception {

    }

    @Override
    public void eliminarDetalleReserva(String idDetalle) throws Exception {

    }

    @Override
    public DetalleReservaDTO obtenerDetalleReservaPorId(String idDetalle) throws Exception {
        return null;
    }

    @Override
    public List<DetalleReservaDTO> obtenerDetallesPorIdReserva(String idReserva) throws Exception {
        return null;
    }
}
