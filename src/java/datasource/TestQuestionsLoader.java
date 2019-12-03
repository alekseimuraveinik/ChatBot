package datasource;

import datamodel.Graph;
import datamodel.GraphNode;
import datamodel.Item;
import datamodel.PlayerInventory;
import logic.CardPlayLogic;
import logic.PlayerModifier;

public class TestQuestionsLoader implements IQuestionGettable {
    @Override
    public Graph getQuestionRoot() {
        PlayerInventory inventoryWithCards = new PlayerInventory(-40, 0, 0);
        inventoryWithCards.items.add(new Item("Карты", 1));
        Graph root = new Graph("Главная площадь", "Вы находитесь на главной площади города:");
        GraphNode bar = new GraphNode("Таверна", "Это таверна - тут вы можете выпить и поговорить с посетителями");
        GraphNode shop = new GraphNode("Магазин", "Тут можно купить что-либо\nТовары:");
        GraphNode cards = new GraphNode("Карты", "Карты были добавленны в ваш инвентарь",
                new PlayerModifier(inventoryWithCards));
        GraphNode troll = new GraphNode("Тролль", "Тролль предлагает тебе сыграть в карты, но ты должен их купить");
        GraphNode playWithTroll = new GraphNode("Сыграть в карты", "Игра началась\n",
                new PlayerModifier(new PlayerInventory(), new CardPlayLogic()));
        GraphNode won = new GraphNode("Won", "Вы победили тролля и получаете 150 золота",
                new PlayerModifier(new PlayerInventory(150, 10, 1)));
        GraphNode lose = new GraphNode("Lose", "Вы проиграли и теряете 150 золота",
                new PlayerModifier(new PlayerInventory(-150, 10, 0)));
        GraphNode noCards = new GraphNode("nocards", "нененене братан тащи карты иначе не получится поиграть");
        GraphNode kickTroll = new GraphNode("Ударить тролля", "Пустошь поглатила вас", true);
        GraphNode pereulok = new GraphNode("Подворотня", "Ты зашел в подворотню, перед тобой стоят 2 неприятных типа(возможно они из альянса)");
        GraphNode stay = new GraphNode("Остаться",
                "Тебя избили, отобрали все золото и оружие",
                new PlayerModifier(new PlayerInventory(-50, 10, 0)));
        GraphNode run = new GraphNode("Убежать", "Ты убежал, отдышался и видешь перед собой главную площадь");
        GraphNode thief = new GraphNode("Воровать", "Ты украл 50 золота",
                new PlayerModifier(new PlayerInventory(50, 10, -2)));
        root.addIncidentNode(bar);
        root.addIncidentNode(shop);
        root.connectNodes(bar, troll);
        root.oneWayConnectNodes(pereulok, run);
        root.oneWayConnectNodes(bar, pereulok);
        root.connectNodes(shop, cards);
        root.connectNodes(pereulok, stay);
        root.oneWayConnectNodes(troll, kickTroll);
        root.oneWayConnectNodes(troll, playWithTroll);
        root.oneWayConnectNodes(playWithTroll, won);
        root.oneWayConnectNodes(playWithTroll, lose);
        root.oneWayConnectNodes(playWithTroll, noCards);
        root.oneWayConnectNodes(noCards, bar);
        root.oneWayConnectNodes(won, bar);
        root.oneWayConnectNodes(won, playWithTroll);
        root.oneWayConnectNodes(lose, bar);
        root.oneWayConnectNodes(lose, playWithTroll);
        root.oneWayConnectNodes(run, root.getRoot());
        root.oneWayConnectNodes(stay, root.getRoot());
        root.connectNodes(root.getRoot(), thief);
        return root;
    }
}
