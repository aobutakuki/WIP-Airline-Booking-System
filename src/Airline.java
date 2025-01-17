import java.util.*;
import java.io.*;
import java.util.regex.*;



public class Airline {

    String[][] airplaneModel = {{"737","350"},{"A350","300"}};

    int emptySeats;
    double price;

    //Client
    String clientName;
    int clientAge;
    int reservationNumber;



    public static void seatMapping(int seatNumber,String seatConfiguration)
    {
        if(seatConfiguration.equals("737"))
        {
            int[] seatRow = new int[45];
            String[] seatColumn = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I","X"};

            String[][] seat = new String[seatRow.length][seatColumn.length];
            // X X X O X X X O X X X (315)
int totalSection = seatNumber -  35;
int rowNumber = totalSection / 9;



for(int i = 0; i < rowNumber; i++)
{
    seat[i][0] = String.valueOf(i + 1);
    for(int j = 1; j < 10; j++)
    {
        seat[i][j] = seatColumn[j - 1];
    }
}

             // X X O X X (24)

            rowNumber = 6;

            for(int i = 36; i < 35 + rowNumber; i++)
            {
                seat[i][0] = String.valueOf(i + 1);
                for(int j = 1; j < 5; j++)
                {
                    seat[i][j] = seatColumn[j - 1];
                }
            }


            // X O X (11 - 3)

            rowNumber =  4;

            for(int i = 42; i < 41 + rowNumber; i++)
            {
                seat[i][0] = String.valueOf(i + 1);
                for(int j = 1; j < 3; j++)
                {
                    seat[i][j] = seatColumn[j - 1];
                }
            }

            
            
            switch(userMenu())
            {
                case 1:
                    reserveSeat(seat);
                    break;
                case 9:
                    System.out.println("QUITTING...");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + userMenu());
            }
            
  
            
        //IF END
        }

        //FUNC END
    }





static void reserveSeat(String[][] seat)
{
    System.out.print("\033[H\033[2J");
    System.out.flush();

    //Color Reset
    final String RESET = "\033[0m";

    // Text colors
    final String RED = "\033[0;31m";

    while(true) {
        System.out.println("\n\n\n");
        System.out.println("RESERVATION MENU\n");

        System.out.println("[1] SEE SEAT MAP");
        System.out.println("[2] RESERVE SEAT");
        System.out.println("[3] BACK");

        Scanner scanner = new Scanner(System.in);
        System.out.println(">:");
        int userInput = scanner.nextInt();


        //SEAT MAP
        File seatMap = openFile();
        if(userInput == 1)
        {




            System.out.println("\nECONOMY CLASS\n");
            for (int i = 0; i < 35; i++) {
                for (int j = 1; j < 10; j++) {
                    if (j == 3 || j == 6) {
                        if(checkSeat(i,j,seatMap,seat))
                        {
                            System.out.print(RED + seat[i][0] + seat[i][j] + "   " + RESET);
                        }else {
                            System.out.print(seat[i][0] + seat[i][j] + "   ");
                        }
                    } else {
                        if(checkSeat(i,j,seatMap,seat))
                        {
                            System.out.print(RED + seat[i][0] + seat[i][j] + " " + RESET);
                        }else {
                            System.out.print(seat[i][0] + seat[i][j] + " ");
                        }
                    }
                }
                System.out.println();
            }

            System.out.println("\nEXECUTIVE CLASS\n");
            for (int i = 36; i < 35 + 6; i++) { // 6 rows for Executive Class
                for (int j = 1; j < 5; j++) {
                    if (j == 2) { // Additional spacing logic for column 2
                        if (checkSeat(i, j, seatMap, seat)) {
                            System.out.print(RED + seat[i][0] + seat[i][j] + "   " + RESET);
                        } else {
                            System.out.print(seat[i][0] + seat[i][j] + "   ");
                        }
                    } else {
                        if (checkSeat(i, j, seatMap, seat)) {
                            System.out.print(RED + seat[i][0] + seat[i][j] + " " + RESET);
                        } else {
                            System.out.print(seat[i][0] + seat[i][j] + " ");
                        }
                    }
                }
                System.out.println();
            }

            System.out.println("\nFIRST CLASS\n");
            for (int i = 42; i < 41 + 4; i++) { // 4 rows for First Class
                for (int j = 1; j < 3; j++) {
                    if (j == 1) { // Additional spacing logic for column 1
                        if (checkSeat(i, j, seatMap, seat)) {
                            System.out.print(RED + seat[i][0] + seat[i][j] + "   " + RESET);
                        } else {
                            System.out.print(seat[i][0] + seat[i][j] + "   ");
                        }
                    } else {
                        if (checkSeat(i, j, seatMap, seat)) {
                            System.out.print(RED + seat[i][0] + seat[i][j] + " " + RESET);
                        } else {
                            System.out.print(seat[i][0] + seat[i][j] + " ");
                        }
                    }
                }
                System.out.println();
            }


            //IF END
        }





        if(userInput == 2) {
            System.out.println("\nSEAT RESERVATION MENU\n");
            System.out.println("TYPE THE SEAT YOU WOULD LIKE TO RESERVE:");

            Scanner seatScanner = new Scanner(System.in);
            String inputSeat = seatScanner.nextLine();

            Pattern pattern = Pattern.compile("([0-9]+)([A-Za-z])");
            Matcher matcher = pattern.matcher(inputSeat);

            if (matcher.matches())
            {

            String number = matcher.group(1);
            String letter = matcher.group(2);

                //System.out.println(number + " -- " + letter);

            int i = Integer.parseInt(number) - 1;
            int j = letterToArray(letter) + 1;

                //System.out.println("i = " + i + " j = " + j);
                System.out.println("SEAT: " + seat[i][0] + seat[i][j]);
                String exactSeat = seat[i][0] + seat[i][j];

                if(checkSeat(i,j,seatMap,seat))
                {
                    System.out.println("SEAT ALREADY TAKEN PLEASE SELECT ANOTHER SEAT!\n");

                }
                else if (!checkSeat(i,j,seatMap,seat))
                {
                    //CONFIRMATION MESSAGE [>=36 && 42< (EXECUTIVE CLASS) || >=42 (FIRST CLASS)]
                    System.out.println("SEAT AVAILABLE");

                    int fClass = checkClass(i);
                    // 1 - Economy || 2 - Excecutive || 3 - First Class

                    //PRICING
                    double economy = 230;
                    double executive = 546.5;
                    double firstClass = 1230.8;

                    Boolean seatBooking;
                    switch(fClass)
                    {
                        case 1:
                            System.out.println("Economy Seat Fare: " + economy);
                            System.out.println("Would you like to book the seat?");
                            System.out.println("[Y] to confirm // [N] to cancel");

                            Scanner confirmationScan = new Scanner(System.in);
                            char c = confirmationScan.next().charAt(0);

                            if(c == 'Y')
                            {
                                System.out.println("Booking Seat...");
                                BookSeat(exactSeat,fClass,seatMap);
                               break;
                            }
                            if(c == 'N')
                            {
                                System.out.println("CANCELING");
                                break;
                            }





                    }


                    //WRITE TO FILE
                    //GENERATE RESERVATION NUMBER


                }


            }


            //IF END
        }





        //WHILE END
    }
    //FUNC END
}

static int userMenu()
{
    System.out.println("\n USER MENU");
    System.out.println("[1] RESERVE SEAT");
    System.out.println("[2] CHECK OR CHANGE RESERVATION");
    System.out.println("[3] UPGRADE SEAT");
    System.out.println("\n[9] QUIT");

    System.out.println(">:");
    Scanner scanner = new Scanner(System.in);
    int userInput = scanner.nextInt();

    return switch (userInput) {
        case 1 -> 1;
        case 2 -> 2;
        case 3 -> 3;
        case 9 -> 9;
        default -> {
            System.out.println("Invalid User Input");
            yield 0;
        }
    };
    
    //FUNC END
}

static public File openFile()
{
    try {
        File myObj = new File("seatmap.csv");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
            return myObj;
        } else {
            System.out.println("File already exists.");
            return myObj;
        }
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }

return null;
}

//WILL RETURN FALSE IF NO SEAT IS FOUND
static public Boolean checkSeat(int i, int j, File seatMap,String[][] seat) {

    //System.out.println("Checking Seat.. " + seat[i][0] + seat[i][j]);
    Set<String> bookedSeats = new HashSet<>();
    try (BufferedReader br = new BufferedReader(new FileReader(seatMap))) {
        String line;
        while ((line = br.readLine()) != null) {
            bookedSeats.add(line.replace(",", "").trim());
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
        return false;
    }

    // Check if the seat is in the booked set
    if (bookedSeats.contains(seat[i][0] + seat[i][j])) {
        //System.out.println("Booked Seat Found!");
        return true;
    } else {
        return false;
    }
    // FUNC END
}

static int letterToArray(String letter)
{
    if(letter.equals("A"))
    {
        return 0;
    }
    if(letter.equals("B"))
    {
        return 1;
    }
    if(letter.equals("C"))
    {
    return 2;
    }
    if(letter.equals("D"))
    {
        return 3;
    }
    if(letter.equals("E"))
    {
        return 4;
    }
    if(letter.equals("F"))
    {
        return 5;
    }
    if(letter.equals("G"))
    {
        return 6;
    }
    if(letter.equals("H"))
    {
        return 7;
    }
    if(letter.equals("I"))
    {
        return 8 ;
    }
    else {
        return 10;
    }



}

static int checkClass(int row)
{
if(row < 36)
{
    return 1;
}
if((row >= 36) && (row < 42))
{
    return 2;
}
 if(row >= 42)
 {
     return 3;
 }

 return 10;
}

static void BookSeat(String seat,int fClass, File seatmap)
{
    try {
        FileWriter seatmapWriter = new FileWriter(seatmap,true);
        seatmapWriter.write(seat + ",\n");
        seatmapWriter.close();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    //CHECK IF reservationkey.csv ALREADY EXIST, IF NOT CREATE
    try {
        File myObj = new File("reservationkey.csv");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());

        } else {
            System.out.println("File already exists.");
        }
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }

    //RESERVATION NUMBER GENERATION
    Random rand = new Random();
    int upperbound = 10000;

    int int_random = rand.nextInt(upperbound);
    int int_random1 = rand.nextInt(upperbound);

String reservationkey;

    System.out.println("fClass: " + fClass);
switch(fClass)
{
    case 1:
        reservationkey = "ECO" + int_random + int_random1;
        break;
    case 2:
        reservationkey = "EXEC" + int_random1 + int_random;
        break;
    case 3:
        reservationkey = "FRST" + int_random + int_random1;
        break;

    default:
        reservationkey = "ERROR";
}




    try {
        FileWriter seatmapWriter = new FileWriter("reservationkey.csv",true);
        seatmapWriter.write(reservationkey + ",\n");
        seatmapWriter.close();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("YOUR RESERVATION KEY IS: [" + reservationkey + "]");
    System.out.println("DO NOT LOSE OR SHARE IT WITH ANYONE");

}


//CLASS END
}
