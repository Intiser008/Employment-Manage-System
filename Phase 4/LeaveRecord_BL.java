public class LeaveRecord_BL extends LeaveRecord{
    public LeaveRecord_BL(String startDate, String endDate) throws ExLeaveBeforeStartDate { super(startDate, endDate); }

    @Override
    public String toString() { return super.getLeaveRange() + " [BL]";}
}
