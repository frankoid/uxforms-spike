#!/usr/bin/env bash

parseVersionNumber () {
    local returnVal=$1
    local result=$(grep 'version\s*:=' *.sbt | sed -En "s/version.*\"(.*)\"/\1/p")
    eval $returnVal="'$result'"
}

parseVersionNumber versionNumber

writeDockerComposeEnv () {
cat >.env <<EOL
UXFORMS_FORMDEF_PORT=9000
UXFORMS_DEBUG_PORT=5000
UXFORMS_FORMDEF_JAR_DIR=$PWD/target/scala-2.11
UXFORMS_FORMDEF_LIB_DIR=$PWD/lib
UXFORMS_STATICSERVER_PORT=8080
UXFORMS_FILESERVER_PORT=8088
UXFORMS_THEME_DIR=$PWD/../uxforms-themes-govuk/target
UXFORMS_THEME_NAME=govuk-francis
UXFORMS_FORM_NAME=uxforms-spike-francis
UXFORMS_FORM_VERSION=$versionNumber
UXFORMS_FORM_FACTORYCLASSNAME=com.example.form.MyFormDefinitionFactory
EOL
}

writeDockerComposeEnv