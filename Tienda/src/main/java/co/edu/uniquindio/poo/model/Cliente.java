package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private final String documentoIdentidad;
    private  String nombreCompleto;

    private final String telefono;
    private final String correo;
    private final String ciudadResidencia;
    private final  Tienda ownedByTienda;

    private final List< Factura > listaFacturas;

    public Cliente(String documentoIdentidad, String telefono, String correo, String ciudadResidencia, String nombreCompleto,  Tienda ownedByTienda) {
        this.documentoIdentidad = documentoIdentidad;
        this.telefono = telefono;
        this.correo = correo;
        this.ciudadResidencia = ciudadResidencia;
        this.nombreCompleto = nombreCompleto;
        this.ownedByTienda = ownedByTienda;
        this.listaFacturas = new ArrayList<>();




    }



    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public Tienda getOwnedByTienda() {
        return ownedByTienda;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "documentoIdentidad='" + documentoIdentidad + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", ciudadResidencia='" + ciudadResidencia + '\'' +
                ", ownedByTienda=" + ownedByTienda +
                ", listaFacturas=" + listaFacturas +
                '}';
    }

}
