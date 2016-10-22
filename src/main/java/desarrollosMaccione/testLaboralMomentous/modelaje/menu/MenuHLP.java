package desarrollosMaccione.testLaboralMomentous.modelaje.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import desarrollosMaccione.testLaboralMomentous.Log.LogTimeMethod;
import desarrollosMaccione.testLaboralMomentous.excepciones.EntidadException;
import desarrollosMaccione.testLaboralMomentous.excepciones.RecursionException;
import desarrollosMaccione.testLaboralMomentous.modelaje.constantes.GeneralesCTE;
import desarrollosMaccione.testLaboralMomentous.modelaje.constantes.MensajesCTE;
import desarrollosMaccione.testLaboralMomentous.modelaje.entidad.EntidadHLP;
import desarrollosMaccione.testLaboralMomentous.modelaje.entidad.pruebas.ClasesPruebas;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.comparadores.CompararItemPrecio;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.comparadores.CompararItemRanking;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.model.ItemMenuVO;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.model.MenuVO;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.model.SubMemuVO;

public class MenuHLP extends EntidadHLP<MenuVO>{

	@Override
	protected MenuVO getVO() { 
		return new MenuVO();
	} 
	
	public List<MenuVO> getMenuDisponibles(java.util.Date date) throws EntidadException{
		try {
			String jpql = "SELECT m FROM MenuVO WHERE m.Activo = 1 AND p.esPadre = 1";
			//falta terminar
			//List<MenuVO> menuAll = this.getByJPQL(jpql); por ahora no hay base... la idea esta(?
			List<MenuVO> menuAll = ClasesPruebas.findAllMenu();
			
			//por ahora filtro por codigo, cuando implemente criteria filtro desde la base.
		
			return menuAll;
		} catch (EntidadException e) {
			throw e;
		}
	}
	
	public MenuVO getMenuOrdenado(MenuVO menu, Comparator<ItemMenuVO> comp ) throws EntidadException{
		try {
			ArrayList<ItemMenuVO> cItemMenu = menu.getItemMenu();
			cItemMenu.sort(comp);;
			menu.setItemMenu(cItemMenu);
			return menu;
		} catch (Exception e) {
			throw new EntidadException(MensajesCTE.ERROR_GENERICO_ORDENAMIENTO, e);
		}
	}
	
	public MenuVO ordenarPorPrecio(MenuVO menu) throws EntidadException{
		return getMenuOrdenado(menu, new CompararItemPrecio());
	}
	
	public MenuVO ordenarPorRanking(MenuVO menu) throws EntidadException{
		return getMenuOrdenado(menu, new CompararItemRanking());
	}
	
	////Funciones para el Calculo del precio total del menu
	public BigDecimal getPrecioMenuTotal(MenuVO menu) throws RecursionException, EntidadException{
		LogTimeMethod logT = new LogTimeMethod();
		try {
			BigDecimal precioAux = getPrecioMenuItem (menu.getItemMenu());
			
			if (menu.getSubMenu() != null && !menu.getSubMenu().isEmpty()){
				precioAux = getPrecioSubMenu(precioAux, menu.getSubMenu(), 0, 0);
			}
		return precioAux;
		} catch (RecursionException e) {
			throw e;			
		} catch (Exception e) {
			throw new EntidadException(MensajesCTE.ERROR_GENERICO_NEGOCIOS, e);
		}finally {
			logT.finish();
		}
	
	}

	private BigDecimal getPrecioSubMenu(BigDecimal precioAux, ArrayList<SubMemuVO> cSubMenu, int posicion, int nivelRecursion) throws RecursionException {

		SubMemuVO subMenu = cSubMenu.get(posicion);
		precioAux = precioAux.add(getPrecioMenuItem(subMenu.getItemMenu()));
		
		if (subMenu.getSubMenu() != null && !subMenu.getSubMenu().isEmpty()){
			precioAux = getPrecioSubMenu(precioAux, subMenu.getSubMenu(), 0, 0);
		}
		
		posicion++;
		nivelRecursion++;
		if (nivelRecursion > GeneralesCTE.CANTIDAD_NIVELES_SUBMENU)
			throw new RecursionException(MensajesCTE.ERROR_RECURSION_MENU);
		
		if (posicion < cSubMenu.size())
			precioAux = getPrecioSubMenu(precioAux, cSubMenu, posicion, nivelRecursion);

		return precioAux;
	}

	private BigDecimal getPrecioMenuItem(ArrayList<ItemMenuVO> itemMenu) {
		BigDecimal precio = BigDecimal.ZERO;
		for (ItemMenuVO item:itemMenu){
			if (item.getPrecio() != null)
				precio = precio.add(item.getPrecio());
			
		}
		return precio;
	}
	
	///Funciones para calcular la cantidad de submenu activos que hay.	
	public int getCantidadMenuActivos(MenuVO menu) throws RecursionException, EntidadException{
		LogTimeMethod logT = new LogTimeMethod();
		try{
			int cantidad = 0;
			
			if (menu.getSubMenu() != null && !menu.getSubMenu().isEmpty()){
				cantidad = getCantidadSubMenuActivos(cantidad, menu.getSubMenu(), 0, 0);
			}
		
			return cantidad;
		} catch (RecursionException e) {
			throw e;			
		} catch (Exception e) {
			throw new EntidadException(MensajesCTE.ERROR_GENERICO_NEGOCIOS, e);
		}finally {
			logT.finish();
		}
	}

	private int getCantidadSubMenuActivos(int cantidadSubMenu, ArrayList<SubMemuVO> cSubMenu, int posicion, int nivelRecursion) throws RecursionException {
		
		SubMemuVO subMenu = cSubMenu.get(posicion);
		if (subMenu.getActivo())
			cantidadSubMenu++;
		
		if (subMenu.getSubMenu() != null && !subMenu.getSubMenu().isEmpty()){
			cantidadSubMenu = getCantidadSubMenuActivos(cantidadSubMenu, subMenu.getSubMenu(), 0, 0);
		}
		
		posicion++;
		nivelRecursion++;
		if (nivelRecursion > GeneralesCTE.CANTIDAD_NIVELES_SUBMENU)
			throw new RecursionException(MensajesCTE.ERROR_RECURSION_MENU);
		
		if (posicion < cSubMenu.size())
			cantidadSubMenu = getCantidadSubMenuActivos(cantidadSubMenu, cSubMenu, posicion, nivelRecursion);
		
		return cantidadSubMenu;
	}
	
}
