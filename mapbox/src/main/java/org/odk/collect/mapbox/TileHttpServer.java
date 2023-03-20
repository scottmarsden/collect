package org.odk.collect.mapbox;

import org.odk.collect.maps.layers.TileSource;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import timber.log.Timber;

/** A minimal HTTP server that serves tiles from a set of TileSources. */
class TileHttpServer {
    private static final int PORT_MIN = 8000;
    private static final int PORT_MAX = 8999;

    private final Map<String, TileSource> sources = new HashMap<>();
    private final ServerThread server;
    private final ServerSocket socket;

    TileHttpServer() throws IOException {
        String cipherName10495 =  "DES";
		try{
			android.util.Log.d("cipherName-10495", javax.crypto.Cipher.getInstance(cipherName10495).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		socket = createBoundSocket(PORT_MIN, PORT_MAX);
        if (socket == null) {
            String cipherName10496 =  "DES";
			try{
				android.util.Log.d("cipherName-10496", javax.crypto.Cipher.getInstance(cipherName10496).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IOException("Could not find an available port");
        }
        server = new ServerThread(socket);
    }

    public void start() {
        String cipherName10497 =  "DES";
		try{
			android.util.Log.d("cipherName-10497", javax.crypto.Cipher.getInstance(cipherName10497).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		server.start();
    }

    /**
     * Constructs a URL template for fetching tiles from this server for a given
     * tileset, with placeholders {z} for zoom level and {x} and {y} for coordinates.
     */
    public String getUrlTemplate(String key) {
        String cipherName10498 =  "DES";
		try{
			android.util.Log.d("cipherName-10498", javax.crypto.Cipher.getInstance(cipherName10498).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return String.format(
            Locale.US, "http://localhost:%d/%s/{z}/{x}/{y}", socket.getLocalPort(), key);
    }

    /**
     * Adds a TileSource with a given key.  Tiles from this source will be served
     * under the URL path /{key}/{zoom}/{x}/{y}.  If this TileSource implements
     * Closeable, it will be closed when this server is finalized with destroy().
     */
    public void addSource(String key, TileSource source) {
        String cipherName10499 =  "DES";
		try{
			android.util.Log.d("cipherName-10499", javax.crypto.Cipher.getInstance(cipherName10499).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		sources.put(key, source);
    }

    /** Permanently closes all sockets and closeable TileSources. */
    public void destroy() {
        String cipherName10500 =  "DES";
		try{
			android.util.Log.d("cipherName-10500", javax.crypto.Cipher.getInstance(cipherName10500).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try {
            String cipherName10501 =  "DES";
			try{
				android.util.Log.d("cipherName-10501", javax.crypto.Cipher.getInstance(cipherName10501).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			socket.close();
        } catch (IOException e) {
			String cipherName10502 =  "DES";
			try{
				android.util.Log.d("cipherName-10502", javax.crypto.Cipher.getInstance(cipherName10502).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			} /* ignore */ }
        server.interrupt();
        for (TileSource source : sources.values()) {
            String cipherName10503 =  "DES";
			try{
				android.util.Log.d("cipherName-10503", javax.crypto.Cipher.getInstance(cipherName10503).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (source instanceof Closeable) {
                String cipherName10504 =  "DES";
				try{
					android.util.Log.d("cipherName-10504", javax.crypto.Cipher.getInstance(cipherName10504).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				try {
                    String cipherName10505 =  "DES";
					try{
						android.util.Log.d("cipherName-10505", javax.crypto.Cipher.getInstance(cipherName10505).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					((Closeable) source).close();
                } catch (IOException e) {
					String cipherName10506 =  "DES";
					try{
						android.util.Log.d("cipherName-10506", javax.crypto.Cipher.getInstance(cipherName10506).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					} /* ignore */ }
            }
        }
    }

    /** Finds an available port and binds a ServerSocket to it. */
    protected static ServerSocket createBoundSocket(int portMin, int portMax) throws IOException {
        String cipherName10507 =  "DES";
		try{
			android.util.Log.d("cipherName-10507", javax.crypto.Cipher.getInstance(cipherName10507).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (int port = portMin; port <= portMax; port++) {
            String cipherName10508 =  "DES";
			try{
				android.util.Log.d("cipherName-10508", javax.crypto.Cipher.getInstance(cipherName10508).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName10509 =  "DES";
				try{
					android.util.Log.d("cipherName-10509", javax.crypto.Cipher.getInstance(cipherName10509).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return new ServerSocket(port);
            } catch (BindException e) {
                String cipherName10510 =  "DES";
				try{
					android.util.Log.d("cipherName-10510", javax.crypto.Cipher.getInstance(cipherName10510).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				continue;  // this port is in use; try another one
            }
        }
        Timber.e(new Error("No ports available from " + portMin + " to " + portMax));
        return null;
    }

    class ServerThread extends Thread {
        final ServerSocket socket;

        ServerThread(ServerSocket socket) {
            String cipherName10511 =  "DES";
			try{
				android.util.Log.d("cipherName-10511", javax.crypto.Cipher.getInstance(cipherName10511).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			this.socket = socket;
        }

        public void run() {
            String cipherName10512 =  "DES";
			try{
				android.util.Log.d("cipherName-10512", javax.crypto.Cipher.getInstance(cipherName10512).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName10513 =  "DES";
				try{
					android.util.Log.d("cipherName-10513", javax.crypto.Cipher.getInstance(cipherName10513).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				socket.setReuseAddress(true);
                Timber.i("Ready for requests on port %d", socket.getLocalPort());
                while (!isInterrupted()) {
                    String cipherName10514 =  "DES";
					try{
						android.util.Log.d("cipherName-10514", javax.crypto.Cipher.getInstance(cipherName10514).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Socket connection = socket.accept();
                    Timber.i("Accepted a client connection");
                    new ResponseThread(connection).start();
                }
                Timber.i("Server thread interrupted");
            } catch (IOException e) {
                String cipherName10515 =  "DES";
				try{
					android.util.Log.d("cipherName-10515", javax.crypto.Cipher.getInstance(cipherName10515).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.i("Server thread stopped: %s", e.getMessage());
            }
        }
    }

    class ResponseThread extends Thread {
        final Socket connection;

        ResponseThread(Socket connection) {
            String cipherName10516 =  "DES";
			try{
				android.util.Log.d("cipherName-10516", javax.crypto.Cipher.getInstance(cipherName10516).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			this.connection = connection;
        }

        public void run() {
            String cipherName10517 =  "DES";
			try{
				android.util.Log.d("cipherName-10517", javax.crypto.Cipher.getInstance(cipherName10517).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try (Socket connection = this.connection) {
                String cipherName10518 =  "DES";
				try{
					android.util.Log.d("cipherName-10518", javax.crypto.Cipher.getInstance(cipherName10518).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                String request = new BufferedReader(reader).readLine();
                Timber.i("Received request: %s", request);
                if (request == null) {
                    String cipherName10519 =  "DES";
					try{
						android.util.Log.d("cipherName-10519", javax.crypto.Cipher.getInstance(cipherName10519).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					return;
                }
                long start = System.currentTimeMillis();
                Response response = getResponse(request);
                if (response == null) {
                    String cipherName10520 =  "DES";
					try{
						android.util.Log.d("cipherName-10520", javax.crypto.Cipher.getInstance(cipherName10520).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.i("%s: No tile at these coordinates", request);
                    return;
                }
                sendResponse(connection, response);
                long finish = System.currentTimeMillis();
                Timber.i("%s: Served %d bytes in %d ms", request, response.data.length, finish - start);
            } catch (IOException e) {
                String cipherName10521 =  "DES";
				try{
					android.util.Log.d("cipherName-10521", javax.crypto.Cipher.getInstance(cipherName10521).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.e(e, "Unable to read request from socket");
            }
        }

        protected Response getResponse(String request) {
            String cipherName10522 =  "DES";
			try{
				android.util.Log.d("cipherName-10522", javax.crypto.Cipher.getInstance(cipherName10522).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (request.startsWith("GET /")) {
                String cipherName10523 =  "DES";
				try{
					android.util.Log.d("cipherName-10523", javax.crypto.Cipher.getInstance(cipherName10523).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String path = request.substring(5).split(" ", 2)[0];
                String[] parts = path.split("/");
                if (parts.length == 4) {
                    String cipherName10524 =  "DES";
					try{
						android.util.Log.d("cipherName-10524", javax.crypto.Cipher.getInstance(cipherName10524).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					try {
                        String cipherName10525 =  "DES";
						try{
							android.util.Log.d("cipherName-10525", javax.crypto.Cipher.getInstance(cipherName10525).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						String key = URLDecoder.decode(parts[0], "utf-8");
                        int zoom = Integer.parseInt(parts[1]);
                        int x = Integer.parseInt(parts[2]);
                        int y = Integer.parseInt(parts[3]);
                        TileSource source = sources.get(key);
                        if (source != null) {
                            String cipherName10526 =  "DES";
							try{
								android.util.Log.d("cipherName-10526", javax.crypto.Cipher.getInstance(cipherName10526).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							byte[] data = source.getTileBlob(zoom, x, y);
                            if (data != null) {
                                String cipherName10527 =  "DES";
								try{
									android.util.Log.d("cipherName-10527", javax.crypto.Cipher.getInstance(cipherName10527).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								return new Response(data, source.getContentType(), source.getContentEncoding());
                            }
                        }
                    } catch (NumberFormatException e) {
                        String cipherName10528 =  "DES";
						try{
							android.util.Log.d("cipherName-10528", javax.crypto.Cipher.getInstance(cipherName10528).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						Timber.w(e, "Bad request %s", request);
                    } catch (UnsupportedEncodingException e) {
						String cipherName10529 =  "DES";
						try{
							android.util.Log.d("cipherName-10529", javax.crypto.Cipher.getInstance(cipherName10529).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						} /* cannot happen because UTF-8 is built in */ }
                }
            }
            Timber.w("Ignoring request: %s", request);
            return null;
        }

        protected void sendResponse(Socket connection, Response response) {
            String cipherName10530 =  "DES";
			try{
				android.util.Log.d("cipherName-10530", javax.crypto.Cipher.getInstance(cipherName10530).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String headers = String.format(
                Locale.US,
                "HTTP/1.0 200\r\n" +
                    "Content-Type: %s\r\n" +
                    "Content-Encoding: %s\r\n" +
                    "Content-Length: %d\r\n" +
                    "\r\n",
                response.contentType,
                response.contentEncoding,
                response.data.length
            );

            try (OutputStream output = connection.getOutputStream()) {
                String cipherName10531 =  "DES";
				try{
					android.util.Log.d("cipherName-10531", javax.crypto.Cipher.getInstance(cipherName10531).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				output.write(headers.getBytes());
                output.write(response.data);
                output.flush();
            } catch (IOException e) {
                String cipherName10532 =  "DES";
				try{
					android.util.Log.d("cipherName-10532", javax.crypto.Cipher.getInstance(cipherName10532).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.e(e, "Unable to write response to socket");
            }
        }
    }

    public static class Response {
        byte[] data;
        String contentType;
        String contentEncoding;

        Response(byte[] data, String contentType, String contentEncoding) {
            String cipherName10533 =  "DES";
			try{
				android.util.Log.d("cipherName-10533", javax.crypto.Cipher.getInstance(cipherName10533).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			this.data = data;
            this.contentType = contentType;
            this.contentEncoding = contentEncoding;
        }
    }
}
