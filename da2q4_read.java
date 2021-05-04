import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.regex.*;  
import java.util.ArrayList;

class da2q4_read{
    public static void drawline(String symbol){
        for(int i = 0; i < 50; i++){
            System.out.print(symbol);
        }
        System.out.println("");
    }
    public static void main(String[] args) throws Exception{
        Pattern p = Pattern.compile("[1][9]([A-Z]){3}[0-9]{4}"); 
        ArrayList<Student> array = new ArrayList<>();
        FileInputStream fin = new FileInputStream("student_details.txt");
        ObjectInputStream in = new ObjectInputStream(fin);
        
        try{
            array = (ArrayList<Student>)in.readObject();
            drawline("*");
            System.out.println("The students that graduate in 2023 or joined UG in 2019 are:");
        } catch (FileNotFoundException e){
            e.getStackTrace();
        }
        for (int i = 0; i < 3; i++) {
            Student student = array.get(i);
            Matcher m = p.matcher(student.get_regno());  
            boolean matches = m.matches();  
            if(matches){
                drawline("_");
                System.out.println("Name: " + student.name);
                System.out.println("Age: " + student.age);
                System.out.println("Registration number: " + student.regno);
                System.out.println("Phone: " + student.phone);
                System.out.println("Address: " + student.address);
            }
        }
        in.close();
    }
}
