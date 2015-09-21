//*******1*********2*********3*********4*********5*********6*********7*********8
// FourPixCreatorGUI.java, Group 3, Midterm, July 9, 2014

/** 
 * 
 * ICS 240, Summer 2014
 * Midterm, Group Project
 * Development team members: 	
 * 		Je Moua <rh4208et@metrostate.edu>
 * 		Tim Schleicher <timdan2@gmail.com>
 * 		Linh Huynh <md3113oz@metrostate.edu>
 * 		Yuxiang Wang <gj4912oy@metrostate.edu>
 * 		Travis Worthen <ra2730vp@metrostate.edu>
 *		Mastewal Zelalem <em0133qa@metrostate.edu>
 */

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

public class FourPixCreatorGUI extends Applet
{
	// prepare the FourPix's from the input file
	FourPixCreatorCollection fpcc = new FourPixCreatorCollection(
			Configuration.INPUT_FILE_NAME);

	// get a random FourPix from the input collection
	FourPixCreator fpc = fpcc.getRandomFourPixCreator();

	static final long serialVersionUID = 1L;

	// Integer representations of letter types
	static final int ANY_LETTER_TYPE = 0;
	static final int CONSONANT_LETTER_TYPE = 1;
	static final int VOWEL_LETTER_TYPE = 2;

	// Width and height of the JLabels
	static final int LABEL_SIZE = 180;

	// Pic JLabels
	JLabel labelImg1 = new JLabel();
	JLabel labelImg2 = new JLabel();
	JLabel labelImg3 = new JLabel();
	JLabel labelImg4 = new JLabel();

	// Text fields for the answer
	JTextField[] jtf;

	// Create panels for game
	private JPanel mainGridPanel = new JPanel(new GridLayout(3, 1));
	private JPanel mainPanel = new JPanel(new FlowLayout(
			FlowLayout.LEFT, 2, 0));
	private JPanel imagePanel = new JPanel(new GridLayout(2, 2));
	private JPanel subPanel1 = new JPanel(new FlowLayout(
			FlowLayout.LEFT, 2, 0));
	private JPanel subPanel2 = new JPanel(new FlowLayout(
			FlowLayout.LEFT, 2, 0));
	private JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
	private JPanel fieldPanel = new JPanel(new FlowLayout(
			FlowLayout.LEFT, 10, 10));
	private JPanel buttonPanel = new JPanel(new GridLayout(
			Configuration.NUMBER_OF_FILLER_ROWS,
			Configuration.NUMBER_OF_TILES_PER_ROW, 0, 5));

	// The FourPixCreator being displayed
	FourPixCreator fourPix;

	// Maps for the letters and their JButton/JTextField
	Map<String, String> textMap;
	Map<String, JButton> buttonMap;

	/**
	 * Init method for applet
	 */
	@Override
	public void init()
	{
		setSize(500,650);
		initializeGUI();
	}

	/**
	 * Initializes the GUI
	 */
	public void initializeGUI()
	{
		fourPix = fpc;
		textMap = new HashMap<String, String>();
		buttonMap = new HashMap<String, JButton>();

		initFourPix();
		initCharacters();

		mainGridPanel.setBackground(Color.BLACK);
		mainPanel.setBackground(Color.BLACK);
		imagePanel.setBackground(Color.BLACK);
		subPanel1.setBackground(Color.BLACK);
		subPanel2.setBackground(Color.BLACK);
		bottomPanel.setBackground(Color.BLACK);
		fieldPanel.setBackground(Color.BLACK);
		buttonPanel.setBackground(Color.BLACK);

		add(mainPanel);  
	}

	/**
	 * Initializes the display of the FourPixCreator.
	 */
	private void initFourPix() 
	{
		if ("text".equalsIgnoreCase(Configuration.DISPLAY_MODE)) 
		{
			int fontSize = 30;
			labelImg1.setText(fourPix.getWord1());
			labelImg1.setForeground(Color.red);//
			labelImg1.setFont(new Font("Serif", Font.PLAIN, fontSize));//
			labelImg1.setPreferredSize(new Dimension(LABEL_SIZE, LABEL_SIZE));

			labelImg2.setText(fourPix.getWord2());
			labelImg2.setFont(new Font("Serif", Font.PLAIN, fontSize));//
			labelImg2.setForeground(Color.blue);//
			labelImg2.setPreferredSize(new Dimension(LABEL_SIZE, LABEL_SIZE));

			labelImg3.setText(fourPix.getWord3());
			labelImg3.setFont(new Font("Serif", Font.PLAIN, fontSize));// /
			labelImg3.setForeground(Color.orange);//
			labelImg3.setPreferredSize(new Dimension(LABEL_SIZE, LABEL_SIZE));

			labelImg4.setText(fourPix.getWord4());
			labelImg4.setFont(new Font("Serif", Font.PLAIN, fontSize));//
			labelImg4.setForeground(Color.green);//
			labelImg4.setPreferredSize(new Dimension(LABEL_SIZE, LABEL_SIZE));
		} 
		else if ("image".equalsIgnoreCase(Configuration.DISPLAY_MODE)) 
		{
			ImageIcon icon1 = null;
			ImageIcon icon2 = null;
			ImageIcon icon3 = null;
			ImageIcon icon4 = null;
			try 
			{
				URL imgURL1 = new URL(fourPix.getURL1());
				icon1 = new ImageIcon(imgURL1);

				URL imgURL2 = new URL(fourPix.getURL2());
				icon2 = new ImageIcon(imgURL2);

				URL imgURL3 = new URL(fourPix.getURL3());
				icon3 = new ImageIcon(imgURL3);

				URL imgURL4 = new URL(fourPix.getURL4());
				icon4 = new ImageIcon(imgURL4);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			//set the dimensions of the images
			ScaleIcon scaleicon1 = new ScaleIcon(
					icon1, LABEL_SIZE, LABEL_SIZE);
			labelImg1.setIcon(scaleicon1);
			labelImg1.setPreferredSize(new Dimension(LABEL_SIZE, LABEL_SIZE));
			labelImg1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

			ScaleIcon scaleicon2 = new ScaleIcon(
					icon2, LABEL_SIZE, LABEL_SIZE);
			labelImg2.setIcon(scaleicon2);
			labelImg2.setPreferredSize(new Dimension(LABEL_SIZE, LABEL_SIZE));
			labelImg2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

			ScaleIcon scaleicon3 = new ScaleIcon(
					icon3, LABEL_SIZE, LABEL_SIZE);
			labelImg3.setIcon(scaleicon3);
			labelImg3.setPreferredSize(new Dimension(LABEL_SIZE, LABEL_SIZE));
			labelImg3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

			ScaleIcon scaleicon4 = new ScaleIcon(
					icon4, LABEL_SIZE, LABEL_SIZE);
			labelImg4.setIcon(scaleicon4);
			labelImg4.setPreferredSize(new Dimension(LABEL_SIZE, LABEL_SIZE));
			labelImg4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		}

		//add images to imagePanel
		imagePanel.add(labelImg1);
		imagePanel.add(labelImg2);
		imagePanel.add(labelImg3);
		imagePanel.add(labelImg4);

		//putting the panels together onto one another
		subPanel1.add(imagePanel);
		bottomPanel.add(fieldPanel);
		bottomPanel.add(buttonPanel);
		subPanel2.add(bottomPanel);
		mainGridPanel.add(subPanel1);
		mainGridPanel.add(subPanel2);
		mainPanel.add(mainGridPanel);
	}

	/**
	 * Initializes the display of the characters available.
	 */
	private void initCharacters() 
	{
		// generate the available letters
		ArrayList<String> letters = generateAvailableLetters();
		int index = 1;
		for (int i = 0; i < Configuration.NUMBER_OF_FILLER_ROWS; i++) 
		{
			for (int j = 0; j < Configuration.NUMBER_OF_TILES_PER_ROW; j++) 
			{
				JButton jb = new JButton();
				jb.setPreferredSize(new Dimension(50, 55));
				String sT = letters.get(index-1);
				jb.setText(sT);
				jb.setName("JButton" + (index++));
				jb.addActionListener(new LetterButtonListener());
				buttonMap.put(jb.getName(), jb);
				buttonPanel.add(jb);
			}
		}

		// Set the attributes for the text fields that will hold the 
		// solution word
		int solutionLength = fourPix.getSolutionWord().length();
		jtf = new JTextField[solutionLength];
		for (int i = 0; i < solutionLength; i++) 
		{
			jtf[i] = new JTextField();
			jtf[i].setName("JTextField" + i);
			jtf[i].setHorizontalAlignment(SwingConstants.CENTER);
			jtf[i].setPreferredSize(new Dimension(40, 40));
			jtf[i].setBackground(Color.WHITE);
			jtf[i].addMouseListener(new LetterTextListener());
			jtf[i].setEditable(false);
			fieldPanel.add(jtf[i]);
		}
	}

	/**
	 * Generates the letters available for the word set.
	 * Includes the letters from the solution word and
	 * random filler letters.
	 */
	private ArrayList<String> generateAvailableLetters() 
	{
		ArrayList<String> letters = new ArrayList<>();

		// add the letters from the solution word
		for (char solutionLetter : fourPix.getSolutionWord().toCharArray())
		{
			letters.add(Character.toString(solutionLetter));
		}

		// number of random letters needed
		int nAvailableLetters = Configuration.NUMBER_OF_FILLER_ROWS 
				* Configuration.NUMBER_OF_TILES_PER_ROW;
		int nRandomLetters = nAvailableLetters - 
				fourPix.getSolutionWord().length();

		if ("consonant".equalsIgnoreCase(Configuration.FILLER_CHARACTERS)) 
		{
			// add consonant letters
			for (int i=0; i<nRandomLetters; i++) 
			{
				letters.add(generateRandomLetter(CONSONANT_LETTER_TYPE));
			}
		} 
		else if ("vowel".equalsIgnoreCase(Configuration.FILLER_CHARACTERS)) 
		{
			// add vowel letters
			for (int i=0; i<nRandomLetters; i++) 
			{
				letters.add(generateRandomLetter(VOWEL_LETTER_TYPE));
			}
		} 
		else {
			// add any letters
			for (int i=0; i<nRandomLetters; i++) 
			{
				letters.add(generateRandomLetter(ANY_LETTER_TYPE));
			}
		}
		// shuffle the letters and return
		Collections.shuffle(letters);
		return letters;
	}

	/**
	 * Produces a random letter.
	 * 
	 * @param letterType type of letter needed:
	 * consonant, vowel, or any
	 * @return a random letter
	 */
	private static String generateRandomLetter(int letterType)
	{
		// prepare the available vowel and consonant letters
		String vowelLetters = "AEIOU";
		String consonantLetters = "BCDFGHJKLMNPQRSTVWXYZ";
		String allLetters = vowelLetters + consonantLetters;

		Random random = new Random();
		int randomInt;
		char randomCharacter;

		// get a random integer i from 0 to the length of the
		// available letters, then get the ith character
		// of the available letters
		if (letterType == CONSONANT_LETTER_TYPE) 
		{
			randomInt = random.nextInt(consonantLetters.length());
			randomCharacter = consonantLetters.charAt(randomInt);
		} 
		else if (letterType == VOWEL_LETTER_TYPE) 
		{
			randomInt = random.nextInt(vowelLetters.length());
			randomCharacter = vowelLetters.charAt(randomInt);
		} 
		else 
		{
			randomInt = random.nextInt(allLetters.length());
			randomCharacter = allLetters.charAt(randomInt);
		}
		// return a String representation of the random character
		return Character.toString(randomCharacter).toUpperCase();
	}

	/**
	 * Action Listener when user clicks on a button of a letter.
	 */
	private class LetterButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton jButton = (JButton) e.getSource();
			for (int index = 0; index < jtf.length; index++) 
			{
				if (jtf[index].getText().trim().equals("")) 
				{
					if (!textMap.keySet().contains(jtf[index])) 
					{
						textMap.put(jtf[index].getName(), jButton.getName());
					}
					jtf[index].setText(jButton.getText());
					jButton.setVisible(false);
					break;
				}
			}

			StringBuffer strButBuffer = new StringBuffer();

			for (int index = 0; index < jtf.length; index++) 
			{
				strButBuffer.append(jtf[index].getText());
			}

			// check if the solution word has been found
			if (strButBuffer.length() == (fourPix.getSolutionWord().length()))
			{
				if (strButBuffer.toString().equalsIgnoreCase(
						fourPix.getSolutionWord().toUpperCase())) 
				{
					JOptionPane.showMessageDialog(null, 
							"Congratulations! Run program" + 
									" again and play next game!", "",
									JOptionPane.INFORMATION_MESSAGE);
//										dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Sorry, your answer " +
							"is wrong. Please try again", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	/**
	 * Mouse Listener when user clicks on a text field of a letter.
	 */
	private class LetterTextListener extends MouseAdapter 
	{
		@Override
		public void mousePressed(MouseEvent e) 
		{
			JTextField jTextField = (JTextField) e.getSource();
			if (jTextField.getText().equals(""))
				return;
			String jButtonName = textMap.get(jTextField.getName());
			JButton jButton = buttonMap.get(jButtonName);
			jButton.setText(jTextField.getText());
			jButton.setVisible(true);
			jTextField.setText("");
		}
	}

	/**
	 * Main method to provide option of running applet as an application
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();

		FourPixCreatorGUI applet = new FourPixCreatorGUI();

		applet.init();

		frame.add(applet, BorderLayout.CENTER);

		frame.setSize(500, 675);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}




