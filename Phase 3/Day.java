import java.lang.Cloneable;

public class Day implements Cloneable, Comparable<Day>{
	
	private int year;
	private int month;
	private int day;
	private static final String MonthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";

	
	//Constructor
	public Day(int y, int m, int d) {
		this.year=y;
		this.month=m;
		this.day=d;		
	}
	public Day(String sDay) {set(sDay);} //Constructor, simply call set(sDay)
	
	// check if a given year is a leap year
	static public boolean isLeapYear(int y)
	{
		if (y%400==0)
			return true;
		else if (y%100==0)
			return false;
		else if (y%4==0)
			return true;
		else
			return false;
	}
	
	// check if y,m,d valid
	static public boolean valid(int y, int m, int d)
	{
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}

	// Return a string for the day like dd MMM yyyy

	public void set(String sDay) //Set year,month,day based on a string like 01-Mar-2022
	{
		String[] sDayParts = sDay.split("-");
		this.day = Integer.parseInt(sDayParts[0]); //Apply Integer.parseInt for sDayParts[0];
		this.year = Integer.parseInt(sDayParts[2]);
		this.month = MonthNames.indexOf(sDayParts[1])/3+1;
	}

	public boolean isEndOfAMonth(){
        if (valid(year, month, day+1))
            return false;
        else
            return true;
    }

    public Day next() 
	{
		if (isEndOfAMonth())
			if (month==12)
				return new Day(year+1,1,1); //Since the current day is the end of the year, the next day should be Jan 01
			else
				return new Day(year, month + 1, 1); // <== your task: first day of next month
		else
			return new Day(year, month, day+1); // <== your task: next day of current month
	}

	public static int countdays(Day start, Day end){
		Day dayTracker = start.clone();
		int count = 1;
		while (!dayTracker.equals(end)){
			count++;
			dayTracker = dayTracker.next();
		}
		return count;
	}

	@Override
	public String toString()
	{
		return day+"-"+ MonthNames.substring((month-1)*3,(month*3)) + "-"+ year; // (month-1)*3,(month)*3
	}

	public boolean equals(Day another){
		if (this.year == another.year &&
		this.month == another.month &&
		this.day == another.day){ return true; }

		return false;
	}

	@Override
	public Day clone()
	{
		Day copy=null;
		try{
			copy = (Day) super.clone();
			
		} catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return copy;
	}
	@Override
	public int compareTo(Day another){
		int thisComparableDate = this.day + (this.month * 100) + (this.year * 10000);
		int anotherComparableDate = another.day + (another.month * 100) + (another.year * 10000);

		return (Integer.valueOf(thisComparableDate)).compareTo(Integer.valueOf(anotherComparableDate));
	}
}