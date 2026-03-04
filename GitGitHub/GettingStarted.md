# Getting Started with Branches using Git Commmands

## Learning Objectives

1. Create a new local repository using `git init`
2. Create and add a file to the repo using `git add`
3. Commit changes using `git commit`
4. Create a branch using `git branch`
5. Switch to a branch using `git checkout`
6. Check the status of files changed using `git status`
7. Review recent commits using `git log`
8. Revert changes using `git revert`
9. Get a list of branches and active branches using `git branch`
10. Merge changes in your active branch into another branch using `git merge`

### Exercise 1: Create a new local repo

1. Create a myrepo directory by running the following command: `mkdir myrepo`
2. Go inside the new directory by running the following command: `cd myrepo`
3. Initiate the `myrepo` directory as a git repository by using the `git init` command.
4. A local git repository is now initiated with a `.git` folder containing all of the git files, which can be verified by doing a directory listing using the `ls -la .git` command into the terminal window.

### Exercise 2: Create and add a file to the local repo

1. Create an empty file named `newfile` using the following `touch` command.
   `touch newfile`
2. Add the file to the repo using the following `git add` command.
   `git add newfile`

### Exercise 3: Commit changes

1. Before commiting the changes, we need to tell Git who we are. We can do this by using the following commands. Replace "you@example.com" with the email address used to login to GitHub.

`git config --global user.email "you@example.com"
`

`git config --global user.name "Your Name"
`

Once done, we can then commit the changes using the `git commit` command.
Note that the commit requires a message, which can be included using the -m parameter.

`git commit -m "added newfile"`

### Exercise 4: Create a branch

1.The previous commit created a default branch called `main`. 2. In order to make subsequent changes in the repository, create a new branch in the local repository by running the following command `git branch` followed by the branch name.

`git branch my1stbranch`

### Exercise 5: Get a list of branches and active branch

1. Check the list of branches contained in the repository by runnning the following command:
   `git branch`
2. Note that the output lists two branches: The default `main` branch with an asterisk next to it, indicating that is the current active branch and the newly created branch `my1stbranch`

### Exercise 6: Switch to a different branch

1. When working with a different branch, switch to the active branch by running the following command:
   `git checkout my1stbranch`
2. Verify that the new branch is now the active branch by running the following command:
   `git branch`

Note that the asterisk `*` is now next to the `my1stbranch`, indicating that it is now active.
As a shortcut, instead of creating a branch using `git branch` and then making it active using `git checkout`, we can use the git checkout command followed by the `-b` option that creates the branch and makes it active in one step.
`git checkout -b my1stbranch`

### Exercise 7: Make changes in your branch and check the status of files added or changed

1. Make some changes to the new branch, called `my1stbranch`. Start by adding some text to the `newfile` by running the following command into the terminal that will append the string "Here is some text in my newfile". into the file.

`echo 'Here is some text in my newfile.' >> newfile
`

2. Verify that the text has been added by running the following command:
   `cat newfile`

3. Now, create another file called `readme.md` using the following command:
   `touch readme.md`

4. Add the file to the repo with the following command:
   `git add readme.md`

5. We can easily verify the changes in the current branch using the `git status` command.

6. The output of the git status command shows that the file `readme.md` has been added to the branch and is ready to be committed. However, even though the file has been modified is has not been explicitly added using `git add`, and hence it is not ready to be committed.

7. A shortcut to adding all modifications and additions is to use the following `git add` command with an asterisk `*`. This will also add the modified file `newfile` to the branch and make it ready to be committed.
   `git add *`

8. Check the status: `git status`
9. The output will show that both the files can now be committed.

### Exercise 8: Commit and review commit history

1. Commit the changes to the branch using the following commit command followed by a message indicating the changes.
   `git commit -m "added readme.md modified newfile"`
2. We can then run the following git log command to get a history of the recent commits: `git log`

3. The log shows two recent commits. The last commit to `my1stbranch` as well as the previous commit to `main`.
   Note: To exit the `git log` command, simply press the "Q" key. This action will close the log view and bring back to the command prompt.

### Exercise 9: Revert committed changes

1. Sometimes, we may not fully test the changes before committing them, which may have an undesirable consequence. We can back out the changes by using a `git revert` command like the following.
   We can either specify the ID of the commit that can be seen from the previous log output, or by using the shortcut `HEAD` to rollback the last commit:

`git revert HEAD --no-edit`

NOTE: 1. If you we don't specify the `--no-edit` flag, it may be presented with an editor screen showing the message with changes to be reverted. In that case, press the Control (or Ctrl) key simultaneously with X. 2. `git revert HEAD` does not delete a file, it adds a new commit that undoes the changes introduced by the previous commit.

2. The output shows the most recent commit with the specified id that has been reverted.

### Exercise 10: Merge the changes into another branch

1. Let's make one more change in the currently active `my1stbranch` using the following commands:

```
touch goodfile
git add goodfile
git commit -m "added goodfile"
git log
```

2. The output of the log shows the newly added `goodfile` has been committed to the `my1stbranch` branch:

Note: To exit the `git log` command, simply press the "Q" key. This action will close the log view and bring back to the command prompt.

3. Now, let's merge the contents of the `my1stbranch` into the `main` branch. First, make the main branch active using the `git checkout main` command.

4. Now, let's merge the changes from `my1stbranch` into the main branch.

`git merge my1stbranch`

`git log`

5. The output and log will show the successful merging of the branch.

6. Now that the changes have been merged into the `main` branch, the `myfirstbranch` can be deleted using the following `git branch` command with the -d option:
   `git branch -d myfirstbranch`

### Exercise 11: Practice

1. Create a new directory and branch called `newbranch`
2. Make a `newbranch` the active branch
3. Create an empty file called `newbranchfile`
4. Add the newly created file to your branch
   5. Commit the changes in your `newbranch`
5. Revert the last committed changes
6. Create a new file called `newgoodfile`
7. Add the latest file to `newbranch`
8. Commit the changes
9. Merge the changes in `newbranch` into `main`

#### Summary

This lab demonstrated how to create and work with branches using Git commands in a local repository.
