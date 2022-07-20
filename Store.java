package eShop;

import java.io.PrintWriter;
import java.util.Scanner;

public class Store {
	
	private String name;
	private Product[] products;
	private int productsCounter;
	private Customer[] customers;
	private int customersCounter;
	private Cart[] carts;
	private int cartsCounter;
	private float dailyIncome;
	private int productsSold;
	private Manager manager;
	private OutletProducts outlet;
	
	//store's constructor
	public Store(String name) {
		this.name = name;
		products = new Product[1000];
		productsCounter = 0;
		customers = new Customer[100];
		customersCounter = 0;
		carts = new Cart[100];
		this.cartsCounter = 0;
		dailyIncome = 0;
		productsSold = 0;
		outlet = new OutletProducts();
	}
	
	//store's name getter
	public String getStoreName() {
		return name;
	}
	
	//view daily incomes
	public float getDailyIncome() {
		return dailyIncome;
	}
	
	//view daily amount of products sold
	public int getProductsSold() {
		return productsSold;
	}
	
	//products counter getter
	public int getProductsCounter() {
		return productsCounter;
	}
	
	//products array getter
	public Product[] getProducts() {
		return this.products;
	}
	
	//outlet section getter
	public OutletProducts getOutlet() {
		return outlet;
	}
	
	//customer counter getter
	public int getCustomersCounter() {
		return customersCounter;
	}
	
	//customers array getter
	public Customer[] getCustomers() {
		return customers;
	}
	
	//carts counter getter
	public int getCartsCounter() {
		return cartsCounter;
	}
	
	//carts array getter
	public Cart[] getCarts() {
		return carts;
	}
	
	//store manager getter
	public Manager getStoreManager() {
		return manager;
	}
	
	//hire a manager to store
	public void hireManager(String username, String password) {
		if(this.manager == null) {
			manager = new Manager(username, password);
			this.manager.store = this;
			
		}
	}
	
	//fire manager
	public void fireManager() {
		if(this.manager != null) {
			manager.store = null;
			this.manager = null;
		}
	}
	
	//add a customer to store
	public boolean signUp(String username, String password, String address, String phone) {
		if(customersCounter < customers.length) {
			customers[customersCounter++] = new Customer(username, password, address, phone);
			return true;
		}
		return false;
	}
	
	//remove a customer from store
	public boolean removeCustomer(String username) {
		for(int i=0; i < customersCounter; i++) {
			if(customers[i].getUsername().equals(username)) {
				customers[i] = customers[--customersCounter];
				customers[customersCounter] = null;
				return true;
			}
		}
		return false;
	}
	
	//add a cart to store
	public boolean addCart() {
		if(cartsCounter < carts.length) {
			carts[cartsCounter++] = new Cart();
			return true;
		}
		return false;
	}
	
	//remove a cart from store
	public boolean removeCart() {
		for(int i=0; i < cartsCounter; i++) {
			if(carts[i].getCartHolder() == null) {
				carts[i] = carts[--cartsCounter];
				carts[cartsCounter] = null;
				return true;
			}
		}
		return false;
	}
	
	//view amount of customers online
	public int activeCustomers() {
		int counter = 0;
		for(int i=0; i < customersCounter; i++) {
			counter += customers[i].getIsLoggedIn() == true ? 1 : 0;
		}
		return counter;
	}
	
	//view amount of carts in use
	public int cartsInUse() {
		int counter = 0;
		for(int i=0; i < cartsCounter; i++) {
			counter += carts[i].getCartHolder() != null ? 1 : 0;
		}
		return counter;
	}
	
	//add a product to store
	public boolean addProduct(String name, float price, int discount) {
		if(productsCounter < products.length) {
			this.products[productsCounter++] = new Product(name, price, discount);
			return true;
		}
		return false;
	}
	
	//remove a product from store
	public boolean removeProduct(int product) {
		if(productsCounter > 0) {
			for(int i=0; i < productsCounter; i++) {
				if(product == products[i].getCatalogNumber()) {
					products[i] = products[--productsCounter];
					products[productsCounter] = null;
					return true;
				}
			}
		}
		return false;
	}
	
	//receive a payment from customer
	public void receivePayment(Cart cart) {
		dailyIncome += cart.calcPrice();
		productsSold += cart.getProductsCounter();
	}
	
	//view current store products
	public String viewStoreProducts() {
		String productsDetails = new String();
		productsDetails += "Serial number\tProduct name\tReg. Price\tDiscount\tFinal price\n";
		productsDetails += "---------------------------------------------------------------------------\n";
		for(int i=0;i < productsCounter; i++) {
			productsDetails += products[i].viewProduct() + "\n";
		}
		return productsDetails;
	}
	
	//create a file with store's products
	public void getProductsReport(PrintWriter file) {
		file.print(viewStoreProducts());
	}
	
	//import products into store from a file
	public void importFromSupplier(Scanner file) {
		while(file.hasNext()) {
			String name = file.next();
			float price = file.nextFloat();
			int discount = file.nextInt();
			this.addProduct(name, price, discount);
		}
	}
}
