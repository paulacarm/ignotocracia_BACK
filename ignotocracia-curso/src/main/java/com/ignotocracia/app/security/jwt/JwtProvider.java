package com.ignotocracia.app.security.jwt;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ignotocracia.app.security.entity.UsuarioPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**@author paula.carmona.moreno
Esta clase genera el token. 
También posee un método de validación para ver que esté bien formado,no expirado, etc..
*/
@Component
public class JwtProvider {
	
	private final static Logger logger=LoggerFactory.getLogger(JwtProvider.class);
	//Estos valores los tenemos en el proterties
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration;
	

	/**
	 * Generación del token
	 * @param authentication
	 * @return
	 */
	public String generateToken(Authentication authentication) {
		
		UsuarioPrincipal usuarioPrincipal=(UsuarioPrincipal) authentication.getPrincipal();
		
		//Construye el token a partir del usuario
		
		  return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
	                .signWith(SignatureAlgorithm.HS512, secret)
	                .compact();
	}
	
	/**
	 * Obtiene el nombre de usuario
	 * @param token
	 * @return 
	 */
	public String getNombreUsuarioFromToken(String token) {
		 return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	/**
	 * Valida el token
	 * @param token
	 * @return true o false si hay algun error en el token
	 */
	public boolean validateToken(String token) {
		  try {
	            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
	            return true;
	        }catch (MalformedJwtException e){
	            logger.error("token mal formado");
	        }catch (UnsupportedJwtException e){
	            logger.error("token no soportado");
	        }catch (ExpiredJwtException e){
	            logger.error("token expirado");
	        }catch (IllegalArgumentException e){
	            logger.error("token vacío");
	        }catch (SignatureException e){
	            logger.error("fallo en la firma");
	        }
	        return false;
	}
	
}
