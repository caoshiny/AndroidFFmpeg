#!/bin/bash
NDK=/home/ubuntu/ffmpeg/android-ndk-r21e
HOST_TAG=linux-x86_64
TOOLCHAIN=$NDK/toolchains/llvm/prebuilt/$HOST_TAG
API=21
ARCH=arm64
CPU=armv8-a
TRIPLE=aarch64-linux-android
STRIP=aarch64-linux-android
OPTIMIZE_CFLAGS="-march=$CPU"
CC=$TOOLCHAIN/bin/$TRIPLE$API-clang
CXX=$TOOLCHAIN/bin/$TRIPLE$API-clang++
SYSROOT=$NDK/toolchains/llvm/prebuilt/$HOST_TAG/sysroot
CROSS_PREFIX=$TOOLCHAIN/bin/$STRIP-
PREFIX=$(pwd)/android/$CPU
function build_android
{
echo "Compiling FFmpeg for $CPU and prefix is $PREFIX"
./configure \
 --prefix=$PREFIX \
 --enable-neon \
 --disable-hwaccels \
 --disable-gpl \
 --disable-postproc \
 --enable-shared \
 --enable-jni \
 --enable-small \
 --enable-mediacodec \
 --enable-decoder=h264_mediacodec \
 --disable-static \
 --disable-doc \
 --disable-programs \
 --disable-encoders \
 --disable-muxers \
 --disable-filters \
 --disable-ffmpeg \
 --disable-ffplay \
 --disable-ffprobe \
 --disable-avdevice \
 --disable-symver \
 --cross-prefix=$CROSS_PREFIX \
 --target-os=android \
 --arch=$ARCH \
 --cpu=$CPU \
 --cc=$CC \
 --cxx=$CXX \
 --enable-cross-compile \
 --sysroot=$SYSROOT \
 --extra-cflags="-Os -fpic $OPTIMIZE_CFLAGS" \
 --extra-ldflags="$ADDI_LDFLAGS" \
 $ADDITIONAL_CONFIGURE_FLAG
echo "The Compilation of FFmpeg for $CPU is completed"
}
echo "set arm cup"
function build
{
 build_android
 echo "start make"
 make clean
 make -j4
 echo "start make install"
 make install
 echo "make finish"
}
build