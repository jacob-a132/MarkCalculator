import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

@SuppressWarnings("serial")
public class MarkPanel extends JPanel implements ActionListener {
	ArrayList<Component> marks;
	ArrayList<Component> comps;
	JButton addLine = new JButton("Add Line");
	JButton calcMark = new JButton("Calculate");
	JButton reset = new JButton("Reset");
	JTextField examMark = new JTextField("0");
	JTextField examWeight = new JTextField("0");

	public MarkPanel(){
		this.setLayout(new GridLayout(0,4));
		addLine.addActionListener(this);
		calcMark.addActionListener(this);
		reset.addActionListener(this);
		marks = new ArrayList<Component>();
		comps = new ArrayList<Component>();
		comps.add(addLine);
		comps.add(calcMark);
		comps.add(new JLabel());
		comps.add(reset);
		comps.add(new JLabel("Current Mark:"));
		comps.add(new JLabel("  0 %"));
		comps.add(new JLabel());
		comps.add(new JLabel());
		comps.add(new JLabel("Mark With Exam:"));
		comps.add(new JLabel("  0 %"));
		add(new JLabel("Exam Mark:"));
		add(examMark);
		add(new JLabel("Exam Weight:"));
		add(examWeight);
		for(int i = 0; i<4; i++){
			add(new JLabel());
		}
		for(Component comp: comps){
			add(comp);
		}
		addLine(marks);
	}
	
	public void addLine(ArrayList<Component> marks){
		marks.add(new JLabel("Mark:"));
		marks.add(new JTextField("100"));
		marks.add(new JLabel("Weight:"));
		marks.add(new JTextField("0"));
		for(Component comp: comps){
			remove(comp);
		}
		for(int i = marks.size()-4; i < marks.size(); i++)		{
			add(marks.get(i));
		}
		for(Component comp: comps){
			add(comp);
		}
		updateUI();
	}
	
	public void calcMark(){
		Pattern pFraction=Pattern.compile("^(\\d+\\.?\\d*)(/(\\d+\\.?\\d*))?$");
		float mark = 0;
		float weight = 0;
		float sum = 0;
		float totalWeight = 0;
		try{
			for(int i=1; i<marks.size(); i+=4){
				Matcher m=pFraction.matcher(((JTextField) marks.get(i)).getText());
				if(m.matches()){
					if(m.group(2)!=null){
						mark = Float.parseFloat(m.group(1)) / Float.parseFloat(m.group(3)) * 100;
					}
					else{
						mark = Float.parseFloat(m.group(1));
					}
				}
				m=pFraction.matcher(((JTextField) marks.get(i+2)).getText());
				if(m.matches()){
					if(m.group(2)!=null){
						weight = Float.parseFloat(m.group(1)) / Float.parseFloat(m.group(3));
					}
					else{
						weight = Float.parseFloat(m.group(1));
					}
				}
				sum += mark*weight/100;
				totalWeight += weight;
			}
			float curMark = sum*100/totalWeight;
			((JLabel) comps.get(5)).setText("  " + Float.toString(curMark) + " %");
			
			mark = Float.parseFloat(examMark.getText());
			weight = Float.parseFloat(examWeight.getText());
			sum += mark*weight/100;
			totalWeight += weight;
			float curExamMark = sum*100/totalWeight;
			((JLabel) comps.get(9)).setText("  " + Float.toString(curExamMark) + " %");
		}
		catch(NumberFormatException nfe){
			((JLabel) comps.get(5)).setText("  only numbers");
			((JLabel) comps.get(9)).setText("  only numbers");
		}
	}
	
	public void resetMarks(){
		for(int i=1; i< marks.size(); i+=4){
			((JTextField) marks.get(i)).setText("100");
			((JTextField) marks.get(i+2)).setText("0");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Add Line"){
			addLine(marks);
		}
		if(e.getActionCommand() == "Calculate"){
			calcMark();
		}
		if(e.getActionCommand() == "Reset"){
			resetMarks();
		}
	}
}
