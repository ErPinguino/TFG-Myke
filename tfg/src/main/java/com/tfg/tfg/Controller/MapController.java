// src/main/java/com/tfg/tfg/Controller/MapController.java
package com.tfg.tfg.Controller;

import com.tfg.tfg.Services.MapService;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

@RestController
@RequestMapping("/MinecraftProject/map")
public class MapController {

    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping(value = "/tile", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> tile(
            @RequestParam long seed,
            @RequestParam int tx,
            @RequestParam int tz,
            @RequestParam(defaultValue = "256") int size
    ) {
        try {
            BufferedImage img = mapService.generateTile(seed, tx, tz, size);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "PNG", baos);
            byte[] bytes = baos.toByteArray();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                    .cacheControl(CacheControl.maxAge(0, TimeUnit.SECONDS).mustRevalidate())
                    .body(bytes);

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(new byte[0]);
        }
    }
}
