package com.sophos.board.web;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.board.conversor.TaskConversor;
import com.sophos.board.modelo.TaskDTO;
import com.sophos.board.servicio.TaskServicio;

@RestController
@RequestMapping(path = "/sophos/board")
@CrossOrigin
public class TaskCrontrolador {

	private final Logger logger = LogManager.getLogger(TaskCrontrolador.class);
	
	 @Autowired
	 private TaskConversor conversor;
	
	@Autowired
	private TaskServicio servicio;
	

	@GetMapping(value = "/{idPersona}")
    public List<TaskDTO> obtenerPorId(@PathVariable(name = "idPersona") int idPersona) {
        logger.info("Iniciando consumo del API Task obtenerPorId");

        try {
            return servicio.obtenerPorId(idPersona);
        } finally {
            logger.info("Finalizando consumo del API Task obtenerPorId");
        }
    }
	 
	@PostMapping
    public List<TaskDTO> insertar(@RequestBody List<TaskDTO> listaModelo) {
        logger.info("Iniciando consumo del API Task insertar");

        try {
            return conversor.listaEntidadADto(servicio.insertar(conversor.listaDtoAEntidad(listaModelo)));
        } finally {
            logger.info("Finalizando consumo del API Task insertar");
        }
    }

		@PutMapping
		public TaskDTO actualizar(@RequestBody TaskDTO dto) {
			logger.info("Iniciando consumo del API Task actualizar");
			try {
				
				if (dto.getTaskname().isEmpty()) {
					logger.warn("El nombre de la tarea no puede ser nulo");
					throw new RuntimeException("El nombre de la tarea no puede ser nulo");
				}
				if (dto.getDescription().isEmpty()) {
					logger.warn("La descripción de la tarea no puede ser nula");
					throw new RuntimeException("La descripción de la tarea no puede ser nula");
				}
				
				//validación de que el usuario exista

				servicio.actualizar(conversor.dtoAEntidad(dto));
				return dto;
			} finally {
				logger.info("Finalizando consumo del API Task actualizar");
			}
		}

		@DeleteMapping(value = "/{idTask}")
		public boolean eliminar(@PathVariable(name = "idTask") int idTask) {
			logger.info("Iniciando consumo del API Task eliminar");

			try {
				servicio.eliminar(idTask);
				return true;
			} finally {
				logger.info("Finalizando consumo del API Task eliminar");
			}
		}
}
