package co.edu.uniquindio.poo.model;

import java.util.*;

public class Tienda {
    private final String nombre; //no se puede modificar con final
    private final String nit;
    private String telefono;

    private final ArrayList<Cliente> listaCliente = new ArrayList<>();
    private final List<Factura> listaFactura = new LinkedList<>();
    private Map<String, Producto> listaProductos = new HashMap<>();


    public Tienda(String nombre, String nit, String telefono) {
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }




    public String registrarCliente (Cliente cliente){
        Optional<Cliente> clienteEncontrado = buscarCliente(cliente.getDocumentoIdentidad());

        if (clienteEncontrado.isPresent()) {
            return "No se puede registrar. Ya existe un cliente con esa información anteriormente";
        }
        listaCliente.add(cliente);
        return "El cliente ha sido registrado exitosamente";
    }

    public Optional<Cliente> buscarCliente(String documentoIdentidad) {
        return listaCliente.stream() //.steam retorna una liksta de clientes auxiliar que no se puede modificar
                .filter(cliente -> cliente.getDocumentoIdentidad().equals(documentoIdentidad)) //el filtro actua sobre lista de clientes y se usa la condicion
                .findFirst(); //el primero que encuentre
    }

    public boolean eliminarCliente(String documentoIdentidad){
        return listaCliente.removeIf(cliente -> cliente.getDocumentoIdentidad().equals(documentoIdentidad));
    }
    public boolean actualizarCliente(String documentoIdentidadAntigua, String nombreCompletoNuevo){
        Optional<Cliente> clienteEncontrado=buscarCliente(documentoIdentidadAntigua);
        clienteEncontrado.ifPresent(cliente -> cliente.setNombreCompleto(nombreCompletoNuevo));
        return clienteEncontrado.isPresent();

    }


    //PRODUCTO
    public String registrarProducto (Producto producto){
        Optional<Producto> productoEncontrado = buscarProducto(producto.getCodigo());

        if (productoEncontrado.isPresent()) {
            return "No se puede registrar. Ya existe un cliente con esa información anteriormente";
        }
        listaProductos.put(producto.getCodigo(), producto);
        return "El cliente ha sido registrado exitosamente";
    }

    public Optional<Producto> buscarProducto(String codigo){
        return listaProductos.values().stream().filter(pro -> pro.getCodigo().equals(codigo)).findFirst();
    }
    public boolean eliminarProducto(String codigo){
        return listaProductos.values().removeIf(producto -> producto.getCodigo().equals(codigo));
    }
    public boolean actualizarCantidadProducto(String codigo, String nuevaCantidad){
        Optional<Producto> productoEncontrado= buscarProducto(codigo);
        productoEncontrado.ifPresent(p -> p.setCantidadDisponible(Integer.parseInt(nuevaCantidad))); //se hace conversion de integer
        return productoEncontrado.isPresent();
    }


    //Factura
    public String registrarFactura (Factura factura){
        Optional<Factura> facturaEncontrada = buscarFactura(factura.codigo());

        if (facturaEncontrada.isPresent()) {
            return "No se puede registrar. Ya existe un cliente con esa información anteriormente";
        }
        listaFactura.add(factura);
        return "El cliente ha sido registrado exitosamente";
    }


    public Optional<Factura> buscarFactura(String codigo){
        return listaFactura.stream().filter(f1 -> f1.codigo().equals(codigo)).findFirst();
    }
    public boolean eliminarFactura(String codigo){
        return listaFactura.removeIf(factura -> factura.codigo().equals(codigo));
    }
}
