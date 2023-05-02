/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemplojwt.resources;

import com.mycompany.ejemplojwt.entities.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.Common;

/**
 *
 * @author Felix
 */
@Path("login")
public class LoginResource {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validarLogin(Usuario usuario) {
        
        boolean status = usuario.getUsername().equals("admin") 
                            && usuario.getPassword().equals("admin");
        
        if(status) {
            long time = System.currentTimeMillis();
            String jwt = Jwts.builder()
                            .signWith(SignatureAlgorithm.HS256, Common.SECRET)
                            .setSubject(usuario.getUsername())
                            .setIssuedAt(new Date(time))
                            .setExpiration(new Date(time + 900000))
                            .compact();
            
            JsonObject json = Json.createObjectBuilder().add("jwt", jwt).build();
            
            return Response.status(Response.Status.CREATED).entity(json).build();
        }
        
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
    
}
