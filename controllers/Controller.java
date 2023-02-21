package controllers;

import java.util.List;

import model.Repository;
import model.Toy;

public class Controller {
    
    private final Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(Toy toy) throws Exception {
        validateUser(toy);
        repository.CreateUser(toy);
    }

    public Toy readUser(String toyId) throws Exception {
        return repository.readUser(toyId);
    }

    public List<Toy> readUserList() {
        return repository.getAllUsers();
    }

    public Toy updateUser(Toy toy) throws Exception{
        validateUser(toy);
        return repository.updateUser(toy);
    }

    public void deleteUser(String deleteId) throws Exception {
        repository.deleteUser(deleteId);
    }

    private void validateUser(Toy toy) throws Exception{
        if (toy.getName().isEmpty()) {
            throw new Exception("Отсутствует название");
        }
        if (toy.getCount().isEmpty()) {
            throw new Exception("Отсутствует количество");
        }
        if (toy.getWeight().isEmpty()) {
            throw new Exception("Отсутствует частота выпадения");
        }
    }
}
