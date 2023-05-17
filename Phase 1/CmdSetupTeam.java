public class CmdSetupTeam extends RecordedCommand{
    private Team newTeam;

    @Override
    public void execute(String[] cmdParts) {
        Company c = Company.getInstance();
        try{
            if (cmdParts.length < 3)
                throw new ExInsufficientArguments("Insufficient command arguments!");
            newTeam = c.createTeam(cmdParts[1], cmdParts[2]);
            
            clearRedoList();
            addUndoCommand(this);

            System.out.println("Done.");
        } catch (ExInsufficientArguments e){
            System.out.println(e.getMessage());
        }
        catch (ExEmployeeNotFound e){
            System.out.println(e.getMessage());
        } catch (ExTeamAlreadyExists e){
            System.out.println(e.getMessage());
        }
    }    
    
    @Override
    public void undoMe() {
        Company c =  Company.getInstance();
        c.removeTeam(newTeam);
        addRedoCommand(this);
        
    }

    @Override
    public void redoMe() {
        Company c =  Company.getInstance();
        c.addTeam(newTeam);
        addUndoCommand(this);
    }
}
