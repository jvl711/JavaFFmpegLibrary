#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc
CCC=g++
CXX=g++
FC=gfortran
AS=as

# Macros
CND_PLATFORM=MinGW-Windows
CND_DLIB_EXT=dll
CND_CONF=Release_32bit
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/AVCodecContextJNI.o \
	${OBJECTDIR}/AVCodecJNI.o \
	${OBJECTDIR}/AVCodecParametersJNI.o \
	${OBJECTDIR}/AVFormatJNI.o \
	${OBJECTDIR}/AVFrameJNI.o \
	${OBJECTDIR}/AVPacketJNI.o \
	${OBJECTDIR}/AVStreamJNI.o \
	${OBJECTDIR}/test.o


# C Compiler Flags
CFLAGS=-m32

# CC Compiler Flags
CCFLAGS=
CXXFLAGS=

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=-L\"C\:/Program\ Files/Java/jdk1.8.0_231/lib\" -Llibraries/ffmpeg-4.2.2-win32-dev/lib -lavcodec -lavdevice -lavfilter -lavformat -lavutil -lpostproc -lswscale

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libFFmpegJNIWrapper.${CND_DLIB_EXT}

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libFFmpegJNIWrapper.${CND_DLIB_EXT}: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${LINK.c} -o ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libFFmpegJNIWrapper.${CND_DLIB_EXT} ${OBJECTFILES} ${LDLIBSOPTIONS} -shared

${OBJECTDIR}/AVCodecContextJNI.o: AVCodecContextJNI.c
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -g -I/C/Program\ Files/Java/jdk1.8.0_231/include/win32 -I/C/Program\ Files/Java/jdk1.8.0_231/include/ -Ilibraries/ffmpeg-4.2.2-win32-dev/include  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/AVCodecContextJNI.o AVCodecContextJNI.c

${OBJECTDIR}/AVCodecJNI.o: AVCodecJNI.c
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -g -I/C/Program\ Files/Java/jdk1.8.0_231/include/win32 -I/C/Program\ Files/Java/jdk1.8.0_231/include/ -Ilibraries/ffmpeg-4.2.2-win32-dev/include  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/AVCodecJNI.o AVCodecJNI.c

${OBJECTDIR}/AVCodecParametersJNI.o: AVCodecParametersJNI.c
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -g -I/C/Program\ Files/Java/jdk1.8.0_231/include/win32 -I/C/Program\ Files/Java/jdk1.8.0_231/include/ -Ilibraries/ffmpeg-4.2.2-win32-dev/include  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/AVCodecParametersJNI.o AVCodecParametersJNI.c

${OBJECTDIR}/AVFormatJNI.o: AVFormatJNI.c
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -g -I/C/Program\ Files/Java/jdk1.8.0_231/include/win32 -I/C/Program\ Files/Java/jdk1.8.0_231/include/ -Ilibraries/ffmpeg-4.2.2-win32-dev/include  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/AVFormatJNI.o AVFormatJNI.c

${OBJECTDIR}/AVFrameJNI.o: AVFrameJNI.c
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -g -I/C/Program\ Files/Java/jdk1.8.0_231/include/win32 -I/C/Program\ Files/Java/jdk1.8.0_231/include/ -Ilibraries/ffmpeg-4.2.2-win32-dev/include  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/AVFrameJNI.o AVFrameJNI.c

${OBJECTDIR}/AVPacketJNI.o: AVPacketJNI.c
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -g -I/C/Program\ Files/Java/jdk1.8.0_231/include/win32 -I/C/Program\ Files/Java/jdk1.8.0_231/include/ -Ilibraries/ffmpeg-4.2.2-win32-dev/include  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/AVPacketJNI.o AVPacketJNI.c

${OBJECTDIR}/AVStreamJNI.o: AVStreamJNI.c
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -g -I/C/Program\ Files/Java/jdk1.8.0_231/include/win32 -I/C/Program\ Files/Java/jdk1.8.0_231/include/ -Ilibraries/ffmpeg-4.2.2-win32-dev/include  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/AVStreamJNI.o AVStreamJNI.c

${OBJECTDIR}/test.o: test.c
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -g -I/C/Program\ Files/Java/jdk1.8.0_231/include/win32 -I/C/Program\ Files/Java/jdk1.8.0_231/include/ -Ilibraries/ffmpeg-4.2.2-win32-dev/include  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/test.o test.c

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
