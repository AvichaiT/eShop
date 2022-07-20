package eShop;

public class Product {
	
	private String name;
	private static int counter = 1000;
	private int catalogNumber;
	private float price;
	private int discount;
	
	//product's constructor
	public Product(String name, float price, int discount) {
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.catalogNumber = counter++;
	}
	
	//products copy constructor
	public Product(Product product) {
		this.name = product.name;
		this.price = product.price;
		this.discount = product.discount;
		this.catalogNumber = product.catalogNumber;
	}
	
	//catalog number getter
	public int getCatalogNumber() {
		return this.catalogNumber;
	}
	
	//discount getter
	public int getDiscount() {
		return this.discount;
	}
	
	//price getter
	public float getPrice() {
		return this.price;
	}
	
	//change product's price
	public boolean changePrice(float newPrice) {
		this.price = newPrice > 0 ? newPrice : this.price;
		return newPrice > 0;
	}
	
	//change product's discount
	public boolean changeDiscount(int newDiscount) {
		this.discount = newDiscount >=0 && newDiscount < 100 ? newDiscount : this.discount;
		return newDiscount >= 0 && newDiscount < 100;
	}
	
	//view product's details
	public String viewProduct() {
		return this.catalogNumber + "\t\t" + this.name + "\t\t" + String.format("%.2f", this.price) + " $\t\t" + this.discount + " %\t\t" +String.format("%.2f", this.price * (1-((float)this.discount/100))) + " $";
	}
}
