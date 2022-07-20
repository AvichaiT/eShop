package eShop;

public class OutletProducts {
	
	private Product[] products;
	private int productsCounter;
	private int discount;
	
	//outlet constructor
	public OutletProducts() {
		products = new Product[100];
		productsCounter = 0;
		discount = 30;
	}
	
	//products counter getter
	public int getProductsCounter() {
		return productsCounter;
	}
	
	//products getter
	public Product[] getProducts() {
		return products;
	}
	
	//view outlet products
	public String viewStoreProducts() {
		String productsDetails = new String();
		productsDetails += "--------------------------------- OUTLET ----------------------------------\n";
		productsDetails += "Serial number\tProduct name\tPrice\t\tDiscount\tFinal price\n";
		productsDetails += "---------------------------------------------------------------------------\n";
		for(int i=0;i < productsCounter; i++) {
			productsDetails += products[i].viewProduct() + "\n";
		}
		return productsDetails;
	}
	
	//set outlet discount
	public boolean setDiscount(int discount) {
		this.discount = discount > 0 && discount < 100 ? discount : this.discount;
		return discount > 0 && discount < 100;
	}
	
	//remove entire outlet products from store
	public boolean removeFromStock() {
		if(productsCounter > 0) {
			for(int i=0; i < productsCounter; i++) {
				this.products[i] = null;
			}
			productsCounter = 0;
			return true;
		}
		return false;
	}
	
	//add a product to outlet section
	public boolean addProduct(Product product) {
		if(productsCounter < products.length) {
			products[productsCounter++] = new Product(product);
			products[productsCounter - 1].changeDiscount(this.discount != 0 ? this.discount : products[productsCounter - 1].getDiscount());
			return true;
		}
		return false;
	}
}
