#ifndef ORE_FINDER_H
#define ORE_FINDER_H

#include "generator.h"

typedef struct {
    int64_t x;
    int64_t y;
    int64_t z;
} OrePosition;

int findOreDeposit(Generator *g, int x, int z, int radius, OrePosition *pos);

#endif