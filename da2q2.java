// ead the aadhar number and Mobile Number of a employee.
// fun1 If the aadhar number does not contain exactly 12 characters or if the Mobile Number does not contain exactly 10 characters, throw an IllegalArgumentException.
// fun2 If the Mobile Number contains any character other than a digit, raise a NumberFormatException.
// If the aadhar number contains any character other than digits and alphabets, throw a NoSuchElementException.
// If they are valid, print the message ‘valid’ else ‘invalid’. Write a java program for the above scenario with an appropiate exceptions.

class NoSuchElementException extends Exception{
    NoSuchElementException(String s){
        super(s);
    }
}
class Employee{
    String phone;
    String aadhar;
    Employee(String phone, String aadhar){
        this.phone = phone;
        this.aadhar = aadhar;
    }
    public void check_aadhar_character() throws NoSuchElementException{
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] valid_characters = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        System.arraycopy(alphabet, 0, valid_characters, 0, alphabet.length);
        int count = 0;
        char[] aadhar_array = aadhar.toCharArray();
        for (char c : aadhar_array) {
            for (char d : valid_characters) {
                if(c == d){
                    count += 1;
                }
            }
        }
        if(count != aadhar.length()){
            throw new NoSuchElementException("There are invalid characters in your aadhar");
        }
    }
    public void validate_aadhar(){
        if(aadhar.length() != 12){
            throw new IllegalArgumentException("Your aadhar number does not have 12 characters");
        } else {
            try{
                check_aadhar_character();
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }
    public void validate_phone(){
        if(phone.length() != 10){
            throw new IllegalArgumentException("Your phone number does not have 10 characters");
        } else {
            char[] phone_array = phone.toCharArray();
            for (char c : phone_array) {
                if(!Character.isDigit(c)){
                    throw new NumberFormatException("There are non-digits in the phone number");
                }
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
        Employee e1 = new Employee("1234110123", "@11111111111");
        try{
            e1.validate_aadhar();
            e1.validate_phone();
        } catch(Exception e){
            // Print the error
            System.out.println(e);
        }
    }
}
