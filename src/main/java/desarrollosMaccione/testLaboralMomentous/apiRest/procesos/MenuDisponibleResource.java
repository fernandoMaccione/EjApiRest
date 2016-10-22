package desarrollosMaccione.testLaboralMomentous.apiRest.procesos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import desarrollosMaccione.testLaboralMomentous.apiRest.json.constantes.GeneralesCTE;
import desarrollosMaccione.testLaboralMomentous.apiRest.json.constantes.MensajesCTE;
import desarrollosMaccione.testLaboralMomentous.apiRest.procesos.interfaces.ProcesarRequest;
import desarrollosMaccione.testLaboralMomentous.excepciones.EntidadException;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.MenuHLP;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.model.MenuVO;
import spark.Request;
import spark.Response;

public class MenuDisponibleResource implements ProcesarRequest{

	public JsonObject get(Request req, Response res, JsonObject json) throws EntidadException {
		try{			
			String sFechaHora = req.params("fechaHora");
			SimpleDateFormat formatoFecha = new SimpleDateFormat(GeneralesCTE.FORMATO_FECHA_HORA);
			Date fecha;
			fecha = formatoFecha.parse(sFechaHora);
			
			List<MenuVO> lMenu = new MenuHLP().getMenuDisponibles(fecha);
			JsonArray jArray = new JsonArray();
			for (MenuVO menu:lMenu)
				jArray.add(menu.getJson());
			json.add("menuDisponible", jArray);
			return json;
		} catch (ParseException e) {
			throw  new EntidadException(MensajesCTE.ERROR_PARSE_DATE, e);
		}catch (EntidadException ex) {
			throw ex;
		}
		
	}

	@Override
	public JsonObject post(Request req, Response res, JsonObject json) {
		return null;
	}

	@Override
	public JsonObject put(Request req, Response res, JsonObject json) {
		return null;
	}

	@Override
	public JsonObject delete(Request req, Response res, JsonObject json) {
		return null;
	}
}
