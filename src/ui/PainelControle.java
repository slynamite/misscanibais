package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import exceptions.*;
import travessia.*;

public class PainelControle extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JanelaPrincipal janelaPrincipal;

	private JPanel painelEstadoInicial;

	private JComboBox comboMissionarios;
	private JComboBox comboCanibais;
	private JComboBox comboMargem;

	private JButton botaoGerarEstados;

	public PainelControle(JanelaPrincipal janelaPrincipal)
	{
		super();
		setJanelaPrincipal(janelaPrincipal);
		setLayout(new BorderLayout());
		add(criarPainelEstadoInicial(), BorderLayout.NORTH);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}
	
	private void setJanelaPrincipal(JanelaPrincipal janelaPrincipal)
	{
		this.janelaPrincipal = janelaPrincipal;
	}

	public void setMissionarios(int missionarios)
	{
		comboMissionarios.setSelectedIndex(missionarios);
	}

	public void setCanibais(int canibais)
	{
		comboCanibais.setSelectedIndex(canibais);
	}

	public void setMargem(int margem)
	{
		comboMargem.setSelectedIndex(margem);
	}

	private JPanel criarPainelEstadoInicial()
	{
		if (painelEstadoInicial == null)
		{
			painelEstadoInicial = new JPanel();
			painelEstadoInicial.setLayout(new FlowLayout());

			painelEstadoInicial.add(new JLabel("Esquerda: "));
			painelEstadoInicial.add(criarComboMissionarios());
			painelEstadoInicial.add(new JLabel("M"));
			painelEstadoInicial.add(criarComboCanibais());
			painelEstadoInicial.add(new JLabel("C     "));
			painelEstadoInicial.add(new JLabel("Início do Barco:"));
			painelEstadoInicial.add(criarComboMargem());

			painelEstadoInicial.add(criarBotaoGerarEstados());
		}
		return painelEstadoInicial;
	}

	private JComboBox criarComboMissionarios()
	{
		if (comboMissionarios == null)
		{
			String[] conteudo = new String[]
			{ "0", "1", "2", "3" };
			comboMissionarios = new JComboBox(conteudo);
			comboMissionarios.setSelectedIndex(3);
			comboMissionarios.setFocusable(false);
		}
		return comboMissionarios;
	}

	private JComboBox criarComboCanibais()
	{
		if (comboCanibais == null)
		{
			String[] conteudo = new String[] { "0", "1", "2", "3" };
			comboCanibais = new JComboBox(conteudo);
			comboCanibais.setSelectedIndex(3);
			comboCanibais.setFocusable(false);
		}
		return comboCanibais;
	}

	private JComboBox criarComboMargem()
	{
		if (comboMargem == null)
		{
			String[] conteudo = new String[]
			{ "Esquerda", "Direita" };
			comboMargem = new JComboBox(conteudo);
			comboMargem.setFocusable(false);
		}
		return comboMargem;
	}

	private JButton criarBotaoGerarEstados()
	{
		if (botaoGerarEstados == null)
		{
			botaoGerarEstados = new JButton("Gerar");
			botaoGerarEstados.addActionListener(new ListenerGerarSaida(this));
		}
		return botaoGerarEstados;
	}

	public void gerarSaida()
	{
		int missionarios = comboMissionarios.getSelectedIndex();
		int canibais = comboCanibais.getSelectedIndex();
		int margem = comboMargem.getSelectedIndex();

		Estado estadoInicial = new Estado(missionarios, canibais, margem);

		try
		{
			estadoInicial.validoException();
			janelaPrincipal.gerarSaida(estadoInicial);
		}
		catch (EstadoInvalidoException e)
		{
			JOptionPane.showMessageDialog(null, "Estado inválido:\n"
					+ e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

}

class ListenerGerarSaida implements ActionListener
{
	private PainelControle painelControle;
	
	public ListenerGerarSaida(PainelControle painel)
	{
		painelControle = painel;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		painelControle.gerarSaida();
	}
}