import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.*;

public class textUIFrame extends JFrame {
	
	private JRadioButton smallButton;
	private JRadioButton mediumButton;
	private JRadioButton largeButton;
	private JCheckBox wordCount;
	private JCheckBox characterCount;
	private JCheckBox vowelCount;
	private JTextArea textArea;
	private JTextField textField;
	private JScrollPane scroll1;
	private JScrollPane scroll2;
	private ActionListener listener;
	
	public textUIFrame() {
		
		JPanel finalPanel = new JPanel();
		finalPanel.setLayout(new BorderLayout());
		
		listener = new ChoiceListener();
		
		JPanel textProcessor = textProcessorPanel();
		JPanel analysisPanel = analysisResultsPanel();
		finalPanel.add(textProcessor, BorderLayout.WEST);
		finalPanel.add(analysisPanel, BorderLayout.EAST);
		
		add(finalPanel);
		
		final int FRAME_WIDTH = 700;
		final int FRAME_HEIGHT = 500;
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public JPanel createSizeButtons() {
		
		smallButton = new JRadioButton("Small");
		smallButton.addActionListener(listener);
		
		mediumButton = new JRadioButton("Medium");
		mediumButton.addActionListener(listener);
		
		largeButton = new JRadioButton("Large");
		largeButton.addActionListener(listener);
		largeButton.setSelected(true);
		
		//Add Radio Buttons to Button Group
		ButtonGroup group = new ButtonGroup();
		group.add(smallButton);
		group.add(mediumButton);
		group.add(largeButton);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		panel.add(smallButton);
		panel.add(mediumButton);
		panel.add(largeButton);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Size"));
		
		return panel;
	}
	
	public JPanel createAnalysisTypeBoxes() {
		
		wordCount = new JCheckBox("Word Count");
		wordCount.addActionListener(listener);
		
		characterCount = new JCheckBox("Character Count");
		characterCount.addActionListener(listener);
		
		vowelCount = new JCheckBox("Vowel Count");
		vowelCount.addActionListener(listener);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		panel.add(wordCount);
		panel.add(characterCount);
		panel.add(vowelCount);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Analysis Type"));
		
		return panel;
	}
	
	public JPanel textProcessorPanel() {
		
		textArea = new JTextArea(20,10);
		textArea.setEditable(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		scroll1 = new JScrollPane(textArea);
		scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel.add(scroll1, BorderLayout.CENTER);
		
		JPanel size = createSizeButtons();
		panel.add(size, BorderLayout.SOUTH);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Text Processor"));
		
		return panel;
	}
	
	public JPanel analysisResultsPanel() {
		
		textField = new JTextField(10);
		textField.setEditable(false);
		setSize();
		Analysis();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		scroll2 = new JScrollPane(textField);
		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll2, BorderLayout.CENTER);
		
		JPanel analysisType = createAnalysisTypeBoxes();
		panel.add(analysisType, BorderLayout.SOUTH);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Analysis Results"));
		
		return panel;
	}
	
	public void Analysis() {
		
		int numberOfWords = 0;
		int	numberOfChars = 0;
		int numberOfVowels = 0;
		
		String str1 = textArea.getText();
		String[] wordArray = str1.trim().split("\\s+");
		numberOfWords = wordArray.length;
		
		char[] charArray = str1.toCharArray();
		numberOfChars = charArray.length;
		
		for(int i = 0; i < str1.length(); i++) {
			if(str1.charAt(i) == 'a' || str1.charAt(i) == 'e' || str1.charAt(i) == 'i'
                    || str1.charAt(i) == 'o' || str1.charAt(i) == 'u') {
				numberOfVowels++;
			}
		}
		
		textField.setText("Word Count: " + numberOfWords + "\nCharacter Count: " + numberOfChars 
				+ "\nVowel Count: " + numberOfVowels);
	}
	
	public void setSize() {
		 int size = 0;
	      
	     final int SMALL_SIZE = 10;
	     final int MEDIUM_SIZE = 14;
	     final int LARGE_SIZE = 18;
	     
	     if (smallButton.isSelected()) { size = SMALL_SIZE; }
	     else if (mediumButton.isSelected()) { size = MEDIUM_SIZE; }
	     else if (largeButton.isSelected()) { size = LARGE_SIZE; }
	     
	     textArea.setFont(new Font("monospace", 0, size));
	     textArea.repaint();
		
	}
	
	class ChoiceListener implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			
			Analysis();
			setSize();
		}
	}
	
	

}
