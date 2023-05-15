package dat.backend.model.services;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Material;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialFacade;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class MaterialListAlgorithm {


    public static void calcMaterialList(Order order, ConnectionPool connectionPool) throws DatabaseException {
        int length = order.getLength();
        int height = order.getHeight();
        int width = order.getWidth();

        //Calculating length of posts
            //Maximum length between two posts is 310 cm.
            int amountOfPosts = ((int) Math.ceil((double) length / 310) + 1) * 2;
            Material post = MaterialFacade.getMaterialByNameAndLength("Stolpe", height, connectionPool);
            Pair<Material, Integer> posts = new Pair<>(post, amountOfPosts);

        //Calculating length of rafters
            //Maximum length between two rafters is 55 cm.
            int amountOfRafters = (int) Math.floor((double) length / 55);
            Material rafter = MaterialFacade.getMaterialByNameAndLength("Spær", width, connectionPool);
            Pair<Material, Integer> rafters = new Pair<>(rafter, amountOfRafters);

        //Calculating length of purlins
            int amountOfPurlins = 2;
            Material purlin = MaterialFacade.getMaterialByNameAndLength("Rem", length, connectionPool);
            Pair<Material, Integer> purlins = new Pair<>(purlin, amountOfPurlins);


            List<Pair<Material, Integer>> listOfPairs = new ArrayList<>();
            listOfPairs.add(posts);
            listOfPairs.add(rafters);
            listOfPairs.add(purlins);
            MaterialFacade.createOrderLink(order, listOfPairs, connectionPool);
    }
}
