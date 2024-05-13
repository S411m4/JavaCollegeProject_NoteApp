/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paneltags;

import DatabaseHelpers.DatabaseHelper;
import java.util.ArrayList;

/**
 *
 * @author salma
 */
public class TagService implements ITagService {
    public ArrayList<String> fetchTags(){return DatabaseHelper.getAllTags();}
    
}
