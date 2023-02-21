package model;

import java.util.List;

public interface Repository {
    List<Toy> getAllUsers();
    String CreateUser(Toy toy);
    Toy updateUser(Toy toy) throws Exception;
    Toy readUser(String toyId) throws Exception;
    void deleteUser(String deleteId) throws Exception;
    
}
