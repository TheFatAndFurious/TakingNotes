package commands;

import Services.NoteService;
import exceptions.ServiceException;
import picocli.CommandLine;

@CommandLine.Command(
        name="add",
        description="adds a new note"
)

public class AddNoteCommand implements Runnable {

    @CommandLine.Option(names={"-c", "--content"}, description = "content of the note", required = true)
    private String content;

//    @CommandLine.Option(names={"-k", "--keyword"}, description = "keyword of the note", required = true)
//    private String keyword;

    private final NoteService noteService;

    public AddNoteCommand(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public void run() {
        try{
            noteService.createNote(content);
            System.out.println("Note added with much success");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
