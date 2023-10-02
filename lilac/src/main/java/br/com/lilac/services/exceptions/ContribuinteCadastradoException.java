package br.com.lilac.services.exceptions;

public class ContribuinteCadastradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContribuinteCadastradoException() {
		super("Participantes Já Cadastrados");
		
	}

	public ContribuinteCadastradoException(String message) {
		super(message);
		
	}

}
