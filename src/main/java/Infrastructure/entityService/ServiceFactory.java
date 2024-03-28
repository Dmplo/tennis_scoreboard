package Infrastructure.entityService;

import java.util.Arrays;
import java.util.List;

public abstract class ServiceFactory {

    private static final List<iService> services =
            Arrays.asList(
                    new PlayerService(),
                    new MatchService()
            );

    public static <T extends iService> T getService(Class<T> cl) {
        for(iService service : services)
            if(cl.isInstance(service))
                return cl.cast(service);
        return null;
    }
}