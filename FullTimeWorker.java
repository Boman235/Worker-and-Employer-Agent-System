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

// FullTimeWorker class implementing Worker interface
public class FullTimeWorker implements Worker {
    @Override
    public void applyForJob(String jobTitle) {
        System.out.println("Full-time worker applied for job: " + jobTitle);
    }

    @Override
    public void manageWorkers(User user) {
        // Not applicable for FullTimeWorker
    }

    @Override
    public void manageEmployers(User user) {
        // Not applicable for FullTimeWorker
    }
    
}

