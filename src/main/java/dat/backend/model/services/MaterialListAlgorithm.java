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

        Pair<Material, Integer> posts = getPosts(connectionPool, length, height);
        Pair<Material, Integer> rafters = getRafters(connectionPool, length, width);
        Pair<Material, Integer> purlins = getPurlins(connectionPool, length);

        List<Pair<Material, Integer>> listOfPairs = new ArrayList<>();
        listOfPairs.add(posts);
        listOfPairs.add(rafters);
        listOfPairs.add(purlins);
        MaterialFacade.createOrderLink(order, listOfPairs, connectionPool);
    }

    //Calculating length of purlins
    public static Pair<Material, Integer> getPurlins(ConnectionPool connectionPool, int length) throws DatabaseException {
        //Since we only ever need 1 purlin on each side, the amount is always 2.
        //We keep the variable however, to make it easier to refactor later, if needed.
        int amountOfPurlins = 2;
        Material purlin = MaterialFacade.getMaterialByNameAndLength("Rem", length, connectionPool);
        return new Pair<>(purlin, amountOfPurlins);
    }

    //Calculating length and amount of rafters need
    public static Pair<Material, Integer> getRafters(ConnectionPool connectionPool, int length, int width) throws DatabaseException {
        //Maximum length between two rafters is 55 cm.
        int amountOfRafters = (int) Math.floor(((double) length) / 55);
        Material rafter = MaterialFacade.getMaterialByNameAndLength("Spær", width, connectionPool);
        return new Pair<>(rafter, amountOfRafters);
    }

    //Calculating length and amount of posts need
    public static Pair<Material, Integer> getPosts(ConnectionPool connectionPool, int length, int height) throws DatabaseException {
        //Maximum length between two posts is 310 cm.
        int amountOfPosts = ((int) Math.ceil(((double) length) / 310) + 1) * 2;
        Material post = MaterialFacade.getMaterialByNameAndLength("Stolpe", height, connectionPool);
        return new Pair<>(post, amountOfPosts);
    }
}
