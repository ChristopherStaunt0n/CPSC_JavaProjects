import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Class representing a Phone Book that stores
 * names with their corresponding number and if
 * the number is a home or work phone number
 * @author Christopher Staunton
 * @version 8.0.1110.14
 */
@SuppressWarnings("serial")
public class PhoneBook extends JFrame {

	private ArrayList<String> name;
	private ArrayList<String> number;
	private ArrayList<String> HW;
	private int index;
	private int size;

	/**
	 * Constructor that creates the PhoneBook
	 * Has private variables index (current page), size (number of entries),
	 * name (person's name), number (person's phone number), and HW (determines if the number
	 * is home or work)
	 * The integers: index and size are initialized to 0, meanwhile the other three String
	 * ArrayLists are initialized empty
	 */
	public PhoneBook() {

		index = 0;
		size = 0;
		name = new ArrayList<String>();
		number = new ArrayList<String>();
		HW = new ArrayList<String>();

		JMenuBar m = new JMenuBar();
		setJMenuBar(m);
		JMenu p = new JMenu("Program");
		JMenuItem ex = p.add("Exit");
		JMenu h = new JMenu("Help");
		JMenuItem a = h.add("About...");
		m.add(p);
		m.add(h);
		ex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int result = JOptionPane.showConfirmDialog(PhoneBook.this, "Want to exit?");
				if (result == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(PhoneBook.this,
						new JLabel("<html><hr><>PhoneBook</i><br>by Christopher B. Staunton<hr><html>"));
			}
		});

		setTitle("PhoneBook");
		setLayout(new GridLayout(5, 1));

		// 1st
		// row------------------------------------------------------------------------------------------------
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		add(p1);

		JLabel ent = new JLabel("Entries:");
		p1.add(ent);

		JLabel in = new JLabel(index + "/" + size);
		p1.add(in);

		// 2nd
		// row----------------------------------------------------------------------------------------------
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		add(p2);

		JLabel nam = new JLabel("Name");
		p2.add(nam);

		JTextField ntext = new JTextField(40);
		p2.add(ntext);

		// 3rd
		// row----------------------------------------------------------------------------------------------
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		add(p3);

		JLabel pho = new JLabel("Phone");
		p3.add(pho);

		JTextField ptext = new JTextField(20);
		p3.add(ptext);

		// 4th
		// row------------------------------------------------------------------------------------------------
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		add(p4);

		JCheckBox home = new JCheckBox("Home");
		p4.add(home);

		JCheckBox work = new JCheckBox("Work");
		p4.add(work);

		// 5th
		// row------------------------------------------------------------------------------------------------
		JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));// error
		add(p5);
		JButton next = new JButton("Next");
		p5.add(next);
		JButton before = new JButton("Before");
		p5.add(before);
		JButton delete = new JButton("Delete");
		p5.add(delete);
		JButton add = new JButton("Add");
		p5.add(add);

		ActionListener BeforeB = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (index > 1) {
					index = index - 1;
					in.setText(index + "/" + size);

					ntext.setText(name.get(index - 1));
					ptext.setText(number.get(index - 1));
					if (HW.get(index - 1).equals("WorkAndHome")) {
						work.setSelected(true);
						home.setSelected(true);
					} else if (HW.get(index - 1).equals("Work")) {
						work.setSelected(true);
						home.setSelected(false);
					} else if (HW.get(index - 1).equals("Home")) {
						work.setSelected(false);
						home.setSelected(true);
					} else {
						work.setSelected(false);
						home.setSelected(false);
					}
				}

				if (0 == size) {
					delete.setEnabled(false);
					next.setEnabled(false);
					before.setEnabled(false);
				} else if ((index > 1) && (index < size)) {
					before.setEnabled(true);
					next.setEnabled(true);
				} else if (index == 1) {
					before.setEnabled(false);
					next.setEnabled(true);
				} else if ((index == size) && (size != 1)) {
					before.setEnabled(true);
					next.setEnabled(false);
				}

			}
		};

		before.addActionListener(BeforeB);

		ActionListener NextN = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (index < size) {
					index = index + 1;
					in.setText(index + "/" + size);

					ntext.setText(name.get(index - 1));
					ptext.setText(number.get(index - 1));
					if (HW.get(index - 1).equals("WorkAndHome")) {
						work.setSelected(true);
						home.setSelected(true);
					} else if (HW.get(index - 1).equals("Work")) {
						work.setSelected(true);
						home.setSelected(false);
					} else if (HW.get(index - 1).equals("Home")) {
						work.setSelected(false);
						home.setSelected(true);
					} else {
						work.setSelected(false);
						home.setSelected(false);
					}
				}

				if (0 == size) {
					delete.setEnabled(false);
					next.setEnabled(false);
					before.setEnabled(false);
				} else if ((index > 1) && (index < size)) {
					before.setEnabled(true);
					next.setEnabled(true);
				} else if (index == 1) {
					before.setEnabled(false);
					next.setEnabled(true);
				} else if ((index == size) && (size != 1)) {
					before.setEnabled(true);
					next.setEnabled(false);
				}

			}
		};

		next.addActionListener(NextN);

		ActionListener DeleteD = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				name.remove(index - 1);
				number.remove(index - 1);
				HW.remove(index - 1);

				size = size - 1;
				if (size == 0) {
					index = 0;
				}
				in.setText(index + "/" + size);
				if (index > 1) {
					ntext.setText(name.get(index - 1));
					ptext.setText(number.get(index - 1));
					if (HW.get(index - 1).equals("WorkAndHome")) {
						work.setSelected(true);
						home.setSelected(true);
					} else if (HW.get(index - 1).equals("Work")) {
						work.setSelected(true);
						home.setSelected(false);
					} else if (HW.get(index - 1).equals("Home")) {
						work.setSelected(false);
						home.setSelected(true);
					} else {
						work.setSelected(false);
						home.setSelected(false);
					}
				}

				if ((index > 1) && (index < size)) {
					before.setEnabled(true);
					next.setEnabled(true);
				} else if (index == 1) {
					before.setEnabled(false);
					next.setEnabled(true);
				} else if ((index == size) && (size != 1) && (size != 0)) {
					before.setEnabled(true);
					next.setEnabled(false);
				}

			}
		};

		delete.addActionListener(DeleteD);

		ActionListener AddA = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (index == 0) {
					index = 1;
				}
				size = size + 1;
				in.setText(index + "/" + size);

				name.add(ntext.getText());
				number.add(ptext.getText());
				if ((work.isSelected() && (home.isSelected()))) {
					HW.add("WorkAndHome");
				} else if (work.isSelected()) {
					PhoneBook.this.HW.add("Work");
				} else if (home.isSelected()) {
					PhoneBook.this.HW.add("Home");
				} else {
					PhoneBook.this.HW.add("None");
				}

				index = size;
				in.setText(index + "/" + size);

				if (index > 1) {
					before.setEnabled(true);
				} else {
					before.setEnabled(false);
				}

				next.setEnabled(false);
				delete.setEnabled(true);
			}
		};

		add.addActionListener(AddA);

		// other--------------------------------------------------------------------------------------------------
		if (0 == size) {
			delete.setEnabled(false);
			next.setEnabled(false);
			before.setEnabled(false);
		}

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	}

	public static void main(String[] args) {
		JFrame frame = new PhoneBook();
		frame.setVisible(true);
	}

}
