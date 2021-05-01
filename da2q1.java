import java.util.Scanner;

class InvalidOutTIme extends Exception{
    InvalidOutTIme(String s){
        super(s);
    }
}
class Price{
    static float car_base = 100;
    static float car_hourly = 30;
    static float two_wheeler_base = 40;
    static float two_wheeler_hourly = 10;
    public static float get_base(char c){
        if(c == 'C' || c == 'c'){
            return car_base;
        } else {
            return two_wheeler_base;
        }
    }
    public static float get_hourly_price(char c){
        if(c == 'C' || c == 'c'){
            return car_hourly;
        } else {
            return two_wheeler_hourly;
        }
    }
}
class Vehicle{
    char vehicle_type;
    Hour_glass in_time;
    Hour_glass out_time;
    int total_time;
    Vehicle(char vehicle_type, Hour_glass in_time, Hour_glass out_time){
        this.vehicle_type = vehicle_type;
        this.in_time = in_time;
        this.out_time = out_time;
        total_time = out_time.elapsedTime(in_time);
    }
    public float get_bill(){
        float price = 0;
        float buffer_time = total_time;
        Double x = Math.ceil(buffer_time/60);
        if(x > 3){
            x -= 3;
            price += Price.get_base(vehicle_type);
            price += x * Price.get_hourly_price(vehicle_type);
        } else {
            if(total_time != 0){
                price += Price.get_base(vehicle_type);
            } else {
                price = 0;
            }
            
        }
        return price;
    }
}
class Hour_glass{
    String time;
    int hour;
    int minute;
    Hour_glass(String time){
        this.time = time;
        hour = Integer.parseInt(time.substring(0, 2));
        minute = Integer.parseInt(time.substring(3));
    }
    public int isGreaterThan(Hour_glass t2){
        // return 1 if greater, 0 if equal and -1 if lesser than
        if(t2.hour > hour){
            return -1;
        } else if (t2.hour == hour){
            if(t2.minute > minute){
                return -1;
            } else if(t2.minute == minute){
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
    public int elapsedTime(Hour_glass t2){
        int elapsed_time = 0;
        if(this.isGreaterThan(t2) == 1){
            int x = hour - t2.hour; //5
            int y = minute - t2.minute; //-5
            x = x * 60;//300
            if(y > 0){
                x -= 60;
                x = x + 60-(y*-1);
            } else {
                x = x + y;
            }
            elapsed_time = x;
        }
        System.out.println("Elapsed time: "+ elapsed_time + " minutes");
        return elapsed_time;
    }
}
class da2q1{
    public static void validate_out_time(Hour_glass out_time, Hour_glass in_time) throws InvalidOutTIme{
        if(out_time.isGreaterThan(in_time) < 1){
            throw new InvalidOutTIme("Incorrect Out-time. Perhaps, you have entered IN-time instead of OUT-time?");
        }
    }
    public static void drawline(String symbol){
        for(int i = 0; i < 50; i++){
            System.out.print(symbol);
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        drawline("*");
        System.out.print("Enter the number of vehicles: ");
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Vehicle "+ (i+1)+ ") ");
            System.out.print("Enter the type of vehicle: ");
            char type = input.next().charAt(0);
            System.out.print("Enter the in-time in 24Hr format (hh mm): ");
            input.nextLine();
            Hour_glass in_time = new Hour_glass(input.nextLine());
            System.out.print("Enter the out-time in 24Hr format (hh mm): ");
            Hour_glass out_time = new Hour_glass(input.nextLine());
            drawline("_");
            try{
                validate_out_time(out_time, in_time);
            } catch (Exception timeswap){
                System.out.println(timeswap.getMessage());
                Hour_glass temp = out_time;
                out_time = in_time;
                in_time = temp;
            } finally{
                Vehicle vehicle = new Vehicle(type, in_time, out_time);
                float bill = vehicle.get_bill();
                System.out.println("Bill: " + bill);
                drawline("_");
            }
        }
        input.close();
    }
}