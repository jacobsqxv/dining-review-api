package com.aries.diningreview.controller;

import com.aries.diningreview.model.User;
import com.aries.diningreview.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> getAllUsers (){
        return this.userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser (@RequestBody User user){
        if (checkExistingUser(user)){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "User with display name already exist");
        }
        return this.userRepository.save(user);
    }

    @PutMapping("/update")
    public User updateUser (@RequestBody User user){
        var userToUpdate = this.userRepository.findByDisplayName(user.getDisplayName()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User does not exist.")
        );
        if (!checkStrings(user.getCity(), userToUpdate.getCity()) && !user.getCity().isEmpty()){
            userToUpdate.setCity(user.getCity());
        }
        if (!checkStrings(user.getState(), userToUpdate.getState()) && !user.getState().isEmpty()){
            userToUpdate.setState(user.getState());
        }
        if (!checkStrings(user.getZipCode(), userToUpdate.getZipCode()) && !user.getZipCode().isEmpty()){
            userToUpdate.setZipCode(user.getZipCode());
        }
        if (!checkBoolean(user.getDairyWatch(), userToUpdate.getDairyWatch())){
            userToUpdate.setDairyWatch(user.getDairyWatch());
        }
        if (!checkBoolean(user.getEggWatch(), userToUpdate.getEggWatch())){
            userToUpdate.setEggWatch(user.getEggWatch());
        }
        if (!checkBoolean(user.getPeanutWatch(), userToUpdate.getPeanutWatch())){
            userToUpdate.setPeanutWatch(user.getPeanutWatch());
        }

        return this.userRepository.save(userToUpdate);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser (@RequestBody User user){
        var userToDelete = this.userRepository.findByDisplayName(user.getDisplayName()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User does not exist.")
        );
        this.userRepository.delete(userToDelete);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public User getUser (@RequestParam String name){
        var optionalUser = this.userRepository.findByDisplayName(name);
        return optionalUser.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // Validation checks
    public Boolean checkExistingUser (@NotNull User user) {
        var optionalUser = this.userRepository.findByDisplayName(user.getDisplayName());
        return optionalUser.isPresent();
    }

    public Boolean checkBoolean (Boolean bool1, Boolean bool2){
        return bool1 == bool2;
    }

    public Boolean checkStrings (@NotNull String str1, String str2){
        return str1.equals(str2);
    }

}
