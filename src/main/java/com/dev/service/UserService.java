package com.dev.service;

import java.util.List;
import java.util.Optional;

import com.dev.domain.Rol;
import com.dev.domain.User;

public interface UserService {
	
	
 /**
  * método para validar si existe el correo.
  * @param email
  * @return
  */
    boolean existeCorreo(String email);

	/**
	 * Método registrar
	 * @param user
	 * @return
	 * @throws Exception
	 */
	    User registrar(User user) throws Exception;

    /**
     * método para modificar usuarios
     * @param usuarioDTO
     * @return
     * @throws Exception
     */
    User modificar(User user) throws Exception;

    /**
     * método para listar usuarios
     * @return
     * @throws Exception
     */
    List<User> listarUsuarios() throws Exception;

    List<Rol> obtenerRolesUsuario();


    /**
     * buscar por id
     * @param id
     * @return
     * @throws Exception
     */
    User buscarPorId(Long id) throws Exception;



    /**
     * eliminar
     * @param id
     * @throws Exception
     */
    void eliminar(Long id) throws Exception;
    
    /**
     * 
     * @param username
     * @return
     */
    Optional<User> obtenerUsuario(String username);
    
    
    boolean existeUsuario(String username);
    
    /**
     * Obtiene un usuario por su nombre de usuario o dirección de correo electrónico.
     *
     * @param usernameOrEmail Nombre de usuario o dirección de correo electrónico
     * @return Usuario encontrado (si existe)
     */
    Optional<User> findByUsernameOrEmail(String username, String email);

}
