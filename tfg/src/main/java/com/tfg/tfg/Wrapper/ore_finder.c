#include "ore_finder.h"
#include "generator.h"
#include <stdlib.h>
#include <math.h>

int findOreDeposit(Generator *g, int x, int z, int radius, OrePosition *pos) {
    // Simple diamond distribution logic
    int64_t seed = g->seed;
    int64_t px = x + (seed % radius);
    int64_t pz = z + ((seed >> 32) % radius);
    
    // Diamonds typically spawn between y=-64 and y=16 in 1.19+
    int64_t py = -59 + (seed % 75);
    
    pos->x = px;
    pos->y = py;
    pos->z = pz;
    
    return 1;
}