package commands;

import Services.NoteService;
import exceptions.ServiceException;
import picocli.CommandLine;

@CommandLine.Command(
        name="list",
        description = "Command to print all notes"
)

public class ListAllNotesCommand implements Runnable{
//    @CommandLine.Option(names = {"-l", "--limit"}, description = "list the last N notes", required = true)
//    private Integer limit;

    private final NoteService noteService;

    public ListAllNotesCommand(NoteService noteService){
        this.noteService = noteService;
    }

    @Override
    public void run() {
        try {
            System.out.println("coucou from the listCommand");
            noteService.listAllNotes();
        } catch (ServiceException e){
            throw new RuntimeException(e);
        }
    }
}
