package desarrollosMaccione.testLaboralMomentous.excepciones;

public class EntidadException extends Exception {

	private static final long serialVersionUID = 733815665314610509L;
	
	public EntidadException (String msj, Throwable ex){
		super(msj, ex);
	}
	
	public EntidadException (String msj){
		super(msj);
	} 
}
