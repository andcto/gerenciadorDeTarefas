package br.com.sistema.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.sistema.dao.DAO;
import br.com.sistema.model.Tarefa;
import br.com.sistema.utility.NegocioException;

public class SistemaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private DAO<Tarefa> dao;
	
	public void salvar(Tarefa t) throws NegocioException {
		dao.salvar(t);
	}
	
	public void remover(Tarefa t) throws NegocioException{
		dao.remover(Tarefa.class, t.getId());
	}
	
	public List<Tarefa> todasAsTarefas(){
		return dao.buscarTodos("select t from Tarefa t order by t.titulo");
	}
}

//string t from Tarefa t