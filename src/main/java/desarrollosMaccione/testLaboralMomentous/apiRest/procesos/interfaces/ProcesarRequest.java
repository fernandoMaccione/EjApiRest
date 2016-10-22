package desarrollosMaccione.testLaboralMomentous.apiRest.procesos.interfaces;

import com.google.gson.JsonObject;

import desarrollosMaccione.testLaboralMomentous.excepciones.EntidadException;
import spark.Request;
import spark.Response;

public interface ProcesarRequest {
	public JsonObject get (Request req, Response res, JsonObject json) throws EntidadException;
	public JsonObject post (Request req, Response res, JsonObject json)throws EntidadException;
	public JsonObject put (Request req, Response res, JsonObject json)throws EntidadException;
	public JsonObject delete (Request req, Response res, JsonObject json)throws EntidadException;
}
