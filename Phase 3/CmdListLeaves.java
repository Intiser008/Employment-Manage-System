
public class CmdListLeaves implements Command{
    @Override
    public void execute(String[] cmdParts){
        Company company = Company.getInstance();

        company.listAllLeavesGiven(cmdParts);
    }
}
