package controller;

import java.util.ArrayList;

import dao.CarroDAO;
import entidade.Carro;

public class controllerCarro {
	
	public void inserir (Carro umCarro) {
		new CarroDAO().inserirCarro(umCarro);
		 
	}
	
	public ArrayList<Carro> listarTodos() {
		return new CarroDAO().listarTodosCarro();
	}
	
	public ArrayList<Carro> pesquisarPorDescricao(String nome, String descricao, Integer renavam) {
		return new CarroDAO().pesquisarCarro(nome, descricao, renavam);
	}

}
