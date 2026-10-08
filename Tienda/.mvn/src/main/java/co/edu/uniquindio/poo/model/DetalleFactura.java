package co.edu.uniquindio.poo.model;

public class DetalleFactura {
     this.cantidadComprada = cantidadComprada;
        this.subTotal = subTotal;
        this.producto = producto;
        this.ownedByFactura = ownedByFactura;
}

public int getCantidadComprada() {
    return cantidadComprada;
}

public double getSubTotal() {
    return subTotal;
}

public Producto getProducto() {
    return producto;
}

public Factura getOwnedByFactura() {
    return ownedByFactura;
}

public float calcularSubTotal(){
    return (float) (cantidadComprada * getProducto().getPrecio());
}

}
