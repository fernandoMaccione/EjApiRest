package desarrollosMaccione.testLaboralMomentous.modelaje.menu.comparadores;

import java.util.Comparator;

import desarrollosMaccione.testLaboralMomentous.excepciones.EntidadException;
import desarrollosMaccione.testLaboralMomentous.modelaje.constantes.MensajesCTE;

public class ComparadorSRV {
	public static Comparator<?> getFactory (String claseComparador) throws EntidadException{
		//esto habría que volarlo y hacer una buena inyección de depencias. Ahora no hay tiempo.
		switch (claseComparador) {
		case "ComprarItemPrecio":
			return new CompararItemPrecio();

		case "CompararItemRanking":
			return new CompararItemRanking();
		default:
			throw new  EntidadException(MensajesCTE.ERROR_CLASE_COMPARADOR + claseComparador);
		}
	}
}
