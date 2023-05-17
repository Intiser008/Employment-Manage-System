
public class ExLeaveBeforeStartDate extends Exception{
    public ExLeaveBeforeStartDate(String sysDate){
        super("Wrong Date. Leave start date must not be earlier than the system date (" + sysDate + ")!");
    }
}
