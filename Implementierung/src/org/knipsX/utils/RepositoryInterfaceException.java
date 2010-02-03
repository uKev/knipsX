package org.knipsX.utils;

class RepositoryInterfaceException extends Exception {

	private Throwable cause;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepositoryInterfaceException(Throwable cause) {
		this.cause = cause;
		
	}
	public Throwable getCause(){
		return cause;
		
	}
}
