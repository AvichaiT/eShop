package eShop;

public class Customer extends User {
	
	private String shippingAddress;
	private String phoneNumber;
	private Cart cartHeld;
	
	//customer constructor
	public Customer(String username, String password, String address, String phone) {
		super(username, password);
		this.shippingAddress = address;
		this.phoneNumber = phone;
		this.cartHeld = null;
		this.setIsLoggedIn(false);
	}
	
	//get cart
	public Cart getCart() {
		return this.cartHeld;
	}
	
	//get customer's details
	public String getDeatils() {
		return "------ PERSONAL INFORMATION ------\n" + 
	"Username: " + this.getUsername() + "\n" + "Address: " + shippingAddress + "\n" + "Phone number: " + phoneNumber
	+ "\n----------------------------------";
	}
	
	//change address
	public boolean changeAddress(String newAddress) {
		this.shippingAddress = newAddress.equals("") != true ? newAddress : this.shippingAddress;
		return newAddress.equals("") != true;
	}
	
	public boolean changePhoneNumber(String newPhoneNumber) {
		if(newPhoneNumber.matches("[0-9]+") == true && newPhoneNumber.length() == 10) {
			this.phoneNumber = newPhoneNumber;
			return true;
		}
		return false;
	}
	
	//take cart from carts array. first available cart within array
	public boolean takeCart(Cart cart) {
		if(this.cartHeld == null) {
			this.cartHeld = cart;
			cart.setCartHolder(this);
			return true;
		}
		return false;
	}
	
	//free cart only if one is being held
	public boolean freeCart() {
		if(this.cartHeld != null) {
			this.cartHeld.emptyCart();
			this.cartHeld.setCartHolder(null);
			this.cartHeld = null;
			return true;
		}
		return false;
	}
	
	//add a product to cart
	public boolean addToCart(Product product) {
		if(this.cartHeld != null) {
			return this.cartHeld.addToCart(product);
		}
		return false;
	}
	
	//remove a product from by serial number
	public boolean removeFromCart(int product) {
		if(this.cartHeld != null) {
			return cartHeld.removeFromCart(product);
		}
		return false;
	}
	
	//view bill
	public String viewBill() {
		if(this.cartHeld != null) {
			String bill = new String();
			bill += "----------------------------------- BILL -----------------------------------\n";
			bill += this.cartHeld.viewProducts();
			bill += "----------------------------------------------------------------------------\n";
			bill += "Total savings with this purchase: " + this.cartHeld.calcDiscount() + " $\n";
			bill += "Total to pay: " + this.cartHeld.calcPrice() + " $";
			return bill;
		}
		return "";
	}
	
	//make payment
	public boolean payment(float payment, Store store) {
		if(this.cartHeld.calcPrice() == payment) {
			store.receivePayment(this.cartHeld);
			this.cartHeld.emptyCart();
			return true;
		}
		return false;
	}
}
