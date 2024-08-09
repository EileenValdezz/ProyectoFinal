package Clases;

public class Producto {
	//variables que tendra cada producto que tambien estan en la base de datos

	private int idProducto;
	private String NombreProducto;
	private String MarcaProducto;
	private String CategoriaProducto;
	private int PrecioProducto;
	private int StockProducto;
	
	public Producto(int idProducto, String nombreProducto, String marcaProducto, String categoriaProducto, int precioProducto, int stockProducto) {
		this.idProducto = idProducto;
		NombreProducto = nombreProducto;
		MarcaProducto = marcaProducto;
		CategoriaProducto = categoriaProducto;
		PrecioProducto = precioProducto;
		StockProducto = stockProducto;
	}
	
	//Metodos getters y setters

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return NombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		NombreProducto = nombreProducto;
	}

	public String getMarcaProducto() {
		return MarcaProducto;
	}

	public void setMarcaProducto(String marcaProducto) {
		MarcaProducto = marcaProducto;
	}

	public String getCategoriaProducto() {
		return CategoriaProducto;
	}

	public void setCategoriaProducto(String categoriaProducto) {
		CategoriaProducto = categoriaProducto;
	}

	public int getPrecioProducto() {
		return PrecioProducto;
	}

	public void setPrecioProducto(int precioProducto) {
		PrecioProducto = precioProducto;
	}

	public int getStockProducto() {
		return StockProducto;
	}

	public void setStockProducto(int stockProducto) {
		StockProducto = stockProducto;
	}

}