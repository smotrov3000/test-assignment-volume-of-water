package ru.asmotrov.vow.core.impl;

import org.springframework.stereotype.Service;
import ru.asmotrov.vow.core.WaterVolumeCalculatingService;

import java.util.Objects;

@Service
public class DefaultWaterVolumeCalculatingService implements WaterVolumeCalculatingService {

    /**
     * {@inheritDoc}
     * <p>In this specific implementation, two additional arrays are defined, for left and right
     * max heights. Specifically, for each element with index <b>i</b>, <b>leftMaxHeights[i]</b> is either the
     * highest element to the left, or the element itself (if it is higher). Array <b>maxRightHeights</b>
     * works in a similar way, starting from right instead.</p>
     * <p>Thus, each element can't hold more water than the minimum of left and right tallest elements,
     * minus the element height. The total volume is the sum of the amount of water each element can
     * hold across the whole array.</p>
     * <p><b>Complexity:</b> <b>O(n)</b> - left and right arrays are created for one full array pass, with one
     * additional pass for calculating the height, with <b>O(n)</b> extra memory for two left and right arrays.</p>
     */
    public long calculate(int[] surfaceProfileArray) {
        Objects.requireNonNull(surfaceProfileArray);
        if (surfaceProfileArray.length == 0) {
            return 0;
        }

        int length = surfaceProfileArray.length;
        int[] maxLeftHeights = new int[length];
        int[] maxRightHeights = new int[length];
        int maxIndex = length - 1;

        maxLeftHeights[0] = surfaceProfileArray[0];
        maxRightHeights[maxIndex] = surfaceProfileArray[maxIndex];
        for (int i = 1; i < length; i++) {
            maxLeftHeights[i] = Math.max(maxLeftHeights[i - 1], surfaceProfileArray[i]);
            maxRightHeights[maxIndex - i] = Math.max(
                    maxRightHeights[maxIndex - i + 1],
                    surfaceProfileArray[maxIndex - i]
            );
        }

        long result = 0;
        for (int i = 0; i < length; i++) {
            result += Math.min(maxLeftHeights[i], maxRightHeights[i]) - surfaceProfileArray[i];
        }
        return result;
    }
}
