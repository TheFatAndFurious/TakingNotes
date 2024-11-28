package commands;

import Services.NoteService;
import exceptions.ServiceException;
import picocli.CommandLine;


/**
 * Class used to create all commands related to listing notes
 * We are using the Picocli framework to parse args
 */
@CommandLine.Command(
        name="list",
        description = "Command to print all notes"
)

public class ListAllNotesCommand implements Runnable{
    @CommandLine.Option(names = {"-l", "--limit"}, description = "list the last N notes")
    private Integer limit;

    private final NoteService noteService;

    public ListAllNotesCommand(NoteService noteService){
        this.noteService = noteService;
    }

    @Override
    public void run() {
        try {
            noteService.listLatestNotes(limit);
        } catch (ServiceException e){
            throw new RuntimeException(e);
        }
    }
}
