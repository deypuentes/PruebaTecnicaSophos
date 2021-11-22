package com.sophos.identity.servicio;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sophos.identity.dominio.UserIdentity;
import com.sophos.identity.repositorio.UserIdentityDAO;




@Service
public class UserIdentityServicio {
	private final Logger logger = LogManager.getLogger(UserIdentityServicio.class);

	@Autowired
	private UserIdentityDAO dao;
	
	@Transactional(readOnly = true)
	public List <UserIdentity> obtenerTodo() {
		logger.debug("Iniciando operacion obtener todo");
		List <UserIdentity> lista = dao.findAll();
		
		if (lista.size()==0) {
				logger.warn("No se encontraron usuarios");
				throw new RuntimeException("No se encontraron usuarios");
			}
		
		return lista;
	}
	
	@Transactional(readOnly = true)
	public UserIdentity obtenerPorId(int id) {
		logger.debug("Iniciando operacion obtenerPorId:{" + String.valueOf(id) + "}");
		Optional<UserIdentity>  item = dao.findById(id);
		if (!item.isPresent()) {
			logger.warn("No se encontro el usuario");
			throw new RuntimeException("No se encontro el usuario");

	}
		return item.get();
	}

	@Transactional(readOnly = true)
	public UserIdentity obtenerPorUserName(String username) {
		logger.debug("Iniciando operacion obtenerPorUserName:{" + String.valueOf(username) + "}");
		UserIdentity item = dao.findByUsername(username);
		
		if (null==item) {
			logger.warn("No se encontro el usuario");
			throw new RuntimeException("No se encontro el usuario");

	}
		return item;
	}

	@Transactional(readOnly = false)
	public  UserIdentity insertar(UserIdentity entidad) {
		logger.debug("Iniciando operacion insertar UserIdentity", entidad);

			UserIdentity item = dao.findByUsername(entidad.getUsername());

			if (!(null==item)) {
				logger.warn("Entidad no cumplio la validacion de negocio {UserIdentityLog}", entidad);
				throw new RuntimeException("Error Llave Unica");

		}
			dao.save(entidad);

		return entidad;
	}

	@Transactional(readOnly = false)
	public void actualizar(UserIdentity entidad) {
		logger.debug("Iniciando operacion actualizar:{}", entidad);
		dao.save(entidad);
	}

	@Transactional(readOnly = false)
	public void eliminar(String userName) {
		logger.debug("Iniciando operacion eliminar:{" + userName + "}");
		UserIdentity item = dao.findByUsername(userName);
		dao.deleteById(item.getId());
	}

}
