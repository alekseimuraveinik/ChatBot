package datasource;

import datamodel.GraphNode;

public class TestQuestionsLoader implements IQuestionGettable {
    @Override
    public GraphNode getQuestionRoot() {
        GraphNode root = new GraphNode("Главная площадь", "Вы находитесь на главной площади города:");
        GraphNode bar = new GraphNode("Таверна", "Это таверна - тут вы можете выпить и поговорить с посетителями");
        GraphNode shop = new GraphNode("Магазин", "Тут можно купить что-либо");
        GraphNode troll = new GraphNode("Тролль", "Это пьяный тролль, его лучше не трогать");
        GraphNode kickTroll = new GraphNode("Ударить тролля", "Пустошь поглатила вас", true);
        GraphNode pereulok = new GraphNode("Подворотня", "Ты зашел в подворотню, перед тобой стоят 2 неприятных типа(возможно они из альянса)");
        GraphNode stay = new GraphNode("Оставаться", "Тебя избили, отобрали все золото и оружие", true);
        GraphNode run = new GraphNode("Убежать", "Ты убежал, отдышался и видешь перед собой главную площадь");
        root.addIncidentNode(bar);
        root.addIncidentNode(shop);
        bar.addIncidentNode(troll);
        bar.addOneWayIncidentNode(pereulok);
        pereulok.addOneWayIncidentNode(run);
        pereulok.addIncidentNode(stay);
        run.addOneWayIncidentNode(root);
        stay.addOneWayIncidentNode(root);
        troll.addOneWayIncidentNode(kickTroll);
        return root;
    }
}
