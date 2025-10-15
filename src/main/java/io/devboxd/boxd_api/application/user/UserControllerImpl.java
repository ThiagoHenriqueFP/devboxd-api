package io.devboxd.boxd_api.application.user;

import io.devboxd.boxd_api.application.user.dto.GetUserDTO;
import io.devboxd.boxd_api.application.user.dto.UpdatePasswordDTO;
import io.devboxd.boxd_api.domain.user.User;
import io.devboxd.boxd_api.domain.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<GetUserDTO>> getAllByPrefix(String prefix) {
        var users = this.userService.getUsersStartsWith(prefix).stream().map(User::toDTO).toList();

        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<GetUserDTO> updatePasswd(@PathVariable String username, UpdatePasswordDTO dto) {
        var user = this.userService.updatePassword(username, dto.oldPassword(), dto.newPassword());

        return ResponseEntity.ok(user.toDTO());
    }

    @Override
    public ResponseEntity<Void> deleteUser(String username) {
        this.userService.shadowDelete(username);

        return ResponseEntity.noContent().build();
    }

}
