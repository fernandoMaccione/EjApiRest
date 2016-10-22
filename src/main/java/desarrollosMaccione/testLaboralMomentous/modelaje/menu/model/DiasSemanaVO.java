package desarrollosMaccione.testLaboralMomentous.modelaje.menu.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
public class DiasSemanaVO {
	
	@Enumerated
	private EDiasSemana dia;

	public EDiasSemana getDia() {
		return dia;
	}
	public DiasSemanaVO setDia(EDiasSemana dia) {
		this.dia = dia;
		return this;
	}
	
}
