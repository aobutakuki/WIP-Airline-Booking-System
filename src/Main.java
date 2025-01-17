public class Main {

    public static void main(String[] args) {
    Airline myPlane = new Airline();


        System.out.println(myPlane.airplaneModel[0][0] + " is " + myPlane.airplaneModel[0][1]);
        myPlane.seatMapping(350,"737");
    }
}
