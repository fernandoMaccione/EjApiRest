package desarrollosMaccione.testLaboralMomentous.excepciones;

public class RecursionException extends Exception {

	private static final long serialVersionUID = 733815665314610509L;
	
	public RecursionException (String msj, Throwable ex){
		super(msj, ex);
	}
	
	public RecursionException (String msj){
		super(msj);
	} 
}
