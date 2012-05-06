package org.scoutant.tvcenter.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.listener.KeyPressed;
import org.scoutant.tvcenter.model.Guide;

public class GuideView extends View {

	private static final long serialVersionUID = 7966178905763354703L;
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger( GuideView.class);
	private Guide guide;
	private List<ChannelView> views = new ArrayList<>();
	public static final int CHANNEL_HEIGHT = 40;
	
	public GuideView(int width, int height) {
		super();
    	setLayout(null);
    	setPreferredSize( new Dimension(width, height ));
//    	setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.BLUE), this.getBorder()));
    	this.setFocusable(true);
		this.addKeyListener( new KeyPressed());
//		setBackground(Color.decode("0xFFCB60"));
		setBackground(Color.decode("0xF1E2AE"));
		
    	past = new JPanel();
    	
    	past.setBackground(Color.decode("0xF1E2BE"));
    	add(past);
    	
		guide = App.model().guide;
		guide.addListener(this);
		
		// z order painting...
		int z;
		for (z=0; z<guide.channels.size(); z++){
			ChannelView cv = new ChannelView(guide.channel(z));
			cv.setBounds(0, z*CHANNEL_HEIGHT, 2500, CHANNEL_HEIGHT);
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
		past.setBounds( pastRect());
	}
	
	// http://stackoverflow.com/questions/8472083/how-to-draw-a-jpanel-as-a-nimbus-jbutton
    private final int gradientSize = 18;
    private final Color lighterColor = new Color(250, 250, 250);
    private final Color darkerColor = new Color(225, 225, 230);
    private final Color edgeColor = new Color(140, 145, 145);
    private final Stroke edgeStroke = new BasicStroke(1);
    private final GradientPaint upperGradient = new GradientPaint( 0, 0, lighterColor, 0, gradientSize, darkerColor);
	private JPanel past;

    
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
    
    private Rectangle pastRect() {
    	int pastWidth = (App.model().now - App.model().vpTime) * ProgramWidget.MINUTE;
    	return new Rectangle( 0, 0, pastWidth, getHeight());
    }
}