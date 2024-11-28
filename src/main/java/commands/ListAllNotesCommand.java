package commands;

import Services.NoteService;
import exceptions.ServiceException;
import picocli.CommandLine;

@CommandLine.Command(
        name="List all commands",
        description = "Command to print all notes"
)

public class ListAllNotesCommand implements Runnable{
    @CommandLine.Option(names = {"-l", "--list"}, description = "list all notes", required = true)
    private String list;

    private final NoteService noteService;

    public ListAllNotesCommand(NoteService noteService){
        this.noteService = noteService;
    }

    @Override
    public void run() {
        try {
            noteService.listAllNotes();
        } catch (ServiceException e){
            throw new RuntimeException(e);
        }
    }
}
