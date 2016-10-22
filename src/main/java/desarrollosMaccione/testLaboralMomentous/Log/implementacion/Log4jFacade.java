package desarrollosMaccione.testLaboralMomentous.Log.implementacion;

import desarrollosMaccione.testLaboralMomentous.Log.model.iLog;

public class Log4jFacade implements iLog{

	public void logMessage(String message, Exception ex, int level) {
		// TODO Manejo de log de error pendiente de implementar con Log4J
		ex.printStackTrace();
		
	}

}
