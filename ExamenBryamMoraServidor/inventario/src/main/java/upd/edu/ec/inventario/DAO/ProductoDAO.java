package upd.edu.ec.inventario.DAO;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import upd.edu.ec.inventario.modelo.Producto;

@Stateless
public class ProductoDAO {

	@Inject
	private EntityManager em;
	
	public boolean insertProducto(Producto producto) throws SQLException{
		em.persist(producto);
		return true;
	}
	
	public Producto readProducto(int producto_id ) throws SQLException{
		Producto p = em.find(Producto.class, producto_id);
		return p;
	}
	
	public boolean deleteProducto(int producto_id ) throws SQLException{
		em.remove(em.find(Producto.class, producto_id));
		return true;
	}
	
	public boolean updateProducto(Producto producto) throws SQLException{
		em.merge(producto);
		return true;
	}
}
