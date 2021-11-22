package com.sophos.board.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sophos.board.cliente.IdentityCliente;
import com.sophos.board.conversor.TaskConversor;
import com.sophos.board.dominio.Task;
import com.sophos.board.modelo.TaskDTO;
import com.sophos.board.modelo.UserIdentityDTO;
import com.sophos.board.repositorio.TaskDAO;

@Service
public class TaskServicio {
	private final Logger logger = LogManager.getLogger(TaskServicio.class);

	@Autowired
	private TaskDAO dao;
	
	 @Autowired
	 private TaskConversor conversor;
	 
	 @Autowired
	 private IdentityCliente cliente;

	@Transactional(readOnly = true)
	public List<TaskDTO> obtenerPorId(int idPersona) {
		logger.debug("Iniciando operacion obtenerPorId:{" + String.valueOf(idPersona) + "}");

		List<Task> list = new ArrayList<>();
		dao.findAllByIdPerson(idPersona).forEach(list::add);
		List<TaskDTO> listaDTO =conversor.listaEntidadADto(list);
		for (TaskDTO taskDTO : listaDTO) {
			UserIdentityDTO identity = cliente.obtenerPorId(taskDTO.getIdPerson());
			taskDTO.setUserName(identity.getUsername());
		}

		return listaDTO;
	}

	@Transactional(readOnly = false)
	public List<Task> insertar(List<Task> listaEntidad) {
		logger.debug("Iniciando operacion insertar Task", listaEntidad);

		List<Task> listaEntidadRetorno = new ArrayList<>();

		for (Task entidad : listaEntidad) {
			Optional<Task> item = dao.findById(entidad.getId());

			if (item.isPresent()) {
				logger.warn("Entidad no cumplio la validacion de negocio {TaskLog}", entidad);
				throw new RuntimeException("Error Llave Primaria");
			}
			listaEntidadRetorno.add(dao.save(entidad));

		}

		return listaEntidadRetorno;
	}

	@Transactional(readOnly = false)
	public void actualizar(Task entidad) {
		logger.debug("Iniciando operacion actualizar:{}", entidad);
		dao.save(entidad);
	}

	@Transactional(readOnly = false)
	public void eliminar(int id) {
		logger.debug("Iniciando operacion eliminar:{" + id + "}");
		dao.deleteById(id);
	}

}
