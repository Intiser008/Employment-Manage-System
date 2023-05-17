public class CmdAddTeamMember extends RecordedCommand{
    String[] cmd;
    @Override
    public void execute(String[] cmdParts){
        try{
            if (cmdParts.length < 3)
                throw new ExInsufficientArguments("Insufficient command arguments!");
            cmd = cmdParts;
            Company company = Company.getInstance();
            company.addMemberToTeam(cmdParts[1], cmdParts[2]);

            clearRedoList();
            addUndoCommand(this);

            System.out.println("Done.");
        }
        catch (ExInsufficientArguments e){
            System.out.println(e.getMessage());
        }
        catch (ExEmployeeNotFound e){
            System.out.println(e.getMessage());
        }
        catch (ExTeamNotFound e){
            System.out.println(e.getMessage());
        }
        catch (ExAlreadyMember e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe(){
        Company company = Company.getInstance();
        company.removeMemberFromTeam(cmd[1], cmd[2]);
        addRedoCommand(this);
    }

    @Override
    public void redoMe(){
        try{
            Company company = Company.getInstance();
            company.addMemberToTeam(cmd[1], cmd[2]);
            addUndoCommand(this);
        }
        catch (ExAlreadyMember e){
            // No need
        }
        catch (ExTeamNotFound e){
            // No need
        }
        catch (ExEmployeeNotFound e){
            // No need
        }
    }
}
