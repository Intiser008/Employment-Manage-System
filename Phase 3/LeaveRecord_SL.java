public class LeaveRecord_SL extends LeaveRecord{
    public LeaveRecord_SL(String startDate, String endDate) throws ExLeaveBeforeStartDate { super(startDate, endDate); }

    @Override
    public String toString() { return super.getLeaveRange() + " [SL]";}
}
