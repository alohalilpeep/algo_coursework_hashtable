import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        try {
            NavigatorImpl navigator = new NavigatorImpl();

            MyArrayList<String> points1 = new MyArrayList<>();
            points1.add("1");
            points1.add("2");
            points1.add("4");
            points1.add("5");
            Route route1 = new Route("route1", 5.3, 0, false, points1);

            MyArrayList<String> points2 = new MyArrayList<>();
            points2.add("1");
            points2.add("2");
            points2.add("4");
            points2.add("5");
            Route route2 = new Route("route2", 5.2, 0, false, points2);

            MyArrayList<String> points3 = new MyArrayList<>();
            points3.add("1");
            points3.add("4");
            points3.add("5");
            Route route3 = new Route("route3", 3.1, 1, true, points3);

            MyArrayList<String> points4 = new MyArrayList<>();
            points4.add("1");
            points4.add("3");
            points4.add("5");
            points4.add("6");
            Route route4 = new Route("route4", 7.8, 2, false, points4);

            MyArrayList<String> points5 = new MyArrayList<>();
            points5.add("1");
            points5.add("3");
            points5.add("5");
            Route route5 = new Route("route5", 2.5, 3, true, points5);

            MyArrayList<String> points6 = new MyArrayList<>();
            points6.add("1");
            points6.add("2");
            points6.add("5");
            Route route6 = new Route("route6", 6.0, 4, false, points6);

            MyArrayList<String> points7 = new MyArrayList<>();
            points7.add("1");
            points7.add("3");
            points7.add("5");
            Route route7 = new Route("route7", 6.0, 5, true, points7);

            navigator.addRoute(route1);
            navigator.addRoute(route2);
            navigator.addRoute(route3);
            navigator.addRoute(route4);
            navigator.addRoute(route5);
            navigator.addRoute(route6);
            navigator.addRoute(route7);

            navigator.chooseRoute("route2");

            System.out.println("Все маршруты: ");
            Iterable<Route> allRoute = navigator.printAllRoutes();
            for (Route route : allRoute) {
                System.out.println(route);
            }

            System.out.println("\nКоличество маршрутов: " + navigator.size());

            System.out.println("Удаление 5 маршрута");
            navigator.removeRoute("route5");

            System.out.println("\nПоиск маршрутов с localPoints 1 to 5:");
            Iterable<Route> searchResults = navigator.searchRoutes("1", "5");
            for (Route route : searchResults) {
                System.out.println(route);
            }

            System.out.println("\nМаршруты избранные после удаления:");
            Iterable<Route> favoriteResults = navigator.getFavoriteRoutes("5");
            for (Route route : favoriteResults) {
                System.out.println(route);
            }

            System.out.println("\nСодержит route1 ? - " + navigator.contains(route1));

            System.out.println("\nТоп 5 маршрутов");
            Iterable<Route> top5Routes = navigator.getTop5Routes();
            for (Route route : top5Routes) {
                System.out.println(route);
            }

            System.out.println("Получить маршрут 3");
            System.out.println(navigator.getRoute("route3"));
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
