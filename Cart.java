package eShop;

public class Cart {
	
	private static int counter = 1000;
	private int serialNumber;
	private Product[] cartProducts;
	private int productsCounter;
	private Customer cartHolder;
	
	public Cart() {
		this.serialNumber = counter++;
		this.cartProducts = new Product[100];
		this.productsCounter = 0;
		this.cartHolder = null;
	}
	
	//cart holder getter
	public Customer getCartHolder() {
		return this.cartHolder;
	}
	
	//cart holder getter
	public void setCartHolder(Customer customer) {
		this.cartHolder = customer;
	}
	
	public int getProductsCounter() {
		return productsCounter;
	}
	
	public boolean addToCart(Product product) {
		if(productsCounter < cartProducts.length) {
			cartProducts[productsCounter++] = new Product(product);
			return true;
		}
		return false;
	}
	
	public boolean removeFromCart(int product) {
		for(int i=0; i < productsCounter; i++) {
			if(cartProducts[i].getCatalogNumber() == product) {
				cartProducts[i] = cartProducts[--productsCounter];
				cartProducts[productsCounter] = null;
				return true;
			}
		}
		return false;
	}
	
	public String viewProducts() {
		String productsDetails = new String();
		productsDetails += "Serial number\tProduct name\tPrice\t\tDiscount\tFinal price\n";
		productsDetails += "---------------------------------------------------------------------------\n";
		for(int i=0;i < productsCounter; i++) {
			productsDetails += cartProducts[i].viewProduct() + "\n";
		}
		return productsDetails;
	}
	
	public float calcPrice() {
		float totalPrice = 0;
		for(int i=0; i < productsCounter; i++) {
			totalPrice += cartProducts[i].getPrice();
		}
		return totalPrice - this.calcDiscount();
	}
	
	public float calcDiscount() {
		float totalDiscount = 0;
		for(int i=0; i < productsCounter; i++) {
			totalDiscount += cartProducts[i].getDiscount() == 0 ? 0 : (float)((float)cartProducts[i].getPrice() * cartProducts[i].getDiscount()) / 100;
		}
		return totalDiscount;
	}
	
	public void emptyCart() {
		for(int i=0; i < productsCounter; i++) {
			this.cartProducts[i] = null;
		}
		this.productsCounter = 0;
	}
}
