package ui;

import java.awt.*;
import javax.swing.*;

public class PainelSaida extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabs;

	public PainelSaida()
	{
		super();
		this.setLayout(new BorderLayout());
		this.add(criarTab(), BorderLayout.CENTER);
	}

	private JTabbedPane criarTab()
	{
		if (tabs == null)
		{
			tabs = new JTabbedPane();
		}
		return tabs;
	}

	public void removerTabs()
	{
		tabs.removeAll();
	}

	public void adicionarTabEstado(String label, String conteudo)
	{
		JEditorPane texto = new JEditorPane();
		texto.setText(conteudo);
		texto.setFont(new Font("Monospaced", 0, 12));
		texto.setEditable(false);

		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(texto);

		tabs.add(label, scroll);
	}
}