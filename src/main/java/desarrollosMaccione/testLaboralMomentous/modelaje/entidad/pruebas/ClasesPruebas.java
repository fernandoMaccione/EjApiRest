package desarrollosMaccione.testLaboralMomentous.modelaje.entidad.pruebas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import desarrollosMaccione.testLaboralMomentous.excepciones.EntidadException;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.model.DiasSemanaVO;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.model.EDiasSemana;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.model.ItemMenuVO;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.model.MenuVO;
import desarrollosMaccione.testLaboralMomentous.modelaje.moneda.model.MonedaVO;

public class ClasesPruebas {
	public static List<MenuVO> findAllMenu() throws EntidadException{
		MenuVO menu = new MenuVO();
		menu.setActivo(true).setNombre("MenuGeneral").setDescipcion("el menus es recomendable");
		ItemMenuVO item = new ItemMenuVO();
		item.setActivo(true).setDescipcion("Papas al horno").setMoneda(new MonedaVO().setNombre("peso").setCotizacion(BigDecimal.ONE)).setPrecio(new BigDecimal(34));
		item.setRanking(3);
		ArrayList<DiasSemanaVO> dias = new ArrayList<DiasSemanaVO>();
		dias.add(new DiasSemanaVO().setDia(EDiasSemana.Lunes));
		dias.add(new DiasSemanaVO().setDia(EDiasSemana.Domingo));
		dias.add(new DiasSemanaVO().setDia(EDiasSemana.Jueves));
		item.setDiasDisponible(dias);	
		
		ArrayList<ItemMenuVO> lItem = new ArrayList<>();
		lItem.add(item);
		
		item = new ItemMenuVO();
		item.setActivo(true).setDescipcion("Peras al horno").setMoneda(new MonedaVO().setNombre("Dolar").setCotizacion(BigDecimal.ONE)).setPrecio(new BigDecimal(40));
		item.setRanking(3);
		dias = new ArrayList<DiasSemanaVO>();
		dias.add(new DiasSemanaVO().setDia(EDiasSemana.Martes));
		dias.add(new DiasSemanaVO().setDia(EDiasSemana.Miercoles));
		dias.add(new DiasSemanaVO().setDia(EDiasSemana.Sabado));
		item.setDiasDisponible(dias);
		lItem.add(item);
		menu.setItemMenu(lItem);
		
		
		List<MenuVO> d = new ArrayList<MenuVO>();
		d.add(menu);
		
		return d;
		}
	}

