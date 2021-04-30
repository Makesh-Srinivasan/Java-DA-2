import java.util.Scanner;

class NoSuchElementException extends Exception{
    NoSuchElementException(String s){
        super(s);
    }
}
class Employee{
    String phone;
    String aadhar;
    boolean valid = true;
    Employee(String phone, String aadhar){
        this.phone = phone;
        this.aadhar = aadhar;
    }
    public void validate_3() throws NoSuchElementException{
        char[] aadhar_array = aadhar.toCharArray();
        for (char c : aadhar_array) {
            if(!(Character.isDigit(c) || Character.isAlphabetic(c))){
                valid = false;
                throw new NoSuchElementException("NoSuchElementException: There are invalid characters in your aadhar");
            }
        }
    }
    public void validate_1(){
        if(aadhar.length() != 12 || phone.length() != 10){
            valid = false;
            throw new IllegalArgumentException("IllegalArgumentException: Incorrect number of characters");
        }
    }
    public void validate_2(){
        char[] phone_array = phone.toCharArray();
        for (char c : phone_array) {
            if(!Character.isDigit(c)){
                valid = false;
                throw new NumberFormatException("NumberFormatException: There are non-digits in the phone number");
            }
        }
    }
}
class da2q2{
    public static void drawline(String symbol){
        for(int i = 0; i < 50; i++){
            System.out.print(symbol);
        }
        System.out.println("");
    }
    public static void main(String args[]){
        System.out.println("");
        drawline("*");
        int n = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of employees: ");
        n = input.nextInt();
        Employee[] emploee = new Employee[n];
        for (int i = 0; i < emploee.length; i++) {
            String phone, aadhar;
            System.out.println("\nEmployee " + (i+1) + ")");
            System.out.print("Enter phone number: ");
            phone = input.next();
            System.out.print("Enter aadhar number: ");
            aadhar = input.next();
            emploee[i] = new Employee(phone, aadhar);
        }
        drawline("_");
        System.out.println("\nVALIDATION:\n");
        for (int i = 0; i < emploee.length; i++) {
            System.out.println("Employee " + (i+1) +": ");
            try{
                emploee[i].validate_1();
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
            try{
                emploee[i].validate_2();
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
            try{
                emploee[i].validate_3();
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
            if(emploee[i].valid){
                System.out.println("Valid");
            } else {
                System.out.println("Invalid");
            }
            drawline("_");
        }
        input.close();
    }
}
//emploee[0] = new Employee("12341l3", "@no8g8g");
// emploee[1] = new Employee("12345678900", "12123lqno8g8g");
// emploee[2] = new Employee("123456789w", "12123lno8g8g");
// emploee[3] = new Employee("1234567890", "12123@no8g8g");
// emploee[4] = new Employee("1234567890", "12123qno8g8g");