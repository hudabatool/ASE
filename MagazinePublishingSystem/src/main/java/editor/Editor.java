/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package editor;

/**
 *
 * @author hudab
 */
import models.Issue;
import models.Story;
import models.Photograph;
import system.IContentManager;

public class Editor implements IContentManager {
    @Override
    public void submitStory(Story story) {
        System.out.println("Story submitted: " + story.getTitle());
    }

    @Override
    public void submitPhotograph(Photograph photograph) {
        System.out.println("Photograph submitted: " + photograph.getCaption());
    }

    @Override
    public void matchPhotographToStory(int photoID, int storyID) {
        System.out.println("Photograph " + photoID + " matched to Story " + storyID);
    }

    @Override
    public Issue compileMagazineIssue() {
        Issue issue = new Issue(1, "Default Layout", "Draft");
        System.out.println("Magazine issue compiled: Issue ID " + issue.getIssueId());
        return issue;
    }
}

