package Entities;

public class KeywordEntity implements Identifiable{
    Long ID;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return ID;
    }

    @Override
    public void setId(Long id) {
        this.ID = id;
    }
}
