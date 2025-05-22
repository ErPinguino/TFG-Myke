#!/usr/bin/env bash
set -euo pipefail

if [ $# -ne 1 ]; then
  echo "Uso: $0 <seed>"
  exit 1
fi
SEED="$1"

# Nombre del proyecto (prefijo de volúmenes/red)
PROJECT="tfg"
# Siempre apuntamos al compose que está bajo /host
COMPOSE_ARGS=(-p "$PROJECT" -f /host/compose.yaml)

# Servicios definidos en el docker-compose.yml
SERVICES=(minecraft chunky-pregen bluemap)
# Containers “hardcoded” (container_name) para limpieza forzada
CONTAINERS=(mc-server pregen bluemap)
# Volúmenes que queremos borrar
VOLUMES=("${PROJECT}_mc-data" "${PROJECT}_bluemap-output")

echo "⏱ Parando y removiendo contenedores (via compose)..."
docker compose "${COMPOSE_ARGS[@]}" stop "${SERVICES[@]}" 2>/dev/null || true
docker compose "${COMPOSE_ARGS[@]}" rm -f "${SERVICES[@]}" 2>/dev/null  || true

echo "⏱ Forzando limpieza de containers residuales..."
for c in "${CONTAINERS[@]}"; do
  docker rm -f "$c" 2>/dev/null || true
done

echo "🗑 Eliminando volúmenes: ${VOLUMES[*]}…"
for v in "${VOLUMES[@]}"; do
  docker volume rm "$v" 2>/dev/null || true
done

echo "🚀 Levantando Minecraft con seed=${SEED}…"
SEED="$SEED" docker compose "${COMPOSE_ARGS[@]}" up -d --no-deps --force-recreate minecraft

echo "🔄 Preregenerando chunks…"
docker compose "${COMPOSE_ARGS[@]}" --profile pregen up -d --no-deps chunky-pregen

echo "🌐 Generando mapa 3D con BlueMap…"
docker compose "${COMPOSE_ARGS[@]}" up -d --no-deps bluemap

echo "✅ ¡Listo!"
