package org.scoutant.tvcenter.view;

import java.awt.Graphics;
import java.nio.channels.Channels;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.listener.KeyPressed;
import org.scoutant.tvcenter.model.Guide;
import org.scoutant.tvcenter.model.Program;

public class GuideView extends JPanel {

	private static final long serialVersionUID = 7966178905763354703L;
	/** pixels representing 1 minute */ 
	public static final int MINUTE = 8;
	private static final Logger log = Logger.getLogger( GuideView.class);
	private Guide guide;
	
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

//    	JButton b = new JButton("start");
//    	b.addActionListener( new ActionDispath( "start"));
//    	b.setBounds(40, 50, 100, 30);
//    	this.add(b);
//    	b.addKeyListener( new KeyPressed());
//    	
//    	
//    	JButton b2 = new JButton("stop");
//    	b.addActionListener( new ActionDispath( "stop"));
//    	b2.setBounds(200, 50, 260, 30);
//    	this.add(b2);
//    	b2.addKeyListener( new KeyPressed());

    	// TODO add the listener to all obj that may get focus!
    	this.setFocusable(true);
		this.addKeyListener( new KeyPressed());

//		for(int i=0; i<10;i++){
//			add( new Item("coucou", 250, 80+i*60));
//		}

		guide = App.model().guide;
		
		
		for (int i=0; i<guide.channels.size(); i++){
			ChannelView cv = new ChannelView(guide.channel(i));
			cv.setBounds(0, i*60, getWidth(), 50);
			add( cv);
		}
		
	}
}
