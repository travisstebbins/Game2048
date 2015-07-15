package game2048;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class BlankTile extends JLabel
{
	private static final long serialVersionUID = 1L;
	
	Color backgroundColor = new Color(204,192,180);
	Color borderColor = new Color(188,174,161);
	
	//constructors
		BlankTile()
		{
			this.setOpaque(true);
			this.setBackground(backgroundColor);
			this.setBorder(BorderFactory.createMatteBorder(7,7,7,7,borderColor));
		}

}
