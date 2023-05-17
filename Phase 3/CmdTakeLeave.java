
public class CmdTakeLeave extends RecordedCommand{
    String employeeName;
    LeaveRecord leave;

    @Override
    public void execute(String[] cmdParts){
        try{
            Company company = Company.getInstance();  
            employeeName = cmdParts[1];

            leave = company.giveLeaveToEmployee(cmdParts[1], cmdParts[2], cmdParts[3], cmdParts[4]);
            
            clearRedoList();
            addUndoCommand(this);

            System.out.println("Done.");
        }
        catch (ExLeavesOverlap e){
            System.out.println(e.getMessage());
        }
        catch(ExLeaveBeforeStartDate e){
            System.out.println(e.getMessage());
        }
        catch (ExWrongTypeLeave e){
            System.out.println(e.getMessage());
        }
        catch (ExOutOfLeaves e){
            System.out.println(e.getMessage());
        }
        catch (ExReserveNeededForBL e){
            // All taken care in exception class
        }
    }

    @Override
    public void undoMe(){
        Company company = Company.getInstance();
        company.removeLeaveFromEmployee(employeeName,leave);
        
        addRedoCommand(this);
    }

    @Override
    public void redoMe(){
        Company company = Company.getInstance();
        company.reGiveLeaveToEmployee(employeeName,leave);

        addUndoCommand(this);
    }
}
