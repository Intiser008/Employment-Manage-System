import java.util.ArrayList;
import java.util.Collections;

public class Team implements Comparable<Team>{
    private String teamName;
    private Employee head;
    private Day dateSetup; //the date this team was setup
    private ArrayList<Employee> teamMembers;
    private ArrayList<actinghead> actingHeads;

    public Team(String teamName, Employee head){
        this.teamName = teamName; //Set teamName, head, setup date
        this.head = head;
        (this.head).addTeam(this);
        dateSetup = SystemDate.getInstance().clone();
        teamMembers = new ArrayList<>();
        actingHeads = new ArrayList<>();
        teamMembers.add(head);
    }

    @Override
    public int compareTo(Team another)  {
        return this.teamName.compareTo(another.teamName);
    }

    public static void listTheTeams(ArrayList<Team> allTeams) {
        System.out.printf("%-30s%-10s%-13s\n", "Team Name", "Leader", "Setup Date");
        for (Team t:allTeams)
            System.out.printf("%-30s%-10s%-13s\n", t.teamName, t.head.getName(), t.dateSetup);
    }
    public String getTeamName(){ return teamName; }

    public void addMember(Employee employee) throws ExAlreadyMember{
        for (Employee e:teamMembers)
            if (e.equals(employee))
                throw new ExAlreadyMember("The employee has joined the team already!");
        teamMembers.add(employee);
        Collections.sort(teamMembers);
        employee.addTeam(this);
    }

    public void removeMember(Employee employee) {
        employee.removeTeam(this);
        teamMembers.remove(employee);
    }

    public static void listTheRoles(ArrayList<Team> teamsList, Employee employee) {
        if (teamsList.size() == 0)
            System.out.println("No role");
        else{
            for (Team t:teamsList){
                if ((t.head).equals(employee))
                    System.out.println(t.teamName + " (Head of Team)");
                else
                    System.out.println(t.teamName);
            }
        }
    }

    public static void listTheTeamsMmembers(ArrayList<Team> allTeams) {
        for (Team t:allTeams){
            System.out.println(t.teamName + ":");
            for (Employee e:t.teamMembers){
                if (t.head == e)
                    System.out.println(e.getName() + " (Head of Team)");
                else
                    System.out.println(e.getName());
            }
            if (t.actingHeads.size() > 0){
                System.out.println("Acting Heads:");
                for (actinghead aH:t.actingHeads){
                    System.out.println(aH.toString());
                }
            }
            
            System.out.println();
        }
    }

    public static void addHead(Team newTeam) {
        newTeam.head.addTeam(newTeam);
    }

    public static void removeHead(Team newTeam) {
        newTeam.head.removeTeam(newTeam);
    }

    public actinghead makeActingHead(String employeeName, String startDate, String endDate) 
                        throws ExWillNotBeAvailable, ExNotFoundInTeam{
        Employee e = Employee.searchEmployee(teamMembers, employeeName);
        if (e == null)
            throw new ExNotFoundInTeam(employeeName, teamName);
        Day start = new Day(startDate);
        Day end = new Day(endDate);
        if (e.overlapsWithLeave(start,end))
            throw new ExWillNotBeAvailable(e.getName(),e.actingPeriodOverlap(start, end));
        actinghead actHead = new actinghead(e, start, end);
        actingHeads.add(actHead);
        Collections.sort(actingHeads);

        return actHead;
    }

    public void removeActingHead(actinghead actHead) {
        actingHeads.remove(actHead);
    }

    public void addActingHead(actinghead actHead) {
        actingHeads.add(actHead);
        Collections.sort(actingHeads);
    }
}
