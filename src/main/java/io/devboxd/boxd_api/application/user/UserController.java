package io.devboxd.boxd_api.application.user;

import io.devboxd.boxd_api.application.user.dto.GetUserDTO;
import io.devboxd.boxd_api.application.user.dto.UpdatePasswordDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public interface UserController {

    @GetMapping("/{username}")
    ResponseEntity<List<GetUserDTO>> getAllByPrefix(@RequestParam String prefix);

    @PatchMapping("/{username}")
    ResponseEntity<GetUserDTO> updatePasswd(@PathVariable String username, @RequestBody UpdatePasswordDTO dto);

    @DeleteMapping("/{username}")
    ResponseEntity<Void> deleteUser(@PathVariable String username);

}
