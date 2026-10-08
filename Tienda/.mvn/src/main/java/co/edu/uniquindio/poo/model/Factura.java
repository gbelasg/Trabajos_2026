package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;

public record Factura(String codigo, LocalDate fecha, double total, EstadoFactura estadoFactura,
                      MetodoPago metodoPago, Cliente cliente,
                      ArrayList<DetalleFactura> listaDetallesFactura, Tienda ownedByTienda) {
    public float calcularTotal() {
        return (float) listaDetallesFactura.stream() // lista en flujo de datos
                .mapToDouble(DetalleFactura::calcularSubTotal).sum(); //cada detalle en un subtotal y los suma si la lista esta vacía

    }
}
