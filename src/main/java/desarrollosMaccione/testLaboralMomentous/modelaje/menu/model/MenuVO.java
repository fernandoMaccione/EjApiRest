package desarrollosMaccione.testLaboralMomentous.modelaje.menu.model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import desarrollosMaccione.testLaboralMomentous.Log.LogTimeMethod;
import desarrollosMaccione.testLaboralMomentous.excepciones.EntidadException;
import desarrollosMaccione.testLaboralMomentous.modelaje.constantes.MensajesCTE;
import desarrollosMaccione.testLaboralMomentous.modelaje.entidad.model.EntidadVO;

@Entity
@DiscriminatorValue("1")
public class MenuVO extends EntidadVO<MenuVO>{
	@Id
	@GeneratedValue
	@Column(name = "EntidadID")
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL)
	private ArrayList<ItemMenuVO> itemMenu;
	
	@OneToMany(cascade = CascadeType.ALL)
	private ArrayList<SubMemuVO> subMenu;

	public ArrayList<ItemMenuVO> getItemMenu() {
		return itemMenu;
	}
	public MenuVO setItemMenu(ArrayList<ItemMenuVO> itemMenu) {
		this.itemMenu = itemMenu;
		return this;
	}
	public ArrayList<SubMemuVO> getSubMenu() {
		return subMenu;
	}
	public MenuVO setSubMenu(ArrayList<SubMemuVO> subMenu) {
		this.subMenu = subMenu;
		return this;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public JsonObject getJson() throws EntidadException {
		LogTimeMethod logT = new LogTimeMethod();
		try{
			JsonObject oJson = super.getJson();
			if (this.getItemMenu() != null && !this.getItemMenu().isEmpty()){
				JsonArray jaItemMenu = new JsonArray();
				for (ItemMenuVO item: this.getItemMenu()){
					jaItemMenu.add(item.getJson());
				}
				oJson.add("itemMenu", jaItemMenu);
			}
			
			if (this.getSubMenu() != null && !this.getSubMenu().isEmpty()){
				JsonArray jaSubMenu = new JsonArray();
				for (SubMemuVO subMenu:this.getSubMenu()){
					jaSubMenu.add(subMenu.getJson());
				}
				oJson.add("subMenu", jaSubMenu);			
			}
			
			return oJson;
		}catch (Exception e){
			throw new EntidadException(MensajesCTE.ERROR_ENTIDAD_PERSIST_EJECUTAR_QUERY, e);
		}finally {
			logT.finish();
		}
	}
}
