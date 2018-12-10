package product.transactionController;

import product.Product;
import simulation.Simulator;
import user.Client;
import user.Distributor;
import user.User;

public class TransactionController {

    public void performTranscation(Client client, int id) {
        Product product = Simulator.getInstance().getProducts().get(id);
        Distributor distributor = product.getDistributor();
        client.getBoughtProductsId().add(id);
        Distributor d = (Distributor) Simulator.getInstance().getUsers().get(product.getDistributor().getName());
        d.calculateProfit(product,client.getSubscription());
    }

}
