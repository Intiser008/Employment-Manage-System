public class LeaveRecord_NL extends LeaveRecord {
    public LeaveRecord_NL(String startDate, String endDate) throws ExLeaveBeforeStartDate { super(startDate, endDate); }

    @Override
    public String toString() { return super.getLeaveRange() + " [NL]";}
}
