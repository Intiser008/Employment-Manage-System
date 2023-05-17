
public class ExLeavesOverlap extends Exception{
    public ExLeavesOverlap(String exMsg){
        super("Leave overlapped: The leave period " + exMsg + " is found!");
    }
}
