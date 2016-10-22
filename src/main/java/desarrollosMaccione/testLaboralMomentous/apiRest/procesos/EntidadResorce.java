package desarrollosMaccione.testLaboralMomentous.apiRest.procesos;

import com.google.gson.JsonObject;

import desarrollosMaccione.testLaboralMomentous.Log.LogTimeMethod;
import desarrollosMaccione.testLaboralMomentous.apiRest.json.constantes.MensajesCTE;
import desarrollosMaccione.testLaboralMomentous.apiRest.json.interfaces.SerializeJson;
import desarrollosMaccione.testLaboralMomentous.apiRest.procesos.interfaces.ProcesarRequest;
import desarrollosMaccione.testLaboralMomentous.excepciones.EntidadException;
import desarrollosMaccione.testLaboralMomentous.modelaje.entidad.EntidadHLP;
import desarrollosMaccione.testLaboralMomentous.modelaje.entidad.model.EntidadVO;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.ItemMenuHLP;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.MenuHLP;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.SubMenuHLP;
import desarrollosMaccione.testLaboralMomentous.modelaje.moneda.MonedaHLP;
import spark.Request;
import spark.Response; 

public class EntidadResorce implements ProcesarRequest{


	public JsonObject get(Request req, Response res, JsonObject json) throws EntidadException {
		try{
			String nombreEntidad = req.params("entidadHLP");
			String id = req.params("id");
			 
			SerializeJson entidad = getFactory(nombreEntidad, id);
			json.add(nombreEntidad, entidad.getJson());
			
			return json;
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
	
	private static SerializeJson getFactory(String nombreEntidad, String sId) throws EntidadException {
		/// ACLARO!!! esto habría que volarlo de acá, la idea sería tener una inyección de dependencias como la gente,
		//pero ahora no llego con el tiempo.
		LogTimeMethod logMethod = new LogTimeMethod();
		try {
			EntidadHLP<?> hlp = null;
			switch (nombreEntidad) {
			case "MenuVO":
				hlp = new  MenuHLP();
				break;
			case "SubMenuVO":
				hlp = new SubMenuHLP() ;
				break;
			case "MonedaVO":
				hlp = new  MonedaHLP();
				break;
			case "MenuItemVO":
				hlp = new  ItemMenuHLP();
				break;
			default:
				throw new  EntidadException(MensajesCTE.ERROR_NOMBRE_RECURSO + nombreEntidad);
			}
	
			Long id = Long.valueOf(sId);		
			EntidadVO<?> entidad = hlp.getByPk(id);
			
			return entidad;
		} finally {
			logMethod.finish();
		}
	}
}
