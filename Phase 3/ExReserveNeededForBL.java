
public class ExReserveNeededForBL extends Exception{
    public ExReserveNeededForBL(int annualLeaves){
        System.out.println("The annual leave is invalid.");
        System.out.println("The current balance is " + annualLeaves + " days only.");
        System.out.println("The employee has not taken any block leave yet.");
        System.out.println("The employee can take at most " + (annualLeaves - 7) + " days of non-block annual leave because of the need to reserve 7 days for a block leave.");
    }
}
