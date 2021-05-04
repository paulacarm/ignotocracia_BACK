package com.ignotocracia.app.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ignotocracia.app.security.service.UsuarioDetailsServiceImpl;

/**@author paula.carmona.moreno
Esta clase se ejecuta por cada petición
Comprueba que el token sea válido utilizando el provider
-En caso de que el token sea válido permite el acceso al recurso
-En caso contrario lanzará una excepción 
*/

public class JwtTokenFilter extends OncePerRequestFilter{
	
	private final static Logger logger=LoggerFactory.getLogger(JwtTokenFilter.class);
	
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UsuarioDetailsServiceImpl userDetailsService;
 
    /**
	 * Comprueba el token válido y autentificación
	 *
	 */

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
	    
		try {
         String token= this.getToken(request);
         if(token != null && jwtProvider.validateToken(token)){
             String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
             UserDetails userDetails = userDetailsService.loadUserByUsername(nombreUsuario);

             UsernamePasswordAuthenticationToken auth =
                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
             //Al contexto de autentificación le asignamos ese usuario.
             SecurityContextHolder.getContext().setAuthentication(auth);
         }
            
        } catch (Exception e){
            logger.error("fallo en el método doFilter " + e.getMessage());
        
        }
		filterChain.doFilter(request, response);
	
	}
	
	
	/**
	 * Extrae el token, le quita la cabecera
	 * @param request
	 * @return
	 */
	private String getToken(HttpServletRequest request) {
		   String header = request.getHeader("Authorization");
	        if(header != null && header.startsWith("Bearer"))
	            return header.replace("Bearer ", "");
	    
	        return null;
	}
  
}
