# CHANGES

## v0.6
- Added Jenkins pipline to automate the build process of win32, win64 and linux builds
- Removed netbeans automated Makefile and created a custom one for the C portion of the project
- Added Linux build to the library

## v0.5
* Added Stream ID to the AVStreams. This is required for SageTV for .ts/.ps files. 
* Added a bunch of additional functionality working towards the ability to transcode 

## v.0.4-alpha
* Added the ability to get metadata from containers and streams
* Added the ability to get embeded pictures from stream
* Made some breaking structure changes to the library.  Some of the method prototypes have changed.  And methods have been removed.  I expect this to continue during alpha
* I made some changes to how versioning works when building the library.  It now includes a build time and build number, as well as the version

## v.0.2-alpha
* Added two build profiles.  Win32 and Win64
* Upgraded the FFmpeg liraries to version 4.2.2
* Added some change to the JavaFFmpeg ant script to build the release zip files

## v.0.1-alpha
* Initial release
