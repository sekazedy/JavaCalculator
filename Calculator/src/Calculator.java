import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

@SuppressWarnings("serial")
public class Calculator extends JFrame {
	JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9,
			bSign, bDiv, bMul, bMinus, bPlus,
			bDelDigit, bClearEntry, bClearAll,
			bDecPoint, bEquals;
	JTextField result;
	
	String input = "";
	Double calculatedNumber;
	Double lastEnteredNumber = 0.0;
	Boolean needInputClearance = false;
	String lastOperationName = "";
	
	eHandler handler = new eHandler();
	
	public Calculator(String s) {
		super(s);
		
		setLayout(new FlowLayout());
		
		b0 = new JButton("0");
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		bSign = new JButton("+-");
		bDiv = new JButton("/");
		bMul = new JButton("*");
		bMinus = new JButton("-");
		bPlus = new JButton("+");
		bDelDigit = new JButton("<-");
		bClearEntry = new JButton("CE");
		bClearAll = new JButton("C");
		bDecPoint = new JButton(".");
		bEquals = new JButton("=");
		
		result = new JTextField(18);
		result.setText("0");
		result.setEditable(false);
		result.setHorizontalAlignment(SwingConstants.RIGHT);
		
		b0.setPreferredSize(new Dimension(87, 26));
		bEquals.setPreferredSize(new Dimension(100, 26));
		
		add(result);
		add(bDelDigit);
		add(bClearEntry);
		add(bClearAll);
		add(bSign);
		add(b7);
		add(b8);
		add(b9);
		add(bDiv);
		add(b4);
		add(b5);
		add(b6);
		add(bMul);
		add(b1);
		add(b2);
		add(b3);
		add(bMinus);
		add(b0);
		add(bDecPoint);
		add(bPlus);
		add(bEquals);
		
		b0.addActionListener(handler);
		b1.addActionListener(handler);
		b2.addActionListener(handler);
		b3.addActionListener(handler);
		b4.addActionListener(handler);
		b5.addActionListener(handler);
		b6.addActionListener(handler);
		b7.addActionListener(handler);
		b8.addActionListener(handler);
		b9.addActionListener(handler);
		bDelDigit.addActionListener(handler);
		bClearEntry.addActionListener(handler);
		bClearAll.addActionListener(handler);
		bSign.addActionListener(handler);
		bDiv.addActionListener(handler);
		bMul.addActionListener(handler);
		bMinus.addActionListener(handler);
		bPlus.addActionListener(handler);
		bDecPoint.addActionListener(handler);
		bEquals.addActionListener(handler);
	}
	
	public class eHandler implements ActionListener {
		private Pattern decimalNumberPattern = Pattern.compile("[-]?[0-9]+\\.{1}[0-9]+");
		
		public void actionPerformed(ActionEvent e) {
			Boolean isDecimal = decimalNumberPattern.matcher(input).matches();
			
			if (needInputClearance && e.getSource() != bEquals) {
				input = "";
				result.setText(input);
				needInputClearance = false;
				
				if (lastOperationName == "equals") {
					calculatedNumber = 0.0;
					lastEnteredNumber = 0.0;
				}
			}
			
			if (!isDecimal
				&& e.getSource() != bDecPoint
				&& input.startsWith("0")
				&& !input.contains(".")
			) {
				input = "";
			}
			
			if (e.getSource() == b0 && !input.equals("0")) {
				input += "0";
				result.setText(input);
			}
			if (e.getSource() == b1) {
				input += "1";
				result.setText(input);
			}
			if (e.getSource() == b2) {
				input += "2";
				result.setText(input);
			}
			if (e.getSource() == b3) {
				input += "3";
				result.setText(input);
			}
			if (e.getSource() == b4) {
				input += "4";
				result.setText(input);
			}
			if (e.getSource() == b5) {
				input += "5";
				result.setText(input);
			}
			if (e.getSource() == b6) {
				input += "6";
				result.setText(input);
			}
			if (e.getSource() == b7) {
				input += "7";
				result.setText(input);
			}
			if (e.getSource() == b8) {
				input += "8";
				result.setText(input);
			}
			if (e.getSource() == b9) {
				input += "9";
				result.setText(input);
			}
			
			if (e.getSource() == bSign) {
				if (isDecimal) {
					// it's a double
					double number = Double.parseDouble(input);
					number *= -1;
					input = Double.toString(number);
					lastEnteredNumber = number;
				} else {
					// it's an integer
					int number = Integer.parseInt(input);
					number *= -1;
					input = Integer.toString(number);
					lastEnteredNumber = (double)number;
				}
				
				result.setText(input);
			}
			
			if (e.getSource() == bDecPoint && input.length() > 0) {
				input += ".";
				result.setText(input);
			}
			
			if (e.getSource() == bClearAll) {
				input = "0";
				calculatedNumber = null;
				lastOperationName = "";
				needInputClearance = false;
				result.setText(input);
			}
			
			if (e.getSource() == bClearEntry && input.length() > 0) {
				input = "0";
				result.setText(input);
			}
			
			if (e.getSource() == bDelDigit) {
				input = input.length() > 1 ? input.substring(0, input.length() - 1) : "0";
				result.setText(input);
			}
			
			if (e.getSource() == bPlus && input.length() > 0) {
				double number = Double.parseDouble(input);
				lastEnteredNumber = number;
				
				lastOperationName = "addNumber";
				
				if (calculatedNumber != null) {
					calculatedNumber += number;
					
					if (isDecimal) {
						// it's a double
						input = Double.toString(calculatedNumber);
					} else {
						// it's an integer
						input = Integer.toString(calculatedNumber.intValue());
					}
					
					result.setText(input);
				} else {
					calculatedNumber = number;
				}
				
				needInputClearance = true;
			}
			
			if (e.getSource() == bMinus && input.length() > 0) {
				double number = Double.parseDouble(input);
				lastEnteredNumber = number;
				
				lastOperationName = "subtractNumber";
				
				if (calculatedNumber != null) {
					calculatedNumber -= number;
				
					if (isDecimal) {
						// it's a double
						input = Double.toString(calculatedNumber);
					} else {
						// it's an integer
						input = Integer.toString(calculatedNumber.intValue());
					}
					
					result.setText(input);
				} else {
					calculatedNumber = number;
				}
				
				needInputClearance = true;
			}
			
			if (e.getSource() == bMul && input.length() > 0) {
				double number = Double.parseDouble(input);
				lastEnteredNumber = number;
				
				lastOperationName = "multiplyNumber";
				
				if (calculatedNumber != null) {
					calculatedNumber *= number;
				
					if (isDecimal) {
						// it's a double
						input = Double.toString(calculatedNumber);
					} else {
						// it's an integer
						input = Integer.toString(calculatedNumber.intValue());
					}
					
					result.setText(input);
				} else {
					calculatedNumber = number;
				}
				
				needInputClearance = true;
			}
			
			if (e.getSource() == bDiv && input.length() > 0) {
				double number = Double.parseDouble(input);
				lastEnteredNumber = number;
				
				lastOperationName = "divideNumber";
				
				if (calculatedNumber != null && number != 0) {
					calculatedNumber /= number;
				
					if (isDecimal) {
						// it's a double
						input = Double.toString(calculatedNumber);
					} else {
						// it's an integer
						input = Integer.toString(calculatedNumber.intValue());
					}
					
					result.setText(input);
				} else {
					calculatedNumber = number;
				}
				
				needInputClearance = true;
			}
			
			if (e.getSource() == bEquals && input.length() > 0) {
				Double number = Double.parseDouble(input);

				if (lastOperationName.length() != 0) {
					switch (lastOperationName) {
						case "addNumber":
							calculatedNumber += number;
							break;
						case "subtractNumber":
							calculatedNumber -= number;
							break;
						case "multiplyNumber":
							calculatedNumber *= number;
							break;
						case "divideNumber":
							calculatedNumber = number == 0 ? 0.0 : calculatedNumber / number;
							break;
					}
					
					lastOperationName = "equals";
				}
				
				if (isDecimal) {
					// it's a double
					input = Double.toString(calculatedNumber);
				} else {
					// it's an integer
					input = Integer.toString(calculatedNumber.intValue());
				}
				
				result.setText(input);
				
				needInputClearance = true;
			}
		}
	}
}
