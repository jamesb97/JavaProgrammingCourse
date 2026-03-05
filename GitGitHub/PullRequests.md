# Fork and Pull Requests

## Learning Objectives

1. Use git commands to manage upstream repositories
2. Create a personal access token
3. Fork existing repository using the UI
4. Clone forked repository in the lab environment
5. Create a new branch
6. Make changes locally
7. Add and commit to the local branch
8. Push changes to the forked repository
9. Create a pull request to the upstream repository

### Exercise 1: Generate personal access token

The first step is to generate an access token from GitHub.com.

### Exercise 2: Fork the repository

To fork a source repository:

1. Login to GitHub and go into the project's sample source repository. This is the upstream repository for the project.
2. At the top right of the screen, click on Fork and select your GitHub account as the destination for the fork.

### Exercise 3: Clone the forked repository

A clone is a local copy of a repository.

1. In your list of repositories, click on the forked repository. On the repository's main page, click on the Code button.
2. Click on the clipboard icon to copy the URL. Make sure the HTTPs tab is active.
3. Open the terminal in the lab environment by using the menu in the editor. Terminal > New Terminal
4. Let's export the copied URL in an environment variable so that is is available for us in later steps, run the following command in the terminal:
   `export ORIGIN=<your repository HTTPS URL>
`
   Replace the url with the link copied from exercise 2.
5. Run the following command with the HTTPs URL copied earlier:
   `git clone $ORIGIN`
   This command clones the repository that is on GitHub in the current directory.

### Exercise 4: Explore the cloned repo

To become familiar with the cloned repo, complete the following steps:

1. Click on the Explorer icon in the left panel.
2. Click on the Project and expand the folder of the project that was cloned. Open the files in the editor, on the right side, by clicking on the file name.

### Exercise 5: Create the `feature-circle-500` branch

Increase the size of the circle's size to 500x500 pixels. Before, we make the change, we will create a new branch.

1. Navigate to the repository using the command `cd gkpbt-css-circle`
2. Create a new branch using the `git checkout -b feature-circle-500` command. Notice that we used a single command instead of creating a branch and then checking it out. The `-b` flag creates the branch if it does not already exist.
3. We can check that we are in the new branch by using the `git branch` command.

### Exercise 6: Make the required code changes

1. Let's change the width and height to 500px each. Open the `style.css` file from the file explorer and change the code as follows:

```
.blue {
            background-color:blue
}
.circle{
            border-radius:50%;
            width:500px;
            height:500px;
}
```

2. If you do a git status at this point, you will see a change is shown. This change is not staged at this point, but Git is aware of it.

`git status`

3. Optionally, you can use the git diff command to see the detailed changes:
   `git diff ./style.css`

Notice the text in red was deleted and the text in green was added. Essentially, we changed the height and width from 300px to 500px each.

Note: To exit the git diff command, simply press the "Q" key.

### Exercise 7: Add and commit your changes

A commit is Git's way of recording file changes, similar to how you might save an edited document. To commit the change that you made in the previous exercise, you first need to add it to a staging area.
Git will then take the staged snapshot of changes and commit them to the project. Remember, Git will never change files unless you explicitly ask it to.
To commit the new file, complete the following steps:

1. To move the changes from the working project directory to the staging area, type the following command in the Terminal window:

`git add .`

The git add command has several options. The single . adds all untracked files in the current directory and subdirectories to the staging area. Alternatively, you can add the single file you created by using the git add style.css command. Finally, you can use git add -A to recursively add all files from the top level git folder.

2. If you check the status at this point, you will see the file has changed from Untracked to Changes to be committed:
3. To commit the new file to the local repository, you need to first tell git who you are. Type in the following commands to set your email and username. The email should be the same as your GitHub email.
   Set your email:

`git config --global user.email "email@example.com"`

Set your name:

`git config --global user.name "Your Name"`

4. Type the following command in the Terminal window to commit the file. Note: It's always good practice to add a description for the commit so you can remember what the change was if you have to refer to it later.
   `-m flag:` It is used in Git commit commands to specify the commit message directly in the command line, allowing you to provide a brief description of the changes you are committing.

`git commit -m "Changing the height and the width of the circle"`

As you can see, `git status` now says there is nothing to commit and the working tree is clean. The new file is now ready to be pushed from your local system to origin on GitHub.

### Exercise 8: Merge your branch back into the main branch

If you are happy with your changes in the `feature-circle-500` branch, you can now merge it back into your local `main` branch by following these steps:

1. Confirm that you are currently in the `feature-circle-500` branch.
2. Check out the `main` branch.
   `git checkout main`

If you run `git branch` again, you should see `*` against the `main` branch.

3. Merge the `feature-circle-500` branch into `main`.
   `git merge feature-circle-500`

4. Confirm the change was merged by using the `git log` command. We are using `--oneline` flag to display logs more concisely.

Note: To exit the `git log` command, simply press the "Q" key. This action will close the log view and bring you back to the command prompt.

### Exercise 9: Delete the `feature-circle-500` branch

Since you are done making the change, let's delete the `feature-circle-500` branch by following these steps:

1. Ensure that you are on the `main` branch.

`git checkout main`

2. Delete the feature-circle-500 branch, the common flag used is -d (lowercase), which stands for "delete"

`git branch -d feature-circle-500`

3. You can confirm the branch was deleted by listing all branches:
   `git branch`

### Exercise 10: Push your changes to origin

This push will synchronize all the changes that you made on your local system with your fork repository on GitHub.
To push your update on GitHub, complete the following steps:

1. In the Terminal window, run the following command:
   `git push origin main`

2. Go to the fork repository in your GitHub account and verify that the local changes have now been added to the main branch.

### Exercise 11: Create a pull request

The final step is to request the original project pull in the changes you've made to your fork. To merge your changes to the original repository, you need to create a pull request.
To create a pull request, complete the following steps:

1. Ensure you are on the `Code` tab: Click `Contribute` and then `Open pull request`.

2. In the "Comparing changes" panel, GitHub shows you that it is comparing the main branch of your fork to that of the original repository, and that your changes can be merged. Click the `Create pull request` button.

3. You are taken to the `Open pull request` screen. Notice that your commit message appears as the title of the pull request. Click the `Create pull request` button.

Note: For the purposes of this lab, the pull request will be processed and closed automatically.

### Exercise 12: Practice on your own

1. Create a new branch called `feature-add-color`.

`git branch feature-add-color`

2. Make `feature-add-color` the active branch.

`git checkout feature-add-color`

3. Add another css rule as follows:

```
.red {
    background-color: red
}
```

4. Stage this change.
   `git add -A`

5. Commit the changes in your `feature-add-color`.

`git commit -m 'adding red color feature'`

6. Merge the changes in `feature-add-color` into `main`.

`git checkout main && git merge feature-add-color`

7. Delete the `feature-add-color` branch.

`git branch -d feature-add-color`

8. Push your changes to the origin:
   `git push orign main`

9. Create a new pull request for this feature in the upstream repository using the GitHub UI.

#### Summary

This lab demonstrated how to fork an upstream repository into your own account and then clone it locally in the lab environment. You then learned how to synchronize changes in your local repository with remote GitHub repositories using pull requests.
