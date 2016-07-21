package com.portal.core.common.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(NetUtils.class);

	public static final String LOCALHOST = "127.0.0.1";

	public static final String ANYHOST = "0.0.0.0";

	public static final int MIN_PORT = 0;

	public static final int MAX_PORT = 65535;

	public static final int RND_PORT_START = 30000;

	public static final int RND_PORT_RANGE = 10000;

	private static final Random RANDOM = new Random(System.currentTimeMillis());

	public static int getRandomPort() {
		return RND_PORT_START + RANDOM.nextInt(RND_PORT_RANGE);
	}

	/**
	 * <p>
	 * 取得可用的网络端口 int port=NetUtils.getAvailablePort();
	 * </p>
	 * 
	 * @author wei
	 * @created 2012-11-29 下午2:10:03
	 * @since v1.3.1
	 * @return int
	 */
	public static int getAvailablePort() {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket();
			ss.bind(null);
			return ss.getLocalPort();
		} catch (IOException e) {
			return getRandomPort();
		} finally {
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * <p>
	 * 取得port~65535之间最小可用的网络端口 int port=NetUtils.getAvailablePort(1024);
	 * </p>
	 * 
	 * @author wei
	 * @created 2012-11-29 下午2:11:31
	 * @since v1.3.1
	 * @param port
	 * @return int
	 */
	public static int getAvailablePort(int port) {
		if (port <= 0) {
			return getAvailablePort();
		}
		for (int i = port; i < MAX_PORT; i++) {
			ServerSocket ss = null;
			try {
				ss = new ServerSocket(i);
				return i;
			} catch (IOException e) {
				// continue
			} finally {
				if (ss != null) {
					try {
						ss.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return port;
	}

	/**
	 * <p>
	 * 端口是否在0~65535之间
	 * </p>
	 * 
	 * @author wei
	 * @created 2012-12-4 上午11:15:28
	 * @since v1.3.1
	 * @param port
	 * @return
	 * @return boolean
	 */
	public static boolean isInvalidPort(int port) {
		return port > MIN_PORT || port <= MAX_PORT;
	}

	/**
	 * <p>
	 * IP地址是否有效 正确IP地址127.0.0.1或127.0.0.1:80
	 * </p>
	 * 
	 * @author wei
	 * @created 2012-12-4 上午11:25:35
	 * @since v1.3.1
	 * @param address
	 * @return boolean
	 */

	private static final Pattern ADDRESS_PATTERN = Pattern
			.compile("^\\d{1,3}(\\.\\d{1,3}){3}(\\:\\d{1,5})?$");

	public static boolean isValidAddress(String address) {
		return ADDRESS_PATTERN.matcher(address).matches();
	}

	/**
	 * <p>
	 * 判断是否本地IP地址 127.0.0.1 localhost
	 * </p>
	 * 
	 * @author wei
	 * @created 2012-12-4 上午11:27:09
	 * @since v1.3.1
	 * @param host
	 * @return boolean
	 */
	private static final Pattern LOCAL_IP_PATTERN = Pattern
			.compile("127(\\.\\d{1,3}){3}$");

	public static boolean isLocalHost(String host) {
		return host != null
				&& (LOCAL_IP_PATTERN.matcher(host).matches() || host
						.equalsIgnoreCase("localhost"));
	}

	/**
	 * <p>
	 * 任意主机IP地地址 0.0.0.0
	 * </p>
	 * 
	 * @author wei
	 * @created 2012-12-4 上午11:29:11
	 * @since v1.3.1
	 * @param host
	 * @return
	 * @return boolean
	 */
	public static boolean isAnyHost(String host) {
		return "0.0.0.0".equals(host);
	}

	/**
	 * 取得本地链接地址
	 * <p>
	 * 
	 * </p>
	 * 
	 * @author wei
	 * @created 2012-12-4 上午11:36:41
	 * @since v1.3.1
	 * @param host
	 * @param port
	 * @return InetSocketAddress
	 */
	public static InetSocketAddress getLocalSocketAddress(String host, int port) {
		return isLocalHost(host) ? new InetSocketAddress(host, port)
				: new InetSocketAddress(port);
	}

	/**
	 * 是否有效远程IP地址
	 * <p>
	 * 10.86.87.1 return true 127.0.0.1 return false localhost return false
	 * myhost return true
	 * </p>
	 * 
	 * @author wei
	 * @created 2012-12-4 下午1:09:12
	 * @since v1.3.1
	 * @param address
	 * @return boolean
	 */
	private static final Pattern IP_PATTERN = Pattern
			.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

	private static boolean isValidRemoteAddress(InetAddress address) {
		if (address == null || address.isLoopbackAddress())
			return false;
		String name = address.getHostAddress();
		return (name != null && !ANYHOST.equals(name)
				&& !LOCALHOST.equals(name) && IP_PATTERN.matcher(name)
				.matches());
	}

	/**
	 * 遍历本地网卡，返回第一个合理的IP。
	 * 
	 * @return 本地网卡IP
	 */
	private static volatile InetAddress LOCAL_ADDRESS = null;

	public static InetAddress getLocalAddress() {
		if (LOCAL_ADDRESS != null)
			return LOCAL_ADDRESS;
		InetAddress localAddress = getLocalAddress0();
		LOCAL_ADDRESS = localAddress;
		return localAddress;
	}

	/**
	 * Returns the IP address string in textual presentation form.
	 *
	 * @author wei
	 * @created 2012-12-4 下午1:43:07
	 * @since v1.3.1
	 * @return the raw IP address in a string format.
	 */
	public static String getLocalHost() {
		InetAddress address = getLocalAddress();
		return address == null ? LOCALHOST : address.getHostAddress();
	}

	/**
	 * 返回本地IP地址
	 * 
	 * @author wei
	 * @created 2012-12-4 下午1:50:38
	 * @since v1.3.1
	 * @return InetAddress
	 */
	private static InetAddress getLocalAddress0() {
		InetAddress localAddress = null;
		try {
			localAddress = InetAddress.getLocalHost();
			if (isValidRemoteAddress(localAddress)) {
				return localAddress;
			}
		} catch (Throwable e) {
			logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
		}
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface
					.getNetworkInterfaces();
			if (interfaces != null) {
				while (interfaces.hasMoreElements()) {
					try {
						NetworkInterface network = interfaces.nextElement();
						Enumeration<InetAddress> addresses = network
								.getInetAddresses();
						if (addresses != null) {
							while (addresses.hasMoreElements()) {
								try {
									InetAddress address = addresses
											.nextElement();
									if (isValidRemoteAddress(address)) {
										return address;
									}
								} catch (Throwable e) {
									logger.warn(
											"Failed to retriving ip address, "
													+ e.getMessage(), e);
								}
							}
						}
					} catch (Throwable e) {
						logger.warn(
								"Failed to retriving ip address, "
										+ e.getMessage(), e);
					}
				}
			}
		} catch (Throwable e) {
			logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
		}
		logger.error("Could not get local host ip address, will use 127.0.0.1 instead.");
		return localAddress;
	}

	/**
	 * 返回IP对应的主机名
	 * 
	 * @author wei
	 * @created 2012-12-4 下午1:57:17
	 * @since v1.3.1
	 * @param address
	 * 
	 * @return String
	 */
	private static final Map<String, String> hostNameCache = new HashMap<String, String>();

	public static String getHostName(String address) {
		try {
			int i = address.indexOf(':');
			if (i > -1) {
				address = address.substring(0, i);
			}
			String hostname = hostNameCache.get(address);
			if (hostname != null && hostname.length() > 0) {
				return hostname;
			}
			InetAddress inetAddress = InetAddress.getByName(address);
			if (inetAddress != null) {
				hostname = inetAddress.getHostName();
				hostNameCache.put(address, hostname);
				return hostname;
			}
		} catch (Throwable e) {
			// ignore
		}
		return address;
	}

	/**
	 * 返回主机名对应的IP地址
	 * 
	 * @author wei
	 * @created 2012-12-4 下午1:59:08
	 * @since v1.3.1
	 * @param hostName
	 * @return ip address or hostName if UnknownHostException
	 *
	 */
	public static String getIpByHost(String hostName) {
		try {
			return InetAddress.getByName(hostName).getHostAddress();
		} catch (UnknownHostException e) {
			return hostName;
		}
	}

	/**
	 * 返回IP:port字符串 Description:
	 * 
	 * @author wei
	 * @created 2012-12-4 下午2:07:08
	 * @since v1.3.1
	 * @param address
	 * @return
	 */
	public static String toAddressString(InetSocketAddress address) {
		return address.getAddress().getHostAddress() + ":" + address.getPort();
	}

	public static InetSocketAddress toAddress(String address) {
		int i = address.indexOf(':');
		String host;
		int port;
		if (i > -1) {
			host = address.substring(0, i);
			port = Integer.parseInt(address.substring(i + 1));
		} else {
			host = address;
			port = 0;
		}
		return new InetSocketAddress(host, port);
	}

	/**
	 * 返回protocol://host:port/path字符串
	 * 
	 * @author wei
	 * @created 2012-12-4 下午2:10:01
	 * @since v1.3.1
	 * @param protocol
	 * @param host
	 * @param port
	 * @param path
	 * @return
	 */
	public static String toURL(String protocol, String host, int port,
			String path) {
		StringBuilder sb = new StringBuilder();
		sb.append(protocol).append("://");
		sb.append(host).append(':').append(port);
		if (path.charAt(0) != '/')
			sb.append('/');
		sb.append(path);
		return sb.toString();
	}
}
