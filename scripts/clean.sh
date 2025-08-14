killall java
echo "Cleaning up dev files ..."
cd ..
echo "cleaning Node Projects Pods folders ..."
find . -name node_modules | sed -e 's/[[:space:]]/\\ /g' | xargs rm -rf
echo "cleaning iOS Cocoapods Projects Pods folders ..."
find . -name Pods | sed -e 's/[[:space:]]/\\ /g' | xargs rm -rf
echo "cleaning iOS SPM Projects Pods folders ..."
find . -name .build | sed -e 's/[[:space:]]/\\ /g' | xargs rm -rf
echo "cleaning Android Projects build folders ..."
find . -name build | sed -e 's/[[:space:]]/\\ /g' | xargs rm -rf
echo "cleaning Gradle Projects node_modules folders ..."
find . -name .gradle | sed -e 's/[[:space:]]/\\ /g' | xargs rm -rf
echo "cleaning Apple Projects Carthage folders ..."

rm -rf .idea
rm -rf .kotlin
mkdir .idea
cp xcode.xml .idea
echo "cleaning Kotlin Multiplatform Projects Carthage folders ..."

echo "Clean up finished!"