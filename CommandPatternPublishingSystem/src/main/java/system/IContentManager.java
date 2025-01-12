/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package system;

/**
 * @author HudaB_21554056 */
import models.Story;
import models.Photograph;
import models.Issue;

public interface IContentManager {
    void submitStory(Story story);
    void submitPhotograph(Photograph photograph);
    void matchPhotographToStory(int photoID, int storyID);
    Issue compileMagazineIssue();
}

