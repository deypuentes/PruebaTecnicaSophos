package com.sophos.board.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sophos.board.dominio.Task;


@Repository
public interface TaskDAO extends CrudRepository<Task, Integer>{
	
	List <Task> findAllByIdPerson(Integer idperson);


}
