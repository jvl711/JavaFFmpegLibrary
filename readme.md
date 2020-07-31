# JavaFFmpegLibrary

This is a (Java Native Interface) JNI wrapper around FFmpeg library.  The intention
of this library is to be able to more easily utilize the functionality of FFmpeg inside
of SageTV customizations.  I plan on adding additional functionallity to this library
as it is needed.  Right now the library contained enough of the FFmpeg functionality
to be able to determine the contents of a media file for creating a format detection
plugin for SageTV.

## Build Status ##
![Project CI CI](https://github.com/jvl711/JavaFFmpegLibrary/workflows/Project%20CI%20CI/badge.svg)

## Getting Started

There are two Netbeans projects in this repository.

* JavaFFmpeg - This is the java library that wraps the functionality of FFmpeg
* FFmpegJNIWrapper - This is the C dll project that provides the JNI entry points for the java library

When you build the JavaFFmpeg project it creates the c header files for all of the c native calls, 
and places them into the FFmpegJNIWrapper project.  When you build the FFmpegJNIWrapper project
it creates a dll and places it and all of the FFmpeg dlls into the JavaFFmpeg project.

The project uses the mingw64 to build the FFmpegJNIWrapper dll.  You would need to have
that library setup to be able to add addition wrapper methods to the project.

### Prerequisites
* mingw64
* java
* netbeans or ant

## Authors
* Joshua Lewis (jvl711)
