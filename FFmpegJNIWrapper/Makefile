SHELL = /bin/sh
#Possible location for download ffmpeg libraries: https://github.com/BtbN/FFmpeg-Builds/releases

#CROSS_PREFIX is used for cross compiling to other formats (i686-w64-mingw32-, x86_64-w64-mingw32-)
CC = $(CROSS_PREFIX)gcc 

#Determine what build target we are creating.  Winx32, Winx64 or Linux
UNAME_S := $(shell uname -s)
ifeq ($(UNAME_S),Linux)

	TARGET = linux
	OUTPUT_PREFIX = lib
	OUTPUT_EXT = .so

	ifeq ($(CROSS_PREFIX),i686-w64-mingw32-)

		OUTPUT_PREFIX = 
		TARGET = win32
		OUTPUT_EXT = .dll

	endif
	ifeq ($(CROSS_PREFIX),x86_64-w64-mingw32-)

		OUTPUT_PREFIX =
		TARGET = win64
		OUTPUT_EXT = .dll

	endif

else

	TARGET =

endif

FFMPEG_BIN = ./libraries/ffmpeg-4.4.2-$(TARGET)/bin
FFMPEG_LIB = ./libraries/ffmpeg-4.4.2-$(TARGET)/lib
FFMPEG_INCLUDE = ./libraries/ffmpeg-4.4.2-$(TARGET)/include
JAVA_PLATFORM_INCLUDE = ./libraries/jni/$(TARGET)
JAVA_INCLUDE = ./libraries/jni

CFLAGS = -fPIC
INCLUDE = -I$(FFMPEG_INCLUDE) -I$(JAVA_INCLUDE) -I$(JAVA_PLATFORM_INCLUDE)
LDLIBSOPTIONS=-L$(FFMPEG_LIB) -lavcodec -lavdevice -lavfilter -lavformat -lavutil -lpostproc -lswscale
OBJECTDIR = obj/$(TARGET)
OUTPUTDIR = ./build/$(TARGET)

.PHONY: all
all: prereqs $(OUTPUTDIR)/$(OUTPUT_PREFIX)FFmpegJNIWrapper$(OUTPUT_EXT) copydepends
	
$(OUTPUTDIR)/$(OUTPUT_PREFIX)FFmpegJNIWrapper$(OUTPUT_EXT):  ${OBJECTDIR}/Utility.o ${OBJECTDIR}/AVCodecContextJNI.o ${OBJECTDIR}/AVCodecJNI.o ${OBJECTDIR}/AVCodecParametersJNI.o ${OBJECTDIR}/AVFormatJNI.o ${OBJECTDIR}/AVFrameJNI.o ${OBJECTDIR}/AVGlobalJNI.o ${OBJECTDIR}/AVPacketJNI.o ${OBJECTDIR}/AVStreamJNI.o 
	$(CC) -shared -o $@ $^ ${LDLIBSOPTIONS}

${OBJECTDIR}/AVCodecContextJNI.o: AVCodecContextJNI.c
	$(CC) ${CFLAGS} ${INCLUDE} -c $< -o $@

${OBJECTDIR}/AVCodecJNI.o: AVCodecJNI.c
	$(CC) ${CFLAGS} ${INCLUDE} -c $< -o $@

${OBJECTDIR}/AVCodecParametersJNI.o: AVCodecParametersJNI.c
	$(CC) ${CFLAGS} ${INCLUDE} -c $< -o $@

${OBJECTDIR}/AVFormatJNI.o: AVFormatJNI.c
	$(CC) ${CFLAGS} ${INCLUDE} -c $< -o $@

${OBJECTDIR}/AVFrameJNI.o: AVFrameJNI.c
	$(CC) ${CFLAGS} ${INCLUDE} -c $< -o $@

${OBJECTDIR}/AVGlobalJNI.o: AVGlobalJNI.c
	$(CC) ${CFLAGS} ${INCLUDE} -c $< -o $@

${OBJECTDIR}/AVPacketJNI.o: AVPacketJNI.c
	$(CC) ${CFLAGS} ${INCLUDE} -c $< -o $@

${OBJECTDIR}/AVStreamJNI.o: AVStreamJNI.c
	$(CC) ${CFLAGS} ${INCLUDE} -c $< -o $@

${OBJECTDIR}/Utility.o: Utility.c
	$(CC) ${CFLAGS} ${INCLUDE} -c $< -o $@

.PHONY: copydepends 
copydepends:
ifeq ($(TARGET),linux)
	cp -u $(FFMPEG_LIB)/libavcodec.so $(OUTPUTDIR)/libavcodec-58.so
	cp -u $(FFMPEG_LIB)/libavdevice.so $(OUTPUTDIR)/libavdevice-58.so
	cp -u $(FFMPEG_LIB)/libavfilter.so $(OUTPUTDIR)/libavfilter-7.so
	cp -u $(FFMPEG_LIB)/libavformat.so $(OUTPUTDIR)/libavformat-58.so
	cp -u $(FFMPEG_LIB)/libavutil.so $(OUTPUTDIR)/libavutil-56.so
	cp -u $(FFMPEG_LIB)/libavfilter.so $(OUTPUTDIR)/libavfilter-7.so
	cp -u $(FFMPEG_LIB)/libpostproc.so $(OUTPUTDIR)/libpostproc-55.so
	cp -u $(FFMPEG_LIB)/libswresample.so $(OUTPUTDIR)/libswresample-3.so
	cp -u $(FFMPEG_LIB)/libswscale.so $(OUTPUTDIR)/libswscale-5.so
endif
ifeq ($(TARGET),win32)
	cp -u $(FFMPEG_BIN)/avcodec-58.dll $(OUTPUTDIR)/avcodec-58.dll
	cp -u $(FFMPEG_BIN)/avdevice-58.dll $(OUTPUTDIR)/avdevice-58.dll
	cp -u $(FFMPEG_BIN)/avfilter-7.dll $(OUTPUTDIR)/avfilter-7.dll
	cp -u $(FFMPEG_BIN)/avformat-58.dll $(OUTPUTDIR)/avformat-58.dll
	cp -u $(FFMPEG_BIN)/avutil-56.dll $(OUTPUTDIR)/avutil-56.dll
	cp -u $(FFMPEG_BIN)/avfilter-7.dll $(OUTPUTDIR)/avfilter-7.dll
	cp -u $(FFMPEG_BIN)/postproc-55.dll $(OUTPUTDIR)/postproc-55.dll
	cp -u $(FFMPEG_BIN)/swresample-3.dll $(OUTPUTDIR)/swresample-3.dll
	cp -u $(FFMPEG_BIN)/swscale-5.dll $(OUTPUTDIR)/swscale-5.dll
endif
ifeq ($(TARGET),win64)
	cp -u $(FFMPEG_BIN)/avcodec-58.dll $(OUTPUTDIR)/avcodec-58.dll
	cp -u $(FFMPEG_BIN)/avdevice-58.dll $(OUTPUTDIR)/avdevice-58.dll
	cp -u $(FFMPEG_BIN)/avfilter-7.dll $(OUTPUTDIR)/avfilter-7.dll
	cp -u $(FFMPEG_BIN)/avformat-58.dll $(OUTPUTDIR)/avformat-58.dll
	cp -u $(FFMPEG_BIN)/avutil-56.dll $(OUTPUTDIR)/avutil-56.dll
	cp -u $(FFMPEG_BIN)/avfilter-7.dll $(OUTPUTDIR)/avfilter-7.dll
	cp -u $(FFMPEG_BIN)/postproc-55.dll $(OUTPUTDIR)/postproc-55.dll
	cp -u $(FFMPEG_BIN)/swresample-3.dll $(OUTPUTDIR)/swresample-3.dll
	cp -u $(FFMPEG_BIN)/swscale-5.dll $(OUTPUTDIR)/swscale-5.dll
endif

.PHONY: prereqs 
prereqs:
ifndef TARGET
	$(error TEST is not set)
endif
	mkdir -p $(OBJECTDIR)
	mkdir -p $(OUTPUTDIR)

.PHONY: clean
clean:
	rm -fr $(OBJECTDIR)
	rm -fr $(OUTPUTDIR)


test: test.o
	$(CC) -o test test.o -Llibraries/ffmpeg-4.4.2-win64-dev/lib -lavcodec -lavdevice -lavfilter -lavformat -lavutil -lpostproc -lswscale -static

test.o: test.c
	$(CC) -Ilibraries/ffmpeg-4.4.2-win64-dev/include -c test.c -o test.o

