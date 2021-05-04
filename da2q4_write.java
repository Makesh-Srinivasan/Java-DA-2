import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;

class Student implements Serializable{
    String name;
    int age;
    String regno;
    String phone;
    String address;

    public Student(String name, int age, String regno, String phone, String address) {
        this.name = name;
        this.age = age;
        this.regno = regno;
        this.phone = phone;
        this.address = address;
    }
    public String get_regno(){
        return regno;
    }
}
class da2q4_write{
    public static void drawline(String symbol){
        for(int i = 0; i < 50; i++){
            System.out.print(symbol);
        }
        System.out.println("");
    }
    public static void main(String[] args) throws Exception{
        int running = 1;
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("something.txt"));
        ArrayList<Student> woi=new ArrayList<>();
        Scanner input = new Scanner(System.in);
        drawline("*");
        while(running >= 1){
            System.out.println("Student " + running + ") ");
            System.out.print("Enter name: ");
            String name = input.next();

            System.out.print("Enter age: ");
            int age = input.nextInt();

            System.out.print("Enter registration number: ");
            String regno = input.next();

            System.out.print("Enter phone number: ");
            String phone = input.next();

            System.out.print("Enter address: ");
            String address = input.next();

            Student student = new Student(name, age, regno, phone,address);
            woi.add(student);
            // woi.flush();
            out.writeObject(woi);
            out.close();
            System.out.println("");
            drawline("_");
            System.out.print("\nDo you want to calculate more? (y/n)");
            char confirmed = input.next().charAt(0);
            if(confirmed == 'n'){
                running = 0;
            } else {
                running += 1;
            }
        }
        
        input.close();
    }
}
