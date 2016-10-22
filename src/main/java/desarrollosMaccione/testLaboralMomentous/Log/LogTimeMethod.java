package desarrollosMaccione.testLaboralMomentous.Log;

public class LogTimeMethod {
	private long startTime;
	private long stopTime;
	
	public LogTimeMethod(){
		start();
	}
	
	public void start(){
		startTime = System.currentTimeMillis();
	}
	
	public void finish(){
		stopTime = System.currentTimeMillis();
		try {			
			if (stopTime!=0){				
				StackTraceElement[] stack = Thread.currentThread().getStackTrace();
				String nombreClase = stack[2].getClassName();
				String nombreMetodo = stack[2].getMethodName();
				long time = stopTime - startTime;
				LogSRV.Log("El metodo: ".concat(nombreMetodo).concat(", de la clase: ".concat(nombreClase)).concat(" se ejecutó en: ".concat(String.valueOf(time).concat(" milisegundos."))), LogCTE.LEVEL_DEBBUG);					
			}
		} catch (Exception e) {
			LogSRV.Log(e, LogCTE.LEVEL_TRACE);
		}		
	}
}
