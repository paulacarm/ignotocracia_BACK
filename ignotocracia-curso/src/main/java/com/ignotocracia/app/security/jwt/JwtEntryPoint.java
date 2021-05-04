package com.ignotocracia.app.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**@author paula.carmona.moreno
   Esta clase comprueba si hay un token válido si no devulve una respuesta 401 no autorizado.
 * Rechaza las peticiones no autenticadas
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
	/**Se utiliza para en caso de error saber cual es el método que lo provoca
	Se utiliza en desarrollo no en producción*/
	private final static Logger logger=LoggerFactory.getLogger(JwtEntryPoint.class);
			
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
		
		logger.error("fallo en el método commence");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"No autorizado");
			
				
	}
}
