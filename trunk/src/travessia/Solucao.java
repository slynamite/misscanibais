package travessia;

import util.*;

public class Solucao
{
	public Solucao()
	{
	}

	public void buscarSolucoes(Estado estado, Pilha pilha)
	{
		Estado filho;

		if (estado.estadoFinal())
		{
			return;
		}
		if (estado.possuiAncestralIgual())
		{
			return;
		}

		estado.procriar();
		if (estado.temFilhos())
		{
			do
			{
				filho = estado.roubarFilho();
				if (filho.estadoFinal())
				{
					pilha.empilha(filho);
					return;
				}
				else
				{
					buscarSolucoes(filho, pilha);
				}
			}
			while (!filho.estadoFinal() && estado.temFilhos());
		}
	}
}