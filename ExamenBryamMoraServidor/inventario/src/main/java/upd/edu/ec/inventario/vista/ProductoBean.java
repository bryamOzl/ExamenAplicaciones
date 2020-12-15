package upd.edu.ec.inventario.vista;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import upd.edu.ec.inventario.modelo.Producto;
import upd.edu.ec.inventario.negocio.ProductoON;

@Named
@RequestScoped
public class ProductoBean {

	private Producto producto;

	@Inject
	private ProductoON on;

	public ProductoBean() {
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String doGuardar() {
		try {
			on.registrarProducto(producto);
			System.out.println("Se guardo correctamente");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No se Guardo " + e.getMessage());
			e.printStackTrace();
		}
		return "registroProducto";
	}

}
