public class CmdHire extends RecordedCommand{
    private Employee newEmployee;
    @Override
    public void execute(String[] cmdParts) {
        Company c =  Company.getInstance();
        try{
            if (cmdParts.length < 3)
                throw new ExInsufficientArguments("Insufficient command arguments!");
            if (Integer.parseInt(cmdParts[2]) < 0 || Integer.parseInt(cmdParts[2]) > 300)
                throw new ExLeaveOutOfRange("Out of range (Entitled Annual Leaves should be within 0-300)!");
                
            newEmployee = c.createEmployee(cmdParts[1], Integer.parseInt(cmdParts[2]));
            
            clearRedoList();
            addUndoCommand(this);

            System.out.println("Done.");
        }
        catch (ExInsufficientArguments e){
            System.out.println(e.getMessage());
        }
        catch (ExLeaveOutOfRange e){
            System.out.println(e.getMessage());
        }
        catch (ExEmployeeAlreadyExists e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void undoMe() {
        Company c =  Company.getInstance();
        c.removeEmployee(newEmployee);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Company c =  Company.getInstance();
        c.addEmployee(newEmployee);
        addUndoCommand(this);
    }
}
