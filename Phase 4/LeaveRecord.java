public abstract class LeaveRecord implements Comparable<LeaveRecord>{
    private Day leaveStart;
    private Day leaveEnd;

    public LeaveRecord(String startDate, String endDate) throws ExLeaveBeforeStartDate{
        if ((new Day(startDate)).compareTo(SystemDate.getInstance()) < 0)
            throw new ExLeaveBeforeStartDate(SystemDate.getInstance().toString());
        this.leaveStart = new Day(startDate);
        this.leaveEnd = new Day(endDate);
    }

    public abstract String toString();

    public String getLeaveRange() {
        return leaveStart + " to " + leaveEnd;
    }

    @Override
    public int compareTo(LeaveRecord another){
        if (this.leaveStart.compareTo(another.leaveStart) == 0){
            if ((this.leaveEnd).compareTo(another.leaveEnd) > 0)
                return 1;
            else if ((this.leaveEnd).compareTo(another.leaveEnd) < 0)
                return -1;
            else
                return 0;
        }
        else if ((this.leaveStart).compareTo(another.leaveStart) > 0)
            return 1;
        else
            return -1;
    }

	public boolean overlaps(LeaveRecord another) {
        Day dayTracker = this.leaveStart.clone();
        Day anotherDayTracker = another.leaveStart.clone();
        Day tempTracker = null;
        if (dayTracker.compareTo(anotherDayTracker) > 0){
            tempTracker = anotherDayTracker;
            anotherDayTracker = dayTracker;
            dayTracker = tempTracker;
            while (!dayTracker.equals(another.leaveEnd.next())){
                if (dayTracker.compareTo(anotherDayTracker) == 0)
                    return true;
                dayTracker = dayTracker.next();
            }
        }
        else{
            while (!dayTracker.equals(this.leaveEnd.next())){
                if (dayTracker.compareTo(anotherDayTracker) == 0)
                    return true;
                dayTracker = dayTracker.next();
            }
        }
		return false;
	}
    public boolean overlaps(Day start, Day end) {
        Day dayTracker = this.leaveStart.clone();
        Day anotherDayTracker = start.clone();
        Day tempTracker = null;
        if (dayTracker.compareTo(anotherDayTracker) > 0){
            tempTracker = anotherDayTracker;
            anotherDayTracker = dayTracker;
            dayTracker = tempTracker;
            while (!dayTracker.equals(end.next())){
                if (dayTracker.compareTo(anotherDayTracker) == 0)
                    return true;
                dayTracker = dayTracker.next();
            }
        }
        else{
            while (!dayTracker.equals(this.leaveEnd.next())){
                if (dayTracker.compareTo(anotherDayTracker) == 0)
                    return true;
                dayTracker = dayTracker.next();
            }
        }
		return false;
    }
    public int count() {
        return Day.countdays(leaveStart, leaveEnd);
    }

    
}
