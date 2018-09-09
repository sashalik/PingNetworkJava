package checkconnection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CheckConnection {

    public String Check(String ip)
    {
        try {
            InetAddress inet = InetAddress.getByName(ip);
            return Boolean.toString(inet.isReachable(500));
        }
        catch (UnknownHostException e)
        {

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

}
