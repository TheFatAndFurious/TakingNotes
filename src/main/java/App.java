import DB.DatabaseInit;
import DB.NotesDAO;
import Services.NoteService;
import commands.AddNoteCommand;
import commands.ListAllNotesCommand;
import commands.RootCommand;
import exceptions.DataAccessException;
import exceptions.ServiceException;
import picocli.CommandLine;

import java.sql.SQLException;
import java.util.Objects;

public class App {
    NoteService noteService;
    NotesDAO notesDAO;
    DatabaseInit databaseInit;
    AddNoteCommand addNoteCommand;
    ListAllNotesCommand listAllNotesCommand;
    RootCommand rootCommand;
    CommandLine commandLine;

    public App() throws SQLException {
        this.databaseInit = new DatabaseInit();
        this.notesDAO = new NotesDAO(databaseInit.Initialize());
        this.noteService = new NoteService(notesDAO);
        this.addNoteCommand = new AddNoteCommand(noteService);
        this.listAllNotesCommand = new ListAllNotesCommand(noteService);
        this.rootCommand = new RootCommand();
        this.commandLine = new CommandLine(rootCommand);
    }



    public void runApp(String[] args) {
        commandLine.addSubcommand(addNoteCommand);
        commandLine.addSubcommand(listAllNotesCommand);

        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
        }
    }
