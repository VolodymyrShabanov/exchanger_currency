package interfaces;

import utils.UserRole;

import java.util.Optional;

public interface IUserService {
    public Optional<String> getCurrentUserEmail();

    public boolean createUser(String email, String password, UserRole role);

    public boolean login(String email, String password);

    public boolean logout();
}