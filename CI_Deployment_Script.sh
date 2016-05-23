
mkdir upload
mkdir upload/$TRAVIS_BRANCH-build-$TRAVIS_BUILD_NUMBER/
#GZ the .apk file and copy it to output
gzip android/build/outputs/apk/android-debug.apk
mv android/build/outputs/apk/android-debug.apk.gz upload/$TRAVIS_BRANCH-build-$TRAVIS_BUILD_NUMBER/
#GZ the .jar file and copy it to output
gzip desktop/build/libs/desktop-1.0.jar
mv desktop/build/libs/desktop-1.0.jar.gz upload/$TRAVIS_BRANCH-build-$TRAVIS_BUILD_NUMBER/

#Remove everything so travis does not get confused
#rm -f *
#rm -rf build
#rm -rf gradle

