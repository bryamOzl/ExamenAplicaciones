package upd.edu.ec.inventario.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import upd.edu.ec.inventario.modelo.Producto;
import upd.edu.ec.inventario.negocio.ProductoONRemoto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaIngresarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField nombreTxt;
	private JTextField precioTxt;
	private JTextField tipoTxt;

	private ProductoONRemoto on;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaIngresarProducto frame = new VistaIngresarProducto();
					frame.referenciarONProdcuto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaIngresarProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		idTxt = new JTextField();
		idTxt.setBounds(180, 72, 120, 19);
		contentPane.add(idTxt);
		idTxt.setColumns(10);

		JLabel lblNewLabel = new JLabel("Producto ID:");
		lblNewLabel.setBounds(106, 73, 64, 16);
		contentPane.add(lblNewLabel);

		nombreTxt = new JTextField();
		nombreTxt.setColumns(10);
		nombreTxt.setBounds(180, 101, 120, 19);
		contentPane.add(nombreTxt);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(106, 102, 64, 16);
		contentPane.add(lblNombre);

		precioTxt = new JTextField();
		precioTxt.setColumns(10);
		precioTxt.setBounds(180, 130, 120, 19);
		contentPane.add(precioTxt);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(106, 131, 64, 16);
		contentPane.add(lblPrecio);

		tipoTxt = new JTextField();
		tipoTxt.setColumns(10);
		tipoTxt.setBounds(180, 159, 120, 19);
		contentPane.add(tipoTxt);

		JLabel lblTipoProducto = new JLabel("Tipo Producto:");
		lblTipoProducto.setBounds(91, 160, 79, 16);
		contentPane.add(lblTipoProducto);

		JLabel lblIngresarProductos = new JLabel("Ingresar Productos");
		lblIngresarProductos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIngresarProductos.setBounds(122, 26, 200, 16);
		contentPane.add(lblIngresarProductos);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarProducto();
			}
		});
		btnGuardar.setBounds(107, 207, 85, 21);
		contentPane.add(btnGuardar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(215, 207, 85, 21);
		contentPane.add(btnLimpiar);
	}

	protected void guardarProducto() {
		Producto p = new Producto();
		p.setProducto_id(Integer.parseInt(idTxt.getText()));
		p.setNombre(nombreTxt.getText());
		p.setPrecio(Double.parseDouble(precioTxt.getText()));
		p.setTipo_producto(tipoTxt.getText());
		try {
			on.registrarProducto(p);
			System.out.println("Producto Guardado");
		} catch (Exception e) {
			System.out.println("Error al  Guardar " + e.getMessage());
			e.printStackTrace();
		}

	}

	public void referenciarONProdcuto() throws Exception {
		try {
			final Hashtable<String, Comparable> jndiProperties = new Hashtable<String, Comparable>();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.wildfly.naming.client.WildFlyInitialContextFactory");
			jndiProperties.put("jboss.naming.client.ejb.context", true);

			jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			jndiProperties.put(Context.SECURITY_PRINCIPAL, "bmora");
			jndiProperties.put(Context.SECURITY_CREDENTIALS, "bmora");

			final Context context = new InitialContext(jndiProperties);

			final String lookupName = "ejb:/inventario/ProductoON!upd.edu.ec.inventario.negocio.ProductoONRemoto";

			this.on = (ProductoONRemoto) context.lookup(lookupName);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
}
