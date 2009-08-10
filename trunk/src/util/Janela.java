package util;

import java.awt.*;

public class Janela
{
	public static void centraliza(Window janela)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = janela.getSize();
		
		if (frameSize.height > screenSize.height)
		{
			frameSize.height = screenSize.height;
		}
		
		if (frameSize.width > screenSize.width)
		{
			frameSize.width = screenSize.width;
		}
		
		janela.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
	}
}