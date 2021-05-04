import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.regex.*;  

class da2q4_read{
    public static void drawline(String symbol){
        for(int i = 0; i < 50; i++){
            System.out.print(symbol);
        }
        System.out.println("");
    }
    public static void main(String[] args) throws Exception{
        Pattern p = Pattern.compile("[1][9]([A-Z]){3}[0-9]{4}"); 

        FileInputStream fin = new FileInputStream("something.txt");
        ObjectInputStream in = new ObjectInputStream(fin);

        for (int i = 0; i < 3; i++) {
            Student s1 = (Student)in.readObject();
            Matcher m = p.matcher(s1.get_regno());  
            boolean matches = m.matches();  
            if(matches){
                System.out.println(s1.name);
            }
        }
        
        in.close();
    }
}
