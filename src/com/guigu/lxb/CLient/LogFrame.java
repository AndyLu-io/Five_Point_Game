package com.guigu.lxb.CLient;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.*;


class LogFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3607805051002788931L;
	private JTextField input=new JTextField(8);
	private JPasswordField ps=new JPasswordField(8);
	private DataOutputStream out;
	private JButton logBtn=new JButton("登陆");
	private JButton registeBtn=new JButton("注册");
	private JButton exitBtn=new JButton("退出");
	private ClientThread clientThread;
	public LogFrame(final ClientThread clientThread)
	{
		super("网络五子棋");
		this.setResizable(false);
		this.clientThread=clientThread;
		out=clientThread.out;
		JPanel userPane=new JPanel();
		userPane.add(new JLabel("用户名"));
		userPane.add(input);
		JPanel psPane=new JPanel();
		psPane.add(new JLabel("密    码"));
		psPane.add(ps);
		JPanel btnP=new JPanel();
		btnP.add(logBtn);
		btnP.add(registeBtn);
		btnP.add(exitBtn);
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(0,1));
		p.add(userPane);
		p.add(psPane);
		p.add(btnP);
		add(p);
		logBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					out.writeUTF("log "+input.getText()+" "+Arrays.toString(ps.getPassword()));     
					input.setText("");
					ps.setText("");				
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		});
		registeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					out.writeUTF("registe "+input.getText()+" "+Arrays.toString(ps.getPassword()));  //可以考虑在此处加入数据库
					input.setText(""); 
					ps.setText("");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		});
		exitBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {			
				clientThread.setExit(true);
			}
			
		});
		this.setSize(300,200);
		this.setLocationRelativeTo(null);
		setVisible(true);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				try {
					out.writeUTF("log ** **");
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
	}
	public void close()
	{
		this.setVisible(false);
	}
}
