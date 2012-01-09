package org.scoutant.tvcenter.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.listener.KeyPressed;
import org.scoutant.tvcenter.model.Guide;

public class GuideView extends JPanel implements ChangeListener{

	private static final long serialVersionUID = 7966178905763354703L;
	/** pixels representing 1 minute */ 
	private static final Logger log = Logger.getLogger( GuideView.class);
	private Guide guide;
	private List<ChannelView> views = new ArrayList<ChannelView>();
	
	@Override
	public void revalidate() {
		super.revalidate();
		log.debug("revalidating...");
	}
	@Override
	public void repaint() {
		super.repaint();
		log.debug("repaintng...");
	}
	@Override
	public void update(Graphics g) {
		super.update(g);
		log.debug("updating...");
	}
	
	public GuideView() {
		super();
    	setLayout(null);
    	setSize(1000 , 800	);

    	// TODO add the listener to all obj that may get focus!
    	this.setFocusable(true);
		this.addKeyListener( new KeyPressed());

//		setBackground(Color.decode("0xFFCB60"));
		setBackground(Color.decode("0xF1E2AE"));
		
    	JPanel past = new JPanel();
    	past.setBounds(0, 0, 400, getHeight());
    	past.setBackground(Color.decode("0xF1E2BE"));
    	add(past);
    	
		guide = App.model().guide;
		guide.addListener(this);
		
		// z order painting...
		int z;
		for (z=0; z<guide.channels.size(); z++){
			ChannelView cv = new ChannelView(guide.channel(z));
			cv.setBounds(0, z*60, getWidth(), 50);
			add( cv);
			views.add(cv);
			setComponentZOrder(cv, 1);
		}

		setComponentZOrder(past, z);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		for (ChannelView c : views) {
			c.refresh();
		}
	}
}


//JButton b = new JButton("start");
//b.addActionListener( new ActionDispath( "start"));
//b.setBounds(40, 50, 100, 30);
//this.add(b);
//b.addKeyListener( new KeyPressed());
//
//
//JButton b2 = new JButton("stop");
//b.addActionListener( new ActionDispath( "stop"));
//b2.setBounds(200, 50, 260, 30);
//this.add(b2);
//b2.addKeyListener( new KeyPressed());
