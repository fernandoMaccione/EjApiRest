package desarrollosMaccione.testLaboralMomentous.apiRest;
import static spark.Spark.get;

import com.google.gson.JsonObject;

import desarrollosMaccione.testLaboralMomentous.Log.LogCTE;
import desarrollosMaccione.testLaboralMomentous.Log.LogSRV;
import desarrollosMaccione.testLaboralMomentous.Log.LogTimeMethod;
import desarrollosMaccione.testLaboralMomentous.apiRest.procesos.EntidadResorce;
import desarrollosMaccione.testLaboralMomentous.apiRest.procesos.MenuDisponibleResource;
import desarrollosMaccione.testLaboralMomentous.apiRest.procesos.MenuResource;
import desarrollosMaccione.testLaboralMomentous.apiRest.procesos.interfaces.ProcesarRequest;
import desarrollosMaccione.testLaboralMomentous.excepciones.EntidadException;
import spark.Request;
import spark.Response;

public class MainRest {
    public static void main(String[] args)
    {   //http://localhost:4567/menuDisponible/20161010%20105000
        get("/menuDisponible/:fechaHora", (req, res) -> procesarGet(req, res, new MenuDisponibleResource()));
        get("/menu/:id/:ordenamiento", (req, res) -> procesarGet(req, res, new MenuResource()));
        get("/entidad/:entidadHLP/:id", (req, res) -> procesarGet(req, res, new EntidadResorce()));
    	
    }

	private static Object procesarGet(Request req, Response res, ProcesarRequest proceso){
		LogTimeMethod logMethod = new LogTimeMethod();
		try {
			JsonObject json = new JsonObject();
			String errores = "";
			try{			
				proceso.get(req, res, json);
				
			}catch (EntidadException ex) {
				LogSRV.Log(ex, LogCTE.LEVEL_TRACE);
				errores = ex.getMessage();
			}catch (Exception e) {
				EntidadException ex = new EntidadException(e.getMessage(),e);
				errores = ex.getLocalizedMessage();
				LogSRV.Log(ex, LogCTE.LEVEL_TRACE);
			}
			json.addProperty("error", errores);
			return json.toString();
		} finally {
			logMethod.finish();
		}
	}
}
