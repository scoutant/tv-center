package org.scoutant.tvcenter;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;

import org.scoutant.mvc.Event;
import org.scoutant.mvc.EventWith;
import org.scoutant.tvcenter.listener.KeyPressed;
import org.scoutant.tvcenter.model.Model;
import org.scoutant.tvcenter.view.GuideView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

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
	private static App _app = null;
	public static App app(){
		return _app;
	}
	private GuideView panel;
	
	
	public void quit() {
	}
	
	public App() throws IOException, Exception {
		super();
		context();

    	Resource res = context().getResource("tv2.xml");
    	new EventWith<InputStream>( "parse", res.getInputStream()).dispatch();
		new Event("init").dispatch();
		//		setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS));
		panel = new GuideView();
		this.add( panel);
//		this.add( panel,  BorderLayout.PAGE_START);
//		add( new GuideView(), BorderLayout.PAGE_END);
		
		this.addKeyListener( new KeyPressed());
    	setSize(1000, 800);
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation( EXIT_ON_CLOSE);
    	setVisible(true);
    	
	}
	
	public void refreh(){
		panel.repaint();
		panel.updateUI();
		panel.validate();
	}
	
    public static void main( String[] args ) throws IOException, Exception {
    	_app = new App();
    }	
}
