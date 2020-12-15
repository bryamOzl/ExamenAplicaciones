package upd.edu.ec.inventario.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import upd.edu.ec.inventario.DAO.ProductoDAO;
import upd.edu.ec.inventario.modelo.Producto;

@Stateless
public class ProductoON implements ProductoONRemoto{

	@Inject
	private ProductoDAO daoProducto;
	
	public boolean registrarProducto(Producto producto) throws Exception {
		if (producto == null  ) 
			throw new Exception("Producto vacio");
		try {
			daoProducto.insertProducto(producto);
		}catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al Guardar");
		}
		return true;	
	}
	
	public Producto leerProducto(int producto_id) throws Exception {
		if (producto_id == 0  ) 
			throw new Exception("Id vacio");
		try {
			Producto producto = daoProducto.readProducto(producto_id);
			return producto;	
		}catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al Buscar");
		}
		
	}
	
	public boolean actualizarProducto(Producto producto) throws Exception {
		if (producto == null  ) 
			throw new Exception("Producto vacio");
		try {
			daoProducto.updateProducto(producto);
		}catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al Guardar");
		}
		return true;	
	}
	
	public boolean eliminarProducto(int producto_id) throws Exception {
		if (producto_id == 0  ) 
			throw new Exception("Id Vacio");
		try {
			daoProducto.deleteProducto(producto_id);
		}catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al Eliminar");
		}
		return true;	
	}

}
