package com.sophos.board.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sophos.board.dominio.Task;
import com.sophos.board.modelo.TaskDTO;


@Component
public class TaskConversor {


	public TaskDTO entidadADto(Task entidad) {
		TaskDTO dto = new TaskDTO();
		dto.setId(entidad.getId());
		dto.setTaskname(entidad.getTaskname());
		dto.setDescription(entidad.getDescription());
		dto.setState(entidad.getState());
		dto.setIdPerson(entidad.getIdPerson());
		return dto;
	}
	
	public Task dtoAEntidad(TaskDTO dto) {
		Task entidad = new Task();
		entidad.setId(dto.getId());
		entidad.setTaskname(dto.getTaskname());
		entidad.setDescription(dto.getDescription());
		entidad.setState(dto.getState());
		entidad.setIdPerson(dto.getIdPerson());
		
		return entidad;
	}
	
	public List<Task> listaDtoAEntidad(List<TaskDTO> listaModelo) {
        List<Task> listaEntidad = new ArrayList<>();

        listaModelo.forEach((modelo) -> {
            listaEntidad.add(dtoAEntidad(modelo));
        });

        return listaEntidad;
    }

    public List<TaskDTO> listaEntidadADto(List<Task> listaEntidad) {
        List<TaskDTO> listaModelo = new ArrayList<>();

        listaEntidad.forEach((entity) -> {
            listaModelo.add(entidadADto(entity));
        });

        return listaModelo;
    }
}
