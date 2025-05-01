#ifndef FINDERS_H_
#define FINDERS_H_

#include <stdint.h>
#include "generator.h"

#ifdef __cplusplus
extern "C" {
#endif

#define MASK48 (((int64_t)1 << 48) - 1)

// Define STRUCT macro if not already defined
#ifndef STRUCT
#define STRUCT(name) typedef struct name name; struct name
#endif

// Define ATTR macro if not already defined
#ifndef ATTR
#define ATTR(x) __attribute__((x))
#endif

enum StructureType {
    Feature,
    Desert_Pyramid,
    Jungle_Temple,
    Swamp_Hut,
    Igloo,
    Village,
    Ocean_Ruin,
    Shipwreck,
    Monument,
    Mansion,
    Outpost,
    Ruined_Portal,
    Ancient_City,
    Treasure,
    Mineshaft,
    Desert_Well,
    Geode,
    Fortress,
    Bastion,
    End_City,
    End_Gateway,
    End_Island,
    Trail_Ruins,
    Trial_Chambers,
    FEATURE_NUM
};

enum Dimension {
    DIM_OVERWORLD = 0,
    DIM_NETHER = -1, 
    DIM_END = 1
};

enum LegacyStructureType {
    Temple = Desert_Pyramid,
    Stronghold = 6,
    EndCity = End_City,
    Portal = Ruined_Portal
};

STRUCT(StructureConfig) {
    int32_t salt;
    int8_t  regionSize;
    int8_t  chunkRange;
    uint8_t structType;
    int8_t  dim;
    float   rarity;
};

STRUCT(Pos) { 
    int x, z; 
};

STRUCT(Pos3) { 
    int x, y, z; 
};

STRUCT(StrongholdIter) {
    Pos pos;
    Pos nextapprox;
    int index;
    int ringnum;
    int ringmax;
    int ringidx;
    double angle;
    double dist;
    uint64_t rnds;
    int mc;
};

// Function declarations
int getStructureConfig(int structureType, int mc, StructureConfig *sconf);
int getStructurePos(int structureType, int mc, uint64_t seed, int regX, int regZ, Pos *pos);
int isViableStructurePos(int structType, Generator *g, int x, int z, int approx);
int getBiomeAt(Generator *g, int scale, int x, int y, int z);

static inline Pos getFeatureChunkInRegion(StructureConfig config, uint64_t seed, int regX, int regZ) {
    Pos pos;
    const uint64_t K = 0x5deece66dULL;
    const uint64_t M = (1ULL << 48) - 1;
    const uint64_t b = 0xb;

    seed = seed + regX*341873128712ULL + regZ*132897987541ULL + config.salt;
    seed = (seed ^ K);
    seed = (seed * K + b) & M;

    uint64_t r = config.chunkRange;
    if (r & (r-1)) {
        pos.x = (int)(seed >> 17) % r;
        seed = (seed * K + b) & M;
        pos.z = (int)(seed >> 17) % r;
    } else {
        pos.x = (int)((r * (seed >> 17)) >> 31);
        seed = (seed * K + b) & M;
        pos.z = (int)((r * (seed >> 17)) >> 31);
    }

    return pos;
}

static inline Pos getFeaturePos(StructureConfig config, uint64_t seed, int regX, int regZ) {
    Pos pos = getFeatureChunkInRegion(config, seed, regX, regZ);
    pos.x = (int)(((uint64_t)regX*config.regionSize + pos.x) << 4);
    pos.z = (int)(((uint64_t)regZ*config.regionSize + pos.z) << 4);
    return pos;
}

#ifdef __cplusplus
}
#endif



#endif // FINDERS_H_
