package com.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import com.domain.Operation;
import com.domain.OperationFactory;

public class CalculatorMain {
	public CalculatorMain() {
		// 创建窗口
		final JFrame jf = new JFrame("简易计算器");

		jf.setSize(400, 400);// 设置宽度和高度
		// 设置窗口相对于指定组件的位置：参数为null则此窗口将置于屏幕的中央
		jf.setLocationRelativeTo(null);
		// 关闭窗口时，同时结束窗口所在的应用程序（即彻底退出程序）
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// 不可以改变大小
		jf.setResizable(false);

		// 创建面板panel0,存放显示数字框，即文本框
		JPanel panel0 = new JPanel();
		// 创建面板panel1,存放退格、清零和倒数按钮
		JPanel panel1 = new JPanel();
		// 创建按钮面板panel2，并设置为网格布局，4行4列，组件水平、垂直间距均为3
		JPanel panel2 = new JPanel(new GridLayout(4, 6, 1, 1));

		// 创建文本域，放置输入数据和结果
		JTextArea text = new JTextArea(2, 13);
		// 设置字体
		text.setFont(new Font(null, Font.PLAIN, 15));
		// 设置大小
		text.setPreferredSize(new Dimension(281, 28));
		// 不可编辑
		text.setEditable(false);
		// 自动换行
		text.setLineWrap(true);
		// 将文本域放在面板0中
		panel0.add(text);

		// 创建三个按钮：退格、清零和倒数
		JButton btnBak = new JButton("退格");
		btnBak.setFont(new Font(null, Font.PLAIN, 17));
		btnBak.setPreferredSize(new Dimension(90, 40));
		JButton btnC = new JButton("清零");
		btnC.setFont(new Font(null, Font.PLAIN, 17));
		btnC.setPreferredSize(new Dimension(90, 40));
		JButton btnRec = new JButton("1 / x");
		btnRec.setFont(new Font(null, Font.PLAIN, 17));
		btnRec.setPreferredSize(new Dimension(90, 40));
		// 将三个按钮放在面板panel1中（面板panel1使用FlowLayout布局）
		// panel1.setLayout(new FlowLayout());//不需要，JPanel默认即FlowLayout
		panel1.add(btnBak);
		panel1.add(btnC);
		panel1.add(btnRec);

		MyActionListener myActionListener = new MyActionListener();
		btnBak.addActionListener(myActionListener);
		btnC.addActionListener(myActionListener);
		btnRec.addActionListener(myActionListener);

		// 按钮标题数组：结合界面UI，思考为何这么放置16个字符串（软件开发流程）
		String btn_str[] = { "1", "2", "3", "/", "4", "5", "6", "*", "7", "8", "9", "-", "0", ".", "=", "+" ,"%","sin","cos","tan","sqrt","cbrt","pow"};
		// 按钮对象数组
		JButton btn[] = new JButton[btn_str.length];
		// 将数字按钮通过循环，依次放在面板2中
		for (int i = 0; i < btn_str.length; i++) {
			btn[i] = new JButton(btn_str[i]);// 实例化一个按钮对象，标题为对应下标的标题数组元素值
			btn[i].setFont(new Font(null, Font.PLAIN, 20));
			btn[i].setPreferredSize(new Dimension(70, 42));
			panel2.add(btn[i]);
			btn[i].addActionListener(myActionListener);
		}

		/*
		 * 把 面板容器 设置到 窗口
		 * 注意：JFrame的默认布局管理器是:BorderLayout，因此最终放在JFrame上的面板为3个：panel0、panel1、panel2
		 */
		// panel0 设置到顶部
		jf.getContentPane().add(panel0, BorderLayout.NORTH);
		// panel1 设置到中部
		jf.getContentPane().add(panel1, BorderLayout.CENTER);
		// panel2设置到底部
		jf.getContentPane().add(panel2, BorderLayout.SOUTH);
		// 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
		jf.setVisible(true);
	}

	class MyActionListener implements ActionListener {

		private int operationNum = 0;
		private String operation = "";
		private double numA;
		private double numB;

		// 文本框内容显示
		public void appendInfo(String info) {
			AbstractButton text = null;
			text.setText(text.getText() + info);
		}

		// 清零操作		
		public void reset() {
			operation = "";
			operationNum = 0;
			AbstractButton text = null;
			text.setText("");
		}

		// 从文本框文本info中，解析操作数的值和操作符（即字符串中解析对应的操作数和操作符）
		public void getNumAandB(String info, String operation) {
			int index = info.indexOf(operation);
			String strNumA = info.substring(0, index);
			numA = Double.parseDouble(strNumA);
			String strNumB = info.substring(index + 1);
			numB = Double.parseDouble(strNumB);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("0")) {
				appendInfo("0");
			}
			if (e.getActionCommand().equals("1")) {
				appendInfo("1");
			}
			if (e.getActionCommand().equals("2")) {
				appendInfo("2");
			}
			if (e.getActionCommand().equals("3")) {
				appendInfo("3");
			}
			if (e.getActionCommand().equals("4")) {
				appendInfo("4");
			}
			if (e.getActionCommand().equals("5")) {
				appendInfo("5");
			}
			if (e.getActionCommand().equals("6")) {
				appendInfo("6");
			}
			if (e.getActionCommand().equals("7")) {
				appendInfo("7");
			}
			if (e.getActionCommand().equals("8")) {
				appendInfo("8");
			}
			if (e.getActionCommand().equals("9")) {
				appendInfo("9");
			}
			if (e.getActionCommand().equals(".")) {
				appendInfo(".");
			}
			if (e.getActionCommand().equals("+")) {
				appendInfo("+");
				operation = "+";
				operationNum++;
			}
			if (e.getActionCommand().equals("-")) {
				appendInfo("-");
				operation = "-";
				operationNum++;
			}
			if (e.getActionCommand().equals("*")) {
				appendInfo("*");
				operation = "*";
				operationNum++;
			}
			if (e.getActionCommand().equals("/")) {
				appendInfo("/");
				operation = "/";
				operationNum++;
			}
			if (e.getActionCommand().equals("%")) {
				appendInfo("%");
				operation = "%";
				operationNum++;
			}
			if (e.getActionCommand().equals("sin")) {
				appendInfo("sin");
				operation = "sin";
				operationNum++;
			}
			if (e.getActionCommand().equals("cos")) {
				appendInfo("cos");
				operation = "cos";
				operationNum++;
			}
			if (e.getActionCommand().equals("tan")) {
				appendInfo("tan");
				operation = "tan";
				operationNum++;
			}
			if (e.getActionCommand().equals("sqrt")) {
				appendInfo("sqrt");
				operation = "*sqrt";
				operationNum++;
			}
			if (e.getActionCommand().equals("cbrt")) {
				appendInfo("cbrt");
				operation = "cbrt";
				operationNum++;
			}
			if (e.getActionCommand().equals("pow")) {
				appendInfo("pow");
				operation = "pow";
				operationNum++;
			}
			AbstractButton text = null;
			if (e.getActionCommand().equals("退格")) {
				String str = text.getText();
				if (str.length() > 0)
					text.setText(str.substring(0, str.length() - 1));
			}
			if (e.getActionCommand().equals("清零")) {
				reset();
			}
			if (e.getActionCommand().equals("1 / x")) {
				if (operationNum > 0) {
					JOptionPane.showMessageDialog(null, "此时无法求倒数", "操作错误", JOptionPane.ERROR_MESSAGE);
				} else {
					String str = text.getText();
					double temp = Double.parseDouble(str);
					text.setText("" + 1 / temp);
				}
			}
			if (e.getActionCommand().equals("=")) {
				if (operationNum < 1) {
					JOptionPane.showMessageDialog(null, "运算符过少", "操作错误", JOptionPane.ERROR_MESSAGE);
					reset();
				}
				if (operationNum > 1) {
					JOptionPane.showMessageDialog(null, "运算符过多", "操作错误", JOptionPane.ERROR_MESSAGE);
					reset();
				}
				if (operationNum == 1) {
					String showInfo = text.getText();
					getNumAandB(showInfo, operation);
					if (operation.equals("+")) {
						// OperationAdd add = new OperationAdd();
						Operation add;
						add = OperationFactory.createOperation("+");
						add.setNumA(numA);
						add.setNumB(numB);
						try {
							double result = add.getResult();
							appendInfo("=" + result);
						} catch (Exception ee) {
						}
					}
					if (operation.equals("-")) {
						// OperationSub sub = new OperationSub();
						Operation sub;
						sub = OperationFactory.createOperation("-");
						sub.setNumA(numA);
						sub.setNumB(numB);
						try {
							double result = sub.getResult();
							appendInfo("=" + result);
						} catch (Exception ee) {
						}
					}
					if (operation.equals("*")) {
						// OperationMul mul = new OperationMul();
						Operation mul;
						mul = OperationFactory.createOperation("*");
						mul.setNumA(numA);
						mul.setNumB(numB);
						try {
							double result = mul.getResult();
							appendInfo("=" + result);
						} catch (Exception ee) {
						}
					}
					if (operation.equals("/")) {
						// OperationDiv div = new OperationDiv();//抽象类模式
						// 简单工厂模式
						Operation div;
						div = OperationFactory.createOperation("/");
						div.setNumA(numA);
						div.setNumB(numB);
						try {
							double result = div.getResult();
							appendInfo("=" + result);
						} catch (Exception ee) {
							ee.printStackTrace();
							reset();
						}
					}
					if (operation.equals("%")) {
					    Operation mod = OperationFactory.createOperation("%");
					    mod.setNumA(numA);
					    mod.setNumB(numB);
					    try {
					        double result = mod.getResult();
					        appendInfo("=" + result);
					    } catch (Exception ee) {
					        // Handle exception
					    }
					}
					if (operation.equals("sin")) {
					    Operation sin = OperationFactory.createOperation("sin");
					    sin.setNumA(numA);
					    try {
					        double result = sin.getResult();
					        appendInfo("=" + result);
					    } catch (Exception ee) {
					        // Handle exception
					    }
					}
					if (operation.equals("cos")) {
					    Operation cos = OperationFactory.createOperation("cos");
					    cos.setNumA(numA);
					    try {
					        double result = cos.getResult();
					        appendInfo("=" + result);
					    } catch (Exception ee) {
					        // Handle exception
					    }
					}
					if (operation.equals("tan")) {
					    Operation tan = OperationFactory.createOperation("tan");
					    tan.setNumA(numA);
					    try {
					        double result = tan.getResult();
					        appendInfo("=" + result);
					    } catch (Exception ee) {
					        // Handle exception
					    }
					}
					if (operation.equals("sqrt")) {
					    Operation sqrt = OperationFactory.createOperation("sqrt");
					    sqrt.setNumA(numA);
					    try {
					        double result = sqrt.getResult();
					        appendInfo("=" + result);
					    } catch (Exception ee) {
					        // Handle exception
					    }
					}
					if (operation.equals("cbrt")) {
					    Operation cbrt = OperationFactory.createOperation("cbrt");
					    cbrt.setNumA(numA);
					    try {
					        double result = cbrt.getResult();
					        appendInfo("=" + result);
					    } catch (Exception ee) {
					        // Handle exception
					    }
					}
					if (operation.equals("pow")) {
					    Operation pow = OperationFactory.createOperation("pow");
					    pow.setNumA(numA);
					    pow.setNumB(numB); // Assuming numB is the exponent
					    try {
					        double result = pow.getResult();
					        appendInfo("=" + result);
					    } catch (Exception ee) {
					        // Handle exception
					    }
					}

				}

			}
		}

	}

	public static void main(String[] args) {
		new CalculatorMain();
	}

}

