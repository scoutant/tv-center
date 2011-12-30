package org.scoutant.mvc;

import java.awt.event.ActionListener;


/**
 * @author coutant
 * So as to be used directly like so :
 * button.addActionLister( new ActionDispatch( "stop") );
 */
public class ActionDispath extends Event implements ActionListener  {

	public ActionDispath(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		this.dispatch();
	}
	
}
