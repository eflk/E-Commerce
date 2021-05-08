package entities.abstracts;

public interface Entity<T extends Entity<?>> {
    public int getId();
    
    public void setId(int id);

    public void clone(T entity);

}
