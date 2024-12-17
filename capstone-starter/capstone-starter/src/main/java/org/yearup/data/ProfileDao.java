package org.yearup.data;


import org.yearup.models.Profile;

public interface ProfileDao
{
    //provided
    Profile create(Profile profile);
    // my interfaces
    Profile getByUserId(int userId);
    void update(int userId, Profile profile);
}
