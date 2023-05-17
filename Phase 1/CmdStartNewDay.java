public class CmdStartNewDay extends RecordedCommand {
    private Day newDate;
    private Day oldDate;

    public void execute(String[] cmdParts){
        oldDate = SystemDate.getInstance().clone();
        SystemDate.getInstance().set(cmdParts[1]);
        newDate = SystemDate.getInstance().clone();

        addUndoCommand(this);
        clearRedoList();

        System.out.println("Done.");
    }

    @Override
    public void redoMe() {
        SystemDate.getInstance().set(newDate.toString());

        addUndoCommand(this);
    }

    @Override
    public void undoMe() {
        SystemDate.getInstance().set(oldDate.toString());

        addRedoCommand(this);
        
    }    
}
