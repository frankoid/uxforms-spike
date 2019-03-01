# uxforms-starter-formdefinition

This is a seed project to quickly get up and running creating a new form definition for UX Forms.

It demonstrates how a simple form can be defined, as well as how it can be tested.

## Usage

Create a directory for your form definition, then in that directory:

`git init .`

`git pull git@bitbucket.org:uxforms/uxforms-starter-formdefinition.git master`

You'll now have a working `sbt` project that you can modify as you need.


## Sbt tasks

`osgiBundle` Creates a jar artifact with the additional Manifest entries required by OSGi. This is what you will load in to UX Forms to deploy your form definition.

See UX Forms' [sbt plugin page](https://bitbucket.org/uxforms/uxforms-formdefinition-sbt-plugin/overview) for full details,
including how to configure sbt to deploy and undeploy your form from sbt. 

## Running locally

This is an interim hack until everything is incorporated into UX Forms' sbt plugin.

You're effectively getting a sneak-peek of UX Forms 2.0, and running the form within its own dedicated docker container.
As a result this won't catch any OSGi packaging snafus (which you may encounter when deploying to the Aiken environment).

### Setup

Run `.setupDockerComposeEnv.sh` to create a `.env` file in the current directory.

The following are hard-coded in the script (and can be either corrected in the script or in the resultant `.env` file)

* Form name
* FormDefinition class name and package
* Relative path to theme's target directory
* Theme name

### Running

`docker-compose up`

Form available on http://localhost:9000/{formName}, i.e. http://localhost:9000/uxforms-spike-francis

Logs can be seen in the console, and a remote debugger can be attached on port `5000`.

### Seeing changes

#### Changing theme contents

Re-run `gulp package` and refresh the browser window

#### Changing form definition

Stop the docker containers, run `sbt package`, then run `docker-compose up` again. 