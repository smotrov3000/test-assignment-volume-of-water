package ru.asmotrov.vow.core;

/**
 * A service for calculating the volume of water which can be hold by a surface described in
 * array of heights in units.
 */
public interface WaterVolumeCalculatingService {

    /**
     * Calculates the volume of water which can be hold by a surface. The details depend on the exact
     * implementation.
     *
     * @param surfaceProfileArray the description of the surface, as an array of heights in units
     * @return the amount of water the described surface can hold
     */
    long calculate(int[] surfaceProfileArray);

}
