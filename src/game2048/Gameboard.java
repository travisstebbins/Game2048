package game2048;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class Gameboard extends JFrame implements ActionListener, KeyListener
{
	
	private static final long serialVersionUID = 1L;
	
	//initialize components
		static JFrame gameboard;
		JPanel gamePanel, topPanel, mainPanel;
		JLabel[][] grid;
		JLabel endLabel, scoreLabel;
		JButton restartButton;
		
	//other variables
		static long score = 0;
		static long highScore;
		static Scanner in;
		static BufferedWriter out;
                Timer timer;
		
	public static void main(String[] args)
	{
		gameboard = new Gameboard();
		gameboard.setVisible(true);
		gameboard.pack();
		gameboard.setFocusable(false);
                gameboard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		score = 0;
		try
		{
			in = new Scanner(new File("highScore.txt"));
		}
		catch(IOException e)
		{
			System.out.println("highScore file open error");
		}
	}
	
	//constructor
		Gameboard()
		{
			//JPanel
				gamePanel = new JPanel();
				gamePanel.setPreferredSize(new Dimension(600,600));
				//gamePanel.setBorder(BorderFactory.createMatteBorder(10,10,10,10, new Color(255,255,255)));
				gamePanel.setLayout(new GridLayout(4,4));
				gamePanel.addKeyListener(this);
				gamePanel.setFocusable(true);
				topPanel = new JPanel();
				topPanel.setPreferredSize(new Dimension(600,60));
				//topPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(255,255,255)));
				topPanel.setBackground(new Color(255,255,255));
				mainPanel = new JPanel();
				mainPanel.setPreferredSize(new Dimension(600,660));
				mainPanel.setLayout(new BorderLayout());
				//mainPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(255,255,255)));
				
			
			//JLabel grid
				grid = new JLabel[4][4];		
				for(int i=0;i<4;i++)
				{
					for(int j=0;j<4;j++)
					{
						grid[i][j] = new BlankTile();
					}
				}
				
			//JLabel
				scoreLabel = new JLabel("Score: 0");
				scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
				scoreLabel.setVerticalAlignment(0);
				scoreLabel.setBorder(BorderFactory.createMatteBorder(13, 0, 0, 0, new Color(255,255,255)));
				
			//end
				endLabel = new JLabel();
				endLabel.setOpaque(true);
				endLabel.setFont(new Font("Arial",Font.BOLD,32));
				endLabel.setHorizontalAlignment(0);
				restartButton = new JButton("Restart");
				restartButton.setFont(new Font("Arial",Font.BOLD,48));
				restartButton.addActionListener(this);
				
			//Add grid to gamePanel
				updateGUI(grid);
				
			//add to mainPanel
				topPanel.add(scoreLabel);
				mainPanel.add(topPanel, BorderLayout.NORTH);
				mainPanel.add(gamePanel, BorderLayout.SOUTH);
			
			//other
				setContentPane(mainPanel);
				
			//create first number tile
				addTile();
				updateGUI(grid);
		}
	
	//move methods
		public void moveUp()
		{
			boolean flag = false;
			
			//move tiles
				for(int r=2;r>=0;r--)
				{
					for(int c=0;c<4;c++)
					{
						if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
						{
							if(grid[r+1][c].getClass().getName().equals("game2048.Tile"))
							{
								flag = true;
							}
							grid[r][c].setText("k");
							grid[r][c] = grid[r+1][c];
							grid[r+1][c] = new BlankTile();
						}
					}
				}
				for(int r=0;r<3;r++)
				{
					for(int c=0;c<4;c++)
					{
						if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
						{
							if(grid[r+1][c].getClass().getName().equals("game2048.Tile"))
							{
								flag = true;
							}
							grid[r][c].setText("k");
							grid[r][c] = grid[r+1][c];
							grid[r+1][c] = new BlankTile();
						}
					}
				}
			
			//combine tiles
				for(int r=0;r<3;r++)
				{
					for(int c=0;c<4;c++)
					{
						if(grid[r][c].getClass().getName().equals("game2048.Tile") && grid[r+1][c].getClass().getName().equals("game2048.Tile"))
						{
							Tile temp1 = (Tile)grid[r][c];
							Tile temp2 = (Tile)grid[r+1][c];
							if(temp1.getValue() == temp2.getValue())
							{
								grid[r][c].setText("p");
								grid[r][c] = new Tile(temp1.getValue()*2);
								grid[r+1][c] = new BlankTile();
								flag = true;
                                                                //System.out.println("\nflashTile triggered on " + ((Tile)(grid[r][c])).getValue() + " at " + "[" + r + "][" + c + "]");
                                                                //flashTile((Tile)grid[r][c]);
                                                                
							}
						}
					}
				}
				
				//move tiles
					for(int r=2;r>=0;r--)
					{
						for(int c=0;c<4;c++)
						{
							if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
							{
								if(grid[r+1][c].getClass().getName().equals("game2048.Tile"))
								{
									flag = true;
								}
								grid[r][c].setText("k");
								grid[r][c] = grid[r+1][c];
								grid[r+1][c] = new BlankTile();
							}
						}
					}
					for(int r=0;r<3;r++)
					{
						for(int c=0;c<4;c++)
						{
							if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
							{
								if(grid[r+1][c].getClass().getName().equals("game2048.Tile"))
								{
									flag = true;
								}
								grid[r][c].setText("k");
								grid[r][c] = grid[r+1][c];
								grid[r+1][c] = new BlankTile();
							}
						}
					}
				
				if(flag)
				{
					addTile();
				}
		}
		
		public void moveDown()
		{
			boolean flag = false;			
			
			//move tiles
				for(int r=1;r<=3;r++)
				{
					for(int c=0;c<4;c++)
					{
						if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
						{
							if(grid[r-1][c].getClass().getName().equals("game2048.Tile"))
							{
								flag = true;
							}
							grid[r][c].setText("k");
							grid[r][c] = grid[r-1][c];
							grid[r-1][c] = new BlankTile();
						}
					}
				}	
				for(int r=3;r>0;r--)
				{
					for(int c=0;c<4;c++)
					{
						if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
						{
							if(grid[r-1][c].getClass().getName().equals("game2048.Tile"))
							{
								flag = true;
							}
							grid[r][c].setText("k");
							grid[r][c] = grid[r-1][c];
							grid[r-1][c] = new BlankTile();
						}
					}
				}	
				
			//combine tiles
				for(int r=3;r>0;r--)
				{
					for(int c=0;c<4;c++)
					{
						if(grid[r][c].getClass().getName().equals("game2048.Tile") && grid[r-1][c].getClass().getName().equals("game2048.Tile"))
						{
							Tile temp1 = (Tile)grid[r][c];
							Tile temp2 = (Tile)grid[r-1][c];
							if(temp1.getValue() == temp2.getValue())
							{
								grid[r][c].setText("k");
								grid[r][c] = new Tile(temp1.getValue()*2);
								grid[r-1][c] = new BlankTile();
								flag = true;
                                                                //flashTile((Tile)grid[r][c]);
							}
						}
					}
				}	
				
				//move tiles
					for(int r=1;r<=3;r++)
					{
						for(int c=0;c<4;c++)
						{
							if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
							{
								if(grid[r-1][c].getClass().getName().equals("game2048.Tile"))
								{
									flag = true;
								}
								grid[r][c].setText("k");
								grid[r][c] = grid[r-1][c];
								grid[r-1][c] = new BlankTile();
							}
						}
					}	
					for(int r=3;r>0;r--)
					{
						for(int c=0;c<4;c++)
						{
							if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
							{
								if(grid[r-1][c].getClass().getName().equals("game2048.Tile"))
								{
									flag = true;
								}
								grid[r][c].setText("k");
								grid[r][c] = grid[r-1][c];
								grid[r-1][c] = new BlankTile();
							}
						}
					}	
				
				if(flag)
				{
					addTile();
				}
		}
		
		public void moveLeft()
		{
			boolean flag = false;
			
			//move tiles
				for(int c=2;c>=0;c--)
				{
					for(int r=0;r<4;r++)
					{
						if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
						{
							if(grid[r][c+1].getClass().getName().equals("game2048.Tile"))
							{
								flag = true;
							}
							grid[r][c].setText("k");
							grid[r][c] = grid[r][c+1];
							grid[r][c+1] = new BlankTile();
						}
					}
				}
				for(int c=0;c<3;c++)
				{
					for(int r=0;r<4;r++)
					{
						if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
						{
							if(grid[r][c+1].getClass().getName().equals("game2048.Tile"))
							{
								flag = true;
							}
							grid[r][c].setText("k");
							grid[r][c] = grid[r][c+1];
							grid[r][c+1] = new BlankTile();
						}
					}
				}
			
			//combine tiles
				for(int c=0;c<3;c++)
				{
					for(int r=0;r<4;r++)
					{
						if(grid[r][c].getClass().getName().equals("game2048.Tile") && grid[r][c+1].getClass().getName().equals("game2048.Tile"))
						{
							Tile temp1 = (Tile)grid[r][c];
							Tile temp2 = (Tile)grid[r][c+1];
							if(temp1.getValue() == temp2.getValue())
							{
								grid[r][c].setText("k");
								grid[r][c] = new Tile(temp1.getValue()*2);
								grid[r][c+1] = new BlankTile();
								flag = true;
                                                                //flashTile((Tile)grid[r][c]);
							}
						}
					}
				}
				
				//move tiles
					for(int c=2;c>=0;c--)
					{
						for(int r=0;r<4;r++)
						{
							if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
							{
								if(grid[r][c+1].getClass().getName().equals("game2048.Tile"))
								{
									flag = true;
								}
								grid[r][c].setText("k");
								grid[r][c] = grid[r][c+1];
								grid[r][c+1] = new BlankTile();
							}
						}
					}
					for(int c=0;c<3;c++)
					{
						for(int r=0;r<4;r++)
						{
							if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
							{
								if(grid[r][c+1].getClass().getName().equals("game2048.Tile"))
								{
									flag = true;
								}
								grid[r][c].setText("k");
								grid[r][c] = grid[r][c+1];
								grid[r][c+1] = new BlankTile();
							}
						}
					}
			
			if(flag)
			{
				addTile();
			}
		}
		
		public void moveRight()
		{
			boolean flag = false;
				
			//move tiles
				for(int c=1;c<=3;c++)
				{
					for(int r=0;r<4;r++)
					{
						if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
						{
							if(grid[r][c-1].getClass().getName().equals("game2048.Tile"))
							{
								flag = true;
							}
							grid[r][c].setText("k");
							grid[r][c] = grid[r][c-1];
							grid[r][c-1] = new BlankTile();
						}
					}
				}
				for(int c=3;c>0;c--)
				{
					for(int r=0;r<4;r++)
					{
						if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
						{
							if(grid[r][c-1].getClass().getName().equals("game2048.Tile"))
							{
								flag = true;
							}
							grid[r][c].setText("k");
							grid[r][c] = grid[r][c-1];
							grid[r][c-1] = new BlankTile();
						}
					}
				}
			
			//combine tiles
				for(int c=3;c>0;c--)
				{
					for(int r=0;r<4;r++)
					{
						if(grid[r][c].getClass().getName().equals("game2048.Tile") && grid[r][c-1].getClass().getName().equals("game2048.Tile"))
						{
							Tile temp1 = (Tile)grid[r][c];
							Tile temp2 = (Tile)grid[r][c-1];
							if(temp1.getValue() == temp2.getValue())
							{
								grid[r][c].setText("k");
								grid[r][c] = new Tile(temp1.getValue()*2);
								grid[r][c-1] = new BlankTile();
								flag = true;
                                                                //flashTile((Tile)grid[r][c]);
							}
						}
					}
				}
				
				//move tiles
					for(int c=1;c<=3;c++)
					{
						for(int r=0;r<4;r++)
						{
							if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
							{
								if(grid[r][c-1].getClass().getName().equals("game2048.Tile"))
								{
									flag = true;
								}
								grid[r][c].setText("k");
								grid[r][c] = grid[r][c-1];
								grid[r][c-1] = new BlankTile();
							}
						}
					}
					for(int c=3;c>0;c--)
					{
						for(int r=0;r<4;r++)
						{
							if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
							{
								if(grid[r][c-1].getClass().getName().equals("game2048.Tile"))
								{
									flag = true;
								}
								grid[r][c].setText("k");
								grid[r][c] = grid[r][c-1];
								grid[r][c-1] = new BlankTile();
							}
						}
					}
			
			if(flag)
			{
				addTile();
			}
		}
		
	//other methods
		public void updateGUI(JLabel[][] gridInput)
		{
			gamePanel.removeAll();
			for(int i=0;i<gridInput.length;i++)
			{
				for(int j=0;j<gridInput[i].length;j++)
				{
					gamePanel.add(gridInput[i][j]);
				}
			}
		}
		
		public void addTile()
		{
                    Random rand = new Random();
                    int row = rand.nextInt(4);
                    int col = rand.nextInt(4);
                    if(grid[row][col].getClass().getName().equals("game2048.BlankTile"))
                    {
                        grid[row][col].setText("k");
                        Random rand2 = new Random();
			if(rand2.nextDouble() < 0.7)
                            grid[row][col] = new Tile(2);
                        else
                            grid[row][col] = new Tile(4);
                        //System.out.println("\nflashTile triggered on " + ((Tile)(grid[row][col])).getValue() + " at " + "[" + row + "][" + col + "]");
                        //flashTile((Tile)grid[row][col]);
                    }
                    else
                    {
                        addTile();
                    }
		}
                
		public void flashTile(final Tile inputTile)
		{
			double period = 200;
			int delay = 10;
			final Color initialColor = inputTile.getColor();
                        System.out.println("initial color");
                        System.out.println(inputTile.getValue() + ", " + initialColor);
			final double rIncrement = (255-initialColor.getRed())*(delay/period);
			final double gIncrement = (255-initialColor.getGreen())*(delay/period);
			final double bIncrement = (255-initialColor.getBlue())*(delay/period);
			inputTile.color = new Color(255,255,255);
                        inputTile.setBackground(inputTile.color);
			timer = new Timer(delay,new ActionListener()
			{
			   public void actionPerformed(ActionEvent e)
			   {
			       int r = inputTile.color.getRed();
			       int g = inputTile.color.getGreen();
			       int b = inputTile.color.getBlue();
			       if(r >= initialColor.getRed())
			       {
			           r = (int)(r - rIncrement);
			       }
			       if(g >= initialColor.getGreen())
			       {
			           g = (int)(g - gIncrement);
			       }
			       if(b >= initialColor.getBlue())
			       {
			           b = (int)(b - bIncrement);
			       }
			       inputTile.color = new Color(r,g,b);
			       inputTile.setBackground(inputTile.color);
			       /*if(inputTile.color.getRed() >= initialColor.getRed() || inputTile.color.getGreen() >= initialColor.getGreen() || inputTile.color.getBlue() >= initialColor.getBlue())
			       {
			           int r = (int)(inputTile.color.getRed() - rIncrement);
			           int g = (int)(inputTile.color.getGreen() - gIncrement);
			           int b = (int)(inputTile.color.getBlue() - bIncrement);
			           inputTile.color = new Color(r,g,b);
			           inputTile.setBackground(new Color(r,g,b));
			           System.out.println(inputTile.color);
			       }*/
			       if(inputTile.color.getRed() <= initialColor.getRed() && inputTile.color.getGreen() <= initialColor.getGreen() && inputTile.color.getBlue() <= initialColor.getBlue())
			       {
                                   System.out.println("finished timer animation");
			           inputTile.setColor();
                                   System.out.println(inputTile.value + ", " + inputTile.color);
			           timer.stop();
                                   return;
			       }
			   }
			});                 
			timer.start();
		}
		
		public void verify(JLabel[][] grid)
		{
			for(int r=0;r<grid.length;r++)
			{
				for(int c=0;c<grid[r].length;c++)
				{
					if(grid[r][c].getClass().getName().equals("game2048.Tile"))
					{
						Tile temp = (Tile)grid[r][c];
						if(temp.getValue() == 2048)
							endGame(1);
					}
				}
			}
			int counter = 0;
			for(int r=0;r<grid.length;r++)
			{
				for(int c=0;c<grid[r].length;c++)
				{
					if(grid[r][c].getClass().getName().equals("game2048.BlankTile"))
					{
						counter++;
					}
				}
			}
			for(int r=0;r<grid.length-1;r++)
			{
				for(int c=0;c<grid[r].length;c++)
				{
					if(grid[r][c].getClass().getName().equals("game2048.Tile") && grid[r+1][c].getClass().getName().equals("game2048.Tile"))
					{
						Tile temp1 = (Tile)grid[r][c];
						Tile temp2 = (Tile)grid[r+1][c];
						if(temp1.getValue() == temp2.getValue())
						{
							counter++;
						}
					}
				}
			}
			for(int c=0;c<grid[0].length-1;c++)
			{
				for(int r=0;r<grid.length;r++)
				{
					if(grid[r][c].getClass().getName().equals("game2048.Tile") && grid[r][c+1].getClass().getName().equals("game2048.Tile"))
					{
						Tile temp1 = (Tile)grid[r][c];
						Tile temp2 = (Tile)grid[r][c+1];
						if(temp1.getValue() == temp2.getValue())
						{
							counter++;
						}
					}
				}
			}
			if(counter == 0)
				endGame(0);
		}
		
		public void calculateScore()
		{
			score = 0;
			for(int r=0;r<grid.length;r++)
			{
				for(int c=0;c<grid[r].length;c++)
				{
					if(grid[r][c].getClass().getName().equals("game2048.Tile"))
					{
						int x = calculateExponent(((Tile)grid[r][c]).getValue());
						score += Math.pow(2, x)*(x-1);
					}
				}
			}
		}
		
		public int calculateExponent(int value)
		{
			int x = 1;
			if(value==2)
				return x;
			return x+calculateExponent(value/2);
		}
		
		public void endGame(int endType)
		{
			try
			{
				highScore = (long)Integer.parseInt(in.nextLine());
			}
			catch(NoSuchElementException e)
			{
				System.out.println("highScore read error");
			}
                        catch(NullPointerException e)
                        {
                            System.out.println("highScore read error");
                        }
			if(score>highScore)
			{
				highScore = score;
			}
			try
			{
				out = new BufferedWriter(new FileWriter("highScore.txt"));
				out.write("" + highScore);
				out.close();
			}
			catch(IOException e)
			{
				System.out.println("output error");
			}
			mainPanel.removeAll();
			gamePanel.removeAll();
			topPanel.removeAll();
			gamePanel.removeKeyListener(this);
			mainPanel.setLayout(new GridLayout(3,1));
			scoreLabel.setText("Score: " + score + "   High Score: " + highScore);
			scoreLabel.setFont(new Font("Arial", Font.BOLD, 32));
			scoreLabel.setHorizontalAlignment(0);
			scoreLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(255,255,255)));
			if(endType == 0)
			{
				endLabel.setText("Game Over");
				mainPanel.add(endLabel);
				mainPanel.add(scoreLabel);
				mainPanel.add(restartButton);
			}
			if(endType == 1)
			{
				endLabel.setText("You Win");
				mainPanel.add(endLabel);
				mainPanel.add(scoreLabel);
				mainPanel.add(restartButton);
			}
		}
	
	//key listener
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_UP)
			{
				moveUp();
			}	
			
			if(e.getKeyCode()==KeyEvent.VK_DOWN)
			{
				moveDown();
			}
			
			if(e.getKeyCode()==KeyEvent.VK_LEFT)
			{
				moveLeft();
			}
			
			if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			{
				moveRight();
			}
			updateGUI(grid);
			calculateScore();
			scoreLabel.setText("Score: " + score);
			verify(grid);
		}
	
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == restartButton)
			{
				String[] restart = {"","",""};
				gameboard.dispose();
				main(restart);
			}
		}
}