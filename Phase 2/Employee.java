import java.util.ArrayList;
import java.util.Collections;

public class Employee implements Comparable<Employee>{
    private String name;
    private int annualLeaves;
    private ArrayList<Team> teamsList;

    public Employee(String name, int annualLeaves){
        this.name = name;
        this.annualLeaves = annualLeaves;
        teamsList = new ArrayList<>();
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
}
