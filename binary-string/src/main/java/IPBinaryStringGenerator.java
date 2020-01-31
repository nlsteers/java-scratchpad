import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPBinaryStringGenerator {

    public String ipAddressToBinary(String input) throws UnknownHostException {

        if (input.contains("/")){
            String[] parts = input.split("/");
            return String.format("%s %s", new BigInteger(1, ipAddressToByteArray(parts[0])).toString(2), Integer.toBinaryString(Integer.parseInt(parts[1])));
        } else {
            try {
                return new BigInteger(1, ipAddressToByteArray(input)).toString(2);
            } catch (UnknownHostException e) {
                throw new UnknownHostException("Could not convert address to binary");
            }
        }
    }

    private byte[] ipAddressToByteArray(String input) throws UnknownHostException {
        return InetAddress.getByName(input).getAddress();
    }

}
