//#include <jni.h>
//
//#include <unistd.h>

//extern "C" JNIEXPORT jstring JNICALL
//Java_com_shiny_ffmpegdemo_MainActivity_stringFromJNI(
//        JNIEnv* env,
//        jobject /* this */) {
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
//}

//extern "C" {
//#include <libavcodec/avcodec.h>
//#include <libavformat/avformat.h>
//#include <libavfilter/avfilter.h>
//#include <libavcodec/jni.h>
//JNIEXPORT jstring JNICALL
//Java_com_shiny_ffmpegdemo_MainActivity_ffmpegInfo(JNIEnv *env, jobject  /* this */) {
//
//    char info[40000] = {0};
//    AVCodec *c_temp = av_codec_next(NULL);
//    while (c_temp != NULL) {
//        if (c_temp->decode != NULL) {
//            sprintf(info, "%sdecode:", info);
//        } else {
//            sprintf(info, "%sencode:", info);
//        }
//        switch (c_temp->type) {
//            case AVMEDIA_TYPE_VIDEO:
//                sprintf(info, "%s(video):", info);
//                break;
//            case AVMEDIA_TYPE_AUDIO:
//                sprintf(info, "%s(audio):", info);
//                break;
//            default:
//                sprintf(info, "%s(other):", info);
//                break;
//        }
//        sprintf(info, "%s[%s]\n", info, c_temp->name);
//        c_temp = c_temp->next;
//    }
//
//    return env->NewStringUTF(info);
//}
//}

#include <jni.h>
#include "ffmpeg.h"
JNIEXPORT jint
JNICALL
Java_com_shiny_ffmpegdemo_utils_FFmpegCmd_runFFMpegCMD(JNIEnv *env, jclass obj, jobjectArray commands) {
    int argc = (*env)->GetArrayLength(env, commands);
    char *argv[argc];
    int i;
    for (i = 0; i < argc; i++) {
        jstring js = (jstring) (*env)->GetObjectArrayElement(env, commands, i);
        argv[i] = (char *) (*env)->GetStringUTFChars(env, js, 0);
    }
    return run(argc, argv);
}

