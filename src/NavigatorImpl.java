import java.util.NoSuchElementException;

public class NavigatorImpl implements Navigator {
    private HashTable<Route> routes;

    public NavigatorImpl() {
        this.routes = new HashTable<>();
    }

    @Override
    public void addRoute(Route route) {
        if (route == null) throw new NullPointerException("Маршрут не может быть нулевым");
        for (Route route1 : routes) {
            if (route.hashCode() == route1.hashCode()) {
                if (route.equals(route1)) {
                    return; // Маршрут уже существует
                }
            }
        }
        routes.add(route);
    }

    @Override
    public void removeRoute(String routeId) {
        Route routeToRemove = null;
        for (Route route : routes) {
            if (route.getId().equals(routeId)) {
                routeToRemove = route;
                break;
            }
        }
        if (routeToRemove != null) {
            routes.remove(routeToRemove);
        }
    }

    @Override
    public boolean contains(Route route) {
        if (route == null) {
            throw new IllegalArgumentException("Route не может быть пустым");
        }
        return routes.contains(route);
    }

    @Override
    public int size() {
        return routes.size();
    }

    @Override
    public Route getRoute(String routeId) {
        for (Route route : routes) {
            if (route.getId().equals(routeId)) {
                return route;
            }
        }
        return null; 
    }

    @Override
    public void chooseRoute(String routeId) {
        Route routeToChoose = getRoute(routeId);
        if (routeToChoose != null) {
            routeToChoose.increasePopularity();
            routes.remove(routeToChoose);
            routes.add(routeToChoose);
        } else {
            throw new NoSuchElementException("Route с ID " + routeId + " не найден");
        }
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        MyArrayList<Route> result = new MyArrayList<>();
        for (Route route : routes) {
            MyArrayList<String> points = route.getLocationPoints();
            if (points.get(0).equals(startPoint) && points.get(points.size() - 1).equals(endPoint) && isRouteLogical(points)) {
                result.add(route);
            }
        }
        if (result.size() == 0) {
            return new MyArrayList<>(); 
        }
        // Сортировка маршрутов: избранные маршруты будут отсортированы по расстоянию и популярности в порядке убывания
        result.sort(new SearchRouteComparator());
        return result;
    }
    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        MyArrayList<Route> result = new MyArrayList<>();
        for (Route route : routes) {
            MyArrayList<String> points = route.getLocationPoints();
            if (route.isFavorite() && points.contains(destinationPoint) && !points.get(0).equals(destinationPoint)) {
                result.add(route);
            }
        }
        if (result.size() == 0) {
            return new MyArrayList<>(); 
        }
        // Сортировка маршрутов: сначала по расстоянию, затем по популярности в порядке убывания
        result.sort(new FavoriteRouteComparator());
        return result;
    }

    @Override
    public Iterable<Route> getTop5Routes() {
        MyArrayList<Route> result = new MyArrayList<>();
        for (Route route : routes) {
            result.add(route);
        }

        if (result.size() == 0) {
            return new MyArrayList<>(); 
        }

        result.sort(new Top5RouteComparator());
        MyArrayList<Route> top5 = new MyArrayList<>();
        for (int i = 0; i < Math.min(5, result.size()); i++) {
            top5.add(result.get(i));
        }
        return top5;
    }

    public Iterable<Route> printAllRoutes() {
        MyArrayList<Route> result = new MyArrayList<>();
        for (Route route : routes) {
            result.add(route);
        }
        result.sort(new PrintAllComparator());
        return result;
    }

    private boolean isRouteLogical(MyArrayList<String> points) {
        if (points == null || points.size() <= 1) {
            return false;
        }
        for (int i = 0; i < points.size() - 1; i++) {
            try {
                int current = Integer.parseInt(points.get(i));
                int next = Integer.parseInt(points.get(i + 1));
                if (next <= current) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}
