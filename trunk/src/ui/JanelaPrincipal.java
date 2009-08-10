package ui;

import travessia.*;
import util.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class JanelaPrincipal extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static final int WINDOW_WIDTH  = 500;
	private static final int WINDOW_HEIGHT = 400;

	private JPanel painelPrincipal;
	private PainelControle painelControle;
	private PainelSaida painelSaida;

	public JanelaPrincipal()
	{
		super("Missionários e Canibais");

		try
		{
			UIManager.setLookAndFeel(
				UIManager.getInstalledLookAndFeels()[0].getClassName());
			SwingUtilities.updateComponentTreeUI(this);

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		Janela.centraliza(this);

		getContentPane().add(criarPainelPrincipal());
		setJMenuBar(new MenuPrincipal(this));

		painelSaida.adicionarTabEstado("Saída", "");

		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JPanel criarPainelPrincipal()
	{
		if (painelPrincipal == null)
		{
			painelPrincipal = new JPanel();
			painelPrincipal.setLayout(new BorderLayout());
			painelPrincipal.add(criarPainelControle(), BorderLayout.NORTH);
			painelPrincipal.add(criarPainelSaida(), BorderLayout.CENTER);
			painelPrincipal.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		}
		return painelPrincipal;
	}

	private PainelControle criarPainelControle()
	{
		if (painelControle == null)
		{
			painelControle = new PainelControle(this);
		}
		return painelControle;
	}

	private PainelSaida criarPainelSaida()
	{
		if (painelSaida == null)
		{
			painelSaida = new PainelSaida();
		}
		return painelSaida;
	}

	public void gerarSaida(Estado estadoInicial)
	{
		if (estadoInicial.estadoFinal())
		{
			painelSaida.removerTabs();
			painelSaida.adicionarTabEstado("Saída",
				"Você ja está no estado final.");
			return;
		}

		Pilha pilha = new Pilha();
		Solucao solucao = new Solucao();
		ArrayList<Estado> estadosAncestrais = new ArrayList<Estado>();

		solucao.buscarSolucoes(estadoInicial, pilha);
		int j = 1;

		painelSaida.removerTabs();
		
		if (pilha.vazia())
		{
			painelSaida.adicionarTabEstado("Saída",
				"Não existe solução para esse estado.");
		}
		
		while (!pilha.vazia())
		{
			String saida = new String("");
			Estado estadoFinal = pilha.desempilha();
			estadosAncestrais = estadoFinal.retornarAcestrais();

			for (int i = estadosAncestrais.size() - 1; i >= 0; i--)
			{
				saida += (Estado) estadosAncestrais.get(i) + "\n";
			}
			painelSaida.adicionarTabEstado("Solução " + (j++), saida);
		}
	}

	public void novo()
	{
		painelSaida.removerTabs();
		painelSaida.adicionarTabEstado("Saída", "");
		painelControle.setMissionarios(3);
		painelControle.setCanibais(3);
		painelControle.setMargem(Estado.MARGEM_A);
	}

	public void changeLookAndFeel(int i)
	{
		try
		{
			UIManager.setLookAndFeel(
				UIManager.getInstalledLookAndFeels()[i].getClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args)
	{
		JanelaPrincipal janela = new JanelaPrincipal();
		janela.setVisible(true);
	}
}