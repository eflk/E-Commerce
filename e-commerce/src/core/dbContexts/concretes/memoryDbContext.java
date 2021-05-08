package core.dbContexts.concretes;

import entities.concretes.User;

import java.util.ArrayList;
import java.util.List;

public class memoryDbContext {
    public final static List<User> users = new ArrayList<User>();

}
