import java.util.ArrayList;
import java.util.Collections;

public class Employee implements Comparable<Employee>{
    private String name;
    private int annualLeaves;
    private int sickLeaves = 135;
    private ArrayList<LeaveRecord_BL> hasTakenBL;
    private ArrayList<Team> teamsList;
    private ArrayList<LeaveRecord> leavesTaken;
    

    public Employee(String name, int annualLeaves){
        this.name = name;
        this.annualLeaves = annualLeaves;
        hasTakenBL = new ArrayList<>();
        teamsList = new ArrayList<>();
        leavesTaken = new ArrayList<>();
    }

    public String getName(){ return name; }

    public int getAnnualLeaves() { return annualLeaves; }

    public static Employee searchEmployee(ArrayList<Employee> allEmployees, String name){
        for (Employee e:allEmployees){
            if (e.getName().equals(name))
                return e;
        }
        return null;
    }

    public void addTeam (Team team){
        teamsList.add(team);
        Collections.sort(teamsList);
    }
    public void removeTeam(Team team){
        teamsList.remove(team);
    }

    @Override
    public int compareTo(Employee another)  {
        return this.name.compareTo(another.name);
    }

    public void listRoles() {
        Team.listTheRoles(teamsList, this);
    }

    public LeaveRecord addLeave(String leaveType, String startDate, String endDate) 
                throws ExLeavesOverlap, ExLeaveBeforeStartDate, ExWrongTypeLeave, ExOutOfLeaves, ExReserveNeededForBL{
        LeaveRecord leave = null;
        if (leaveType.equals("AL")){
            if (annualLeaves - Day.countdays(new Day(startDate), new Day(endDate)) < 7 && hasTakenBL.size() == 0)
                throw new ExReserveNeededForBL(annualLeaves);              
            else if(Day.countdays(new Day(startDate), new Day(endDate)) > 6)
                throw new ExWrongTypeLeave("To apply annual leave for 7 days or more, please use the Block Leave (BL) type.");      
            else if (Day.countdays(new Day(startDate), new Day(endDate)) > annualLeaves)
                throw new ExOutOfLeaves("Insufficient balance of annual leaves. " + annualLeaves + " days left only!");
            else{
                annualLeaves -= Day.countdays(new Day(startDate), new Day(endDate));
                leave = (new LeaveRecord_AL(startDate, endDate));
            }
        }
        else if (leaveType.equals("BL")){
            if(Day.countdays(new Day(startDate), new Day(endDate)) < 7)
                throw new ExWrongTypeLeave("To apply annual leave for 6 days or less, you should use the normal Annual Leave (AL) type.");
            if (Day.countdays(new Day(startDate), new Day(endDate)) > annualLeaves)
                throw new ExOutOfLeaves("Insufficient balance of annual leaves. " + annualLeaves + " days left only!");
            
            annualLeaves -= Day.countdays(new Day(startDate), new Day(endDate));
            leave = (new LeaveRecord_BL(startDate, endDate));
            hasTakenBL.add((LeaveRecord_BL)leave);
        }
        else if (leaveType.equals("SL")){
            if (Day.countdays(new Day(startDate), new Day(endDate)) > sickLeaves)
                throw new ExOutOfLeaves("Insufficient balance of sick leaves. " + sickLeaves + " days left only!");
            sickLeaves -= Day.countdays(new Day(startDate), new Day(endDate));
            leave = (new LeaveRecord_SL(startDate, endDate));
        }
        else if (leaveType.equals("NL")){
            leave = (new LeaveRecord_NL(startDate, endDate));
        }
        
        for(LeaveRecord l:leavesTaken){
            if (leave.overlaps(l) == true)
                throw new ExLeavesOverlap(l.toString());
        }

        if (leave != null)
            leavesTaken.add(leave);
        Collections.sort(leavesTaken);

        return leave;
    }

    public static void leavesTakenByAll(ArrayList<Employee> allEmployees) {
        for (Employee e:allEmployees){
            System.out.println(e.name + ":");
            if (e.leavesTaken.size() == 0)
                System.out.println("No leave record");
            else{
                for (LeaveRecord l:e.leavesTaken)
                    System.out.println(l); 
            }
        }
    }

    public void listLeavesTaken() {
        if (leavesTaken.size() == 0)
            System.out.println("No leave record");
        else {
            for (LeaveRecord l:leavesTaken)
                System.out.println(l);
        }
    }

    public void addLeaveAgain(LeaveRecord leave) {
        leavesTaken.add(leave);
        if (leave instanceof LeaveRecord_BL)
            hasTakenBL.add((LeaveRecord_BL)leave);
        if (leave instanceof LeaveRecord_SL)
            sickLeaves -= leave.count();
        else
            annualLeaves -= leave.count();
        Collections.sort(leavesTaken);
    }

    public void removeLeave(LeaveRecord leave){
        leavesTaken.remove(leave);
        if (leave instanceof LeaveRecord_BL)
            hasTakenBL.remove(leave);
        if (leave instanceof LeaveRecord_SL)
            sickLeaves += leave.count();
        else
            annualLeaves += leave.count();
    }

    public boolean overlapsWithLeave(Day start, Day end) {
        for (LeaveRecord l:leavesTaken){
            if (l.overlaps(start,end))
                return true;
        }
        return false;
    }

    public LeaveRecord actingPeriodOverlap(Day start, Day end){
        for (LeaveRecord l:leavesTaken){
            if (l.overlaps(start,end))
                return l;
        }
        return null;
    }
}
