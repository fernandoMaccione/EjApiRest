package desarrollosMaccione.testLaboralMomentous.modelaje.moneda.model;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.google.gson.JsonObject;

import desarrollosMaccione.testLaboralMomentous.excepciones.EntidadException;
import desarrollosMaccione.testLaboralMomentous.modelaje.entidad.model.EntidadVO;

@Entity
@DiscriminatorValue("3")
public class MonedaVO extends EntidadVO<MonedaVO>{
	/*No se contempla historial de cotizaciones*/
	
	private BigDecimal cotizacion;
	
	public BigDecimal getCotizacion() {
		return cotizacion;
	}
	public MonedaVO setCotizacion(BigDecimal cotizacion) {
		this.cotizacion = cotizacion;
		return this;
	}
	@Override
	public JsonObject getJson() throws EntidadException  {
		JsonObject oJson = super.getJson();
		oJson.addProperty("cotizacion", (this.getCotizacion()!=null?this.getCotizacion():0));
		return oJson;
	}
}
