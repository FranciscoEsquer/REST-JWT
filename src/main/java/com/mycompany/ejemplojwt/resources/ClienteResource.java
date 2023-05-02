/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemplojwt.resources;

import com.mycompany.ejemplojwt.entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

@Path("cliente")
public class ClienteResource {
    
    private static final List<Cliente> clientes = new ArrayList<>();
    static {
        // Ejemplo de datos pre-cargados
        clientes.add(new Cliente(1, "cliente1", "1231231231"));
        clientes.add(new Cliente(2, "cliente2", "1231231232"));
        clientes.add(new Cliente(3, "cliente3", "1231231233"));
    }
    
    @Context
    private UriInfo context;
    
    public ClienteResource() {}
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getClientes() {
        return clientes;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarCliente(Cliente cliente) {
        // Validación de datos
        if (cliente == null || cliente.getId() <= 0 || cliente.getNombre() == null || cliente.getRFC() == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        // Agregar cliente a la lista
        clientes.add(cliente);
        // Retornar la respuesta con el código 201 (Created)
        return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarCliente(@PathParam("id") int id, Cliente cliente) {
        // Validación de datos
        if (cliente == null || cliente.getId() <= 0 || cliente.getNombre() == null || cliente.getRFC() == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        // Buscar cliente a actualizar
        int indice = -1;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                indice = i;
                break;
            }
        }
        // Si no se encontró el cliente, retornar código 404 (Not Found)
        if (indice == -1) {
            return Response.status(Status.NOT_FOUND).build();
        }
        // Actualizar cliente
        clientes.set(indice, cliente);
        // Retornar código 204 (No Content)
        return Response.noContent().build();
    }
    
}
