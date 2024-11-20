package DB;

/**
 * Data access layer for the Notes, we should be able to:
 *  - create
 *  - read (retrieve one or multiple notes using params like timestamps or keywords)
 *  - delete
 *  - update
 *
 */
public class NotesDAO {
    DatabaseInit database;

    public NotesDAO(DatabaseInit database){
        this.database = database;
    }


}
