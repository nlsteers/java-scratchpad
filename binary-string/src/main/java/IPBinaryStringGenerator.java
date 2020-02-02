import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class IPBinaryStringGenerator {

    public String ipAddressToBinary(String input) throws UnknownHostException {

        if (input.contains("/")){
            String[] parts = input.split("/");
            return String.format("%s %s", new BigInteger(1, ipAddressToByteArray(parts[0])).toString(2), cidrToBinarySubnetMask(parts[1]));
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

    public String cidrToBinarySubnetMask(String input){
        int prefix = Integer.parseInt(input);
        return Integer.toBinaryString(0xffffffff << (32 - prefix));
    }

    public String convertHostAndMaskToNetAddress(String h, String m) {
        byte[] host = new BigInteger(h, 2).toByteArray();
        byte[] mask = new BigInteger(m, 2).toByteArray();
        byte[] address = new byte[host.length];
        int i = 0;
        for (byte hostByte : host) {
            address[i] = (byte) (hostByte & mask[i]);
            i++;
        }
        return new BigInteger(1, address).toString(2);
    }
}
