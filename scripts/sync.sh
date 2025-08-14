killall java
cd ..

./gradlew syncDebugLibJars
./gradlew :kapp:composeApp:resolveIdeDependencies --refresh-dependencies
./gradlew prepareKotlinBuildScriptModel
./gradlew prepareKotlinIdeaImport