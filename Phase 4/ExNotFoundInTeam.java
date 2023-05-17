
public class ExNotFoundInTeam extends Exception{

    public ExNotFoundInTeam(String employeeName, String teamName) {
        super("Employee (" + employeeName + ") not found for "+ teamName + "!");
    }
    
}
