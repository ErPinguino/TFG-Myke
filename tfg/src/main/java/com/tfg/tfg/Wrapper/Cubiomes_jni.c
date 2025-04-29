#include "com_tfg_tfg_Wrapper_CubiomesWrapper.h"
#include "generator.h"
#include <stdlib.h>
#include <stdint.h>

static Generator g;
static int mcversion;

JNIEXPORT void JNICALL Java_com_tfg_tfg_Wrapper_CubiomesWrapper_initialize
  (JNIEnv *env, jobject obj) {
    setupGenerator(&g, MC_1_19, 0);
}

JNIEXPORT void JNICALL Java_com_tfg_tfg_Wrapper_CubiomesWrapper_setupSeed
  (JNIEnv *env, jobject obj, jlong seed, jint version) {
    mcversion = version;
    applySeed(&g, 1, (uint64_t)seed);
}

JNIEXPORT jintArray JNICALL Java_com_tfg_tfg_Wrapper_CubiomesWrapper_findStructure
  (JNIEnv *env, jobject obj, jint structureType, jint x, jint z, jint radius) {
    int64_t px = 0, pz = 0;
    
    // Buscar la estructura más cercana
    if (findStructure(&g, structureType, x, z, radius, &px, &pz)) {
        jintArray result = (*env)->NewIntArray(env, 2);
        if (result == NULL) {
            return NULL; // Out of memory
        }
        
        jint coords[2];
        coords[0] = (jint)px;
        coords[1] = (jint)pz;
        
        (*env)->SetIntArrayRegion(env, result, 0, 2, coords);
        return result;
    }
    
    return NULL;
}

JNIEXPORT jint JNICALL Java_com_tfg_tfg_Wrapper_CubiomesWrapper_getBiomeAt
  (JNIEnv *env, jobject obj, jint x, jint y, jint z) {
    return getBiome(&g, x, y, z);
}