package com.ljja.monitor

import java.net.{InetSocketAddress, Socket}

class TcpSocketPortMonitor(val server: String, val port: Int, val timeout: Int) {

  def open(): Boolean = {

    try {

      val client = new Socket()

      client.connect(new InetSocketAddress(server, port), timeout)

      val out = client.getOutputStream
      out.write(1)
      out.flush()
      out.close()

      client.close()

      return true
    } catch {
      case e: Throwable => {
        return false
      }
    }

    return false
  }

}
