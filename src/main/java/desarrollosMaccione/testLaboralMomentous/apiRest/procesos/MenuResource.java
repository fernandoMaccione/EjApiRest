package desarrollosMaccione.testLaboralMomentous.apiRest.procesos;

import java.util.Comparator;

import com.google.gson.JsonObject;

import desarrollosMaccione.testLaboralMomentous.apiRest.procesos.interfaces.ProcesarRequest;
import desarrollosMaccione.testLaboralMomentous.excepciones.EntidadException;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.MenuHLP;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.comparadores.ComparadorSRV;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.model.ItemMenuVO;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.model.MenuVO;
import spark.Request;
import spark.Response;

public class MenuResource implements ProcesarRequest{

	@SuppressWarnings("unchecked")
	public JsonObject get(Request req, Response res, JsonObject json) throws EntidadException {
		try{			
			String sId = req.params("id");
			String claseOrdenamiento = req.params("ordenamiento");
			MenuHLP hlp = new MenuHLP();
			MenuVO menu = hlp.getByPk(Long.valueOf(sId));
			Comparator<ItemMenuVO> c = (Comparator<ItemMenuVO>) ComparadorSRV.getFactory(claseOrdenamiento);
			menu = hlp.getMenuOrdenado(menu, c);
			
			json.add("menu", menu.getJson());
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
}
