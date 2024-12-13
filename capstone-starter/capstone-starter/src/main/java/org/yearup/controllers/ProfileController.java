package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Product;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("profile")
@CrossOrigin
public class ProfileController {

    private ProfileDao profileDao;
    private UserDao userDao;

    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public Profile getById(Principal principal)
    {
        User user = userDao.getByUserName(principal.getName());
        /*
        *   Maaike helped me do this, the id was not being passed through the url
        *   Principal is a bean available, and with it, I can get the name of the current logged-in user
        *   so, I get user by the name
        *   then I can get the id of the user through the instance
        * */


        // get the category by id
        try
        {
            var profile = profileDao.getByUserId(user.getId());

            if(profile == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            return profile;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public Profile addProduct(@RequestBody Profile profile)
    {
        try
        {
            return profileDao.create(profile);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

}
