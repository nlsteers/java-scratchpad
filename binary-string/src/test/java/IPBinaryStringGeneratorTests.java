import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

public class IPBinaryStringGeneratorTests {

    IPBinaryStringGenerator ipBinaryStringGenerator = new IPBinaryStringGenerator();
    String ipv6 = "2041:0000:140F:0000:0000:0000:875B:131B";
    String ipv6Short = "2041:0000:140F::875B:131B";
    String ipv6Shorter = "2041:0:140F::875B:131B";
    String ipv6WithLeadingZeroes = "2001:0001:0002:0003:0004:0005:0006:0007";
    String ipv6NoLeadingZeroes = "2001:1:2:3:4:5:6:7";
    String ipv6BinaryVal = "100000010000010000000000000000000101000000111100000000000000000000000000000000000000000000000010000111010110110001001100011011";
    String ipv4 = "192.0.2.1";
    String ipv4LeadingZeroes = "192.000.002.001";
    String ipv4WithCIDR = "156.197.050.000/24";

    @Before
    public void setup() {
    }

    @Test
    public void printVals() throws UnknownHostException {
        System.out.println(String.format("2041:0000:140F:0000:0000:0000:875B:131B = %s", ipBinaryStringGenerator.ipAddressToBinary(ipv6)));
        System.out.println(String.format("2041:0000:140F::875B:131B = %s", ipBinaryStringGenerator.ipAddressToBinary(ipv6Short)));
        System.out.println(String.format("2001:0001:0002:0003:0004:0005:0006:0007 = %s", ipBinaryStringGenerator.ipAddressToBinary(ipv6WithLeadingZeroes)));
        System.out.println(String.format("2001:1:2:3:4:5:6:7 = %s", ipBinaryStringGenerator.ipAddressToBinary(ipv6NoLeadingZeroes)));
        System.out.println(String.format("192.0.2.1 = %s", ipBinaryStringGenerator.ipAddressToBinary(ipv4)));
        System.out.println(String.format("156.197.050.000/24 = %s", ipBinaryStringGenerator.ipAddressToBinary(ipv4WithCIDR)));
    }

    @Test
    public void shouldHandleDifferentAddressTypes() throws UnknownHostException {
        //IPV6
        assertEquals(ipv6BinaryVal, ipBinaryStringGenerator.ipAddressToBinary(ipv6));
        //IPv4
        assertEquals("11000000000000000000001000000001", ipBinaryStringGenerator.ipAddressToBinary(ipv4));
    }

    @Test
    public void shouldHandleIPV6Shorthand() throws UnknownHostException {
        assertEquals(ipBinaryStringGenerator.ipAddressToBinary(ipv6Short),ipv6BinaryVal);
        assertEquals(ipBinaryStringGenerator.ipAddressToBinary(ipv6Shorter),ipv6BinaryVal);
    }

    @Test
    public void shouldHandleIPV6NoLeadingZeroes() throws UnknownHostException {
        assertEquals("100000000000010000000000000001000000000000001000000000000000110000000000000100000000000000010100000000000001100000000000000111", ipBinaryStringGenerator.ipAddressToBinary(ipv6NoLeadingZeroes));
        assertEquals(ipBinaryStringGenerator.ipAddressToBinary(ipv6NoLeadingZeroes), ipBinaryStringGenerator.ipAddressToBinary(ipv6WithLeadingZeroes));
    }

    @Test
    public void shouldHandleIPV4NoLeadingZeroes() throws UnknownHostException {
        assertEquals("11000000000000000000001000000001", ipBinaryStringGenerator.ipAddressToBinary(ipv4LeadingZeroes));
        assertEquals(ipBinaryStringGenerator.ipAddressToBinary(ipv4LeadingZeroes), ipBinaryStringGenerator.ipAddressToBinary(ipv4));
    }

    @Test
    public void shouldHandleIPV4WithCidr() throws  UnknownHostException {
        assertEquals("10011100110001010011001000000000 11111111111111111111111100000000", ipBinaryStringGenerator.ipAddressToBinary(ipv4WithCIDR));
    }

    @Test
    public void shouldCreateBinarySubnetMask() {
        System.out.println(ipBinaryStringGenerator.cidrToBinarySubnetMask("24"));
    }

    @Test
    public void binaryBitwiseOperation() {
        System.out.println("host: 10011100110001010011001000000000");
        System.out.println("mask: 11111111111111111111111100000000");
        System.out.println(String.format("net : %s", ipBinaryStringGenerator.convertHostAndMaskToNetAddress("10011100110001010011001000000000", "11111111111111111111111100000000")));
    }




}
