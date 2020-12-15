package upd.edu.ec.inventario.negocio;

import java.sql.SQLException;

import javax.ejb.Remote;

import upd.edu.ec.inventario.modelo.Producto;

@Remote
public interface ProductoONRemoto {
	
	public boolean registrarProducto(Producto producto) throws Exception;
	public Producto leerProducto(int producto_id) throws Exception;
	public boolean actualizarProducto(Producto producto) throws Exception;
	public boolean eliminarProducto(int producto_id) throws Exception;

}
