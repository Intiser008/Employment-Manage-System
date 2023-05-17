public class CmdListRoles implements Command{
    @Override
    public void execute(String[] cmdParts){
        try{
            Company company = Company.getInstance();
            company.listRolesOfEmployee(cmdParts[1]);
        }
        catch(ExEmployeeNotFound e){
            System.out.println(e.getMessage());
        }
    }
}