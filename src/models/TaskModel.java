/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author salma
 */
public class TaskModel {
    String task;
    public boolean checked;
    
    public TaskModel()
    {task = "Task...";}
    
    
    public TaskModel(String task)
    {
        this.task = task;
        checked = false;
    }
    
    public void SetTaskDone(boolean done)
    {
        checked = done;
    }
    
    public void SetTaskString(String task)
    {
        this.task = task;
    }
}
