package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hsqldb.lib.StringUtil;

import conexaoHSQLDB.CNXHSQLDB;
import entidade.Carro;

public class CarroDAO {
	private final String SQL_INSERE_CARRO = "INSERT INTO carro(descricao,nome,renavam) VALUES (?,?,?);";
	private final String SQL_ALTERA_CARRO = "UPDATE carro SET descricao=?, nome=?, renavam=? WHERE ID=?;";
	private final String SQL_EXCLUI_CARRO = "DELETE FROM carro WHERE ID=?";
	private final String SQL_SELECIONA_CARRO = "SELECT * FROM carro";
	private final String SQL_PESQUISA_CARRO = "SELECT * FROM carro WHERE descricao LIKE ? ";

	private PreparedStatement pst = null;

	public void inserirCarro(Carro umCarro) {
		try (	Connection conn = new CNXHSQLDB().conectar(); 
				PreparedStatement pst = conn.prepareStatement(SQL_INSERE_CARRO);) {
			pst.setString(1, umCarro.getDescricao());
			pst.setString(2, umCarro.getNome());
			pst.setInt(3, umCarro.getRenavam());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}

	public ArrayList<Carro> listarTodosCarro() {
		ArrayList<Carro> listaDeCarros = new ArrayList<Carro>();

		Carro umCarro;
		try (	Connection conn = new CNXHSQLDB().conectar();
				PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_CARRO);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				umCarro = new Carro();
				umCarro.setId(rs.getInt("ID"));
				umCarro.setDescricao(rs.getString("descricao"));
				umCarro.setNome(rs.getString("nome"));
				umCarro.setRenavam(rs.getInt("renavam"));
				listaDeCarros.add(umCarro);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		}

		return listaDeCarros;
	}

	public boolean alterarCarro(Carro umCarro) {
		boolean ret = false;
		try (	Connection conn = new CNXHSQLDB().conectar();
				PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_CARRO);
				/*ResultSet rs = pst.executeQuery();*/) {
			pst.setString(1, umCarro.getDescricao());
			pst.setString(2, umCarro.getNome());
			pst.setInt(3, umCarro.getRenavam());
			pst.setInt(4, umCarro.getId());
			
			ret = pst.execute();
			pst.close();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		return ret;
	}

	public boolean excluiCarro(Carro umCarro) {
		boolean ret = false;
		try (	Connection conn = new CNXHSQLDB().conectar();
				
				PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_CARRO);
				
				/*ResultSet rs = pst.executeQuery();*/) {
			pst.setInt(1, umCarro.getId());
			ret = pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		return ret;
	}
	
	public ArrayList<Carro> pesquisarCarro(String nome, String descricao, Integer renavam) {
		ArrayList<Carro> pesquisaCarro = new ArrayList<Carro>();
		Carro umCarro;
		try {	Connection conn = new CNXHSQLDB().conectar();
				
				StringBuilder sb = new StringBuilder("SELECT * FROM carro ");
				
				if(!StringUtil.isEmpty(nome) || !StringUtil.isEmpty(descricao) || renavam != null) {
					
					sb.append(" WHERE 1 = 1 ");
					if(!StringUtil.isEmpty(nome)) {						
						sb.append(" AND nome like '%").append(nome).append("%' ");
					}					
					if(!StringUtil.isEmpty(descricao)) {						
						sb.append(" AND descricao like '%").append(descricao).append("%' ");
					}					
					if(renavam != null) {						
						sb.append(" AND renavam = ").append(renavam);
					}
				}
				
				PreparedStatement pst = conn.prepareStatement(sb.toString());
				
				ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				umCarro = new Carro();
				umCarro.setId(rs.getInt("ID"));
				umCarro.setDescricao(rs.getString("descricao"));
				umCarro.setNome(rs.getString("nome"));
				umCarro.setRenavam(rs.getInt("renavam"));
				pesquisaCarro.add(umCarro);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		}

		return pesquisaCarro;
	}
}
