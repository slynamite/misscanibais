package travessia;

import java.util.*;
import exceptions.*;
import util.*;

public class Estado
{
	public static int MARGEM_A = 0;
	public static int MARGEM_B = 1;

	private int missionarios;
	private int canibais;
	private int margem;

	private Estado estadoPai;

	private Pilha estadosFilho;

	public Estado(int missionarios, int canibais, int margem)
	{
		setMissionarios(missionarios);
		setCanibais(canibais);
		setMargem(margem);
		setEstadoPai(null);
	}

	public Estado(int missionarios, int canibais, int margem, Estado estadoPai)
	{
		this(missionarios, canibais, margem);
		setEstadoPai(estadoPai);
	}

	public int getMissionarios()
	{
		return missionarios;
	}

	public int getCanibais()
	{
		return canibais;
	}

	public int getMargem()
	{
		return margem;
	}

	public Estado getEstadoPai()
	{
		return estadoPai;
	}

	public Pilha getEstadosFilho()
	{
		return estadosFilho;
	}

	public void setMissionarios(int missionarios)
	{
		this.missionarios = missionarios;
	}

	public void setCanibais(int canibais)
	{
		this.canibais = canibais;
	}

	public void setMargem(int margem)
	{
		this.margem = margem;
	}

	public void setEstadoPai(Estado estadoPai)
	{
		this.estadoPai = estadoPai;
	}

	public void procriar()
	{
		Pilha pilhaFilhos = new Pilha();
		int margem = (getMargem() == MARGEM_A) ? MARGEM_B : MARGEM_A;
		int controle = (getMargem() == MARGEM_A) ? -1 : 1;
		Estado estado;
		int i = 0;
		for (int j = 1; j < 3; j++)
		{
			estado = new Estado(getMissionarios() + (i * controle),
					getCanibais() + (j * controle), margem, this);
			if (estado.valido())
			{
				pilhaFilhos.empilha(estado);
			}
			estado = new Estado(getMissionarios() + (j * controle),
					getCanibais() + (i * controle), margem, this);
			if (estado.valido())
			{
				pilhaFilhos.empilha(estado);
			}
			if (j == 1)
			{
				estado = new Estado(getMissionarios() + (j * controle),
						getCanibais() + (j * controle), margem, this);
				if (estado.valido())
				{
					pilhaFilhos.empilha(estado);
				}
			}
		}

		this.estadosFilho = pilhaFilhos;
	}

	public boolean valido()
	{
		if ((getMissionarios() >= 0) && (getCanibais() >= 0)
				&& (getMissionarios() <= 3) && (getCanibais() <= 3))
		{
			if ((getMissionarios() < getCanibais()) && (getMissionarios() != 0))
			{
				return false;
			}
			if ((getMissionarios() > getCanibais()) && (getMissionarios() != 3))
			{
				return false;
			}
			if ((getMissionarios() == 3) && (getCanibais() == 3)
					&& (getMargem() == MARGEM_B))
			{
				return false;
			}
			return true;
		}
		return false;
	}

	public boolean validoException() throws EstadoInvalidoException
	{

		if ((getMissionarios() < 0) || (getMissionarios() > 3))
			throw new EstadoInvalidoException(
					"Quantidade inválida de Missionários.");
		if ((getCanibais() < 0) || (getCanibais() > 3))
			throw new EstadoInvalidoException(
					"Quantidade inválida de Canibais.");
		if ((getMissionarios() < getCanibais()) && (getMissionarios() != 0))
		{
			throw new EstadoInvalidoException(
					"Número de Canibais > que o de Missionários.");
		}
		if ((getMissionarios() > getCanibais()) && (getMissionarios() != 3))
		{
			throw new EstadoInvalidoException(
					"Número de Canibais > que o de Missionários.");
		}
		if ((getMissionarios() == 3) && (getCanibais() == 3)
				&& (getMargem() == MARGEM_B))
		{
			throw new EstadoInvalidoException(
					"O barco não pode ficar sozinho na margem.");
		}
		if ((getMissionarios() == 0) && (getCanibais() == 0)
				&& (getMargem() == MARGEM_A))
		{
			throw new EstadoInvalidoException(
					"O barco não pode ficar sozinho na margem.");
		}
		return true;
	}

	public boolean equals(Estado estado)
	{
		if ((getMissionarios() == estado.getMissionarios())
				&& (getCanibais() == estado.getCanibais())
				&& (getMargem() == estado.getMargem()))
		{
			return true;
		}
		return false;
	}

	public boolean estadoFinal()
	{
		Estado estadoFinal = new Estado(0, 0, MARGEM_B);
		return (this.equals(estadoFinal));
	}

	public Estado roubarFilho()
	{
		return estadosFilho.desempilha();
	}

	public boolean temFilhos()
	{
		if (estadosFilho.vazia())
		{
			return false;
		}
		return true;
	}

	public boolean possuiAncestralIgual()
	{
		Estado estadoAtual = this;
		Estado novoEstado = estadoAtual.getEstadoPai();
		while (novoEstado != null)
		{
			if (estadoAtual.equals(novoEstado))
			{
				return true;
			}
			else
			{
				novoEstado = novoEstado.getEstadoPai();
			}
		}
		return false;
	}

	public ArrayList<Estado> retornarAcestrais()
	{
		ArrayList<Estado> estadosAncestrais = new ArrayList<Estado>();
		estadosAncestrais.add(this);
		Estado estadoAtual = this;
		Estado novoEstado = estadoAtual.getEstadoPai();
		while (novoEstado != null)
		{
			estadosAncestrais.add(novoEstado);
			novoEstado = novoEstado.getEstadoPai();
		}
		return estadosAncestrais;
	}
	
	public String toString()
	{
		String str = "";
		
		str += getMissionarios() + "M " + getCanibais() + "C ";
		str += (getMargem() == MARGEM_A) ? " <-->        " : "        <--> ";
		str += (3-getMissionarios()) + "M " + (3-getCanibais()) + "C ";
		
		return str;
	}
}