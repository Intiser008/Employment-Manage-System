public class LeaveRecord_AL extends LeaveRecord{
    public LeaveRecord_AL(String startDate, String endDate) throws ExLeaveBeforeStartDate{
        super(startDate, endDate);
    }
    @Override
    public String toString() { return super.getLeaveRange() + " [AL]";}
}
