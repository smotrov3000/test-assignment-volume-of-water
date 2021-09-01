package ru.asmotrov.vow.web.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asmotrov.vow.core.WaterVolumeCalculatingService;
import ru.asmotrov.vow.core.impl.DefaultWaterVolumeCalculatingService;

@RestController
@RequestMapping("volume")
public class WaterVolumeController {

    private final WaterVolumeCalculatingService calculatingService;

    public WaterVolumeController(DefaultWaterVolumeCalculatingService calculatingService) {
        this.calculatingService = calculatingService;
    }

    /**
     * Calculates volume that can be hold by a surface, described by the specified array as request body
     * in a JSON format.
     *
     * @param surfaceProfileArray the surface array description, where each element represents a height
     *                            of the terrain at that index, in units
     * @return the amount of water the described surface can hold, in units
     */
    @PostMapping
    public long calculateVolume(@RequestBody int[] surfaceProfileArray) {
        return calculatingService.calculate(surfaceProfileArray);
    }
}
