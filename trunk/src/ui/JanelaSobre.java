/*
 * JanelaSobre.java
 * Created on 06/03/2005
 *
 * cvs-id : $Id$
 */
package ui;

import java.awt.*;
import javax.swing.*;
import util.*;

/**
 * 
 * @author Vinicius Rocha
 */

public class JanelaSobre extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final int WINDOW_WIDTH = 350;
	private final int WINDOW_HEIGHT = 280;

	public JanelaSobre()
	{
		super();

		setTitle("Sobre");
		setModal(true);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		Janela.centraliza(this);

		getContentPane().add(createTabs());

		setVisible(true);
	}

	private JTabbedPane createTabs()
	{
		JTabbedPane tabs = new JTabbedPane();
		tabs.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		tabs.add("Sobre", createPainelSobre());
		tabs.add("Créditos", createPainelCreditos());
		return tabs;
	}

	private JPanel createPainelSobre()
	{
		JPanel painelEditor = new JPanel();
		painelEditor.setLayout(new BorderLayout());
		JTextPane editor = new JTextPane();
		
		String str = "Descrição do projeto."; // TODO Adicionar texto sobre o projeto.
		
		editor.setText(str);
		editor.setEditable(false);
		editor.setCaretPosition(0);
		painelEditor.add(new JScrollPane(editor), BorderLayout.CENTER);
		return painelEditor;
	}

	private JPanel createPainelCreditos()
	{
		JPanel painelCreditos = new JPanel();
		painelCreditos.setLayout(new BorderLayout());
		JTextPane creditos = new JTextPane();
		
		String str = ""; // TODO Adicionar texto creditos.
		
		creditos.setText(str);
		creditos.setEditable(false);
		creditos.setCaretPosition(0);
		painelCreditos.add(new JScrollPane(creditos), BorderLayout.CENTER);
		return painelCreditos;
	}
}