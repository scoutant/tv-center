package org.scoutant.tvcenter;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.scoutant.mvc.Event;
import org.scoutant.mvc.EventWith;
import org.scoutant.tvcenter.listener.KeyPressed;
import org.scoutant.tvcenter.model.Model;
import org.scoutant.tvcenter.view.GuideView;
import org.scoutant.tvcenter.view.ProgramView;
import org.scoutant.tvcenter.view.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class App extends JFrame implements ChangeListener {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(App.class);
	private static final int PRAGRAM_VIEW_HEIGHT = 200;
	
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
	public View headerView;
	
	public void quit() {
	}
	
	public App() throws IOException, Exception {
		super();
		// with Java 6 use : "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
		UIManager.setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel");
		LookAndFeel laf = UIManager.getLookAndFeel();
		log.info("Using Look and feel : " + laf);
		laf.getDefaults().put("Button.font", new Font("Tahoma", Font.BOLD, 15));

//		Resource res = context().getResource("file:/tmp/tv2.xml");
		Resource res = context().getResource("file:/home/coutant/2010/airtv/src/main/flex/assets/tv.xml");
//		Resource res = context().getResource("file:src/test/resources/tv.xml");
		
    	new EventWith<InputStream>( "parse", res.getInputStream()).dispatch();

    	setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
    	
		App.model().now = (int) (new Date().getTime()/1000/60);
		// showing what has been on last 45 min:
		App.model().vpTime = App.model().now - 45;
		
		setSize(1200, 1000);
//		programView = new ProgramView(getWidth(), PRAGRAM_VIEW_HEIGHT );
		// TODO test
		headerView = new ProgramView(getWidth(), PRAGRAM_VIEW_HEIGHT );
		
		this.add(headerView);

		guide = new GuideView(getWidth(), getHeight()-PRAGRAM_VIEW_HEIGHT);
		this.add( guide);
		
		new Event("init").dispatch();
		
		this.addKeyListener( new KeyPressed());
		
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation( EXIT_ON_CLOSE);
    	setVisible(true);

    	addComponentListener( new ResizeListener());

    	Timer timer = new Timer(true);
    	timer.schedule( new RepetedStepForwardTask(), 0, 1*60*1000);
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

	@Override
	public void stateChanged(ChangeEvent e) {
		headerView.stateChanged(e);
		guide.stateChanged(e);
	}
	
	private class RepetedStepForwardTask extends TimerTask {
		@Override
		public void run() {
			try {
				new Event("step").dispatch();
			} catch (Exception e) {
				log.error("timer...");
			}
		}
	}	
}
