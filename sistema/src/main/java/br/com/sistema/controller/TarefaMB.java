package br.com.sistema.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.model.Tarefa;
import br.com.sistema.service.SistemaService;
import br.com.sistema.utility.Message;
import br.com.sistema.utility.NegocioException;

@Named
@ViewScoped
public class TarefaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Tarefa tarefa;

	@Inject
	private SistemaService service;

	private List<Tarefa> tarefas;

	@PostConstruct
	public void carregar() {
		tarefas = service.todasAsTarefas();
	}

	public void adicionar() {
		try {
			service.salvar(tarefa);
			tarefa = new Tarefa();
			carregar();

			Message.info("Cadastrado com sucesso");
		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			service.remover(tarefa);
			carregar();

			Message.info(tarefa.getTitulo() + "foi removido.");

		} catch (NegocioException e) {
			Message.erro(e.getMessage());

		}
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

}
