package product.podamStrategies;

import timeController.TimeController;
import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;

public class DateStrategy implements AttributeStrategy<Date> {

    @Override
    public Date getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        return TimeController.getInstance().getRawSimulationDate();
    }
}
