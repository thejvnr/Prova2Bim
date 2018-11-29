package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hsqldb.lib.StringUtil;

import conexaoHSQLDB.CNXHSQLDB;
import entidade.Carro;
import entidade.Multa;

public class MultaDAO {
	private final String SQL_INSERE_MULTA = "INSERT INTO multa(tipo,quantidade) VALUES (?,?);";
	private final String SQL_EXCLUI_MULTA = "DELETE FROM multa WHERE ID=?";
	private final String SQL_SELECIONA_MULTA = "SELECT * FROM multa";

	private PreparedStatement pst = null;

	public void inserirMulta(Multa umaMulta) {
		try (	Connection conn = new CNXHSQLDB().conectar(); 
				PreparedStatement pst = conn.prepareStatement(SQL_INSERE_MULTA);) {
			pst.setString(1, umaMulta.getTipo());
			pst.setInt(2, umaMulta.getQuantidade());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}

	public ArrayList<Multa> listaTodasMultas() {
		ArrayList<Multa> listaMultas = new ArrayList<Multa>();

		Multa umaMulta;
		try (	Connection conn = new CNXHSQLDB().conectar();
				PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_MULTA);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				umaMulta = new Multa();
				umaMulta.setId(rs.getInt("ID"));
				umaMulta.setTipo(rs.getString("descricao"));
				umaMulta.setQuantidade(rs.getInt("renavam"));
				listaMultas.add(umaMulta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
			
		}

		return listaMultas;
	}

	public boolean excluiMulta(Multa umaMulta) {
		boolean ret = false;
		try (	Connection conn = new CNXHSQLDB().conectar();
				
				PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_MULTA);
				) {
			pst.setInt(1, umaMulta.getId());
			ret = pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		return ret;
	}
	
	public ArrayList<Multa> pesquisarMulta(String tipo, Integer quantidade) {
		ArrayList<Multa> pesquisaMulta = new ArrayList<Multa>();
		Multa umaMulta;
		try {	Connection conn = new CNXHSQLDB().conectar();
				
				StringBuilder sb = new StringBuilder("SELECT * FROM multa ");
				
				if(!StringUtil.isEmpty(tipo) || quantidade != null) {
					
					sb.append(" WHERE 1 = 1 ");
					if(!StringUtil.isEmpty(tipo)) {						
						sb.append(" AND tipo like '%").append(tipo).append("%' ");
					}								
					if(quantidade != null) {						
						sb.append(" AND quantidade = ").append(quantidade);
					}
				}
				
				PreparedStatement pst = conn.prepareStatement(sb.toString());
				
				ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				umaMulta = new Multa();
				umaMulta.setId(rs.getInt("ID"));
				umaMulta.setTipo(rs.getString("tipo"));
				umaMulta.setQuantidade(rs.getInt("quantidade"));
				pesquisaMulta.add(umaMulta);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		}

		return pesquisaMulta;
	}
}
