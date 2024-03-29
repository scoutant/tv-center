package org.scoutant.tvcenter;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
	private static final int PROGRAM_VIEW_HEIGHT = 300;
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 1000;
	
	
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
	public View programView;
	public GuideView guide;
	
	
	// TODO on exiting, do stop all processes!
	public void quit() {
		
	}
	
	public App() throws IOException, Exception {
		super();
		UIManager.setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel");
		LookAndFeel laf = UIManager.getLookAndFeel();
		log.info("Using Look and feel : " + laf);
		laf.getDefaults().put("Button.font", new Font("Tahoma", Font.BOLD, 15));
		
		Resource res = context().getResource("file:" + System.getProperty("user.home") + "/downloads/tv.xml");
		log.info("Sourcing EPG file :  " + res.getFile().getAbsolutePath());
		
    	new EventWith<InputStream>( "parse", res.getInputStream()).dispatch();

    	setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
    	
		App.model().now = (int) (new Date().getTime()/1000/60);
		// showing what has been on last 45 min:
		App.model().vpTime = App.model().now - 45;
		
		setSize(WIDTH, HEIGHT);
		programView = new ProgramView(WIDTH, PROGRAM_VIEW_HEIGHT );
		this.add(programView);

		guide = new GuideView(getWidth(), getHeight()-PROGRAM_VIEW_HEIGHT);
		this.add( guide);
		
		new Event("init").dispatch();
		
		this.addKeyListener( new KeyPressed());
		
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation( EXIT_ON_CLOSE);
    	// TODO ok? better?
    	addWindowListener( new WindowListener() {
			@Override
			public void windowOpened(WindowEvent arg0) {
			}
			@Override
			public void windowIconified(WindowEvent arg0) {
			}
			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}
			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				new Event("stop").dispatch();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
			}
		});
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
		programView.stateChanged(e);
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
