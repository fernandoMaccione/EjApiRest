package desarrollosMaccione.testLaboralMomentous.modelaje.menu.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import desarrollosMaccione.testLaboralMomentous.excepciones.EntidadException;
import desarrollosMaccione.testLaboralMomentous.modelaje.entidad.model.EntidadVO;
import desarrollosMaccione.testLaboralMomentous.modelaje.moneda.model.MonedaVO;

@Entity
@DiscriminatorValue("2")
public class ItemMenuVO extends EntidadVO<ItemMenuVO>{
	@Id
	@GeneratedValue
	@Column(name = "EntidadID")	 
	private Long id;
	
	private BigDecimal precio;
	
	@OneToOne
	@JoinColumn(name = "MONEDA_FK") 
	private MonedaVO moneda;

	@OneToMany(cascade = CascadeType.ALL)
	private ArrayList<DiasSemanaVO> diasDisponible;
	private String pathImage;
	private Date fechaDesde;
	private Date fechaHasta;
	private Time horaDesde;
	private Time horaHasta;
	private Integer ranking;
	
	public BigDecimal getPrecio() {
		return precio;
	}
	public ItemMenuVO setPrecio(BigDecimal precio) {
		this.precio = precio;
		return this;
	}
	public MonedaVO getMoneda() {
		return moneda;
	}
	public ItemMenuVO setMoneda(MonedaVO moneda) {
		this.moneda = moneda;
		return this;
	}
	public String getPathImage() {
		return pathImage;
	}
	public ItemMenuVO setPathImage(String pathImage) {
		this.pathImage = pathImage;
		return this;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}
	public ItemMenuVO setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
		return this;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public ItemMenuVO setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
		return this;
	}
	public Time getHoraDesde() {
		return horaDesde;
	}
	public ItemMenuVO setHoraDesde(Time horaDesde) {
		this.horaDesde = horaDesde;
		return this;
	}
	public Integer getRanking() {
		return ranking;
	}
	public ItemMenuVO setRanking(Integer ranking) {
		ranking = (ranking<0?0:ranking);
		ranking = (ranking>5?5:ranking);
		this.ranking = ranking;
		return this;
	}
	public Time getHoraHasta() {
		return horaHasta;
	}
	public ItemMenuVO setHoraHasta(Time horaHasta) {
		this.horaHasta = horaHasta;
		return this;
	}
	public ArrayList<DiasSemanaVO> getDiasDisponible() {
		return diasDisponible;
	}
	public ItemMenuVO setDiasDisponible(ArrayList<DiasSemanaVO> diasDisponible) {
		this.diasDisponible = diasDisponible;
		return this;
	}
	
	@Override
	public JsonObject getJson() throws EntidadException {
		JsonObject oJson = super.getJson();
		oJson.addProperty("precio", (this.getPrecio()!=null?this.getPrecio():0));
		oJson.addProperty("fechaDesde", (this.getFechaDesde()!=null?this.getFechaDesde().toString():""));
		oJson.addProperty("fechaHasta", (this.getFechaHasta()!=null?this.getFechaHasta().toString():""));
		oJson.addProperty("horaDesde", (this.getHoraDesde()!=null?this.getHoraDesde().toString():""));
		oJson.addProperty("horaHasta", (this.getHoraHasta()!=null?this.getHoraHasta().toString():""));
		oJson.addProperty("ranking", (this.getRanking()!=null?this.getRanking():0));
		
		if (this.getDiasDisponible() != null && !this.diasDisponible.isEmpty()){
			JsonArray jaDias = new JsonArray();
			for (DiasSemanaVO dias:this.diasDisponible){
				jaDias.add(dias.getDia().name());
			}
			oJson.add("diasDisponible", jaDias);
		}
		
		oJson.add("moneda", (this.getMoneda()!=null?this.getMoneda().getJson():new JsonObject()));
		
		return oJson;
	}

}
