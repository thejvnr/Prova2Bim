package entidade;

public class Carro {
	private int id;
	private String descricao;
	private String nome;
	private int renavam;

	
	
	public Carro(int id, String descricao, String nome, int renavam) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.nome = nome;
		this.renavam = renavam;
	}
	
	
	public Carro() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Carro [descricao=" + descricao + ", id=" + id + ", nome=" + nome + ", renavam=" + renavam + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + renavam;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (renavam != other.renavam)
			return false;
		return true;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getRenavam() {
		return renavam;
	}


	public void setRenavam(int renavam) {
		this.renavam = renavam;
	}


	
	

}
