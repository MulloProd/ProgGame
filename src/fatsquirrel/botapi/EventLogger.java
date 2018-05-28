package fatsquirrel.botapi;

import fatsquirrel.XY;
import fatsquirrel.Logging;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventLogger implements InvocationHandler{
    private ControllerContext controllerContext;
    private final Logging logging;

    public EventLogger(ControllerContext controllerContext, Logging logging) {
        this.controllerContext = controllerContext;
        this.logging = logging;
    }

    public Object invoke(Object proxy, Method method, Object[] args)  {
        switch(method.getName()){
            case "move":
                XY dir = (XY)args[0];
                logging.getLogger().info("Bot moved in direction (" + dir.x + "/" + dir.y + ")");
                break;
            case "spawnMiniBot":
                logging.getLogger().info("MiniBot spawned!");
                break;
            case "doImplosion":
                logging.getLogger().info("MiniBot imploded!");
                break;
        }
        Object result = null;
        try  {
            result = method.invoke(controllerContext, args);
        } catch(IllegalAccessException ex)  {
        } catch(InvocationTargetException ex)  {
            System.out.println("* exception:" + ex.getTargetException());
            try {
                throw ex.getTargetException();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return result;
    }
}
