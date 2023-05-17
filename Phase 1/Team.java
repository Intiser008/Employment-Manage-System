import java.util.ArrayList;

public class Team implements Comparable<Team>{
    private String teamName;
    private Employee head;
    private Day dateSetup; //the date this team was setup
    private ArrayList<Employee> teamMembers;

    public Team(String teamName, Employee head){
        this.teamName = teamName; //Set teamName, head, setup date
        this.head = head;
        dateSetup = SystemDate.getInstance().clone();
        teamMembers = new ArrayList<>();
        teamMembers.add(this.head);
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
}
