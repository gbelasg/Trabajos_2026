package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Producto;
import co.edu.uniquindio.poo.model.Tienda;

import javax.swing.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Tienda tienda= new Tienda("Tienda UQ", "34567", "71085426");

        Cliente c1=new Cliente("1091", "3005", "juli12", "Armenia","Juliana Rojas", tienda);
        String mensajeResultado= tienda.registrarCliente(c1);
        JOptionPane.showMessageDialog(null, mensajeResultado);

        Optional<Cliente> resultado= tienda.buscarCliente("1234");

        if (resultado.isPresent()){
            JOptionPane.showMessageDialog(null, "El cliente existe en la tienda");
        }else{
            JOptionPane.showMessageDialog(null, "El cliente no existe en la tienda");
        }

        //Productos cantidad disponible mas de 10
        public List<Producto> obtenerMayoresDiez() { no usages new*
                List<Producto> productosAdecuado = new ArrayList<>();

            for (Producto productosBuenos : hashMaplistaProductos.values()) {
                if (productosBuenos.getCantidadDisponible() >= 10) {
                    productosAdecuado.add(productosBuenos);

                    return productosAdecuado;

                    public ArrayList<String> productosConCodigosMayores(int limiteInferior, int limiteSuperior){
                        ArrayList<String> resultado=new ArrayList<>();

                        for (String codigo: hashMapListaProductos.keySet()) {
                            Producto producto = hashMaplistaProductos.get(codigo);
                            if (producto.getCantidadDisponible() >= 10 && producto.getCantidadDisponible() < 50) {
                                resultado.add(codigo);
                            }
                        }
                                return resultado;
                        }

                        //punto3
                    public ArrayList<Cliente> obtenerClientesCompraronEnFecha(LocalDate fecha) {
                        ArrayList<Cliente> clientes = new ArrayList<>();

                        for (Factura factura : listaFactura) {
                            boolean esDeEsaFecha = factura.fecha().equals(fecha);
                            boolean estaCancelada = factura.estadoFactura().equals(EstadoFactura.CANCELADA);

                            if (esDeEsaFecha && !estaCancelada && !clientes.contains(factura.cliente())) {
                                clientes.add(factura.cliente());
                            }
                        }

                        return clientes;
                    }




    }
}
