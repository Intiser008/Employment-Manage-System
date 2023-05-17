
public class ExWillNotBeAvailable extends Exception{
    public ExWillNotBeAvailable(String employeeName, LeaveRecord leave){
        super(employeeName + " is on leave during " + leave.toString() + "!");
    }
}
