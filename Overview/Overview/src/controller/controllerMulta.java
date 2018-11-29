package controller;

import java.util.ArrayList;

import dao.CarroDAO;
import dao.MultaDAO;
import entidade.Carro;
import entidade.Multa;

public class controllerMulta {
	
	public void inserirMulta (Multa umaMulta) {
		new MultaDAO().inserirMulta(umaMulta);
		 
	}
	
	public ArrayList<Multa> listarMultas() {
		return new MultaDAO().listaTodasMultas();
	}
	
	public ArrayList<Multa> pesquisarPorMulta(String tipo, Integer quantidade) {
		return new MultaDAO().pesquisarMulta(tipo, quantidade);
	}

}
