#/bin/bash

CURRENT_STARTER_VERSION=$(xmllint --xpath "//*[local-name()='project']/*[local-name()='version']/text()" ../../pom.xml)
echo "Current ngrok-spring-boot-starter version is $CURRENT_STARTER_VERSION"

sed -i "s/ci-version-placeholder/$CURRENT_STARTER_VERSION/" ../test-app/pom.xml

TEST_APP_STARTER_DEPENDENCY_VERSION=$(xmllint --xpath "//*[local-name()='project']/*[local-name()='properties']/*[local-name()='ngrok-spring-boot-starter.version']/text()" ../test-app/pom.xml)
echo "Starter dependency version successfully updated to $TEST_APP_STARTER_DEPENDENCY_VERSION"