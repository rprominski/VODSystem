package simulation;

import org.apache.log4j.BasicConfigurator;
import product.LiveStream;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import user.Distributor;

public class ControlPanel {

    public static void main(String args[]) {
        BasicConfigurator.configure();
        Simulator.getInstance().run();
     //   LiveStream liveStream = new LiveStream();
    }
}
