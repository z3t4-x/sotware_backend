package com.dev.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dev.domain.Rol;
import com.dev.domain.User;

import com.dev.domain.UsuarioPrincipal;
import com.dev.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repo;
	

	
	
	
	@Override
	public boolean existeCorreo(String email) {
	
		return repo.existsByEmail(email);
	}

	/**
	 * 
	 */
	@Override
	public User registrar(User user) throws Exception {
		
		Boolean existeUsuarioName = repo.existsByUsername(user.getUsername());
		//valida si existe el username
		if(Boolean.TRUE.equals(existeUsuarioName)) {
			
			throw new ServiceException("El usersename: "+ user.getUsername() +" ya existe");
		}
		
		Boolean existeEmail = repo.existsByEmail(user.getEmail());
		//valida si existe el email
		if(Boolean.TRUE.equals(existeEmail)) {
			
			throw new ServiceException("El email que intenta registrar ya existe: " + user.getEmail());
		}
		
		// se registra la fecha del día del registro
		user.setFcRegistro(LocalDate.now());
		
		
		return repo.save(user);
	}
	
/**
 * 
 */
	@Override
	public User modificar(User user) throws ServiceException {
	   	    
	    if (Objects.isNull(user.getId())) {
	        throw new ServiceException("El ID del usuario es nulo, no se puede modificar.");
	    }
	    
	    Optional<User> existingUser = repo.findById(user.getId());
	    
	    if (existingUser.isEmpty()) {
	        throw new ServiceException("El usuario con ID " + user.getId() + " no existe en la base de datos.");
	    }
	    
	    
	    return repo.save(user);
	}

	/**
	 * 
	 */
	@Override
	public List<User> listarUsuarios() throws Exception {
		
		return repo.findAll();
	}

	


	/**
	 * 
	 */
	@Override
	public User buscarPorId(Long id) throws Exception {
		
		return repo.findById(id).orElse(null);
	}

	/**
	 * 
	 */
	@Override
	public void eliminar(Long id) throws Exception {
		repo.deleteById(id);
		
	}

	/**
	 * 
	 */
	@Override
	public Optional<User> obtenerUsuario(String username) {
		
		
		return repo.findByUsername(username);
	}

	/**
	 * 
	 */
	@Override
	public boolean existeUsuario(String username) {

		return repo.existsByUsername(username);
	}

	/**
	 * 
	 */
	@Override
	public Optional<User> findByUsernameOrEmail(String username, String email) {
		
		 return repo.findByUsernameOrEmail(username, email);
	}

	
    @Override
    public List<Rol> obtenerRolesUsuario(){

        UsuarioPrincipal usuarioPrincipal = getUsuarioPrincipalAutenticado();
        String cdUsuario = usuarioPrincipal.getUsername();
        User user = repo.findByUsername(cdUsuario).orElse(null);
        if (user == null) {
            throw new ServiceException("No se encontró el usuario.");
        }
        return user.getRoles();
    }

	
    private UsuarioPrincipal getUsuarioPrincipalAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UsuarioPrincipal) {
            return (UsuarioPrincipal) authentication.getPrincipal();
        } else {
            throw new ServiceException("No se pudo obtener el usuario autenticado.");
        }
    }


}
