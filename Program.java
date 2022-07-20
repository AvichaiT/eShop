package eShop;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Program {
	
	//PERSONAL DETAILS MANAGEMENT
	public static void manageProfile(Customer customer, Scanner c) {
		
		String selection;
		
		do {
			System.out.println(customer.getDeatils());
			System.out.println("1 - Change password");
			System.out.println("2 - Change address");
			System.out.println("3 - Change phone number");
			System.out.println("4 - Exit profile management");
			System.out.println("----------------------------------");
			System.out.println("Select your choice:");
			selection = c.nextLine();
			
			switch(selection) {
			case "1":{
				System.out.println("Enter username: ");
				String username = c.nextLine();
				System.out.println("Enter password: ");
				String password = c.nextLine();
				System.out.println("Enter new password: ");
				String newPassword = c.nextLine();
				//check if passwords contains valid characters only
				while(newPassword.matches("[a-zA-Z0-9]*") == false) {
					System.out.println("Enter a valid password:");
					newPassword = c.nextLine();
				}
				System.out.println(customer.changePasssword(username, password, newPassword) ? "Password changed successfully" : "Wrong username or password entered");
				
			}break;
			case "2":{
				System.out.println("Enter new address: ");
				String address = c.nextLine();
				System.out.println(customer.changeAddress(address) ? "Address changed successfully" : "Invalid address entered");
			}break;
			case "3":{
				System.out.println("Enter new phone number: ");
				String phone = c.nextLine();
				System.out.println(customer.changePhoneNumber(phone) ? "Phone number changed successfully" : "Failed to change phone number");
			}break;
			default:{
				System.out.println("Invalid selection insereted");
			}break;
			}
			
			if(selection.equals("4") != true) {
				System.out.println("Press Enter to continue...");
				c.nextLine();
			}
		}while(selection.equals("4") != true);	
	}
	/*-----------------------------------------------------------------------------------------------------------------*/
	
	//PRODUCTS EDITOR
	public static void editProducts(Manager manager, Scanner c) {
		
		System.out.println(manager.store.viewStoreProducts());
		System.out.println(manager.store.getOutlet().viewStoreProducts());
		
		String selection;
		do {
			System.out.println("1 - Edit a product's price");
			System.out.println("2 - Edit a product's discount");
			System.out.println("3 - Remove a product");
			System.out.println("4 - Move product to outlet");
			System.out.println("5 - Remove outlet items from store");
			System.out.println("6 - Import products from supplier");
			System.out.println("7 - Create existing products report");
			System.out.println("8 - Exit products editor");
			
			selection = c.nextLine();
			switch(selection) {
			case "1":{
				System.out.println(manager.store.viewStoreProducts());
				System.out.println(manager.store.getOutlet().viewStoreProducts());
				int flag = 0;
				System.out.println("Enter product's serial number:");
				String serial = c.nextLine();
				int serialNumber = serial.matches("[0-9]+") == false ? 0 : Integer.parseInt(serial);
				//find product in store
				for(int i=0; i < manager.store.getProductsCounter(); i++) {
					//manipulate product's price once found
					if(serialNumber == manager.store.getProducts()[i].getCatalogNumber()) {
						System.out.println("Enter new price: ");
						String newPrice = c.nextLine();
						float price = newPrice.matches("[0-9]+") == false ? 0 : Integer.parseInt(newPrice);
						System.out.println(manager.store.getProducts()[i].changePrice(price) ? "Price has changed successfully" : "Failed to change price");
						flag = 1;
					}
				}
				System.out.printf(flag == 0 ? "Wrong catalog number entered\n" : "");
			}break;
			
			case "2":{
				System.out.println(manager.store.viewStoreProducts());
				System.out.println(manager.store.getOutlet().viewStoreProducts());
				int flag = 0;
				System.out.println("Enter product's serial number:");
				String serial = c.nextLine();
				int serialNumber = serial.matches("[0-9]+") == false ? 0 : Integer.parseInt(serial);
				//find product in store
				for(int i=0; i < manager.store.getProductsCounter(); i++) {
					//manipulate product's disount once found
					if(serialNumber == manager.store.getProducts()[i].getCatalogNumber()) {
						System.out.println("Enter new discount: ");
						String newDiscount = c.nextLine();
						int discount = newDiscount.matches("[0-9]+") == false ? 0 : Integer.parseInt(newDiscount);
						System.out.println(manager.store.getProducts()[i].changeDiscount(discount) ? "Discount has changed successfully" : "Failed to change discount");
						flag = 1;
					}
				}
				System.out.printf(flag == 0 ? "Wrong catalog number entered\n" : "");
			}break;
			
			case "3":{
				System.out.println(manager.store.viewStoreProducts());
				System.out.println(manager.store.getOutlet().viewStoreProducts());
				System.out.println("Enter product's serial number:");
				String serial = c.nextLine();
				int serialNumber = serial.matches("[0-9]+") == false ? 0 : Integer.parseInt(serial);
				System.out.println(manager.store.removeProduct(serialNumber) ? "Product removed successfully" : "Wrong serial number entered");
			}break;
			
			case "4":{
				int flag = 0;
				System.out.println(manager.store.viewStoreProducts());
				System.out.println(manager.store.getOutlet().viewStoreProducts());
				System.out.println("Enter product's serial number:");
				String serial = c.nextLine();
				int serialNumber = serial.matches("[0-9]+") == false ? 0 : Integer.parseInt(serial);
				//find product in store
				for(int i=0; i < manager.store.getProductsCounter(); i++) {
					//move product to outlet once found
					if(manager.store.getProducts()[i].getCatalogNumber() == serialNumber) {
						System.out.println(manager.store.getOutlet().addProduct(manager.store.getProducts()[i]) ? "Product moved to outlet successfully" : "Failed to move product to outlet");
						manager.store.removeProduct(serialNumber);
						flag = 1;
					}
				}
				System.out.printf("%s",flag == 1 ? "" : "Wrong serial number etered\n");
			}break;
			
			case "5":{
				System.out.println(manager.store.getOutlet().removeFromStock() ? "Outletm items have been removed from store successfully" : "Outlet section is empty");
			}break;
			
			case "6":{
				System.out.println("Enter supplier's file name:");
				String filePath = c.nextLine();
				File f = new File(filePath);
				try {
					Scanner s = new Scanner(f);
					if(f.exists()) {
						manager.store.importFromSupplier(s);
						System.out.println("Products imported successfully");
					}
				}
				catch(FileNotFoundException e) {
					System.out.println("Failed to load file");
				}
			}break;
			
			case "7":{
				System.out.println("Name the file:");
				String name = c.nextLine();
				try {
					PrintWriter pw = new PrintWriter(name);
					manager.store.getProductsReport(pw);
					System.out.println("Products report created");
					pw.close();
				}
				catch(FileNotFoundException e) {
					System.out.println("Could not create file");
				}
			}
			}
			if(selection.equals("8") != true) {
				System.out.println("Press Enter to continue...");
				c.nextLine();
			}
		}while(selection.equals("8") != true);
	}
	
	//CART MANAGEMENT
	public static void manageCart(Customer customer, Store store, Scanner c) {
		String selection;
		
		do {
			System.out.println("-------------- CART --------------");
			System.out.println("1 - View bill");
			System.out.println("2 - View cart");
			System.out.println("3 - Remove product from cart");
			System.out.println("4 - Empty cart");
			System.out.println("5 - Show total cost");
			System.out.println("6 - Show total savings");
			System.out.println("7 - Add product to cart");
			System.out.println("8 - Make payment");
			System.out.println("9 - Exit cart");
			System.out.println("----------------------------------");
			System.out.println("Select your choice:");
			selection = c.nextLine();
			
			switch(selection) {
			case "1":{
				System.out.println(customer.viewBill());
			}break;
			case "2":{
				System.out.println(customer.getCart().viewProducts());
			}break;
			case "3":{
				System.out.println(customer.getCart().viewProducts());
				System.out.println("Type in product's serial number you would like to remove from cart");
				String serial = c.nextLine();
				int product = serial.matches("[0-9]+") == false ? 0 : Integer.parseInt(serial);
				System.out.println(customer.removeFromCart(product) ? "Product removed successfully" : "Serial number entered does not exist within cart");
			}break;
			case "4":{
				customer.getCart().emptyCart();
			}break;
			case "5":{
					System.out.println("Total value of cart: " + customer.getCart().calcPrice());
			}break;
			case "6":{
				System.out.println("Total savings with cart: " + customer.getCart().calcDiscount());
			}break;
			case "7":{
				System.out.println(store.viewStoreProducts());
				System.out.println(store.getOutlet().viewStoreProducts());
				System.out.println("Type in product's serial number you would like to add into the cart");
				String serial = c.nextLine();
				int product = serial.matches("[0-9]+") == false ? 0 : Integer.parseInt(serial);
				int flag = 0;
				for(int i=0; i < store.getProductsCounter(); i++) {
					if(product == store.getProducts()[i].getCatalogNumber()) {
						flag = 1;
						System.out.println(customer.addToCart(store.getProducts()[i]) == true ? "Product added successfully" : "Cart is already full");
						break;
					}
				}
				for(int i=0; i < store.getOutlet().getProductsCounter(); i++) {
					if(product == store.getOutlet().getProducts()[i].getCatalogNumber()) {
						System.out.println(customer.addToCart(store.getOutlet().getProducts()[i]) == true ? "Product added successfully" : "Cart is already full");
						flag = 1;
						break;
					}
				}
				System.out.printf("%s\n", flag == 0 ? "Serial number entered does not exist": "");
			}break;
			case "8":{
				//view bill only if cart is not empty
				if(customer.getCart().getProductsCounter() > 0) {
					System.out.println(customer.viewBill());
					System.out.println("Enter amount to be charged:");
					float payment = c.nextFloat();
					c.nextLine();
					System.out.println(customer.payment(payment, store) ? "Payment was processed successfully" : "Failed to process payment");
				}
				else {
					System.out.println("Cart is empty");
				}
			}break;
			case "9":{
				break;
			}
			default:{
				System.out.println("Invalid selection insereted");
			}break;
			}
			
			if(selection.equals("9") != true) {
				System.out.println("Press Enter to continue...");
				c.nextLine();
			}
		}while(selection.equals("9") != true);
	}
	
	//MANAGER'S MENU
	public static void manager(Manager manager, Scanner c) {
		
		String selection;
		
		do {
			System.out.println("-------------- MENU --------------");
			System.out.println("1 - Remove customer");
			System.out.println("2 - Add carts");
			System.out.println("3 - Remove carts");
			System.out.println("4 - View online customers");
			System.out.println("5 - View taken carts");
			System.out.println("6 - View daily incomes report");
			System.out.println("7 - View daily products report");
			System.out.println("8 - Edit store products");
			System.out.println("9 - Log out");
			System.out.println("----------------------------------");
			System.out.println("Select your choice:");
			selection = c.nextLine();
			
			switch(selection) {
			case "1":{
				//find customer in list
				for(int i=0; i < manager.store.getCustomersCounter(); i++) {
					System.out.println(manager.store.getCustomers()[i].getUsername());
				}
				System.out.println("Enter customer's username for removal:");
				String username = c.nextLine();
				System.out.println(manager.store.removeCustomer(username) ? "Customer removal succedded" : "Wrong username entered");
			}break;
			
			case "2":{
				//type in amount of carts to add
				System.out.println("Enter amount of carts to add:");
				String carts = c.nextLine();
				int cartsToAdd = carts.matches("[0-9]+") == false ? 0 : Integer.parseInt(carts);
				int i;
				for(i=0; i < cartsToAdd; i++) {
					//check if reached limit amount of carts
					if(manager.store.addCart() == false) {
						System.out.println("Entered amount of carts can't be added to store. Limit amount of carts reached");
						break;
					}
				}
				System.out.println(i + " carts have been added to store");
			}break;
			
			case "3":{
				System.out.println("Enter amount of carts to remove:");
				String carts = c.nextLine();
				int cartsToRemove = carts.matches("[0-9]+") == false ? 0 : Integer.parseInt(carts);
				int i;
				for(i=0; i < cartsToRemove; i++) {
					//check if reached 0 carts
					if(manager.store.removeCart() == false) {
						System.out.println("Entered amount of carts can't be removed from store. 0 carts remaining");
						break;
					}
				}
				System.out.println(i + " carts have been removed from store");
			}break;
			
			case "4":{
				System.out.println("Customers online: " + manager.store.activeCustomers());
			}break;
			
			case "5":{
				System.out.println("Carts taken: " + manager.store.cartsInUse());
			}break;
			
			case "6":{
				System.out.println("Total incomes today: " + manager.store.getDailyIncome() + " $");
			}break;
			
			case "7":{
				System.out.println("Total products sold today: " + manager.store.getProductsSold());
			}break;
			
			case "8":{
				editProducts(manager, c);
			}break;
			
			case "9":{
				manager.logout();
			}break;
			
			default:{
				System.out.println("Invalid selection insereted");
			}break;
			}
			
			if(selection.equals("9") != true && selection.equals("8") != true) {
				System.out.println("Press Enter to continue...");
				c.nextLine();
			}
		}while(selection.equals("9") != true);
	}
	
	//CUSTOMER'S MENU
	public static void customer(Customer customer, Store store, Scanner c) {
		
		String selection;
		
		do {
			System.out.println("-------------- MENU --------------");
			System.out.println("1 - Manage profile");
			//view "free cart" if customer holds a cart or "take cart" if doesn't
			System.out.println("2 - " + (customer.getCart() != null ? "Free cart" : "Take cart"));
			System.out.println("3 - View store products");
			System.out.println("4 - View outlet products");
			System.out.println("5 - Log out");
			System.out.printf("%s", customer.getCart() != null ? "6 - Manage cart\n" : "");
			System.out.println("----------------------------------");
			System.out.println("Select your choice:");
			selection = c.nextLine();
			
			switch(selection) {
			case "1":{
				//go to profile management
				manageProfile(customer, c);
			}break;
			case "2":{
				//check if customer holds a cart
				if(customer.getCart() != null) {
					System.out.println(customer.freeCart() == true ? "Cart left successfully" : "No cart is being held");
				}
				else {
					for(int i=0; i < store.getCartsCounter(); i++) {
						if(store.getCarts()[i].getCartHolder() == null) {
							System.out.println(customer.takeCart(store.getCarts()[i]) ? "Cart taken successfully" : "No cart available or a cart is already being held");
							break;
						}
					}
				}
			}break;
			case "3":{
				System.out.println(store.viewStoreProducts());
			}break;
			
			case "4":{
				System.out.println(store.getOutlet().viewStoreProducts());
			}break;
			case "5":{
				//log out only if not holding a cart
				if(customer.getCart() == null) {
					customer.logout();
				}
				else {
					System.out.println("Cannot log out while holding a cart");
					selection = "";
				}
			}break;
			case "6":{
				//let customer go to cart management only if a cart is being held
				if(customer.getCart() != null) {
					manageCart(customer, store, c);
					break;
				}
			}
			default:{
				System.out.println("Invalid selection insereted");
			}break;
			}
			
			if(selection.equals("5") != true && selection.equals("1") != true && selection.equals("6") != true) {
				System.out.println("Press Enter to continue...");
				c.nextLine();
			}
		}while(selection.equals("5") != true);
	}
	
	//MAIN
	public static void main(String[] argv) {
		
		Store store = new Store("shop");
		
		for(int i=0; i < 50; i++) {
			store.addCart();
		}
		//hire a manager for store
		store.hireManager("manager", "123");
		
		store.addProduct("banana", 10, 10);
		store.addProduct("kiwi", 20, 10);
		store.addProduct("orange", 10, 10);
		store.addProduct("apple", 10, 10);
		
		Scanner s = new Scanner(System.in);
		String selection;
		
		do {
			System.out.println("-------------- MENU --------------");
			System.out.println("1 - Login");
			System.out.println("2 - Sign Up");
			System.out.println("3 - Exit store");
			System.out.println("----------------------------------");
			
			selection = s.nextLine();
			
			switch(selection) {
			//login
			case "1":{
				int flag = 0;
				System.out.println("Enter username: ");
				String username = s.nextLine();
				System.out.println("Enter password: ");
				String password = s.nextLine();
				for(int i=0; i < store.getCustomersCounter(); i++) {
					//look for a matching customer
					if(store.getCustomers()[i].login(username, password) == true) {
						flag = 1;
						customer(store.getCustomers()[i], store, s);
						break;
					}
				}
				//check if manager is trying to login
				if(store.getStoreManager().login(username, password) == true) {
					flag = 1;
					manager(store.getStoreManager(), s);
				}
				if(flag == 0) {
					System.out.println("Invalid username or password entered");
				}
			}break;
				
			//sign up	
			case "2":{
				int flag;
				String username;
				//check if username already exists
				do {
					flag = 1;
					System.out.println("Enter username: ");
					username = s.nextLine();
					for(int i=0; i < store.getCustomersCounter(); i++) {
						if(store.getCustomers()[i].getUsername().equals(username)) {
							System.out.println("Entered username is already in use!");
							flag = 0;
							break;
						}
					}
					System.out.printf("%s", store.getStoreManager().getUsername().equals(username) ? "Entered username is already in use!\n" : "");
				}while(flag == 0 || store.getStoreManager().getUsername().equals(username));
				System.out.println("Enter password: ");
				String password = s.nextLine();
				System.out.println("Enter shipping address: ");
				String address = s.nextLine();
				while(address.equals("")) {
					System.out.println("Enter a valid shipping address");
					address = s.nextLine();
				}
				String phone;
				//validate phone number
				do {
					System.out.println("Enter a valid phone number: ");
					phone = s.nextLine();
				}while(phone.matches("[0-9]+") == false || phone.length() != 10);
				store.signUp(username, password, address, phone);
				System.out.println("You have successfully signed up");
			}break;
			
			case "3":{
				System.out.println("Thank you for shopping with us!");
			}break;
			default:{
				System.out.println("Invalid selection insereted");
			}break;
			
			}
		}while(selection.equals("3") != true);
		s.close();
	}
}