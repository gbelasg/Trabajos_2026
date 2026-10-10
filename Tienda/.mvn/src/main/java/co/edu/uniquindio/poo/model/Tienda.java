package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
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

    //Productos cantidad disponible mas de 10
    public List<Producto> obtenerMayoresDiez() {
        List<Producto> productosAdecuado = new ArrayList<>();

        for (Producto productosBuenos : listaProductos.values()) {
            if (productosBuenos.getCantidadDisponible() >= 10) {
                productosAdecuado.add(productosBuenos);
            }
        }
        return productosAdecuado;
    }

    //punto 2

    public ArrayList<String> productosConCodigosMayores ( int limiteInferior, int limiteSuperior){
        ArrayList<String> resultado = new ArrayList<>();

        for (String codigo :listaProductos.keySet()) {
                        Producto producto = listaProductos.get(codigo);
                        if (producto.getCantidadDisponible() >= 10 && producto.getCantidadDisponible() < 50) {
                            resultado.add(codigo);
                        }
                    }
                    return resultado;
                }

                //punto3
                public ArrayList<Cliente> obtenerClientesCompraronEnFecha (LocalDate fecha){
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
                //Mejorar punto 3
                public ArrayList<Cliente> obtenerClientesCompras2 (LocalDate fechaConsulta){

                    ArrayList<Cliente> listaClientes = new ArrayList<>();
                    for (Factura factura : listaFactura) {
                        if (factura.fecha().isEqual(fechaConsulta)) {
                            listaClientes.add(factura.cliente());
                        }
                    }

                    return listaClientes;
                }
                //obtener facturas donde el nombre de su cliente empiece por r
                public ArrayList<Factura> obtenerFacturasClienteR() {
                    ArrayList<Factura> facturasNombre = new ArrayList<>();

                    for (Factura factura : listaFactura) {
                        String nombre = factura.cliente().getNombreCompleto();

                        if (nombre.length() > 0) {
                            char primeraLetra = nombre.charAt(0);

                            if (primeraLetra == 'r' || primeraLetra == 'R') {
                                facturasNombre.add(factura);
                            }
                        }
                    }
                    return facturasNombre;
                }
                //Obtener las facturas donde se haya comprado un celular de marca iphone 16 pro max
                public ArrayList<Factura> obtenerFacturasIphone16() {
                    ArrayList<Factura> facturasEncontradas = new ArrayList<>();
                    for (Factura factura: listaFactura){
                        for (DetalleFactura detalleFactura: factura.listaDetallesFactura()){
                            Producto producto= detalleFactura.getProducto();
                            if (producto.getCategoria()==Categoria.CELULARES && producto.getNombre().equalsIgnoreCase("iPhone 16" +
                                    "pro max")){
                                facturasEncontradas.add(factura);
                            }
                        }

                    }

                    return facturasEncontradas;
                }
                //Punto 6
    public ArrayList<Factura>obtenerFacturasJuanYIphone16(){
        ArrayList<Factura> nuevasFacturas=new ArrayList<>();
        ArrayList<Factura> facturasIphone16= obtenerFacturasIphone16();
        for (Factura factura: facturasIphone16){
            Cliente cliente= factura.cliente();
            if (cliente.getNombreCompleto().toLowerCase().contains("Juan")){ //cambair resultado a minuscula y asi comparar
                nuevasFacturas.add(factura);
            }
        }
        return nuevasFacturas;

    }
    //Punto 7
    public ArrayList<Producto> obtenerProductosPorCategoria(Categoria categoria){
        ArrayList<Producto> listaCategorias= new ArrayList<>();
        for (Producto producto: listaProductos.values()){
            if (producto.getCategoria()==categoria){
                listaCategorias.add(producto);
            }

        }
        return listaCategorias;
    }
    //punto8
    public double encontrarPrecioMinimo(){
        return listaProductos.values().stream().mapToDouble(producto ->producto.getPrecio())
                .min().orElse(0); //devulve producto minimo, or else devulve un valor alterno (como un optional)

    }
    public double encontrarPrecioMaximo(){
        return listaProductos.values().stream().mapToDouble(producto ->producto.getPrecio())
                .max().orElse(0); //devuelve producto maximo

    }
    public ArrayList<Producto>obtenerRangoDePrecios(double precioMinimo, double precioMaximo){
        ArrayList<Producto> rangoFinal= new ArrayList<>();
        for (Producto producto: listaProductos.values()){
            if (producto.getPrecio()>= precioMinimo && producto.getPrecio()<= precioMaximo){
                rangoFinal.add(producto);
            }
        }
        return rangoFinal;
    }
    //punto 9
    public ArrayList<Producto>obtenerOrdenProductosPorPrecio(){
        List<Producto> listaProductoPrecios= listaProductos.values().stream().sorted //ordenador (ordena elementos segun condiciones)
                        (Comparator.comparingDouble(producto-> producto.getPrecio())) // 1. comparador java 2. compara segun el tipo 3. que tiene que hacer
                .toList(); // convierte lo ya ordenado en lista
        return new ArrayList<>(listaProductoPrecios); //devuelve la lista convertida en arrayList
    }

    //punto 10
    public Optional<Producto> encontrarProductoMasCaro(){
        return listaProductos.values().stream().max(Comparator.comparingDouble(producto->producto.getPrecio()));
    }

    










}
