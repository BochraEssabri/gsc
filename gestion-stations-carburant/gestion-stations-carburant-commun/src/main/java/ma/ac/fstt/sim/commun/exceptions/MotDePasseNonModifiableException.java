package ma.ac.fstt.sim.commun.exceptions;

import ma.ac.fstt.sim.commun.ressoureces.Messages;

public class MotDePasseNonModifiableException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MotDePasseNonModifiableException() {
		super(Messages.ERR_MDPASSE_MODIFICATION);
	}

	
}
