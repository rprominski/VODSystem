package product.transactionController;

import product.Product;
import simulation.Simulator;
import user.Client;
import user.Distributor;
import user.User;

public class TransactionController {

    public void performTranscation(Client client, int id) {
        Product product = Simulator.getInstance().getProducts().get(id);
        if(product == null) {
            return;
        }
        Distributor distributor = product.getDistributor();
        client.getBoughtProductsId().add(id);
        distributor.calculateProfit(product, client.getSubscription());
    }
}
