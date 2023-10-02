package br.com.lilac.services.exceptions;

public class NotFoundLilacException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundLilacException() {
		super("Não encontrado");
		
	}

	public NotFoundLilacException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
