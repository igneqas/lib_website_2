package com.libraryproject.librarysystem.domain;

public class UserWithCount {
    private Users user;

    private int count;

    public UserWithCount() {
    }

    public UserWithCount(Users user, int count) {
        this.user = user;
        this.count = count;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "UserWithCount{" +
                "user=" + user +
                ", count=" + count +
                '}';
    }
}
