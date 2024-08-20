import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MouseSquares extends JFrame {
	
	private Timer ttt;
	
	private ArrayList<mySquare> currentSquares;
	private ArrayList<mySquare> undoS;
	
	private int xLoc;
	private int yLoc;
	
	private JMenuItem undo;
	private JMenuItem redo;
	private JMenuItem list;
	
	private List<String> sss;
	
	public MouseSquares() {
		
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		
		JMenu program = new JMenu("Program");
		JMenu edit = new JMenu("Edit");
		
		JMenuItem exit = program.add("Exit");
		
		undo = edit.add("Undo");
		redo = edit.add("Redo");
		list = edit.add("List");
		
		menu.add(program);
		menu.add(edit);
		
		undo.setEnabled(false);
		redo.setEnabled(false);
		
		thePanel p = new thePanel();
		add(p);
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					dispose();
			}
		});
		
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				redo.setEnabled(true);
				
				if (((currentSquares.get(currentSquares.size()-1)).getColr() != 1)&&((currentSquares.get(currentSquares.size()-1)).getColr() != 7)) {
					currentSquares.get(currentSquares.size()-1).minColr();
					currentSquares.get(currentSquares.size()-1).plusUn();
				}
				else {
					undoS.add(currentSquares.get(currentSquares.size()-1));
					currentSquares.remove(currentSquares.size()-1);
					
				}
				
				if (currentSquares.size() == 0) {
					undo.setEnabled(false);
				}
				
				repaint();
			}
		});
		
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				if ((currentSquares.size() == 0)&&(undoS.size() != 0)/**/) {
					currentSquares.add(undoS.get(undoS.size()-1));
					undoS.remove(undoS.size()-1);
				}
				else if ((currentSquares.get(currentSquares.size()-1).getUndos() > 0)/*&&(undoS.size() != 0)*/) {
					currentSquares.get(currentSquares.size()-1).plusColr();
					currentSquares.get(currentSquares.size()-1).minUn();
				}
				else {
					currentSquares.add(undoS.get(undoS.size()-1));
					undoS.remove(undoS.size()-1);
				}
				
				
				if (undoS.size() == 0) {
					redo.setEnabled(false);
				}
				
				undo.setEnabled(true);
				
				repaint();
			}
		});
		
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				sss = new ArrayList<String>();
				
				for (int i = 0; i < currentSquares.size(); i++) {
					
					sss.add(getListStringForThisProjectOrWhatever(currentSquares.get(i)));
					
				}
				
				MouseSquaresDialog.showListDialog(MouseSquares.this, sss);
				
			}
		});
		
		setTitle("MouseSquares");
		
		pack();
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	private String getListStringForThisProjectOrWhatever(mySquare rrr) {
		
		String h = "[color=";
		
		if (rrr.getColr() == 1) {
			h = h + "BLUE,x=";
		}
		else if (rrr.getColr() == 2) {
			h = h + "GREEN,x=";
		}
		else if (rrr.getColr() == 3) {
			h = h + "MAGENTA,x=";
		}
		else if (rrr.getColr() == 4) {
			h = h + "ORANGE,x=";
		}
		else if (rrr.getColr() == 5) {
			h = h + "RED,x=";
		}
		else if (rrr.getColr() == 6) {
			h = h + "YELLOW,x=";
		}
		
		h = h + rrr.gX() + ",y=";
		h = h + rrr.gY() + "]";
		
		
		return h;
		
	}
	
	public static void main(String[] args) {
		JFrame f = new MouseSquares();
		f.setVisible(true);
	}
	
	private class thePanel extends JPanel {
		
		public thePanel() {
			
			currentSquares = new ArrayList<mySquare>();
			undoS = new ArrayList<mySquare>();
			
			setBackground(Color.WHITE);
			setSize(500,500);
			setFocusable(true);
			
			
			
			ttt = new Timer(100, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
				}
				
			});
			ttt.start();
			
			
			addMouseListener(new MouseListener() {
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (SwingUtilities.isLeftMouseButton(arg0)) {
						
						xLoc = arg0.getX();
						yLoc = arg0.getY();
						
						currentSquares.add(new mySquare(xLoc,yLoc));
						undo.setEnabled(true);
						
					}
					else if ((SwingUtilities.isRightMouseButton(arg0))&&(currentSquares != null)&&(currentSquares.size() > 0)) {
						
						currentSquares.get(currentSquares.size()-1).plusColr();
					
					}
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					repaint();
					
				}
			});
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			
			if (currentSquares != null) {
				
				for (int i = 0; i < currentSquares.size(); i++) {
					
					currentSquares.get(i).draw(g2);
					
				}
				
			}
			
		}
		
	}
	
	private abstract class Drawable {
		
		private int x;
		private int y;
		
		protected Drawable(int xV, int yV) {
			x = xV;
			y = yV;
		}
		
		public abstract void draw(Graphics2D g2);
		
		protected int gX() {
			return x;
		}
		
		protected int gY() {
			return y;
		}
		
	}
	
	private class mySquare extends Drawable {
		
		private int colr;
		private int cUndos;
		
		protected mySquare(int a, int b) {
			super(a,b);
			colr = 1;
			cUndos = 0;
		}
		
		protected int getUndos() {
			return cUndos;
		}
		
		protected void plusUn() {
			cUndos++;
		}
		
		protected void minUn() {
			cUndos--;
		}
		
		protected int getColr() {
			return colr;
		}
		
		protected void plusColr() {
			if (colr == 6) {
				colr = 1;
			}
			else {
				colr++;
			}
		}
		
		protected void minColr() {
			if (colr == 1) {
				colr = 6;
			}
			else {
				colr--;
			}
		}
		
		@Override
		public void draw(Graphics2D g2) {
			Rectangle2D.Float s = new Rectangle2D.Float(gX(),gY(),10,10);
			if (colr == 1) {
				g2.setColor(Color.BLUE);
			}
			else if (colr == 2) {
				g2.setColor(Color.GREEN);
			}
			else if (colr == 3) {
				g2.setColor(Color.MAGENTA);
			}
			else if (colr == 4) {
				g2.setColor(Color.ORANGE);
			}
			else if (colr == 5) {
				g2.setColor(Color.RED);
			}
			else if (colr == 6) {
				g2.setColor(Color.YELLOW);
			}
			else if (colr >= 7) {
				g2.setColor(Color.BLUE);
				colr = 1;
			}
			else if (colr <= 0) {
				g2.setColor(Color.YELLOW);
				colr = 6;
			}
			
			g2.fill(s);
		}
		
	}
	
}
