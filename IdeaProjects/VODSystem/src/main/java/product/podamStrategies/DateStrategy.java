package product.podamStrategies;

import timeController.TimeController;
import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;
/**
 * Describes how randomly generated Date.
 */
public class DateStrategy implements AttributeStrategy<Date> {

    /**
     * Gets date in simulation and sets it.
     * @param attrType
     * @param attrAnnotations
     * @return
     */
    @Override
    public Date getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        return TimeController.getInstance().getRawSimulationDate();
    }
}
