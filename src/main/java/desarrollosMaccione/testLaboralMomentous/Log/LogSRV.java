package desarrollosMaccione.testLaboralMomentous.Log;

import desarrollosMaccione.testLaboralMomentous.Log.implementacion.LogStandard;
import desarrollosMaccione.testLaboralMomentous.Log.model.iLog;

public class LogSRV {

	public static void Log (String mensaje,int level){
		iLog log = getFactory();
		log.logMessage(mensaje, null, level);
	}
	
	public static void Log (Exception ex, int level){
		iLog log = getFactory();
		log.logMessage(null, ex, level);
	}
	
	public static void Log (String mensaje, Exception ex, int level){
		iLog log = getFactory();
		log.logMessage(mensaje, ex, level);
	}

	private static iLog getFactory() {		
		return new LogStandard();//por ahora solo se usa la clas de log propia.
	}
}
