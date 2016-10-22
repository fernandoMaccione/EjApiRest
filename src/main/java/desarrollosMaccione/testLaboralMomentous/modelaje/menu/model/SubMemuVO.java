package desarrollosMaccione.testLaboralMomentous.modelaje.menu.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("4")
public class SubMemuVO extends MenuVO{
	
	@Id
	@GeneratedValue
	@Column(name = "EntidadID")
	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
