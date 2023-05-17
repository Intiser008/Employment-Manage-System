import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class Company {
    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;
    private static Company instance = new Company();

    private Company(){
        allEmployees = new ArrayList<>();
        allTeams = new ArrayList<>();
    }

    public static Company getInstance(){ return instance; }

    public void listTeams() {
        Team.listTheTeams(allTeams);
    }

    public void listEmployees(){
        for (Employee e:allEmployees)
            System.out.printf("%s (Entitled Annual Leaves: %d days)\n", e.getName(), e.getAnnualLeaves());
    }

    public Employee createEmployee(String name, int yle) throws ExEmployeeAlreadyExists // See how it is called in main()
    {
        Employee e = new Employee(name, yle);
        for (Employee existing:allEmployees){
            if (existing.getName().equals(e.getName()))
                throw new ExEmployeeAlreadyExists("Employee already exists!");
        }
        allEmployees.add(e);
        Collections.sort(allEmployees); //allEmployees
        return e;
    }

    public Team createTeam(String teamName, String head) 
            throws ExEmployeeNotFound, ExTeamAlreadyExists// See how it is called in main()
    {
        Employee e = Employee.searchEmployee(allEmployees, head);
        if (e == null)
            throw new ExEmployeeNotFound("Employee not found!");
        Team t = new Team(teamName, e);
        for (Team existing:allTeams){
            if (existing.getTeamName().equals(teamName))
                throw new ExTeamAlreadyExists("Team already exists!");
        }
        allTeams.add(t);
        Collections.sort(allTeams); //allTeams
        return t; //Why return?  Ans: Later you'll find it useful for undoable comments.
    }

    public void addEmployee(Employee e){
        allEmployees.add(e);
        Collections.sort(allEmployees);
    }

    public void removeEmployee(Employee e){
        allEmployees.remove(e);
    }

    public void removeTeam(Team newTeam) {
        allTeams.remove(newTeam);
    }

    public void addTeam(Team newTeam) {
        allTeams.add(newTeam);
        Collections.sort(allTeams);
    }
}
