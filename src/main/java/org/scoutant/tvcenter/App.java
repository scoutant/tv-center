package org.scoutant.tvcenter;

import static org.junit.Assert.assertTrue;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.scoutant.tvcenter.command.ParseGuide;
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
	
	public void quit() {
	}
	
	public App() throws IOException, Exception {
		super();
		context();

    	Resource res = context().getResource("tv2.xml");
    	new ParseGuide().execute( res.getInputStream());
		
		//		setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JPanel panel = new GuideView();
		this.add( panel);
//		this.add( panel,  BorderLayout.PAGE_START);
//		add( new GuideView(), BorderLayout.PAGE_END);
		
		this.addKeyListener( new KeyPressed());
    	setSize(1000, 800);
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation( EXIT_ON_CLOSE);
    	setVisible(true);
	}
	
    public static void main( String[] args ) throws IOException, Exception {
    	new App();
    }	
}
