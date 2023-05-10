package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

public class UserFacade
{
    public static User login(String username, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.login(username, password, connectionPool);
    }

    public static User createUser(String username, String password, int role, ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.createUser(username, password, role, connectionPool);
    }

    public static boolean doesUserExist(String email, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.doesUserExist(email, connectionPool);
    }

    public static User updateUserProfile(User user, ConnectionPool connectionPool) throws DatabaseException{
        return UserMapper.updateUserProfile(user,connectionPool);
    }
}
