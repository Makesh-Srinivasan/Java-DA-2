import java.util.Scanner;
class da2q3 {

    public static void drawline(String symbol){
        for(int i = 0; i < 50; i++){
            System.out.print(symbol);
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        int running = 1;
        Scanner input = new Scanner(System.in);
        drawline("*");
        System.out.println("Available logical operations in this calculator");
        System.out.println("AND, OR, NOT, XOR, NAND, NOR, XNOR");
        drawline("_");
        while(running == 1){
            System.out.print("Enter operand 1: ");
            String op1 = input.next();

            System.out.print("Enter operand 2: ");
            String op2 = input.next();

            System.out.print("Enter Logical operator: ");
            String operator = input.next();

            if(op1.contains(".") || op2.contains(".")){
                double x = Double.parseDouble(op1);
                double y = Double.parseDouble(op2);
                Calculator<Double, String> ob = new Calculator<>(x,y,operator);
                System.out.print("Calculating "+ob.get_operator()+" operation between "+op1+" (");
                System.out.println(ob.get_op1_type(x)+") and "+op2+" ("+ob.get_op2_type(y)+")");
                System.out.println("\nGeneric calculator returns "+ob.get_operator()+" operation: ");
                ob.calculate();
            } else {
                int x = Integer.parseInt(op1);
                int y = Integer.parseInt(op2);
                Calculator<Integer, String> ob = new Calculator<>(x,y,operator);
                System.out.print("Calculating "+ob.get_operator()+" operation between "+op1+" (");
                System.out.println(ob.get_op1_type(x)+") and "+op2+" ("+ob.get_op2_type(y)+")");
                System.out.println("\nGeneric calculator returns "+ob.get_operator()+" operation: ");
                ob.calculate();
            }
            System.out.println("");
            drawline("_");
            System.out.print("\nDo you want to calculate more? (y/n)");
            char confirmed = input.next().charAt(0);
            if(confirmed == 'n'){
                running = 0;
            }
        }
        input.close();
    }
}

class Calculator<T extends Number, U> {
    private T operand_1;
    private T operand_2;
    private U operator;
    char[] a;
    char[] b;
    char[] result;
    Calculator(T op1, T op2, U operator) {
        this.operand_1 = op1;
        this.operand_2 = op2;
        this.operator = operator;
        if(operand_1.getClass().getName() == "java.lang.Integer"){
            a = Integer.toBinaryString(operand_1.intValue()).toCharArray();
            b = Integer.toBinaryString(operand_2.intValue()).toCharArray();
        } else {
            a = toBinary(operand_1.doubleValue(), 4).toCharArray();
            b = toBinary(operand_2.doubleValue(), 4).toCharArray();
        }
        result = new char[a.length];
    }
    Calculator(T op, U operator) {
        this.operand_1 = op;
        this.operator = operator;
        if(operand_1.getClass().getName() == "java.lang.Integer"){
            a = Integer.toBinaryString(operand_1.intValue()).toCharArray();
        } else {
            a = toBinary(operand_1.doubleValue(), 4).toCharArray();
        }
        result = new char[a.length];
    }
    public <V> String get_op1_type(T op_1) {
        return op_1.getClass().getName();
    }
    public <V> String get_op2_type(T op_2) {
        return op_2.getClass().getName();
    }
    public U get_operator(){
        return operator;
    }
    public void calculate(){
        char[] res = new char[32];
        if(operator.toString().equals("AND")){
            res = AND(a, b);
        } else if(operator.toString().equals("OR")){
            res = OR(a, b);
        } else if(operator.toString().equals("NOT")){
            res = NOT(a);
        } else if(operator.toString().equals("XOR")){
            res = XOR(a, b);
        } else if(operator.toString().equals("XNOR")){
            res = NOT(XOR(a, b));
        } else if(operator.toString().equals("NOR")){
            res = NOT(OR(a, b));
        } else if(operator.toString().equals("NAND")){
            res = NOT(AND(a, b));
        }
        display(res);
    }
    private void display(char[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
    }
    private char[] AND(char[] a, char[] b) {
        for (int i = 0; i < b.length; i++) {
            if(a[i] == b[i] && a[i] == '1'){
                result[i] = '1';
            } else if(a[i] == '.'){
                result[i] = '.';
            } else {
                result[i] = '0';
            }
        }
        return result;
    }

    private char[] OR(char[] a, char[] b) {
        for (int i = 0; i < a.length; i++) {
            if(a[i] == '1' || b[i] == '1'){
                result[i] = '1';
            } else if(a[i] == '.'){
                result[i] = '.';
            } else {
                result[i] = '0';
            }
        }
        return result;
    }

    private char[] NOT(char[] a) {
        for (int i = 0; i < a.length; i++) {
            if(a[i] == '1'){
                result[i] = '0';
            } else if (a[i] == '.'){
                result[i] = '.';
            } else {
                result[i] = '1';
            }
        }
        return result;
    }

    private char[] XOR(char[] a, char[] b) {
        for (int i = 0; i < b.length; i++) {
            if(a[i] != b[i]){
                result[i] = '1';
            } else if(a[i] == '.'){
                result[i] = '.';
            } else {
                result[i] = '0';
            }
        }
        return result;
    }

    public static String toBinary(double d, int precision) {
        long wp = (long) d;
        return wholeToBinary(wp) + '.' + fractionalToBinary(d - wp, precision);
    }
    
    private static String wholeToBinary(long l) {
        return Long.toBinaryString(l);
    }
    
    private static String fractionalToBinary(double num, int precision) {
        StringBuilder binary = new StringBuilder();
        while (num > 0 && binary.length() < precision) {
            double r = num * 2;
            if (r >= 1) {
                binary.append(1);
                num = r - 1;
            } else {
                binary.append(0);
                num = r;
            }
        }
        return binary.toString();
    }
}
