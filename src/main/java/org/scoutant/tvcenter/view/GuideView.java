package org.scoutant.tvcenter.view;

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
	
	public GuideView() {
		super();
    	setLayout(null);

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
		int i=0;
		for(Program p : guide.channel(0).programs) {
			log.debug("prog " + p);
			add( new ProgramWidget( p, 0+200*i, 10));
			i++;
		}
		
		
	}

	
//	private class Item extends JButton {
//		public Item(String label) {
//			super(label);
//			this.addKeyListener( new KeyPressed());
//		}
//		public Item(String label, int x,int y) {
//			this(label);
//			setBounds( x, y, 150, 50);
//		}
//	}
}
