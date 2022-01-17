package com.example.demo.jsonpersoncat.repository.sqlite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.demo.jsonpersoncat.models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    User[] loadAllUsers();

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Query("SELECT * FROM users WHERE age BETWEEN :minAge AND :maxAge")
    User[] loadAllUsersBetweenAges(int minAge, int maxAge);

    @Query("SELECT * FROM users WHERE first_name LIKE :search " +
            "OR last_name LIKE :search")
    List<User> findUserWithName(String search);

    @Insert
    void insertAll(User... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(User... users);

    @Insert
    void insertBothUsers(User user1, User user2);

    @Insert
    void insertUsersAndFriends(User user, List<User> friends);

    @Delete
    void deleteUsers(User... users);

    @Update
    void updateUsers(User... users);

}
