package datasource;

import datamodel.Graph;
import datamodel.GraphNode;
import datamodel.PlayerInventory;
import logic.DefaultPlayerModifier;

public class TestQuestionsLoader implements IQuestionGettable {
    @Override
    public Graph getQuestionRoot() {
        Graph root = new Graph("Главная площадь", "Вы находитесь на главной площади города:");
        GraphNode bar = new GraphNode("Таверна", "Это таверна - тут вы можете выпить и поговорить с посетителями");
        GraphNode shop = new GraphNode("Магазин", "Тут можно купить что-либо\nТовары:");
        GraphNode cards = new GraphNode("Карты", "Карты были добавленны в ваш инвентарь");
        GraphNode troll = new GraphNode("Тролль", "Это пьяный тролль, его лучше не трогать");
        GraphNode kickTroll = new GraphNode("Ударить тролля", "Пустошь поглатила вас", true);
        GraphNode pereulok = new GraphNode("Подворотня", "Ты зашел в подворотню, перед тобой стоят 2 неприятных типа(возможно они из альянса)");
        GraphNode stay = new GraphNode("Остаться",
                "Тебя избили, отобрали все золото и оружие",
                new DefaultPlayerModifier(new PlayerInventory(-50, 10, 0)));
        GraphNode run = new GraphNode("Убежать", "Ты убежал, отдышался и видешь перед собой главную площадь");
        GraphNode thief = new GraphNode("Воровать", "Ты украл 50 золота",
                new DefaultPlayerModifier(new PlayerInventory(50, 10, -2)));
        root.addIncidentNode(bar);
        root.addIncidentNode(shop);
        root.connectNodes(bar, troll);
        root.oneWayConnectNodes(pereulok, run);
        root.oneWayConnectNodes(bar, pereulok);
        root.connectNodes(pereulok, stay);
        root.oneWayConnectNodes(run, root.getRoot());
        root.oneWayConnectNodes(stay, root.getRoot());
        root.oneWayConnectNodes(troll, kickTroll);
        root.connectNodes(root.getRoot(), thief);
        return root;
    }
}
