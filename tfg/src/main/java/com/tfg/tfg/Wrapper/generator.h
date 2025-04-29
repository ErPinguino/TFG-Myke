#ifndef GENERATOR_H
#define GENERATOR_H

#include <stdint.h>

#define MC_1_19 19

typedef struct {
    uint64_t seed;
    int mc_version;
} Generator;

void setupGenerator(Generator *g, int mc, uint32_t flags);
void applySeed(Generator *g, int dim, uint64_t seed);
int findStructure(Generator *g, int type, int x, int z, int radius, int64_t *px, int64_t *pz);
int getBiome(Generator *g, int x, int y, int z);

#endif



