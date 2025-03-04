import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class SliceOHeaven {
    public static void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardStr = Long.toString(cardNumber);
        if (cardStr.length() != 14) {
            System.out.println("Invalid card");
            return;
        }
        System.out.println("Card accepted");

        int firstCardDigit = Character.getNumericValue(cardStr.charAt(0));
        long blacklistedNumber = 12345678901234L;
        if (cardNumber == blacklistedNumber) {
            System.out.println("Card is blacklisted. Please use another card");
            return;
        }
        
        String lastFourDigits = cardStr.substring(cardStr.length() - 4);
        StringBuilder cardNumberToDisplay = new StringBuilder();
        cardNumberToDisplay.append(cardStr.charAt(0));
        for (int i = 1; i < cardStr.length() - 4; i++) {
            cardNumberToDisplay.append("*");
        }
        cardNumberToDisplay.append(lastFourDigits);

        System.out.println("Processed card details:");
        System.out.println("First Digit: " + firstCardDigit);
        System.out.println("Last Four Digits: " + lastFourDigits);
        System.out.println("Masked Card Number: " + cardNumberToDisplay);
    }
    
    public static void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        StringBuilder special = new StringBuilder();
        special.append("Today's Special:");
        special.append("\nPizza: ").append(pizzaOfTheDay);
        special.append("\nSide: ").append(sideOfTheDay);
        special.append("\nPrice: ").append(specialPrice);
        System.out.println(special.toString());
    }

    public static void takeOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter three ingredients for your pizza (use spaces to separate ingredients):");
        String ing1 = scanner.next();
        String ing2 = scanner.next();
        String ing3 = scanner.next();
        
        System.out.println("Enter size of pizza (Small, Medium, Large):");
        String pizzaSize = scanner.next();
        
        System.out.println("Do you want extra cheese (Y/N):");
        String extraCheese = scanner.next();
        
        System.out.println("Enter one side dish (Calzone, Garlic bread, None):");
        String sideDish = scanner.next();
        
        System.out.println("Enter drinks (Cold Coffee, Cocoa drink, Coke, None):");
        String drinks = scanner.next();
        
        System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
        String wantDiscount = scanner.next();
        
        if (wantDiscount.equalsIgnoreCase("Y")) {
            isItYourBirthday();
        } else {
            makeCardPayment();
        }
        
        scanner.close();
    }

    public static void isItYourBirthday() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your birthdate (YYYY-MM-DD):");
        String birthdateInput = scanner.next();
        LocalDate birthdate = LocalDate.parse(birthdateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate today = LocalDate.now();
        int age = Period.between(birthdate, today).getYears();

        if (age < 18 && birthdate.getMonth() == today.getMonth() && birthdate.getDayOfMonth() == today.getDayOfMonth()) {
            System.out.println("Congratulations! You pay only half the price for your order.");
        } else {
            System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
        }
        scanner.close();
    }

    public static void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your card number:");
        long cardNumber = scanner.nextLong();
        
        System.out.println("Enter card expiry date (MM/YY):");
        String expiryDate = scanner.next();
        
        System.out.println("Enter card CVV (3 digits):");
        int cvv = scanner.nextInt();
        
        processCardPayment(cardNumber, expiryDate, cvv);
        scanner.close();
    }
    
    public static void main(String[] args) {
        takeOrder();
        specialOfTheDay("Margherita", "Garlic Bread", "$9.99");
    }
}