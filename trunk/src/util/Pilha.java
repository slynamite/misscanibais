package util;

import travessia.*;
import java.util.*;

public class Pilha extends Stack<Estado>
{
	private static final long serialVersionUID = 1L;
	public Pilha()
	{
		super();
	}

	public void empilha(Estado estado)
	{
		push(estado);
	}

	public Estado desempilha()
	{
		return (Estado) pop();
	}

	public Estado topo()
	{
		return (Estado) peek();
	}

	public boolean vazia()
	{
		return empty();
	}
}