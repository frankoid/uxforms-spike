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