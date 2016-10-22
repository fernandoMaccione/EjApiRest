package desarrollosMaccione.testLaboralMomentous.apiRest.json.interfaces;

import com.google.gson.JsonObject;

import desarrollosMaccione.testLaboralMomentous.excepciones.EntidadException;

public interface SerializeJson {
	public JsonObject getJson () throws EntidadException;
}
