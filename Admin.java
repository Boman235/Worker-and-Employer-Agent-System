package Agenecy;

public class Admin implements User {
    @Override
    public void manageWorkers(User user) {
        System.out.println("Admin is managing worker: " + user.getClass().getSimpleName());
    }

    @Override
    public void manageEmployers(User user) {
        System.out.println("Admin is managing employer: " + user.getClass().getSimpleName());
    }
      
    

}