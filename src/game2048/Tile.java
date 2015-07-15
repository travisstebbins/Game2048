package game2048;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.CENTER;

public class Tile extends JLabel
{
	private static final long serialVersionUID = 1L;
	
	int value;
	Color color;
	Color borderColor = new Color(188,174,161);
	Color fontColor;
	
	//constructors
		Tile()
		{
			value = 2;
			this.setText("" + value);
			this.setHorizontalAlignment(CENTER);
			this.setFont(new Font("Arial", Font.BOLD, 48));
			this.setForeground(fontColor);
			this.setOpaque(true);
			setColor();
			this.setBorder(BorderFactory.createMatteBorder(7,7,7,7,borderColor));
		}
		
		Tile(int val)
		{
			value = val;
			this.setText("" + value);
			this.setHorizontalAlignment(CENTER);
			this.setFont(new Font("Arial", Font.BOLD, 48));
			this.setForeground(fontColor);
			this.setOpaque(true);
			setColor();
			this.setBorder(BorderFactory.createMatteBorder(7,7,7,7,borderColor));
		}
		
	//getter methods
		public int getValue()
		{
			return value;
		}
		
	//setter methods
		public void setValue(int val)
		{
			value = val;
		}
		
	
	public void setColor()
	{
		switch(value)
		{
			case 2:		color = new Color(237,227,217);
					fontColor = new Color(118,108,99);
					break;
			case 4: 	color = new Color(227,215,193);
					fontColor = new Color(118,108,99);
					break;
			case 8:		color = new Color(242,177,121);
					fontColor = new Color(242,255,243);
					break;
			case 16:	color = new Color(236,141,85);
					fontColor = new Color(242,255,243);
					break;
			case 32:	color = new Color(245,126,96);
					fontColor = new Color(242,255,243);
					break;
			case 64:	color = new Color(234,89,58);
					fontColor = new Color(242,255,243);
					break;
			case 128:	color = new Color(237,206,113);
					fontColor = new Color(242,255,243);
					break;
			case 256:	color = new Color(237,204,99);
					fontColor = new Color(242,255,243);
					break;
			case 512:	color = new Color(228,192,42);
					fontColor = new Color(242,255,243);
					break;
			case 1024:	color = new Color(226,185,19);
					fontColor = new Color(242,255,243);
					break;
			case 2048:	color = new Color(236,196,0);
					fontColor = new Color(245,245,245);
					break;
			default:	color = Color.red;
					this.setBackground(color);
					break;
		}
		this.setBackground(color);
		this.setForeground(fontColor);
	}
        
        public Color getColor()
        {
            switch(value)
		{
			case 2:		return new Color(237,227,217);
			case 4: 	return new Color(227,215,193);
			case 8:		return new Color(242,177,121);
			case 16:	return new Color(236,141,85);
			case 32:	return new Color(245,126,96);
			case 64:	return new Color(234,89,58);
			case 128:	return new Color(237,206,113);
			case 256:	return new Color(237,204,99);
			case 512:	return new Color(228,192,42);
			case 1024:	return new Color(226,185,19);
			case 2048:	return new Color(236,196,0);
			default:	return Color.red;
		}
        }
}
