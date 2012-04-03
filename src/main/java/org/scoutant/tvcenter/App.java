package org.scoutant.tvcenter;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
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
	private static final Logger log = Logger.getLogger(App.class);
	
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
	public GuideView guide;
	
	public void quit() {
	}
	
	public App() throws IOException, Exception {
		super();
		// TODO provided as Java 7, with Java 6 use : 
//		UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		UIManager.setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel");
		LookAndFeel laf = UIManager.getLookAndFeel();
		log.info("Using Look and feel : " + laf);
		laf.getDefaults().put("Button.font", new Font("Tahoma", Font.BOLD, 14));

		Resource res = context().getResource("file:/tmp/tv2.xml");
		
    	new EventWith<InputStream>( "parse", res.getInputStream()).dispatch();
//		setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	
		App.model().now = (int) (new Date().getTime()/1000/60);
		// showing what has been on last 45 min:
		App.model().vpTime = App.model().now - 45; 

		setSize(1000, 800);
		guide = new GuideView(getHeight(), getWidth());
		this.add( guide);
		
//		this.add( panel,  BorderLayout.PAGE_START);
//		add( new GuideView(), BorderLayout.PAGE_END);
		
		new Event("init").dispatch();
		
		this.addKeyListener( new KeyPressed());
		
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation( EXIT_ON_CLOSE);
    	setVisible(true);

    	addComponentListener( new ResizeListener());
	}
	
    public static void main( String[] args ) throws IOException, Exception {
    	_app = new App();
    }	
    
    private class ResizeListener extends ComponentAdapter {
    	@Override
    	public void componentResized(ComponentEvent e) {
    		log.debug("resize");
    	}
    }
}
