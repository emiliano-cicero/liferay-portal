/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.arquillian.extension.junit.bridge.connector;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.nio.channels.ClosedByInterruptException;

import org.osgi.framework.BundleContext;

/**
 * @author Matthew Tambara
 */
public class ArquillianConnectorRunnable implements Runnable {

	public ArquillianConnectorRunnable(
		BundleContext bundleContext, int port, String passcode) {

		_bundleContext = bundleContext;
		_port = port;
		_passcode = passcode;
	}

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket();

			serverSocket.bind(new InetSocketAddress(_inetAddress, _port));

			while (true) {
				try (Socket socket = serverSocket.accept();
					ObjectOutputStream objectOutputStream =
						new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream objectInputStream = new ObjectInputStream(
						socket.getInputStream())) {

					String passcode = objectInputStream.readUTF();

					if ((_passcode != null) && !_passcode.equals(passcode)) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Pass code mismatch, dropped connection from " +
									socket.getRemoteSocketAddress());
						}

						continue;
					}

					while (true) {
						FrameworkCommand frameworkCommand =
							(FrameworkCommand)objectInputStream.readObject();

						FrameworkResult frameworkResult = new FrameworkResult();

						try {
							frameworkResult.setBundleId(
								frameworkCommand.execute(_bundleContext));
						}
						catch (Exception e) {
							frameworkResult.setException(e);
						}

						objectOutputStream.writeObject(frameworkResult);

						objectOutputStream.flush();
					}
				}
				catch (EOFException eofe) {
				}
			}
		}
		catch (ClosedByInterruptException cbie) {
		}
		catch (Exception e) {
			_log.error(
				"Encountered a problem while using " +
					_inetAddress.getHostAddress() + ":" + _port +
						". Shutting down now.",
				e);

			System.exit(_port);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ArquillianConnectorRunnable.class);

	private static final InetAddress _inetAddress =
		InetAddress.getLoopbackAddress();

	private final BundleContext _bundleContext;
	private final String _passcode;
	private final int _port;

}