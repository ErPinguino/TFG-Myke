#include <jni.h>
#include <stdlib.h>
#include "generator.h"
#include "finders.h"

static Generator g;
static int mcversion;

// Remove static keyword since it's already declared in finders.h
int getBiomeAt(Generator *g, int scale, int x, int y, int z) {
  if (scale == 0) {
      scale = 4;
  }

  // Usar la función correcta de generación de biomas, incluyendo `y`
  return getBiomeAt(g, scale, x, y, z); 
}


JNIEXPORT void JNICALL Java_com_tfg_tfg_Wrapper_CubiomesWrapper_initialize
(JNIEnv *env, jobject obj) {
  setupGenerator(&g, MC_1_19, 0);
}

JNIEXPORT void JNICALL Java_com_tfg_tfg_Wrapper_CubiomesWrapper_setupSeed
(JNIEnv *env, jobject obj, jlong seed, jint version) {
  mcversion = version;
  applySeed(&g, DIM_OVERWORLD, (uint64_t)seed);
}


JNIEXPORT jintArray JNICALL Java_com_tfg_tfg_Wrapper_CubiomesWrapper_findStructure
  (JNIEnv *env, jobject obj, jint structureType, jint startX, jint startZ, jint radius) {
    
    Pos p;
    int found = 0;
    
    // Convert structure type
    int cubiomesType;
    switch(structureType) {
        case 1: cubiomesType = Village; break;
        case 2: cubiomesType = Mansion; break;
        case 3: cubiomesType = Monument; break;
        case 4: cubiomesType = Fortress; break;
        case 5: cubiomesType = Temple; break;
        case 6: cubiomesType = Stronghold; break;
        case 7: cubiomesType = Mineshaft; break;
        case 8: cubiomesType = EndCity; break;
        case 9: cubiomesType = Fortress; break;
        case 10: cubiomesType = Portal; break;
        case 11: cubiomesType = Outpost; break;
        case 12: cubiomesType = Bastion; break;
        default: return NULL;
    }

    // Find structure
    if (!getStructurePos(cubiomesType, mcversion, g.seed, startX, startZ, &p)) {
        return NULL;
    }

    // Check if structure is viable
    if (!isViableStructurePos(cubiomesType, &g, p.x, p.z, 0)) {
        return NULL;
    }

    // Create return array
    jintArray result = (*env)->NewIntArray(env, 2);
    if (result == NULL) {
        return NULL;
    }
    
    jint coords[2];
    coords[0] = (jint)p.x * 16;
    coords[1] = (jint)p.z * 16;
    
    (*env)->SetIntArrayRegion(env, result, 0, 2, coords);
    return result;
}

JNIEXPORT jint JNICALL Java_com_tfg_tfg_Wrapper_CubiomesWrapper_getBiomeAt
  (JNIEnv *env, jobject obj, jint x, jint y, jint z) {
    int scale = 4; // escala de biomas 1:4
    int id = getBiomeAt(&g, scale, x >> 2, y >> 2, z >> 2);
    return (jint)id;
}