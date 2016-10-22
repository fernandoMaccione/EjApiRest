package desarrollosMaccione.testLaboralMomentous.Log.implementacion;

import desarrollosMaccione.testLaboralMomentous.Log.LogCTE;
import desarrollosMaccione.testLaboralMomentous.Log.model.iLog;

public class LogStandard implements iLog{
		
	public void logMessage(String message, Exception ex, int level) {
		try {
			int logLevel = getLogLevel ();
			switch (logLevel) {
			case LogCTE.LEVEL_TRACE:				
				if (level == LogCTE.LEVEL_TRACE && ex != null)
					ex.printStackTrace();
			case LogCTE.LEVEL_DEBBUG:
				if (level >= LogCTE.LEVEL_DEBBUG && ex != null)
					System.out.println(ex.getMessage());				
			case LogCTE.LEVEL_WARM:
				if (level >= LogCTE.LEVEL_WARM && message != null)
					System.out.println(message);
			case LogCTE.LEVEL_NO_LOG:
				break;
			default:				
				
			}			
		} catch (Exception e) {			
			e.printStackTrace(); 
		}
	}

	private int getLogLevel() {		
		return LogCTE.LEVEL_TRACE; //la idea es que esto se levante por configuracion.
	}
}
