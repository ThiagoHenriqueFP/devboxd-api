package io.devboxd.boxd_api.application.user;

import io.devboxd.boxd_api.application.user.dto.GetUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {
    @Override
    public ResponseEntity<List<GetUserDTO>> getAllByPrefix(String prefix) {
        return null;
    }

    @Override
    public ResponseEntity<GetUserDTO> getByUsername(String username) {
        return null;
    }
}
