package org.scoutant.tvcenter.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

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
//    	past.setBounds(0, 0, 400, getHeight());
    	int width = (App.model().now - App.model().vpTime) * ProgramWidget.MINUTE;
    	past.setBounds(0, 0, width, getHeight());
    	
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
	
	// http://stackoverflow.com/questions/8472083/how-to-draw-a-jpanel-as-a-nimbus-jbutton
    private final int gradientSize = 18;
    private final Color lighterColor = new Color(250, 250, 250);
    private final Color darkerColor = new Color(225, 225, 230);
    private final Color edgeColor = new Color(140, 145, 145);
    private final Stroke edgeStroke = new BasicStroke(1);
    private final GradientPaint upperGradient = new GradientPaint( 0, 0, lighterColor, 0, gradientSize, darkerColor);

    
    // TODO make gradient not look like button but adapted for full screen panel
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint lowerGradient = new GradientPaint(
                0, getHeight()-gradientSize-1, darkerColor,
                0, getHeight(), lighterColor);
        g2.setPaint(upperGradient);
        g2.fillRect(0, 0, getWidth()-1 , gradientSize);
        g2.setPaint(darkerColor);
        g2.fillRect(0, gradientSize, getWidth()-1, getHeight()-gradientSize-1);
        g2.setPaint(lowerGradient);
        g2.fillRect(0, getHeight()-gradientSize, getWidth()-1, getHeight()-1);
        g2.setStroke(edgeStroke);
        g2.setPaint(edgeColor);
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1,gradientSize/2, gradientSize/2);
    }
    
    
//    @Override
//    public void paintComponent( Graphics g ) {
//        int width = getWidth();
//        int height = getHeight();
//
//        GradientPaint paint = null;
//
//        Color sc = Color.CYAN;
//        Color ec = Color.GRAY;
//
//        paint = new GradientPaint( width / 2, 0, sc, width / 2, height, ec, false );
//
//        // we need to cast to Graphics2D for this operation
//        Graphics2D g2d = ( Graphics2D )g;
//        // save the old paint
//        Paint oldPaint = g2d.getPaint();
//        // set the paint to use for this operation
//        g2d.setPaint( paint );
//        // fill the background using the paint
//        g2d.fillRect( 0, 0, width, height );
//        // restore the original paint
//        g2d.setPaint( oldPaint );
//        super.paintComponent( g );
//      }    
    
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
