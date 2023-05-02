/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import utils.Common;

/**
 *
 * @author Felix
 */
@Provider
public class JWTFilter implements ContainerRequestFilter{
    
    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        String method = request.getMethod();
        String path = request.getUriInfo().getPath();
        System.out.println(method + "======= " + path);
        if(method.equals("POST") && path.contains("/login"))
            return;
        
            String token = request.getHeaderString(Common.AUTHENTICATION_HEADER);
            
            if(token == null) {
                throw new WebApplicationException(Response.Status.UNAUTHORIZED);
            }
            
            if(!token.isEmpty()) {
                //verificar token
                Jws<Claims> jwt = Jwts.parser()
                        .setSigningKey(Common.SECRET)
                        .parseClaimsJws(token);
                Claims claims = jwt.getBody();
                System.out.println(claims.get("sub", String.class));
                if(!claims.get("sub", String.class).equals("admin")){
                    throw new WebApplicationException(Response.Status.UNAUTHORIZED);
                }
                
            } else {
                throw new WebApplicationException(Response.Status.UNAUTHORIZED);
            }
        
    }
}
