/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agenecy;

/**
 *
 * @author Hp
 */


// UserAdapter class for adapting user roles
public class UserAdapter implements User {
    private User user;

    public UserAdapter(User user) {
        this.user = user;
    }

    @Override
    public void manageWorkers(User user) {
        this.user.manageWorkers(user);
    }

    @Override
    public void manageEmployers(User user) {
        this.user.manageEmployers(user);
    }
    
    
}
