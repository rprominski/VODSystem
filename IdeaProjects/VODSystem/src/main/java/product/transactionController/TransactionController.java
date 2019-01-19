package product.transactionController;

import product.Product;
import simulation.Simulation;
import simulation.Simulator;
import user.Client;
import user.Distributor;
import user.User;

/**
 * Class responsible for get and give appropriate amount of money to everybody when product is bought.
 */
public class TransactionController {

    /**
     * Method which adds to user account and gives moeny for distributor.
     * @param client Client who bought product
     * @param id Id of bought product.
     */
    public void performTranscation(Client client, int id) {
        Product product = Simulation.getInstance().getSimulator().getProducts().get(id);
        if(product == null) {
            return;
        }
        Distributor distributor = product.getDistributor();
        client.getBoughtProductsId().add(id);
        distributor.calculateProfit(product, client.getSubscription());
    }
}
