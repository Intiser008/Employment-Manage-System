public class CmdListTeams implements Command{
    public void execute(String[] cmdParts){
        Company c = Company.getInstance();
        c.listTeams();
    }
}
