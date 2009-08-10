/*
 * EstadoInvalidoException.java
 * Created on 06/03/2005
 *
 * cvs-id : $Id$
 */
package exceptions;

/**
 * 
 * @author Vinicius Rocha
 */
public class EstadoInvalidoException extends Exception
{
	private static final long serialVersionUID = 1L;

	public EstadoInvalidoException()
	{
		super();
	}
	
	public EstadoInvalidoException(String msg)
	{
		super(msg);
	}
}
