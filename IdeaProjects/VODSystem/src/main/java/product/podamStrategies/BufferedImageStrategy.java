package product.podamStrategies;

import uk.co.jemos.podam.common.AttributeStrategy;

import java.awt.image.BufferedImage;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Random;

/**
 * Describes how randomly generated image should looks like.
 */
public class BufferedImageStrategy implements AttributeStrategy<BufferedImage> {
    private static int WIDTH = 20;
    private static int HEIGHT = 20;

    /**
     * Generate small image with random colors in each pixels.
     * @param aClass
     * @param list
     * @return
     */
    @Override
    public BufferedImage getValue(Class<?> aClass, List<Annotation> list) {
        BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                image.setRGB(i,j,(int) (Math.abs(new Random().nextInt()) %(256*256*256)));
            }
        }
        return image;
    }
}
