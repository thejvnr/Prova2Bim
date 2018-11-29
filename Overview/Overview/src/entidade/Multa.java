package entidade;

public class Multa {
	private int id;
	private String tipo;
	private int quantidade;

	
	
	public Multa(int id, String tipo, int quantidade) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.quantidade = quantidade;
	}
	
	public Multa() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + id;
		result = prime * result + quantidade;
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
		Multa other = (Multa) obj;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (id != other.id)
			return false;
		if (quantidade != other.quantidade)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Multa [id=" + id + ", tipo=" + tipo + ", quantidade=" + quantidade + "]";
	}
	
	
	
}