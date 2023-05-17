import java.util.ArrayList;

public class CmdTakeLeave extends RecordedCommand{
    String employeeName;
    LeaveRecord leave;
    ArrayList<Team> teamsWithActHead = new ArrayList<>();
    ArrayList<actinghead> actHead = new ArrayList<>();
    

    @Override
    public void execute(String[] cmdParts){
        try{
            Company company = Company.getInstance();  
            employeeName = cmdParts[1];
            for (int i = 5; i < cmdParts.length; i+=2){
                actHead.add(company.addActingHead(cmdParts[i], cmdParts[i+1], cmdParts[3], cmdParts[4]));
                teamsWithActHead.add(company.findTeamInCom(cmdParts[i]));
            }
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
        catch(ExWillNotBeAvailable e){
            System.out.println(e.getMessage());
        }
        catch(ExNotFoundInTeam e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe(){
        Company company = Company.getInstance();
        company.removeLeaveFromEmployee(employeeName,leave);
        for (int i = 0; i < actHead.size(); i++)
            company.removeActingHead(actHead.get(i), teamsWithActHead.get(i));
        
        addRedoCommand(this);
    }

    @Override
    public void redoMe(){
        Company company = Company.getInstance();
        company.reGiveLeaveToEmployee(employeeName,leave);
        for (int i = 0; i < actHead.size() && i < teamsWithActHead.size(); i++)
            company.readdActingHead(actHead.get(i), teamsWithActHead.get(i));

        addUndoCommand(this);
    }
}
