package org.scoutant.tvcenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.scoutant.mvc.ActionDispath;
import org.scoutant.mvc.Command;
import org.scoutant.mvc.EventWith;
import org.scoutant.tvcenter.listener.KeyPressed;
import org.scoutant.tvcenter.model.Model;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App extends JFrame {
	private static final long serialVersionUID = 1L;

	private static ApplicationContext _context = null;
	public static ApplicationContext context() {
		if (_context==null) {
			_context = new ClassPathXmlApplicationContext("context.xml");
		}
		return _context;
	}
	private static Model _model = null;
	public static Model model() {
		if (_model==null) {
			_model = new Model();
		}
		return _model;
	}
	
	public void quit() {
	}
	
	public App() {
		context();
    	setLayout(null);
    	
    	JButton b = new JButton("start");
    	b.addActionListener( new ActionDispath( "start"));
    	b.setBounds(40, 50, 100, 30);
    	this.add(b);
    	
    	
    	JButton b2 = new JButton("stop");
//    	b2.addActionListener( new ActionDispath( "stop"));
//   	b2.addActionListener( new ActionDispath( "keypressed"));
    	b2.addActionListener (new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EventWith<Integer>("keypressed", 10).dispatch();
			}
		});
    	
    	b2.setBounds(200, 50, 260, 30);
    	this.add(b2);
    	
    	setSize(400, 250);
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation( EXIT_ON_CLOSE);
    	setVisible(true);

    	// TODO add the listener to all obj that may get focus!
    	this.setFocusable(true);
		this.addKeyListener( new KeyPressed());
		b.addKeyListener( new KeyPressed());
		b2.addKeyListener( new KeyPressed());

	}
	
    public static void main( String[] args ) {
    	new App();
    }	
}
