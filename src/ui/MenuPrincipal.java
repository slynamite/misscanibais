package ui;

import java.awt.event.*;

import javax.swing.*;

public class MenuPrincipal extends JMenuBar
{
	private static final long serialVersionUID = 1L;

	private JanelaPrincipal janelaPrincipal;

	// Atributos do Menu Arquivo
	private JMenu menuArquivo;
	private JMenuItem itemNovo;
	private JMenuItem itemSair;

	// Atributos do Menu LookAndFell
	private JMenu menuLookAndFell;

	// Atributos do Menu Ajuda
	private JMenu menuAjuda;
	private JMenuItem itemSobre;

	public MenuPrincipal(JanelaPrincipal janelaPrincipal)
	{
		super();
		setJanelaPrincipal(janelaPrincipal);
		add(criarMenuArquivo());
		add(criarMenuLookAndFell());
		add(criarMenuAjuda());
	}
	
	private void setJanelaPrincipal(JanelaPrincipal janelaPrincipal)
	{
		this.janelaPrincipal = janelaPrincipal;
	}

	// Metodos do Menu Arquivo

	private JMenu criarMenuArquivo()
	{
		if (menuArquivo == null)
		{
			menuArquivo = new JMenu("Arquivo");
			menuArquivo.setMnemonic('a');
			menuArquivo.add(criarItemNovo());
			menuArquivo.addSeparator();
			menuArquivo.add(criarItemSair());
		}
		return menuArquivo;
	}

	private JMenuItem criarItemNovo()
	{
		if (itemNovo == null)
		{
			itemNovo = new JMenuItem("Novo");
			itemNovo.setMnemonic('n');
			itemNovo.addActionListener(new ListenerNovo(janelaPrincipal));

		}
		return itemNovo;
	}

	private JMenuItem criarItemSair()
	{
		if (itemSair == null)
		{
			itemSair = new JMenuItem("Sair");
			itemSair.setMnemonic('r');
			itemSair.addActionListener(new ListenerSair());

		}
		return itemSair;
	}

	// Metodos do Menu LookAndFeel
	
	private JMenu criarMenuLookAndFell()
	{
		if (menuLookAndFell == null)
		{
			menuLookAndFell = new JMenu("Visual");

			UIManager.LookAndFeelInfo[] looks = UIManager
					.getInstalledLookAndFeels();
			JMenuItem[] itemLookAndFell = new JMenuItem[looks.length];
			for (int i = 0; i < looks.length; i++)
			{
				itemLookAndFell[i] = new JMenuItem(looks[i].getName());
				itemLookAndFell[i].addActionListener(
					new ListenerChangeLookAndFell(janelaPrincipal, i));
				menuLookAndFell.add(itemLookAndFell[i]);
			}
		}
		return menuLookAndFell;
	}

	// Metodos do Menu Ajuda

	private JMenu criarMenuAjuda()
	{
		if (menuAjuda == null)
		{
			menuAjuda = new JMenu("Ajuda");
			menuAjuda.setMnemonic('j');
			menuAjuda.add(criarItemSobre());
		}
		return menuAjuda;
	}

	private JMenuItem criarItemSobre()
	{
		if (itemSobre == null)
		{
			itemSobre = new JMenuItem("Sobre");
			itemSobre.setMnemonic('s');
			itemSobre.addActionListener(new ListenerJanelaSobre());
		}
		return itemSobre;
	}
}

class ListenerSair implements ActionListener
{
	public ListenerSair()
	{
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.exit(0);
	}
}

class ListenerJanelaSobre implements ActionListener
{
	public ListenerJanelaSobre()
	{
	}
	
	public void actionPerformed(ActionEvent e)
	{
		new JanelaSobre();
	}
}

class ListenerChangeLookAndFell implements ActionListener
{
	private JanelaPrincipal main;
	private int indice;
	
	public ListenerChangeLookAndFell(JanelaPrincipal main, int indice)
	{
		this.main = main;
		this.indice = indice;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		main.changeLookAndFeel(indice);		
	}
}

class ListenerNovo implements ActionListener
{
	private JanelaPrincipal main;
	
	public ListenerNovo(JanelaPrincipal main)
	{
		this.main = main;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		main.novo();	
	}
}