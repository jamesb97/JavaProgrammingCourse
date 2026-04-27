# Building a Tekton Pipeline Using Java

## Overview

In this lab, you will create a simple Tekton pipeline with one task in Step 1 and then add a parameter to it in Step 4. You will learn best practices for structuring a Tekton pipeline project and how to author Tekton pipelines and tasks so that they are easy to use and parameterize. Will see that Tekton allows you to reuse your pipelines-as-code artifacts, and you will look at practical approaches to publishing your pipeline and task definitions to a Git repository.

### Learning Objectives

After completing the lab, you will be able to:

- Create a base pipelines and task to echo a message.
- Apply parameters to the task and pipeline.
- Apply additional parameters to a pipelines to clone a Git repository.

### Prerequisites

- As basic understanding of YAML
- A GitHub account
- An intermediate-level knowledge of CLIs

## Set Up the Lab Environment

### Open a Terminal

Open a terminal window by using the menu in the editor: Terminal > New Terminal.
In the terminal, change to the project folder by going to `/home/project`.
`cd /home/project`

### Clone the Code Repo

Now, get the code that you need to test. Use the `git clone` command to clone the Git repository:

`git clone https://github.com/ibm-developer-skills-network/miksg-ci-cd_Practicecode_j.git`

### Change to the Labs Directory

Once the repository is cloned, navigate to the labs directory.
`cd miksg-ci-cd_Practicecode_j/labs/01_base_pipeline/`

### Navigate to the Labs Folder

Navigate to the `labs/01_base_pipeline` folder in the left explorer panel. All of your work will be completed with the files in this folder.

### Optional

If working in the terminal becomes difficult because the command prompt is very long, you can shorten the prompt using the following command:
`export PS1="[\[\033[01;32m\]\u\[\033[00m\]: \[\033[01;34m\]\W\[\033[00m\]]\$ "`

## Step 1: Create an echo Task

In true computer programming tradition, the first task you create will echo "Hello World!" to the console.
There is starter code in the `labs/01_base_pipeline` folder for a task and a pipeline. Navigate to this folder in the left explorer panel, and open the `tasks.yaml` file to edit it:

It should look like this:

```
apiVersion: tekton.dev/v1beta1
kind: Task
metadata:
  name: <place-name-here>
spec:
  steps:
```

Will now create a `hello-world` task.

### Your Task

1. The first thing you want to do is give the task a good name. Change the `<place-name-here>` to `<hello-world>`.
2. The next thing is to add a step that run a single command to include `name`, `image`, `command`, and `args`. Make the name `echo`, using the image `alpine:3`, have the command be `[/bin/echo]` and the args be `["Hello World"]`.

#### Hint:

```
spec:
  steps:
    - name: {name here}
      image: {image here}
      command: {command here}
      args: {args here}
```

Add a step under the `steps:` tag. Since there can be multiple steps, each one starts with a dash `-` to identify it as an item in a `yaml` list.

Double-check that your work matches the solution below.

### Solution

```
apiVersion: tekton.dev/v1beta1
kind: Task
metadata:
  name: hello-world
spec:
  steps:
    - name: echo
      image: alpine:3
      command: [/bin/echo]
      args: ["Hello World!"]
```

Apply it to the cluster:
`kubectl apply -f tasks.yaml`

### Step 2: Create a hello-pipeline Pipeline

Next, you will create a very simple pipeline that only calls the `hello-world` task that you just created. Navigate to this folder in the left explorer panel, and open the `pipeline.yaml` file to edit it.

It should look like this:

```
apiVersion: tekton.dev/v1beta1
kind: Pipeline
metadata:
  name: <place-name-here>
spec:
  tasks:
```

Will now create a `hello-pipeline` pipeline.

### Your Task

1. The first thing you want to do is give the pipeline a good name. Change the `<place-name-here>` to `hello-pipeline`.
2. The next thing is to add a reference to the hello-world task you just created which needs a `name:` for the pipeline task, and a `taskRef:`, with a `name:` tag under it to set to the name of the task you are referencing. Set the name of the pipeline task to `hello` and the name of the task you are referencing as `hello-world`.

### Hint

```
spec:
  tasks:
    - name: {name here}
      taskRef:
        name: {task name here}
```

Add a task under the `tasks:` tag. Since there can be multiple tasks, each one starts with a dash `-` to identify it as an item in a `yaml` list.
Double-check that your work matches the solution below.

#### Solution

```
apiVersion: tekton.dev/v1beta1
kind: Pipeline
metadata:
  name: hello-pipeline
spec:
  tasks:
    - name: hello
      taskRef:
        name: hello-world
```

Apply it to the cluster:

`kubectl apply -f pipeline.yaml`

Are now ready to run the pipeline and see if it works.

## Step 3: Run the hello-pipeline

Run the pipeline using the Tekton CLI:
`tkn pipeline start --showlog hello-pipeline`
You should see the output:

```
PipelineRun started: hello-pipeline-run-9vkbb
Waiting for logs to be available...
[hello : echo] Hello World!
```

Just ran your first pipeline from a pipeline and task that you created.

## Step 4: Add a parameter to the task

Hopefully the hello-world task has given you a sense for how pipelines call tasks. Now it is time to make that task a little more useful by making it print any message that you want, not just the "Hello World".
To do this, you will add a parameter called `message` to the task and use that parameter as the message that it echoes. Will also rename the task to `echo`.

Edit the `tasks.yaml` file to add the parameter to both the input and the echo command:

### Your Task

1. Change the name of the task from `hello-world` to `echo` to more accurately reflect its new functionality, by changing the `name:` in the `metadata:` section.
2. Add a `params:` section to the task with a parameter that has a `name`: of "message", a `type:` of : "string", and a `description` of "The message to echo".
3. Change the name of the step from `echo` to `echo-message` to better describe its new functionality.
4. Modify the `args:` tag to use the message parameter you just created.

#### Hint

```
metadata:
  name: {change the task name}
spec:
  params:
    - name: {name here}
      description: {description here}
      type: {type here}
  steps:
    - name: echo-message
      image: alpine:3
      command: [/bin/echo]
      args: {place parameter reference here}
```

Add a `params:` section under the `spec:` tag. Since there can be multiple parameters, each one starts with a dash `-` to identify it as an item in a `yaml` list.
Double-check that your work matches the solution below:

#### Solution

```
apiVersion: tekton.dev/v1beta1
kind: Task
metadata:
  name: echo
spec:
  params:
    - name: message
      description: The message to echo
      type: string
  steps:
    - name: echo-message
      image: alpine:3
      command: [/bin/echo]
      args: ["$(params.message)"]
```

Apply the new task definition to the cluster:

`kubectl apply -f tasks.yaml`

## Step 5: Update the hello-pipeline

Now need to update the pipeline to pass the message that you want to send to the `echo` task so that it can echo the message to the console.
Edit the `pipeline.yaml` file to add the parameter:

#### Your Task

1. Add a `params:` section to the pipeline under `spec:`, with a parameter that has a `name:` of "message".
2. Change the `name:` of the `taskRef:` from `hello-world` to the new echo task.
3. Add a `params:` section to the task, with a parameter that has a `name:` of "message" and a `value:` that is a reference to the pipeline parameter for `params.message`.

#### Hint

```
spec:
  params:
    - name: {parameter name here}
  tasks:
    - name: hello
      taskRef:
        name: {change parameter value here}
      params:
        - name: {task parameter name here}
          value: "$(params.message)"
```

Double-check that your work matches the solution below.

#### Solution

```
apiVersion: tekton.dev/v1beta1
kind: Pipeline
metadata:
  name: hello-pipeline
spec:
  params:
    - name: message
  tasks:
    - name: hello
      taskRef:
        name: echo
      params:
        - name: message
          value: "$(params.message)"
```

Apply it to the cluster:
`kubectl apply -f pipeline.yaml`

## Step 6: Run the message-pipeline

Run the pipeline using the Tekton CLI:

```
tkn pipeline start hello-pipeline \
    --showlog  \
    -p message="Hello Tekton!"
```

Should see the following output:

```
PipelineRun started: hello-pipeline-run-9qf42
Waiting for logs to be available...
[hello : echo-message] Hello Tekton!
```

Have just created and ran a pipeline that requires a parameter.

## Step 7: Create a Checkout Task

In this step, will combine the knowledge of running a command in a container with your knowledge of passing parameters, to create a task that checks out your code from GitHub as the first step in a CD pipeline.

### Create checkout task

Can have multiple definitions in a single yaml file by separating them with three dashes `---` on a single line. In this step, will add a new task to `tasks.yaml` that uses `itnami/git:latest` image to run the `git` command passing in the branch name and URL of the repo you want to clone.

Open the `tasks.yaml` file to create a new task:

Add three dashes on a separate line:
`---`
Are now ready to add your new task.

### Your Task

Your new task is to create a Tekton task that accepts a repository URL and a branch name and calls `git clone` to clone your source code.

1. Create a new task and name it `checkout`.
2. Add a parameter named `repo-url` with a `type:` of string and a `description:` of "The URL of the git repo to clone".
3. Add a second parameter named `branch` with a `type:` of string and a `description:` of "The branch to clone".
4. Add a step with the `name:` "checkout" that uses the `bitnami/git:latest` image to run the `git` command by specifying `clone` and `--branch` parameters and passing both the params created in spec as the arguments.

### Hint

```
apiVersion: tekton.dev/v1beta1
kind: Task
metadata:
  name: {name of task here}
spec:
  params:
    - name: {1st parameter name here}
      description: {1st parameter name here}
      type: string
    - name: {2nd parameter name here}
      description: {2nd parameter name here}
      type: string
  steps:
    - name: {step name here}
      image: {image name here}
      command: [{command name here}]
      args: [{arguments here}]
```

Pass arguments to the checkout task as `["clone", "--branch", "(params.repo-url)"]`.
Double-check that your work matches the solution below.

#### Solution

```
---
apiVersion: tekton.dev/v1beta1
kind: Task
metadata:
  name: checkout
spec:
  params:
    - name: repo-url
      description: The URL of the git repo to clone
      type: string
    - name: branch
      description: The branch to clone
      type: string
  steps:
    - name: checkout
      image: bitnami/git:latest
      command: [git]
      args: ["clone", "--branch", "$(params.branch)", "$(params.repo-url)"]
```

Apply it to the cluster:
`kubectl apply -f tasks.yaml`
The output should look like the following:

```
task.tekton.dev/echo configured
task.tekton.dev/checkout created
```

The `echo` task was unchanged and the `checkout` task has been created.

## Step 8: Create the cd-pipeline Pipeline

Finally, you will create a pipeline called `cd-pipeline` to be the stating point of your Continuous Delivery pipeline.
Open the `pipeline.yaml` file to create a new pipeline called `cd-pipeline`:
Can use the `---` on a separate line to separate your new pipeline, or can modify the existing pipeline to look like the new one.

### Your Task

1. Create a new pipeline and name it `cd-pipeline`.
2. Add two parameters named `repo-url` and `branch`.
3. Set the `default:` for branch to "master".
4. Add a task with the `name:` "clone" that has a `taskRef:` to the `checkout` task that you just created.
5. Add the two parameters `repo-url` and `branch` to the task, mapping them back to the pipeline parameters of the same name.

#### Hint

```
spec:
  params:
    - name: {1st parameter name here}
    - name: {2nd parameter name here}
      default: {default value here}
  tasks:
    - name: {pipeline task name here}
      taskRef:
        name: {Task name here}
      params:
      - name: {1st parameter name here}
        value: "$(params.repo-url)"
      - name: {2nd parameter name here}
        value: "$(params.branch)"
```

Add params of name `repo-url` and `branch` with values as "(params.branch)" to the `clone` task.
Double-check that your work matches the solution below.

#### Solution

```
---
apiVersion: tekton.dev/v1beta1
kind: Pipeline
metadata:
  name: cd-pipeline
spec:
  params:
    - name: repo-url
    - name: branch
      default: "master"
  tasks:
    - name: clone
      taskRef:
        name: checkout
      params:
      - name: repo-url
        value: "$(params.repo-url)"
      - name: branch
        value: "$(params.branch)"
```

Apply it to the cluster:
`kubectl apply -f pipeline.yaml`

## Step 9: Run the cd-pipeline

Run the pipeline using the Tekton CLI:

```
tkn pipeline start cd-pipeline \
    --showlog \
    -p repo-url="https://github.com/ibm-developer-skills-network/miksg-ci-cd_Practicecode_j.git" \
    -p branch="main"
```

The output should look like this:

```
PipelineRun started: cd-pipeline-run-rf6zp
Waiting for logs to be available...
[clone : checkout] Cloning into 'miksg-ci-cd_Practicecode_j'...
```

## Step 10: Fill Out cd-pipeline with Placeholders

In this final step, will fill out the rest of the pipeline with calls to the `echo` task to simply display a message for now. You will replace these "placeholder" tasks with real ones in the future labs.

Update the pipeline.yaml file to include the placeholders tasks.

Now, you will add four tasks to the pipeline to lint, unit test, build, and deploy. All of these pipelines tasks will reference the `echo` task for now.

### Your Task

Create a pipeline task for each of these:

| Task Name   | Build After | Message                                                     |
| ----------- | ----------- | ----------------------------------------------------------- |
| lint        | clone       | Linting code with CheckStyle...                             |
| tests       | lint        | Running unit tests with Junit...                            |
| setup-maven | tests       | Setting up Maven...                                         |
| build       | setup-maven | Building image for $(params.repo-url) ...                   |
| deploy      | build       | Deploying $(params.branch) branch of $(params.repo-url) ... |

#### Hint

```
spec:
  tasks:
    - name: {pipeline task name here}
      taskRef:
        name: echo
      params:
      - name: message
        value: {message to display here}
      runAfter:
        - {name of previous task}

	...
```

Will now have a base pipeline to build the rest of the tasks into:

Double-check that your work matches the solution below.

#### Solution

```
apiVersion: tekton.dev/v1beta1
kind: Pipeline
metadata:
  name: cd-pipeline
spec:
  params:
    - name: repo-url
    - name: branch
      default: "master"
  tasks:
    - name: clone
      taskRef:
        name: checkout
      params:
      - name: repo-url
        value: "$(params.repo-url)"
      - name: branch
        value: "$(params.branch)"

    - name: lint
      taskRef:
        name: echo
      params:
      - name: message
        value: "Linting code with CheckStyle..."
      runAfter:
        - clone
    - name: tests
      taskRef:
        name: echo
      params:
      - name: message
        value: "Running unit tests with Junit..."
      runAfter:
        - lint
    - name: setup-maven
      taskRef:
        name: echo
      params:
      - name: message
        value: "Setting up Maven..."
      runAfter:
        - tests

    - name: build
      taskRef:
        name: echo
      params:
      - name: message
        value: "Building image for $(params.repo-url) ..."
      runAfter:
        - setup-maven

    - name: deploy
      taskRef:
        name: echo
      params:
      - name: message
        value: "Deploying $(params.branch) branch of $(params.repo-url) ..."
      runAfter:
        - build
```

Apply it to the cluster:

```
kubectl apply -f pipeline.yaml
```

## Step 11: Run the cd-pipeline

Run the pipeline using the Tekton CLI:

```
tkn pipeline start cd-pipeline \
    --showlog \
    -p repo-url="https://github.com/ibm-developer-skills-network/miksg-ci-cd_Practicecode_j.git" \
    -p branch="main"
```

The output will look like the following:

```
PipelineRun started: cd-pipeline-run-wvfzx
Waiting for logs to be available...
[clone : checkout] Cloning into 'miksg-ci-cd_Practicecode_j'...

[lint : echo-message] Linting code with CheckStyle…

[tests : echo-message] Running unit tests with Junit…

[setup-maven : echo-message] Setting up Maven…

[build : echo-message] Building image for https://github.com/ibm-developer-skills-network/miksg-ci-cd_Practicecode_j.git ...

[deploy : echo-message] Deploying main branch of https://github.com/ibm-developer-skills-network/miksg-ci-cd_Practicecode_j.git ...
```

## Conclusion

Are now able to create a Tekton pipeline and pass parameters to a pipeline.

In this lab, learned how to create a base pipeline, specify and pass parameters to a task and pipeline. Learned how to modify the pipeline to reference the task and configure its parameters. Also learned how to pass additional parameters to a pipeline and how to run it to echo and clone a Git repository.

### Next Steps

- Learn and use GitHub Triggers in the next lab.
